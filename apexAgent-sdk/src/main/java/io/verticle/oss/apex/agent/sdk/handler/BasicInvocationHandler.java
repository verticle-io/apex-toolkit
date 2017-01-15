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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Jens Saade
 */
public class BasicInvocationHandler extends AbstractHandler {

    private Map<HandlerOption, Object> handlerOptions = new ConcurrentHashMap<>();


    @Override
    public void handle(AdvisorContext advisorContext) {

    }

    @Override
    public void handleBefore(AdvisorContext advisorContext) {

    }

    @Override
    public void handleAfter(AdvisorContext advisorContext) {

    }
}
