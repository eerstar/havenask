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
#ifndef __INDEXLIB_TIMESTAMP_ATTRIBUTE_WRITER_H
#define __INDEXLIB_TIMESTAMP_ATTRIBUTE_WRITER_H

#include <memory>

#include "indexlib/common_define.h"
#include "indexlib/index/normal/attribute/accessor/single_value_attribute_writer.h"
#include "indexlib/indexlib.h"

namespace indexlib { namespace index {

class TimestampAttributeWriter : public SingleValueAttributeWriter<uint64_t>
{
public:
    TimestampAttributeWriter(const config::AttributeConfigPtr& attrConfig) : SingleValueAttributeWriter(attrConfig) {}

    ~TimestampAttributeWriter() {}

    DECLARE_ATTRIBUTE_WRITER_IDENTIFIER(timestamp);

public:
    class Creator : public AttributeWriterCreator
    {
    public:
        FieldType GetAttributeType() const { return ft_timestamp; }

        AttributeWriter* Create(const config::AttributeConfigPtr& attrConfig) const
        {
            return new TimestampAttributeWriter(attrConfig);
        }
    };

private:
    IE_LOG_DECLARE();
};

DEFINE_SHARED_PTR(TimestampAttributeWriter);
}} // namespace indexlib::index

#endif //__INDEXLIB_TIMESTAMP_ATTRIBUTE_WRITER_H
