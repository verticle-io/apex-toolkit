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
import io.verticle.oss.apex.agent.sdk.AbstractMetricsGenerator;
import io.verticle.oss.apex.agent.sdk.util.DefaultNamingStrategy;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract Handler implementation
 * @author Jens Saade
 */
public abstract class AbstractHandler extends AbstractMetricsGenerator implements Handler {

    Map<Handler.HandlerOption, Object> options = new HashMap<>();

    @Override
    public Map<HandlerOption, Object> getOptions() {
        return options;
    }

    @Override
    public void setOptions(Map<HandlerOption, Object> options) {
        this.options = options;
    }

    protected String name(AdvisorContext advisorContext){
        return DefaultNamingStrategy.getInstrumentedAdviceName(advisorContext);
    }
}
