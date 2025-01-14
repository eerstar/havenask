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
#ifndef __INDEXLIB_MERGE_STRATEGY_PARAMETER_H
#define __INDEXLIB_MERGE_STRATEGY_PARAMETER_H

#include <memory>

#include "autil/legacy/jsonizable.h"
#include "indexlib/common_define.h"
#include "indexlib/indexlib.h"

namespace indexlib { namespace config {

class MergeStrategyParameter : public autil::legacy::Jsonizable
{
public:
    MergeStrategyParameter();
    ~MergeStrategyParameter();

public:
    void Jsonize(autil::legacy::Jsonizable::JsonWrapper& json) override;

    // for legacy format compatible
    void SetLegacyString(const std::string& legacyStr);
    std::string GetLegacyString() const;

public:
    std::string inputLimitParam;
    std::string strategyConditions;
    std::string outputLimitParam;

private:
    IE_LOG_DECLARE();
};

DEFINE_SHARED_PTR(MergeStrategyParameter);
}} // namespace indexlib::config

#endif //__INDEXLIB_MERGE_STRATEGY_PARAMETER_H
