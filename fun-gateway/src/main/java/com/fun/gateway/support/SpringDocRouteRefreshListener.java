package com.fun.gateway.support;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationListener;

import java.util.List;

/**
 * @program: fun-project
 * @description: Swagger 配置 的 routeRefreshListener
 * @author: WhyWhatHow
 * @create: 2022-03-25 14:02
 **/
@Slf4j
public class SpringDocRouteRefreshListener implements ApplicationListener<RefreshRoutesEvent> {
    /**
     * swagger OpenApi 核心类
     */
    @Autowired
    SwaggerUiConfigParameters swaggerUiConfigParameters;
    /**
     * nacos 动态路由注册中心
     */
    @Autowired
    NacosRouteDefinitionRegistry registry;

    /**
     * 监听 RefreshRoutesEvent , 当路由发生变化后,重新生成 新的api
     *  以 fun-services-demo 为例,
     *  json 配置格式如下:
     * @param event RefreshRoutesEvent
     */
    @Override
    public void onApplicationEvent(RefreshRoutesEvent event) {
//        log.warn("[Fun-RefreshRoutesEvent] --- start");
        // 1. 获取消息源数据 即 nacosRouteDefinitionRegistry
        // 2. 获取刷新的routes 信息
        List<RouteDefinition> routes = registry.getList();
        // 3. 清空 swagger urls  重新构建
        swaggerUiConfigParameters.getUrls().clear();
        routes.forEach(routeDefinition -> {
            // 4. 重新构建 api
            String name = routeDefinition.getUri().getHost();
            if (name.contains("service")) {
                //  去掉fun-services- 前缀, 保留服务
                int i = name.lastIndexOf("-");
                name.substring(i, name.length() - 1);
                swaggerUiConfigParameters.addGroup(name);
//                 fun-services-demo 为例, ->/fun-services-demo/**
//                 gateway配置 用户请求 如/api/demo/**
                GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
            }
        });

    }
}
