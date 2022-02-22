package com.fun.common.web.async;

import com.fun.common.web.async.config.AsyncExecutionProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @program: fun-project
 * @description: 自定义线程池配置类
 * @author: WhyWhatHow
 * @create: 2022-02-22 15:22
 * @see TaskExecutionAutoConfiguration
 **/
@Configuration
@Slf4j
@EnableConfigurationProperties(AsyncExecutionProperties.class)
public class AsyncAutoConfiguration {
    /***
     *  todo 思考是否其他的executor配置需要自定义,是否需要开放到yaml文档中,还是自己给默认值
     *  自定义线程池实现 ,只有在开启@EnableAsync 时创建
     * @param properties 线程池的自定义配置
     * @return 线程池executor
     */
    @Bean
    @Primary
    @ConditionalOnBean(annotation = EnableAsync.class)
    public ThreadPoolTaskExecutor getThreadPoolTaskExecutor(AsyncExecutionProperties properties) {
        log.warn(" [fun-task-executor] init");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

//  coreThreadSize, maxThreadSize, KeepAliveTime ,Queue, Timeunit,rejectHandler, Factory
        executor.setCorePoolSize(properties.getCoreSize()); // 核心线程数
        executor.setMaxPoolSize(properties.getMaxSize()); // 最大线程数
        executor.setKeepAliveSeconds(properties.getKeepAlive()); // 最大存活时间
        executor.setQueueCapacity(properties.getQueueCapacity()); // 阻塞队列容量
        executor.setThreadNamePrefix(properties.getThreadNamePrefix()); // 设置名称前缀
        executor.setRejectedExecutionHandler(properties.getRejectedHandler().getHandler());// 设置拒绝策略
        executor.setAllowCoreThreadTimeOut(properties.isAllowCoreThreadTimeout());// 是否允许核心线程超时
        executor.setPrestartAllCoreThreads(properties.isPrestartAllCoreThreads());// 是否启动所有核心线程,使其空闲等待工作
        executor.initialize();
        log.warn(" [fun-task-executor] end ");

        return executor;
    }
}
