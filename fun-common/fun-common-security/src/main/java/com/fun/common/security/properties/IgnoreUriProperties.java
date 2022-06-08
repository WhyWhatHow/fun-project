package com.fun.common.security.properties;

import com.fun.common.security.annotation.Inner;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.LinkedList;
import java.util.Map;

/**
 * @program: fun-project
 * @description: 存储 WebSecurity放行的uri请求列表
 * @author: WhyWhatHow
 * @create: 2022-06-07 10:07
 **/
@Slf4j
@Data
@ConfigurationProperties(prefix = "fun.security")
@RequiredArgsConstructor
public class IgnoreUriProperties implements InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;


    /**
     * spring security 跳过检查的uris
     */
    private LinkedList<String> uris = new LinkedList<>();


    /**
     * test name
     */
    private String name;

    /**
     * 在 spring构建完 所有的bean 对象后, 对 requestMappingHandlerMapping
     * 构建 ignoreUris ,  即 带有 Inner 注解的 request -> requestMapping
     *
     * @throws Exception ex
     *                   //     * @see com.fun.common.web.apiversion.ApiVersionHandlerMapping
     * @see org.springframework.web.servlet.DispatcherServlet #getHandler()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.warn("[ignore-uri]-->started ...........");
        //  1. 获取所有的request 请求
        RequestMappingHandlerMapping bean = applicationContext.getBean(RequestMappingHandlerMapping.class);

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        // 2. 获取 所有带有 Inner 注解 的请求
        handlerMethods.keySet().forEach(reqInfo -> {
            HandlerMethod handlerMethod = handlerMethods.get(reqInfo);
//             2.1 Inner 注解在method
            Inner method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), Inner.class);
            addIgnoreUriList(reqInfo, method);
//            2.2 TODO [whywhathow] [2022/6/7] [must] [opt]   Inner注解 支持 class
//            Inner clazz = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), Inner.class);
//            addIgnoreUriList(reqInfo, clazz);
        });

    }

    /**
     * 添加到 ignoreUris 中.
     *
     * @param reqInfo
     * @param inner
     */
    private void addIgnoreUriList(RequestMappingInfo reqInfo, Inner inner) {
        if (inner != null) {
            reqInfo.getPathPatternsCondition().getPatterns().forEach(val -> {
//                    ISSUE [whywhathow] [2022/6/7] [must]
//                     没有重写uri, 可能存在的问题: /api/hello/c 可能没有权限?
                uris.add(reqInfo.getPathPatternsCondition().getFirstPattern().getPatternString());
            });
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
