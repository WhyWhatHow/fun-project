package com.fun.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: fun-project
 * @description: 后台管理模块启动类
 * @author: WhyWhatHow
 * @create: 2022-03-15 12:54
 **/
@MapperScan({"com.fun.system.api.mapper","com.fun.system.mapper"})
@SpringBootApplication
public class FunSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(FunSystemApplication.class, args);
// TODO [whywhathow] [2022/4/15] [must] 部署时添加skywalking监控 监控平台zero-ali:8899
// -javaagent:agent/skywalking-agent.jar
//-Dskywalking.collector.backend_service=zero-skywalking:11800
//-Dskywalking.agent.service_name=fun-service-system
    }
}
