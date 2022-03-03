package com.fun.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * client->  gateway-> authFilter ->  token校验通过的用户放行
 *
 * @program: fun-project
 * @description: 认证模块全局过滤器-> 思路 校验header 中是否有token ,有则去redis校验, 没有则拒绝
 * @author: WhyWhatHow
 * @create: 2022-03-02 22:58
 **/
public class AuthFilter implements GlobalFilter, Ordered {
    /**
     *  header token 标记  auth-token
     */
    private String TOKEN;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1. 校验是否有token
        List<String> strings = exchange.getRequest().getHeaders().get(TOKEN);
        //2. 校验token是否正确

        // 放行
        return chain.filter(exchange);
    }

    /**
     * @return filter 排序,升序 -> 执行
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
