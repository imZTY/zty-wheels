package com.zty.framework.util.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author tianyi
 * @date 2020-12-20 12:30
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(SpringUtil.class);

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
            logger.info("初始化Spring容器工具类成功, applicationContext={}", applicationContext);
        }
    }

    public static ApplicationContext getApplicationContext() {
        if (SpringUtil.applicationContext == null){
            logger.error("applicationContext为空");
        }
        return applicationContext;
    }

    public static Object getBean(String name, Class clazz) {
        logger.info("根据名称获取BEAN: {}", name);
        Object bean = getApplicationContext().getBean(name);
        return clazz.equals(bean.getClass()) ? bean : null;
    }
}
