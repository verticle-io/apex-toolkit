package io.verticle.oss.apex.agent.ext.bootstrap;

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
