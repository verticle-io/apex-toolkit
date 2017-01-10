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


import io.verticle.apex.commons.oss.collectors.model.Domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Metric Message object
 * @author Jens Saade
 */
public final class MetricMessage {

    Map<String, Object> meta = new HashMap();
    Map<String, Object> metrics = new HashMap();

    /**
     * MetricMessageFormat V1
     * @param index
     * @param type
     * @param timestamp
     */
    public MetricMessage(String index, String type, Date timestamp){
        meta.put("index", index);
        meta.put("type", type);
        meta.put("@timestamp", timestamp);
    }

    /**
     * MetricMessageFormat V2
     * @param domain
     * @param qualifier
     * @param timestamp
     */
    public MetricMessage(Domain domain, String qualifier, Date timestamp){
        meta.put("domain", domain);
        meta.put("qualifier", qualifier);
        meta.put("@timestamp", timestamp);
    }

    public void addField(String fieldName, Object fieldValue){
        metrics.put(fieldName,  fieldValue);
    }


    public Map<String, Object> getMeta() {
        return meta;
    }

    public Map<String, Object> getMetrics() {
        return metrics;
    }

    public void addMeta(String fieldName, Object fieldValue) {
        meta.put(fieldName, fieldValue);
    }
}
