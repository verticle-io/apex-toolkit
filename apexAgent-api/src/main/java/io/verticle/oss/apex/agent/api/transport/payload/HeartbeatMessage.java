/*
 *    Copyright 2016 Jens Saade <jens@verticle.io>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.verticle.oss.apex.agent.api.transport.payload;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Heartbeat Message Object
 * @author Jens Saade
 */
public final class HeartbeatMessage {

    Map<String, Object> meta = new HashMap();

    public HeartbeatMessage(Date timestamp, String inetIP, String macHash, String applicationId){
        meta.put("@timestamp", timestamp);
        meta.put("src_mac_hash", macHash);
        meta.put("src_ip", inetIP);
        meta.put("src_collect", applicationId);
    }

    public Map<String, Object> getMeta() {
        return meta;
    }


}
