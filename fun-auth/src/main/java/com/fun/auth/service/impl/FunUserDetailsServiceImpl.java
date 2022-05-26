package com.fun.auth.service.impl;

import com.fun.auth.dto.FunGrantedAuthority;
import com.fun.auth.dto.FunUserDetails;
import com.fun.common.core.domain.R;
import com.fun.common.core.domain.RCode;
import com.fun.common.web.exception.ServiceException;
import com.fun.system.api.dto.UserInfo;
import com.fun.system.api.entity.User;
import com.fun.system.api.feign.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: fun-project
 * @description: userDetailServices 实现类
 * @author: WhyWhatHow
 * @create: 2022-03-18 10:03
 **/
@Service
@Slf4j
@Primary
public class FunUserDetailsServiceImpl implements UserDetailsService {

    final RemoteUserService remoteUserService;

    public FunUserDetailsServiceImpl(RemoteUserService userService) {
        this.remoteUserService = userService;
    }

    //    TODO [whywhathow] [24/5/2022] [must]  添加缓存,(redis && 本地缓存)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        R res;
        try {
            res = Optional.ofNullable(remoteUserService.getUserDetailsByUsername(username)).get();
        } catch (Exception e) {
            log.error("[Login-Feign]: 通过用户名获取用户信息失败, 请检查provider 端是否正确注入到nacos中");
            throw new ServiceException(RCode.ERROR_LOGIN_NOT_EMPTY);
        }
        UserDetails userDetails = createUserDetails(username, res);
        return userDetails;
    }

    /**
     * 返回 userDetails 对象.
     *
     * @param res result
     * @return
     */
    private UserDetails createUserDetails(String username, R res) {
        if (res == null) {
            throw new UsernameNotFoundException(username + " : 用户信息未找到,请校验用户信息");
        }
        UserInfo info = (UserInfo) res.getData();
        User user = info.getUser();
        Set<String> permissions = info.getPermissions();
//        Collection<? extends GrantedAuthority> authorities = permissions.
        List<FunGrantedAuthority> authorities = permissions.stream().map(obj -> {
            return new FunGrantedAuthority(obj);
        }).collect(Collectors.toList());

        return new FunUserDetails(user.getUsername(), user.getPassword(), authorities, user.getUserId(), user.getEmail(), user.getPhone());
    }
}
