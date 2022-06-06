package com.fun.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: fun-project
 * @description: 测试用
 * @author: WhyWhatHow
 * @create: 2022-05-31 09:22
 **/
@RestController
@Deprecated

public class TestController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        ResponseEntity<String> entity = restTemplate.getForEntity("http://fun-auth/test/sayi?val=remote", String.class);
        return entity.getBody();
    }
}
