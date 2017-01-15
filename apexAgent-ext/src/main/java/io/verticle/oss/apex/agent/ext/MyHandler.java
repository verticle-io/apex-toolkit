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

package io.verticle.oss.apex.agent.ext;

import io.verticle.apex.commons.oss.api.instrumentation.AdvisorContext;
import io.verticle.apex.commons.oss.api.instrumentation.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Skeleton class for you own handler implemention. Do not forget to register it in the instrumentation config.
 * @author Jens Saade
 */
public class MyHandler implements Handler {

    static final Logger logger = LoggerFactory.getLogger(MyHandler.class);


    public Map<HandlerOption, Object> getOptions() {
        return null;
    }

    public void setOptions(Map<HandlerOption, Object> map) {

    }


    public void handle(AdvisorContext advisorContext) {
        logger.info("MyHandler handle() called");


    }

    public void handleBefore(AdvisorContext advisorContext) {
        logger.info("MyHandler handleBefore() called");
    }

    public void handleAfter(AdvisorContext advisorContext) {
        logger.info("MyHandler handleAfter() called");
    }
}
