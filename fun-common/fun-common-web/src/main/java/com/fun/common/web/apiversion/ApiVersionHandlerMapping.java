package com.fun.common.web.apiversion;

import com.fun.common.web.apiversion.annotation.ApiVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @program: fun-project
 * @description: Api多版本控制的 handlerMapping
 * @author: WhyWhatHow
 * @create: 2022-02-25 10:07
 **/
@Slf4j
public class ApiVersionHandlerMapping extends RequestMappingHandlerMapping {
    private double val = 1.0d;

    /**
     * 触发时间: spring 容器初始化
     * 返回controller 类对应的RequestCondition
     * 不会出现小于1.0的版本
     *
     * @param handlerType controller 类
     * @return ApiVersionRequestCondition
     */
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        //1. 找@ApiVersion 注解
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        //2. 返回每一个controller对应的版本号, 有ApiVersion 用ApiVersion, 没有的用1.0 , 此时ApiVersion,可以为0.9
        return new ApiVersionRequestCondition(apiVersion != null && apiVersion.value() > val ? apiVersion.value() : val);
    }

    /**
     * 触发时间: spring 容器初始化
     * 放回controller 方法对应的requestCondition
     * 以 方法对应的ApiVersion为主, 不会出现小于1.0的版本
     * eg: class: ApiVersion=2.0 , method ApiVersion=1.0 ,放回1.0
     *
     * @param method controller中request方法
     * @return
     */
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        //1. 找@ApiVersion 注解
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        //2. 判断方法上是否有apiversion, 没有采用class类上ApiVersion
        if (apiVersion == null) {
            // 从method 的类中中返回版本号
            apiVersion = AnnotationUtils.findAnnotation(method.getDeclaringClass(), ApiVersion.class);
        }
        return new ApiVersionRequestCondition(apiVersion != null && apiVersion.value() > val ? apiVersion.value() : val);
    }


}
