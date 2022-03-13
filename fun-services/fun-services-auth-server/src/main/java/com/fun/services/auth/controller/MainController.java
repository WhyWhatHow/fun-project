package com.fun.services.auth.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fun-project
 * @description:
 * @author: WhyWhatHow
 * @create: 2022-03-13 10:16
 **/
@RestController
@Profile("simple")
public class MainController {
    @GetMapping("/")
    public String  home(){
        return " This is  home page";
    }

    @GetMapping("/admin")
    public String  admin(){
        return " This is  admin page";
    }
    @GetMapping("/user")
    public String  user(){
        return " This is  user page";
    }

}
