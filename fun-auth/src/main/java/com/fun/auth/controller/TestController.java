package com.fun.auth.controller;

import com.fun.common.core.domain.R;
import com.fun.system.api.dto.UserInfo;
import com.fun.system.api.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: fun-project
 * @description: 测试controller .
 * @author: WhyWhatHow
 * @create: 2022-05-24 22:39
 **/
@Deprecated
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    UserDetailsService userDetailsService;

    @GetMapping("/client/{clientId}")
    public R test(@PathVariable("clientId") String clientId) {
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);

        return R.ok(clientDetails);
    }

    @GetMapping("/user/{username}")
    public R testUser(@PathVariable("username") String username) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return R.ok(userDetails);
    }

    @Autowired
    RemoteUserService remoteUserService;

    @GetMapping("/user/info/{username}")
    public R testRemoteUser(@PathVariable("username") String username) {
        R res = remoteUserService.getUserInfoByUsername(username);
        UserInfo info = (UserInfo) res.getData();
        return R.ok(info);
    }

    @GetMapping("/user/details/{username}")
    public R<UserInfo> testRemoteUserDetails(@PathVariable("username") String username) {
        R<UserInfo> res = remoteUserService.getUserDetailsByUsername(username);
        UserInfo info = res.getData();
        return R.ok(info);

    }
}
