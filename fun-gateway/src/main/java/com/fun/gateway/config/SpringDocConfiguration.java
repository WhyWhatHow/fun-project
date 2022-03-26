package com.fun.gateway.config;

import com.fun.gateway.support.NacosRouteDefinitionRegistry;
import com.fun.gateway.support.SpringDocRouteRefreshListener;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: fun-project
 * @description: SpringDoc 配置类
 * @author: WhyWhatHow
 * @create: 2022-03-25 11:13
 **/
@Configuration
public class SpringDocConfiguration {
    @Autowired
    RouteDefinitionLocator locator;
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    ApplicationContext applicationContext;
    //    TODO [whywhathow] [2022/3/25] [must] yaml配置文件读取方式实现
//    @Bean
//    public OpenAPI customOpenApi() {
//        return new OpenAPI().info(new Info().title("Fun Project Gateway API")
//                .description("Spring Doc API")
//                .version("v1.0.0")
//                .contact(new Contact()
//                        .name("whywhathow")
//                        .email("whywhathow.fun@gmail.com"))
//        );
//    }
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("My API")
                        .description("My sample application")
                        .version("v1.0.0")
                        .license(new License().name("Apache 2.0").url("https://whywhathow.fun/")))
                .externalDocs(new ExternalDocumentation()
                        .description("My Blog")
                        .url("https://www.whywhathow.fun/"));
    }
    /**
     * 监听RefreshRoutesEvent事件,当路由刷新时,更新api即可.
     *
     * @return SwaggerRouteRefreshListener.class
     */
    @Bean
    @ConditionalOnBean(NacosRouteDefinitionRegistry.class)
    public SpringDocRouteRefreshListener swaggerRouteRefreshListener() {
        return new SpringDocRouteRefreshListener();
    }

    // 重载默认的多重API定义接口，如果请求的分组为注册的服务ID，则返回对应服务的API定义
//    @Bean
//    MultipleOpenApiWebFluxResource multipleOpenApiResource(List<GroupedOpenApi> groupedOpenApis,
//                                                           ObjectFactory<OpenAPIService> defaultOpenAPIBuilder, AbstractRequestService requestBuilder,
//                                                           GenericResponseService responseBuilder, OperationService operationParser,
//                                                           RequestMappingInfoHandlerMapping requestMappingHandlerMapping,
//                                                           SpringDocConfigProperties springDocConfigProperties,
//                                                           Optional<ActuatorProvider> actuatorProvider) {
//
//        return new MultipleOpenApiWebFluxResource(groupedOpenApis,
//                defaultOpenAPIBuilder, requestBuilder,
//                responseBuilder, operationParser,
//                requestMappingHandlerMapping,
//                springDocConfigProperties,
//                actuatorProvider)
//        {
//            @Override
//            public Mono<String> openapiJson(ServerHttpRequest serverHttpRequest, String apiDocsUrl, String group) throws JsonProcessingException {
//                List<ServiceInstance> serviceInstances = discoveryClient.getInstances(group);
//                if (CollectionUtils.isNotEmpty(serviceInstances)) {
//                    String gatewayUri = serverHttpRequest.getURI().getScheme() + "://" + serverHttpRequest.getURI().getHost();
//                    if (serverHttpRequest.getURI().getPort() != -1) {
//                        gatewayUri += ":" + serverHttpRequest.getURI().getPort();
//                    }
//                    gatewayUri += "/" + group;
//                    String serviceUri = serviceInstances.get(0).getUri().toString();
//                    String url = serviceUri + "/v3/api-docs";
//                    String openapiJson = restTemplate().getForObject(url, String.class);
//                    openapiJson = openapiJson.replace(serviceUri, gatewayUri);
//                    return Mono.just(openapiJson);
//                }
//                return super.openapiJson(serverHttpRequest, apiDocsUrl, group);
//            }
//        };
//
//    }
//    @Bean
//    RestTemplate restTemplate() {
//        return new RestTemplate();
//    }
}

