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

package io.verticle.oss.apex.agent.ext.bootstrap;

import io.verticle.oss.apex.agent.api.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.instrument.Instrumentation;

/**
 * @author Jens Saade
 */
public class BootstrapExtension {

    static final Logger logger = LoggerFactory.getLogger(BootstrapExtension.class);

    public static void premain(String args, Instrumentation inst) throws Exception {
        logger.info(Constants.LOGPREFIX + "APEX Agent Extension Loaded");
    }
}
