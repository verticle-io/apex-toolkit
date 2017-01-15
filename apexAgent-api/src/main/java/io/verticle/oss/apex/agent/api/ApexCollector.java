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

package io.verticle.oss.apex.agent.api;

import com.codahale.metrics.*;

import io.verticle.apex.commons.oss.collectors.model.Domain;
import io.verticle.oss.apex.agent.api.transport.payload.MetricMessage;

import java.util.Set;

/**
 * Apex Collector interface definition
 * @author Jens Saade
 */
public interface ApexCollector {
    Set<Domain> names();

    void remove(Domain key);

    MetricRegistry add(Domain domain, MetricRegistry registry);

    MetricRegistry getOrCreate(Domain domain);

    void reportDirect(Class fromClass, MetricMessage message);

    Gauge createMetricsGauge(Domain domain, String qualifier);

    com.codahale.metrics.Timer createMetricsTimer(Domain domain, String qualifier);

    Meter createMetricsMeter(Domain domain, String qualifier);

    Counter createMetricsCounter(Domain domain, String qualifier);

    Histogram createMetricsHistogram(Domain domain, String qualifier);
}
