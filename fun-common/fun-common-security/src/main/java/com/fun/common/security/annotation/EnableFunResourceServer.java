package com.fun.common.security.annotation;

import com.fun.common.security.config.DefaultWebSecurityConfiguration;
import com.fun.common.security.config.ResourceServerConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用自定义Resource注解, 允许用户通过配置文件配置允许访问的uri,
 * custom @EnableResourceServer
 */
@Target({ElementType.TYPE})
@EnableResourceServer
@Retention(RetentionPolicy.RUNTIME)
@Import({ResourceServerConfiguration.class, DefaultWebSecurityConfiguration.class})
public @interface EnableFunResourceServer {
}
