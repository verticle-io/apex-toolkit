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

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import io.verticle.apex.commons.oss.api.instrumentation.AdvisorContext;
import io.verticle.apex.commons.oss.collectors.model.Domain;
import io.verticle.oss.apex.agent.api.ApexCollectorFactory;
import io.verticle.oss.apex.agent.api.Constants;
import io.verticle.oss.apex.agent.api.transport.payload.MetricMessage;
import io.verticle.oss.apex.agent.sdk.ContextCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This handler executes Groovy scripts.
 * @author Jens Saade
 */
public class BasicScriptedHandler extends AbstractHandler {

    static final Logger logger = LoggerFactory.getLogger(BasicScriptedHandler.class);

    private Map<HandlerOption, Object> handlerOptions = new ConcurrentHashMap<>();

    private String handlerScript;
    private Long handlerScriptLastModified = 0L;



    @Override
    public void handle(AdvisorContext advisorContext) {
        //NOOP
    }

    @Override
    public void handleBefore(AdvisorContext advisorContext) {

        MetricMessage message = new MetricMessage(Domain.application, name(advisorContext), new Date());
        GroovyShell shell = prepareShell(advisorContext, message);

        try {
            logger.info(Constants.LOGPREFIX + "Parsing handlerscript");
            Script scrpt = shell.parse(handlerScript);
            logger.info(Constants.LOGPREFIX + "Invoking handlerscript");
            scrpt.invokeMethod("before", null);
        } catch (Exception e) {
            logger.error(Constants.LOGPREFIX + "could not parse/invoke handler script", e);
        }


        try {
            if (message.getMetrics().size() > 0)
                ApexCollectorFactory.get().reportDirect(this.getClass(), message);
        } catch (Exception e) {
            logger.error(Constants.LOGPREFIX + "Could not send message", e);
        }
    }

    @Override
    public void handleAfter(AdvisorContext advisorContext) {

        MetricMessage message = new MetricMessage(Domain.application, name(advisorContext), new Date());
        GroovyShell shell = prepareShell(advisorContext, message);

        try {
            logger.info(Constants.LOGPREFIX + "Parsing handlerscript");
            Script scrpt = shell.parse(handlerScript);
            logger.info(Constants.LOGPREFIX + "Invoking handlerscript");
            scrpt.invokeMethod("after", null);
        } catch (Exception e) {
            logger.error(Constants.LOGPREFIX + "could not parse/invoke handler script", e);
        }


        try {
            if (message.getMetrics().size() > 0)
                ApexCollectorFactory.get().reportDirect(this.getClass(), message);
        } catch (Exception e) {
            logger.error(Constants.LOGPREFIX + "Could not send message", e);
        }
    }

    private GroovyShell prepareShell(AdvisorContext advisorContext, MetricMessage message ){

        // locate script file
        String groovyScriptFilename =  (String) options.get(HandlerOption.groovy);
        Path modulePath = advisorContext.getInstrumentationConfig().getMetaReference().getRepositoryModulePath();
        String handlerScriptPath = Paths.get(modulePath.toString(), groovyScriptFilename).toString();



        Binding binding = new Binding();
        binding.setVariable("context", advisorContext);
        binding.setVariable("contextCache", ContextCache.getInstance());
        binding.setVariable("message", message);
        binding.setVariable("argsCount", advisorContext.getSignatureArgs().length);

        for (int i=0; i < advisorContext.getSignatureArgs().length; i++){
            binding.setVariable("arg" + (i+1), advisorContext.getSignatureArgs()[i]);
            binding.setProperty("arg" + (i+1), advisorContext.getSignatureArgs()[i]);
        }

        GroovyShell shell = new GroovyShell(binding);

        try {
            loadScript(handlerScriptPath);
        } catch (Exception e) {
            logger.error(Constants.LOGPREFIX + "could not load handler script", e);
        }


        return shell;
    }


    private void loadScript(String handlerScriptPath) {
        Long handlerScriptLastModifiedNow = 0L;

        File scriptFile = new File(handlerScriptPath);
        if (scriptFile.exists()){
            handlerScriptLastModifiedNow = scriptFile.lastModified();

            if (handlerScriptLastModifiedNow > handlerScriptLastModified){
                try {
                    logger.info(Constants.LOGPREFIX + "(Re-)Loading groovy script " + handlerScriptPath);
                    handlerScript = new Scanner(new File(handlerScriptPath)).useDelimiter("\\Z").next();
                } catch (FileNotFoundException e) {
                    logger.error(Constants.LOGPREFIX + "Could not load handler script", e);
                }
            }

            handlerScriptLastModified = handlerScriptLastModifiedNow;
        }
    }

}
