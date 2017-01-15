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

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Factory for {@link ApexCollector} implementations, searches the classpath for available implementations.
 * @author Jens Saade
 */
public final class ApexCollectorFactory {

    static final Logger logger = LoggerFactory.getLogger(ApexCollectorFactory.class);

    static ApexCollector collectorInstance;

    public static ApexCollector get() throws Exception {

        if (collectorInstance != null)
            return collectorInstance;

        Reflections reflections = new Reflections("io.verticle.apex");
        Set<Class<? extends ApexCollector>>  collectors = reflections.getSubTypesOf(ApexCollector.class);

        if (collectors.size() > 0){
            Class<? extends ApexCollector> collectorClass = collectors.iterator().next();
            try {
                collectorInstance = collectorClass.newInstance();
                logger.info("instantiated " + collectorClass);
                return collectorInstance;
            } catch (InstantiationException e) {
                logger.error(Constants.LOGPREFIX + "Failed to instantiate collector implementation", e);
            } catch (IllegalAccessException e) {
                logger.error(Constants.LOGPREFIX + "Failed to instantiate collector implementation", e);
            }

        }

        // no collector found? fast fail
        throw new Exception("No ApexCollector implementation found");
    }
}
