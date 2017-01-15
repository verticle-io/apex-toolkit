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

package io.verticle.oss.apex.agent.sdk.util;


import io.verticle.apex.commons.oss.api.instrumentation.AdvisorContext;
import io.verticle.apex.commons.oss.api.instrumentation.Instrumentation;

/**
 *
 * @author Jens Saade
 */
public class DefaultNamingStrategy implements NamingStrategy{

    public static String getInstrumentedClassName(Instrumentation instrumentationConfig){
        return getInstrumentedName(instrumentationConfig);
    }

    public static String getInstrumentedAdviceName(AdvisorContext advisorContext){
        return getInstrumentedName(advisorContext.getInstrumentationConfig());
    }

    private static String getInstrumentedName(Instrumentation instrumentationConfig){
        String name;
        name = instrumentationConfig.getTargetClass();

        return name;
    }
}
