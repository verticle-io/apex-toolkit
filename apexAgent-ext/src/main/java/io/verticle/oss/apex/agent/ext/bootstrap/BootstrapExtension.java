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
