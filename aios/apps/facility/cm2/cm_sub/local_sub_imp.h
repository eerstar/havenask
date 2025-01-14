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
#ifndef __LOCAL_SUBSCRIBER_IMP_H_
#define __LOCAL_SUBSCRIBER_IMP_H_

#include "aios/apps/facility/cm2/cm_sub/cm_sub_base.h"

namespace cm_basic {
class SubRespMsg;
class CMCentralSub;
} // namespace cm_basic

namespace cm_sub {

class TopoClusterManager;

class LocalSubscriberImp : public CMSubscriberBase
{
private:
    char* getDataFromFile(const char* p_file_name);

public:
    LocalSubscriberImp(SubscriberConfig* cfg_info) : CMSubscriberBase(cfg_info) {}
    virtual ~LocalSubscriberImp() {}

    int32_t init(TopoClusterManager* topo_cluster, cm_basic::CMCentralSub* cm_central);

private:
    AUTIL_LOG_DECLARE();
};

} // namespace cm_sub
#endif
