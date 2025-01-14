/*
 * Copyright 2014-present Alibaba Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#include "indexlib/framework/Locator.h"

#include <iostream>

namespace indexlibv2::framework {

std::pair<bool, base::Progress> Locator::GetLeftProgress(CompareStruct& currentProgress, CompareStruct& updateProgress)
{
    auto current = currentProgress.GetCurrentProgress();
    auto update = updateProgress.GetCurrentProgress();
    if (current.from < update.from) {
        if (current.to < update.from) {
            // current : |---|
            // update  :       |---|
            currentProgress.MoveToNext();
        } else {
            // current : |---|
            // update  :    |---|
            current.to = update.from - 1;
            currentProgress.from = update.from;
        }
        return {true, current};
    } else if (current.from > update.from) {
        if (update.to < current.from) {
            // current :       |---|
            // update  : |---|
            updateProgress.MoveToNext();
        } else {
            // current :    |---|
            // update  : |---|
            update.to = current.from - 1;
            updateProgress.from = current.from;
        }
        return {true, update};
    } else {
        base::Progress::Offset updateOffset = updateProgress.GetCurrentOffset();
        base::Progress::Offset currentOffset = currentProgress.GetCurrentOffset();
        if (updateOffset != base::Progress::INVALID_OFFSET && updateOffset < currentOffset) {
            return {false, base::Progress()};
        }
        if (update.to < current.to) {
            // current : |--------|
            // update  : |-----|
            updateProgress.MoveToNext();
            currentProgress.from = update.to + 1;
            return {true, update};
        } else if (update.to > current.to) {
            // current : |-----|
            // update  : |--------|
            currentProgress.MoveToNext();
            current.offset = updateOffset;
            updateProgress.from = current.to + 1;
            return {true, current};
        } else {
            updateProgress.MoveToNext();
            currentProgress.MoveToNext();
            return {true, update};
        }
    }
    assert(false);
    return {false, base::Progress()};
}

void Locator::MergeProgress(std::vector<base::Progress>& mergeProgress)
{
    std::vector<base::Progress> mergeResult;
    for (const auto& progress : mergeProgress) {
        if (mergeResult.empty()) {
            mergeResult.push_back(progress);
            continue;
        }

        size_t idx = mergeResult.size() - 1;
        if (mergeResult[idx].offset == progress.offset && mergeResult[idx].to + 1 == progress.from) {
            mergeResult[idx].to = progress.to;
            continue;
        }
        mergeResult.push_back(progress);
    }
    mergeProgress.swap(mergeResult);
}

bool Locator::Update(const Locator& other)
{
    if (other._src != _src) {
        // new src locator, just replace it
        *this = other;
        return true;
    }

    if (other._progress.size() == 1 && _progress.size() == 1 && other._progress[0].from == _progress[0].from &&
        other._progress[0].to == _progress[0].to) {
        if (other._progress[0].offset < _progress[0].offset) {
            return false;
        }
        _progress[0].offset = other._progress[0].offset;
        _minOffset = other._progress[0].offset;
        _userData = other._userData;
        return true;
    }
    CompareStruct currentProgress(&_progress);
    CompareStruct updateProgress(&(other._progress));

    std::vector<base::Progress> updateResult;
    while (!currentProgress.IsEof() && !updateProgress.IsEof()) {
        auto [status, progress] = GetLeftProgress(currentProgress, updateProgress);
        if (!status) {
            return false;
        }
        updateResult.push_back(progress);
    }
    while (!currentProgress.IsEof()) {
        auto progress = currentProgress.GetCurrentProgress();
        updateResult.push_back(progress);
        currentProgress.MoveToNext();
    }
    while (!updateProgress.IsEof()) {
        auto progress = updateProgress.GetCurrentProgress();
        updateResult.push_back(progress);
        updateProgress.MoveToNext();
    }
    MergeProgress(updateResult);
    _progress.swap(updateResult);

    _minOffset = base::Progress::INVALID_OFFSET;
    for (const auto& progress : _progress) {
        if (_minOffset == base::Progress::INVALID_OFFSET) {
            _minOffset = progress.offset;
        } else {
            _minOffset = std::min(_minOffset, progress.offset);
        }
    }
    _userData = other._userData;
    return true;
}

bool Locator::IsFullyFasterThan(const Locator& other) const
{
    bool isFullyFaster = true;
    for (auto otherProgress : other._progress) {
        bool hasFullyFaster = false;
        for (const auto& currentProgress : _progress) {
            if (otherProgress.offset <= base::Progress::MIN_OFFSET) {
                hasFullyFaster = true;
                break;
            }
            if (currentProgress.from > otherProgress.from) {
                break;
            }
            if (currentProgress.to < otherProgress.from) {
                continue;
            }

            if (currentProgress.to >= otherProgress.to) {
                if (currentProgress.offset >= otherProgress.offset) {
                    hasFullyFaster = true;
                }
                break;
            }

            if (currentProgress.offset < otherProgress.offset) {
                break;
            }

            otherProgress.from = currentProgress.to + 1;
        }
        if (!hasFullyFaster) {
            isFullyFaster = false;
            break;
        }
    }
    return isFullyFaster;
}
namespace {
template <typename T>
inline void write(char*& str, const T& value)
{
    *(T*)str = value;
    str += sizeof(value);
}

template <typename T>
inline bool read(const char*& begin, const char* end, T& value)
{
    if (begin + sizeof(T) > end) {
        return false;
    }
    value = *(T*)begin;
    begin += sizeof(value);
    return true;
}
} // namespace

std::string Locator::Serialize() const
{
    int32_t serializeVersion = calculateSerializeVersion();
    assert(serializeVersion <= LOCATOR_SUBOFFSET_VERSION);
    if (0 == serializeVersion) {
        return SerializeV0();
    }
    if (1 == serializeVersion) {
        return SerializeV1();
    }
    assert(false);
    return "";
}

std::string Locator::SerializeV0() const
{
    /*
      [   src   ][min offset ][progressCount][progresses(offset.first, from, to)][userdata(length and data if bexist)]
      [<-8byte->][<--8byte-->][<---4byte--->][<-16byte * progress count-------->][<-----0 or 8 + userdata length---->]
     */
    std::string result;
    auto size = Size();
    result.resize(size);
    char* data = result.data();
    write(data, _src);
    write(data, _minOffset.first);
    write(data, (uint64_t)_progress.size());
    for (int i = 0; i < _progress.size(); i++) {
        write(data, _progress[i].offset.first);
        write(data, _progress[i].from);
        write(data, _progress[i].to);
    }
    if (!_userData.empty()) {
        write(data, (uint64_t)_userData.size());
        memcpy(data, _userData.data(), _userData.size());
        data += _userData.size();
    }
    assert(data == result.data() + size);
    return result;
}

std::string Locator::SerializeV1() const
{
    /*
      [      src      ][MAGIC_NUMER][LOCATOR_TYPE][min offset ][progressCount][progresses(offset.first, offset.second,
      from, to)] [userdata(length and data if bexist)]
      [<----8byte---->][<--4byte-->][<--4byte--->][<-12byte-->][<---4byte--->][<----------20byte * progress
      count-------------->][<---0 if no userdata, or 8 + userdata length byte->] LOCATOR_TYPE 这版本为 0，
      未来用于实现locator自定义
     */
    std::string result;
    auto size = Size();
    result.resize(size);
    char* data = result.data();
    write(data, _src);
    write(data, MAGIC_NUMBER);
    write(data, LOCATOR_SUBOFFSET_VERSION);
    write(data, _minOffset.first);
    write(data, _minOffset.second);
    write(data, (uint64_t)_progress.size());
    for (int i = 0; i < _progress.size(); i++) {
        write(data, _progress[i].offset.first);
        write(data, _progress[i].offset.second);
        write(data, _progress[i].from);
        write(data, _progress[i].to);
    }
    if (!_userData.empty()) {
        write(data, (uint64_t)_userData.size());
        memcpy(data, _userData.data(), _userData.size());
        data += _userData.size();
    }
    assert(data == result.data() + size);
    return result;
}

bool Locator::DeserializeProgressV0(const char*& data, const char* end)
{
    uint64_t progressSize = 0;
    if (!read(data, end, progressSize)) {
        return false;
    }
    if (progressSize > 65535) {
        return false;
    }

    _progress.reserve(progressSize);
    for (size_t i = 0; i < progressSize; i++) {
        base::Progress singleProgress;
        if (!read(data, end, singleProgress.offset.first) || !read(data, end, singleProgress.from) ||
            !read(data, end, singleProgress.to)) {
            _progress.clear();
            return false;
        }
        if (singleProgress.offset < _minOffset || singleProgress.from < 0 || singleProgress.from > 65535 ||
            singleProgress.to < 0 || singleProgress.to > 65535) {
            _progress.clear();
            return false;
        }
        _progress.emplace_back(singleProgress);
    }
    return true;
}

bool Locator::DeserializeV0(const char* data, const char* end)
{
    if (!read(data, end, _minOffset.first)) {
        return false;
    }

    if (data == end) {
        base::Progress singleProgress;
        singleProgress.from = 0;
        singleProgress.to = 65535;
        singleProgress.offset = _minOffset;
        _progress.emplace_back(singleProgress);
        _isLegacyLocator = true;
        return true;
    }
    const char* userDataPtr = data;
    if (!DeserializeProgressV0(data, end)) {
        // deserialize progress failed, legacy code for compatible with old user data format
        base::Progress singleProgress;
        singleProgress.from = 0;
        singleProgress.to = 65535;
        singleProgress.offset = _minOffset;
        _progress.emplace_back(singleProgress);
        _userData.append(userDataPtr, end - userDataPtr);
        _isLegacyLocator = true;
        return true;
    }
    if (data == end) {
        return true;
    }
    uint64_t userDataLen = 0;
    if (!read(data, end, userDataLen) || data + userDataLen != end) {
        return false;
    }
    if (userDataLen > 0) {
        _userData.append(data, userDataLen);
    }
    return true;
}

bool Locator::DeserializeV1(const char* data, const char* end)
{
    uint64_t progressSize = 0;
    if (!read(data, end, _minOffset.first) || !read(data, end, _minOffset.second) || !read(data, end, progressSize)) {
        return false;
    }
    _progress.reserve(progressSize);
    for (size_t i = 0; i < progressSize; i++) {
        base::Progress singleProgress;
        if (!read(data, end, singleProgress.offset.first) || !read(data, end, singleProgress.offset.second) ||
            !read(data, end, singleProgress.from) || !read(data, end, singleProgress.to)) {
            return false;
        }
        if (singleProgress.offset < _minOffset || singleProgress.from < 0 || singleProgress.from > 65535 ||
            singleProgress.to < 0 || singleProgress.to > 65535) {
            return false;
        }
        _progress.emplace_back(singleProgress);
    }
    if (data == end) {
        return true;
    }
    uint64_t userDataLen = 0;
    if (!read(data, end, userDataLen) || data + userDataLen != end) {
        return false;
    }
    if (userDataLen > 0) {
        _userData.append(data, userDataLen);
    }
    return true;
}

bool Locator::Deserialize(const char* data, size_t size)
{
    Reset();
    const char* end = data + size;
    if (!read(data, end, _src)) {
        Reset();
        return false;
    }
    const char* legacyLocatorPtr = data;
    int32_t maybeMagicNumber = 0;
    if (!read(data, end, maybeMagicNumber)) {
        Reset();
        return false;
    }

    // legacy locator which has no magic number
    if (maybeMagicNumber != MAGIC_NUMBER) {
        if (!DeserializeV0(legacyLocatorPtr, end)) {
            Reset();
            return false;
        }
        return true;
    }
    int32_t locatorType = 0;
    read(data, end, locatorType);

    if (locatorType == LOCATOR_SUBOFFSET_VERSION) {
        if (!DeserializeV1(data, end)) {
            Reset();
            return false;
        }
        return true;
    }
    return false;
}

Locator::LocatorCompareResult Locator::IsFasterThan(uint16_t hashId, const base::Progress::Offset& offset) const
{
    for (size_t i = 0; i < _progress.size(); i++) {
        if (_progress[i].from <= hashId && hashId <= _progress[i].to) {
            if (_progress[i].offset > offset) {
                return LocatorCompareResult::LCR_FULLY_FASTER;
            } else {
                return LocatorCompareResult::LCR_SLOWER;
            }
        }
    }
    return LocatorCompareResult::LCR_SLOWER;
}

bool Locator::IsSameSrc(const Locator& otherLocator, bool ignoreLegacyDiffSrc) const
{
    if (ignoreLegacyDiffSrc && (_isLegacyLocator || otherLocator._isLegacyLocator)) {
        return true;
    }
    return _src == otherLocator._src;
}

Locator::LocatorCompareResult Locator::IsFasterThan(const Locator& locator, bool ignoreLegacyDiffSrc) const
{
    if (!IsSameSrc(locator, ignoreLegacyDiffSrc)) {
        return LocatorCompareResult::LCR_INVALID;
    }
    if (ignoreLegacyDiffSrc && (IsLegacyLocator() || locator.IsLegacyLocator())) {
        if (IsLegacyLocator()) {
            base::Progress::Offset maxOffset = base::Progress::INVALID_OFFSET;
            base::Progress::Offset minOffset = locator.GetOffset();
            for (const auto& progress : locator.GetProgress()) {
                maxOffset = std::max(maxOffset, progress.offset);
            }
            if (_minOffset < minOffset) {
                return LocatorCompareResult::LCR_SLOWER;
            }
            if (_minOffset >= maxOffset) {
                return LocatorCompareResult::LCR_FULLY_FASTER;
            }
            return LocatorCompareResult::LCR_PARTIAL_FASTER;
        } else {
            base::Progress::Offset maxOffset = base::Progress::INVALID_OFFSET;
            base::Progress::Offset minOffset = _minOffset;
            for (const auto& progress : _progress) {
                maxOffset = std::max(maxOffset, progress.offset);
            }
            if (locator.GetOffset() <= minOffset) {
                return LocatorCompareResult::LCR_FULLY_FASTER;
            }
            if (locator.GetOffset() > maxOffset) {
                return LocatorCompareResult::LCR_SLOWER;
            }
            return LocatorCompareResult::LCR_PARTIAL_FASTER;
        }
    }
    if (!locator.IsValid()) {
        return LocatorCompareResult::LCR_FULLY_FASTER;
    }
    if (!IsValid()) {
        return LocatorCompareResult::LCR_SLOWER;
    }
    bool isFullyFaster = IsFullyFasterThan(locator);
    if (isFullyFaster) {
        return LocatorCompareResult::LCR_FULLY_FASTER;
    }
    bool isFullySlower = locator.IsFullyFasterThan(*this);
    if (isFullySlower) {
        return LocatorCompareResult::LCR_SLOWER;
    }
    return LocatorCompareResult::LCR_PARTIAL_FASTER;
}

bool Locator::ShrinkToRange(uint32_t from, uint32_t to)
{
    std::vector<base::Progress> newProgress;
    if (_progress[0].from > from || _progress[_progress.size() - 1].to < to) {
        return false;
    }
    for (const auto& singleProgress : _progress) {
        if (singleProgress.to < from || singleProgress.from > to) {
            continue;
        }
        newProgress.push_back(base::Progress(std::max(singleProgress.from, from), std::min(singleProgress.to, to),
                                             singleProgress.offset));
    }
    _progress.swap(newProgress);
    return true;
}

// TODO(xiaohao.yxh): add progress
std::string Locator::DebugString() const
{
    std::string debugString = std::to_string(_src) + ":" + std::to_string(_minOffset.first);
    if (!_userData.empty()) {
        debugString += ":" + _userData;
    }
    debugString += ":[";
    for (const auto& progress : _progress) {
        debugString += "{" + std::to_string(progress.from) + "," + std::to_string(progress.to) + "," +
                       std::to_string(progress.offset.first) + "," + std::to_string(progress.offset.second) + "}";
    }
    debugString += "]";
    return debugString;
}

std::pair<uint32_t, uint32_t> Locator::GetLocatorRange() const
{
    uint32_t from = 65535;
    uint32_t to = 0;
    for (const auto& singleProgress : _progress) {
        from = std::min(from, singleProgress.from);
        to = std::max(to, singleProgress.to);
    }
    return {from, to};
}

void Locator::Reset()
{
    _src = 0;
    _minOffset = base::Progress::INVALID_OFFSET;
    _progress.clear();
    _userData.clear();
}

bool Locator::IsValid() const
{
    if (_minOffset >= base::Progress::MIN_OFFSET) {
        return true;
    }
    for (auto& p : _progress) {
        if (p.offset >= base::Progress::MIN_OFFSET) {
            return true;
        }
    }
    return false;
}
size_t Locator::Size() const
{
    size_t ret =
        sizeof(_src) + +sizeof(_minOffset.first) + ProgressSize(_progress.size()) + UserDataSize(_userData.size());
    if (calculateSerializeVersion() == LOCATOR_SUBOFFSET_VERSION) {
        ret += (sizeof(MAGIC_NUMBER) + sizeof(LOCATOR_SUBOFFSET_VERSION) + sizeof(_minOffset.second));
    }
    return ret;
}
bool Locator::TEST_IsLegal() const
{
    std::vector<base::Progress> progresses = _progress;
    std::sort(progresses.begin(), progresses.end(), [](const base::Progress& l, const base::Progress& r) {
        return l.from == r.from ? (l.to < r.to) : (l.from < r.from);
    });
    for (const base::Progress& progress : progresses) {
        if (progress.from > progress.to) {
            return false;
        }
    }
    for (size_t i = 1; i < progresses.size(); ++i) {
        if (progresses[i - 1].to > progresses[i].from) {
            return false;
        }
    }
    return true;
}

int32_t Locator::calculateSerializeVersion() const
{
    if (_minOffset.second > 0) {
        return LOCATOR_SUBOFFSET_VERSION;
    }
    for (const auto& progress : _progress) {
        if (progress.offset.second > 0) {
            return LOCATOR_SUBOFFSET_VERSION;
        }
    }
    return LOCATOR_DEFAULT_VERSION;
}
size_t Locator::ProgressSize(size_t count) const
{
    auto serializeVersion = calculateSerializeVersion();

    size_t ret = 0;

    if (serializeVersion >= LOCATOR_DEFAULT_VERSION) {
        ret =
            ret + sizeof(uint64_t) +
            (sizeof(base::Progress::from) + sizeof(base::Progress::to) + sizeof(base::Progress::Offset::first)) * count;
    }

    if (serializeVersion >= LOCATOR_SUBOFFSET_VERSION) {
        ret = ret + sizeof(base::Progress::Offset::second) * count;
    }
    return ret;
}
} // namespace indexlibv2::framework
