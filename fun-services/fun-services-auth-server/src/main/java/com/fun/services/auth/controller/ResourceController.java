package com.fun.services.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-03-13 10:18
 **/
@Profile("simple")
@RestController
@Slf4j
public class ResourceController {

    @GetMapping("/me")
    public Principal me(Principal principal){
        log.warn("[/me]->{}",principal.toString());
        return  principal;
    }

}
