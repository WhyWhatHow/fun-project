package io.github.whywhathow.controller;

import io.github.whywhathow.domain.R;
import io.github.whywhathow.utils.RUtils;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DemoController {
    @GetMapping("/test")
    public R test(String name) {
        name = " hello, fun_project" + name;
        log.info("[/test/]" + name);
        return RUtils.createSucc(name);
    }

    @Value("${com.name}")
    String name;

    @GetMapping("/mult/env")
    public R testMultEnv() {
        name = " now in " + name;
        return RUtils.createSucc(name);
    }
}
