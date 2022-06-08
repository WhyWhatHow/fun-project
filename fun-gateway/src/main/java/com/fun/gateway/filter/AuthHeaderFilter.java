package com.fun.gateway.filter;

import com.fun.common.core.constants.InnerConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @program: fun-project
 * @description: 对所有走gateway 网关的请求添加 header 头部信息
 * @author: WhyWhatHow
 * @create: 2022-06-07 15:11
 **/

public class AuthHeaderFilter implements GlobalFilter, Ordered {
    /**
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return
     * @see org.springframework.cloud.gateway.filter.ForwardPathFilter
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        List<String> strings = request.getHeaders().get(HttpHeaders.FROM);

        // 避免 用户从前端伪造请求,直接访问内部服务.
        request = request.mutate().header(HttpHeaders.FROM, InnerConstants.OUT).build();
        exchange = exchange.mutate().request(request).build();
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
