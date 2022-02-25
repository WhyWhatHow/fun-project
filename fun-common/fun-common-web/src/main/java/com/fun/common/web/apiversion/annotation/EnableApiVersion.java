package com.fun.common.web.apiversion.annotation;

import com.fun.common.web.apiversion.config.ApiVersionRegistrations;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * api 多版本管理的启动标签 , 一定要开启哦
 *
 * @see org.springframework.scheduling.annotation.EnableAsync
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ApiVersionRegistrations.class)//配置注释
public @interface EnableApiVersion {

}