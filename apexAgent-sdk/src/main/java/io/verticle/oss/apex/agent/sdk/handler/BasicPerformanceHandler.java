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

package io.verticle.oss.apex.agent.sdk.handler;

import com.codahale.metrics.Timer;
import io.verticle.apex.commons.oss.api.instrumentation.AdvisorContext;
import io.verticle.apex.commons.oss.collectors.model.Domain;
import io.verticle.oss.apex.agent.api.ApexCollectorFactory;
import io.verticle.oss.apex.agent.api.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * This handler measures method execution durations
 * @author Jens Saade
 */
public class BasicPerformanceHandler extends AbstractHandler {

    static final Logger logger = LoggerFactory.getLogger(BasicPerformanceHandler.class);

    private ConcurrentHashMap<String, Timer.Context> timerContexts = new ConcurrentHashMap<String, Timer.Context>();

    @Override
    public void handle(AdvisorContext advisorContext) {
        //NOOP
    }

    @Override
    public void handleBefore(AdvisorContext advisorContext) {
        logger.debug(Constants.LOGPREFIX + "before " + name(advisorContext));
        final Timer timer;
        try {
            timer = ApexCollectorFactory.get().createMetricsTimer(Domain.application, name(advisorContext));
            Timer.Context context = timer.time();
            timerContexts.put(name(advisorContext), context);
        } catch (Exception e) {
            logger.error(Constants.LOGPREFIX + "Could not send message", e);
        }
    }

    @Override
    public void handleAfter(AdvisorContext advisorContext) {
        logger.debug(Constants.LOGPREFIX + "after " + name(advisorContext));
        Timer.Context context = timerContexts.get(name(advisorContext));
        context.stop();
    }
}
