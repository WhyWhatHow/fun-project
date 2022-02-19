package com.fun.common.web.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.PostConstruct;

/**
 * @program: fun-project
 * @description: Spring容器util类, 手动获取spring容器中的bean
 * @author: WhyWhatHow
 * @create: 2022-02-19 21:30
 **/
@Component
public class ApplicationContextUtils {
    @Autowired
    ApplicationContext context;

    static ApplicationContext localContext;

    /**
     * 在 spring 创建完当前bean--ApplicationContextUtils 后, 为localContext赋值.
     */
    @PostConstruct
    public void init() {
        localContext = context;
    }

    /**
     * 手动获取 spring容器中的bean
     *
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> cls) {
        if (ObjectUtils.isEmpty(localContext)) {
            return null;
        }
        return localContext.getBean(cls);
    }
}
