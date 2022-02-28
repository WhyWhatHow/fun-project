package com.fun.common.web.log;

import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.util.ObjectUtils;

/**
 * @program: fun-project
 * @description: 监听日志文件, 动态配置日志文件
 * @author: WhyWhatHow
 * @create: 2022-02-16 14:32
 **/
public class LogListener implements GenericApplicationListener {
    static String LOG_NAME = "fun.log.file.name";
    static String LOG_PATH = "spring.application.name";
    //  获取 propertySource  的配置名, CONFIG_PROPERTIES, 以及APPLICATION_CONFIG 都可以获得
    static String CONFIG_PROPERTIES = "configurationProperties";
    static String APPLICATION_CONFIG = "applicationConfig: [classpath:/application.yml]";

    /**
     * 通过 springApplicationlistener 监听器,监听日志文件
     * 参考 {@link LoggingApplicationListener #supportsEventType}  方法写出
     *
     * @param eventType
     * @return
     */
    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        return ApplicationEnvironmentPreparedEvent.class.isAssignableFrom(eventType.getRawClass());
    }


    /***
     * 通过监听 {@link ApplicationPreparedEvent} , 将 application.yml 中 spring.application.name 配置到 log日志中
     *
     * {@link LoggingApplicationListener}
     * @param event
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // spring容器启动过程的加载
        if (event instanceof ApplicationEnvironmentPreparedEvent) {
            //1. 获取environment
            ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) event).getEnvironment();
            // 2. 找到 配置元信息 propertysource
            PropertySource<?> properties = environment.getPropertySources().get(CONFIG_PROPERTIES);
            // 3 .配置 logName, logPath
            String logName = (String) properties.getProperty(LOG_NAME);
            String logPath = (String) properties.getProperty(LOG_PATH);
            if (ObjectUtils.isEmpty(logName)) {
                logName = logPath;
            }
            // 4. 放入MDC, MDC通过修改 log4j2.xml 替换${ctx:logName} 成 logName
            MDC.put("logName", logName);
            MDC.put("logPath", logPath);
        }
    }

    /**
     * 保证这个配置项 在 解析application.yml 之后,  在 xml文件解析前 生效
     * 简单化, 保证自己的log监听器 在springboot 监听器前生效即可.
     * 值越小, 顺序越高,
     * {@link LoggingApplicationListener} getOrder() -> 返回值为 Ordered.HIGHEST_PRECEDENCE + 20 ;
     * 所以 , 我们只需要 返回Ordered.HIGHEST_PRECEDENCE + 19 即可.
     *
     * @return
     */
    @Override
    public int getOrder() {

        return Ordered.HIGHEST_PRECEDENCE + 19;
    }
}
