package com.fun.gateway.support;

import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.ApplicationListener;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: fun-project
 * @description: Swagger 配置 的 routeRefreshListener
 * @author: WhyWhatHow
 * @create: 2022-03-25 14:02
 **/
@Slf4j
public class SpringDocRouteRefreshListener implements ApplicationListener<RefreshRoutesEvent> {
    //    private String REACTIVE_PREFIX = "ReactiveCompositeDiscoveryClient_fun-service";
    /**
     * 微服务前缀
     * TODO [whywhathow] [2022/3/28] [opt] 服务前缀去除可配置
     */
    private String FUN_SERVICE_PREFIX = "fun-service-";
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
     * 通过注册中心获取的路由信息 routeDefinitionLocator
     */
    @Autowired
    RouteDefinitionLocator locator;
    /**
     * 上一次routeDefinitionLocator 中的list 数量判断
     */
    private volatile Integer lastListSize = -1;

    /**
     * 统计更新次数
     */
    private AtomicLong count = new AtomicLong(0L);

    /**
     * 微服务swagger 缓存
     *  TODO [whywhathow] [2022/3/27] [opt] springCaching 配置
     */
//    private ConcurrentHashMap<String, Object> serviceMap = new ConcurrentHashMap<>();


    /**
     * 监听 RefreshRoutesEvent,当 有新增微服务, 更新groupApi -> 向swaggerUiConfigParameters中添加groupApi
     * </br>实现思路:
     * <p>1.从nacos配置中心获取服务后自动注册的服务 -> 读取gateway-route.json 中定义的路由信息</p>
     * 2. 根据路由信息的数量变化来进行 动态api 的变化-> 有点蠢,不过只需要在gateway-route.json中删除一组旧的就可以实现更新.</br>
     * target: 减少 refreshRouteEvent(心跳连接)事件触发此函数的频率</br>
     * 3. 注入swaggerUiConfigParameters ->写入openApi</br>
     *
     * @param event RefreshRoutesEvent
     */
    @Override
    public void onApplicationEvent(RefreshRoutesEvent event) {

        // 1. 获取刷新的routes 信息
        List<RouteDefinition> routeDefinitions = registry.getList();
        int size = routeDefinitions.size();
        if (size == lastListSize) {
            return;
        }
        // 统计 更新时间,以及次数
        log.warn("[Fun-RefreshRoutesEvent] --- start ,count: {}", count.incrementAndGet());

        lastListSize = size;
        // 2. 清空 swagger urls  重新构建
        swaggerUiConfigParameters.getUrls().clear();
        routeDefinitions.forEach(routeDefinition -> {
                    String name = routeDefinition.getId();
                    //   3 重新构建api
                    if (name.startsWith(FUN_SERVICE_PREFIX)) {
                        name = removeServicePrefix(routeDefinition, name);
                        swaggerUiConfigParameters.addGroup(name);
                        // /demo, /api/demo/v3/api-docs
//                GroupedOpenApi build = GroupedOpenApi.builder().pathsToMatch("/" + name + "/**").group(name).build();
                    }
                }
        );

    }

    /**
     * 去除微服务名前缀
     * eg:  fun-service-demo
     * result : demo
     *
     * @param routeDefinition
     * @param name
     * @return
     */
    private String removeServicePrefix(RouteDefinition routeDefinition, String name) {

        name = routeDefinition.getUri().getHost();
        name = name.replace(FUN_SERVICE_PREFIX, "");
        return name;
    }

}
