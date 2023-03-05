package com.zty.framework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertyUtils {

    private final static transient Logger logger = LoggerFactory.getLogger(PropertyUtils.class);

    public static String getValue(String propertyFileName, String key) {
        InputStream in = PropertyUtils.class.getClassLoader().getResourceAsStream(propertyFileName);
        Properties prop = new Properties();

        try {
            prop.load(in);
            in.close();
        } catch (IOException e) {
            logger.error("获取配置异常", e);
            return null;
        }

        return prop.getProperty(key);
    }

}
