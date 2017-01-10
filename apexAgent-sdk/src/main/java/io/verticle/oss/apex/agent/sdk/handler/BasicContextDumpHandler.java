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

import io.verticle.apex.commons.oss.api.instrumentation.AdvisorContext;
import io.verticle.apex.commons.oss.api.instrumentation.Handler;
import io.verticle.apex.commons.oss.collectors.model.Domain;
import io.verticle.oss.apex.agent.api.ApexCollectorFactory;
import io.verticle.oss.apex.agent.api.Constants;
import io.verticle.oss.apex.agent.api.transport.payload.MetricMessage;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This handler dumps the current class instance context into a message
 * @author Jens Saade
 */
public class BasicContextDumpHandler extends AbstractHandler {

    static final Logger logger = LoggerFactory.getLogger(BasicContextDumpHandler.class);

    private Map<Handler.HandlerOption, Object> handlerOptions = new ConcurrentHashMap<>();

    @Override
    public void handle(AdvisorContext advisorContext) {

    }

    @Override
    public void handleBefore(AdvisorContext advisorContext) {
        dumpContext(advisorContext, "before");
    }

    @Override
    public void handleAfter(AdvisorContext advisorContext) {
        dumpContext(advisorContext, "after");
    }

    private void dumpContext(AdvisorContext advisorContext, String trigger){

        Map<Object, Object> args = new HashMap<>();

        for (int i=0; i < advisorContext.getSignatureTypes().length; i++){
            try{
                args.put(advisorContext.getSignatureTypes()[i],  ReflectionToStringBuilder.toString(advisorContext.getSignatureArgs()[i], ToStringStyle.JSON_STYLE));
            } catch (Throwable t){
                logger.info(Constants.LOGPREFIX + " could not serialize Object in Basic Context Dump Handler");
            }

        }

        MetricMessage message = new MetricMessage(Domain.application, name(advisorContext), new Date());
        message.addField("correlationId", advisorContext.getCorrelationId());
        message.addField("trigger", trigger);
        message.addField("args", args);

        try {
            ApexCollectorFactory.get().reportDirect(this.getClass(), message);
        } catch (Exception e) {
            logger.error(Constants.LOGPREFIX + "Could not send message", e);
        }

    }
}
