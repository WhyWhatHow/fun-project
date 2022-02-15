package io.github.whywhathow.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-02-15 20:13
 **/
@RestController
public class DemoController {
    @GetMapping("/test")
    public String test(String name) {
        return " hello, fun_project" + name;
    }

    @Value("${com.name}")
    String name;

    @GetMapping("/mult/env")
    public String testMultEnv() {
        return " now in " + name;
    }
}
