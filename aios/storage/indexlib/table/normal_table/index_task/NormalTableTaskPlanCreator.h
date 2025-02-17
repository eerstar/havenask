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
#pragma once

#include "autil/Log.h"
#include "autil/NoCopyable.h"
#include "indexlib/table/index_task/ComplexIndexTaskPlanCreator.h"
namespace indexlibv2::table {

class NormalTableTaskPlanCreator : public ComplexIndexTaskPlanCreator
{
public:
    NormalTableTaskPlanCreator();
    ~NormalTableTaskPlanCreator();

protected:
    std::shared_ptr<framework::IIndexOperationCreator>
    CreateIndexOperationCreator(const std::shared_ptr<config::ITabletSchema>& tabletSchema) override;
    Status SelectTaskConfigs(const framework::IndexTaskContext* taskContext,
                             std::vector<config::IndexTaskConfig>* configs) override;

private:
    AUTIL_LOG_DECLARE();
};

} // namespace indexlibv2::table
