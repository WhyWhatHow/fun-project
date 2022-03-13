package com.fun.services.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-03-12 15:03
 **/
// test-01:
//  yaml 配置测试:
    // curl test:test@localhost:9999/oauth/token -dgrant_type=client_credentials -dscope=any
    //        {"access_token":"7f6d814c-37f5-4bbb-acf5-dba832fcbe35","token_type":"bearer","expires_in":43199,"scope":"any"}
//@SpringBootApplication
//@Profile("minimal")
//@EnableAuthorizationServer //  开启授权服务器功能
public class AuthServerApplication {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        String admin = encoder.encode("admin");
        String user = encoder.encode("user");
        String encode = encoder.encode("client-for-server");
        System.out.println(encode);
        System.out.println(admin);
        System.out.println(user);
//        SpringApplication.run(AuthServerApplication.class, args);


    }
}
