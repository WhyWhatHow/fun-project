package com.fun.gateway.support;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.api.exception.NacosException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.List;
/**
 * json 格式
 * <p>
 * [{
 * "filters": [], // 每一个属性都不能丢
 * "id": "baidu",
 * "order": 2,
 * "predicates": [{
 * "args": {
 * "pattern": "/bd/**"
 * },
 * "name": "Path"
 * }],
 * "uri": "https://baidu.com"
 * },
 * ]
 */

/**
 * 参考 :https://zhuanlan.zhihu.com/p/289607036
 * 参考 :https://www.cnblogs.com/jian0110/p/12862569.html
 *
 * @program: fun-project
 * @description: 从nacos 读取route 配置信息的配置类
 * @author: WhyWhatHow
 * @create: 2022-03-02 09:34
 **/
@Slf4j
@Data
public class NacosRouteDefinitionRegistry implements RouteDefinitionRepository, ApplicationEventPublisherAware {

    /**
     * Spring应用事件发布器
     */
    private ApplicationEventPublisher publisher;
    @Autowired
    private NacosConfigManager configManager;
    private ConfigService configService;
    //    TODO [whywhathow] [2022/3/2]   [opt] 选择硬编码,或者是读取配置文件的方式处理
    // 监听的 data_Id
//    @Value("${fun.gateway.data_id}:")
    private String DATA_ID = "gateway-route.json";
    //    @Value("${fun.gateway.group_id}:")
    private String GROUP_ID = "gateway";

    private List<RouteDefinition> list;

    @PostConstruct
    public void init() throws NacosException {
        this.configService = configManager.getConfigService();
        if (ObjectUtils.isEmpty(configService)) {
            log.warn("[nacos-route]-->config-service is null");
        }
        //   nacos 每隔多少毫秒去nacos 拉一次config
        String configInfo = configService.getConfigAndSignListener(DATA_ID, GROUP_ID, 1000,
                new AbstractListener() {
                    /**
                     * 监听端口
                     * 接收到 nacos 配置信息改变 -> 格式转化, -> publish
                     * @param configInfo
                     */
                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        // 每次收到消息去发送
                        if (!ObjectUtils.isEmpty(configInfo)) {
                            log.info("[nacos-route-listener]->{}", configInfo);
                            // 1. 更新 routeDefinition
                            list = JSONArray.parseArray(configInfo, RouteDefinition.class);
                            publisher.publishEvent(new RefreshRoutesEvent(NacosRouteDefinitionRegistry.this));

                        }
                    }
                }
        );
        log.info("[msg-> config-service pull ]->{}", configInfo);
        list = JSONArray.parseArray(configInfo, RouteDefinition.class);

    }

    /**
     * 从 nacos 获取route 信息
     *
     * @return
     */
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {

        return CollectionUtils.isEmpty(list) ? Flux.empty() : Flux.fromIterable(list);
    }


    /**
     * @param route
     * @return
     */
    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        log.error("[nacos-route-save]->{}", route);
        return Mono.empty();
    }

    /**
     * @param routeId
     * @return
     */
    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        log.error("[nacos-route-del]->{}", routeId);
        return Mono.empty();

    }

    /**
     * 获取spring容器 中applicationEventPublisher
     *
     * @param applicationEventPublisher
     */
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
