package io.verticle.oss.apex.agent.sdk;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jens Saade
 */
public class ContextCache extends ConcurrentHashMap {

    private static ContextCache instance;

    public static ContextCache getInstance(){

        if (instance == null)
            instance = new ContextCache();

        return instance;
    }
}
