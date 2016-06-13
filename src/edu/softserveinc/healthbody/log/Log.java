package edu.softserveinc.healthbody.log;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	private final static String LOGGER_PROPERTIES_FILENAME = "./resources/log4j.properties";
    
    public static Logger init(String className) {
    	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    	InputStream is = classloader.getResourceAsStream(LOGGER_PROPERTIES_FILENAME);
        final Logger logger = Logger.getLogger(className);
        PropertyConfigurator.configure(is);
        return logger;
    }
}