package edu.softserveinc.healthbody.log;

import org.slf4j.LoggerFactory;

public class LoggerWrapper {
    public static void debug(Class<?> clazz, String msg){
        LoggerFactory.getLogger(clazz).debug(msg);
    }
    public static void info(Class<?> clazz, String msg){
        LoggerFactory.getLogger(clazz).info(msg);
    }
    public static void error(Class<?> clazz, String msg){
        LoggerFactory.getLogger(clazz).error(msg);
    }
}