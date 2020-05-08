package com.zty.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设备需要的权限（默认已限制 IP KEY）
 * @author tianyi
 * @date 2019-07-09 10:48
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRights {
    String rights() default "";  //多个权限用"&"分隔
}
