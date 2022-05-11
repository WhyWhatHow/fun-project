package com.fun.common.captcha.config;

import com.fun.common.captcha.properties.AjCaptchaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: fun-project
 * @description:  anji-captcha 默认配置
 * @author: WhyWhatHow
 * @create: 2022-05-11 11:28
 **/
@Configuration
@EnableConfigurationProperties(AjCaptchaProperties.class)
@ComponentScan("com.anji.captcha")
@Import({AjCaptchaServiceAutoConfiguration.class, AjCaptchaStorageAutoConfiguration.class})

public class CaptchaAutoConfiguration {
}





