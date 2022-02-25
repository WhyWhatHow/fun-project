package com.fun.common.web.apiversion.config;

import com.fun.common.web.apiversion.ApiVersionHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @program: fun-project
 * @description: SpringMVC方案: 将ApiVersionHandlerMapping 注册到 spring容器中.
 * @author: WhyWhatHow
 * @create: 2022-02-25 11:35
 **/

public class ApiVersionRegistrations implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new ApiVersionHandlerMapping();
    }
}
