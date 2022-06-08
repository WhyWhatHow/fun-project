package com.fun.common.security.annotation;

import java.lang.annotation.*;

/**
 * 用于过滤 Feign内部调用请求
 * 目前只允许在method 方法上调用
 *  TODO [whywhathow] [2022/6/7] [must] Inner 支持类方法.
 */
@Target(ElementType.METHOD)
//@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface Inner {
    /**
     * 是否 忽略 Resource-server 端安全校验
     *
     * @return
     */
    boolean value() default true;


}
