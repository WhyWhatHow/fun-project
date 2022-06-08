package com.fun.common.security.utils;

import com.fun.common.security.dto.FunUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Collections;
import java.util.List;

/**
 * @program: fun-project
 * @description: 对SpringSecurityContextHolder的增强.
 * @author: WhyWhatHow
 * @create: 2022-05-26 22:28
 **/
@Slf4j
public class SecurityUtils {


    /**
     * 获取FunUserDetails
     *
     * @return
     */
    public static FunUserDetails getUser() {
        Authentication authentication = getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof FunUserDetails) {
                return (FunUserDetails) principal;
            }
        }
        return null;
    }

    /**
     * @return username
     */
    public static String getUsername() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            return (String) authentication.getPrincipal();
        }
        return null;
    }

    private static Authentication getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            authentication = ((OAuth2Authentication) authentication).getUserAuthentication();
        }
        return authentication;
    }

    public static List getPermissions() {
        Authentication authentication = getAuthentication();
        if (authentication != null) {
            authentication.getAuthorities();
        }
        return Collections.emptyList();
    }

    public static List getRoles() {
        Authentication authentication = getAuthentication();
        authentication.getPrincipal();

        return null;
//        return user.getRoleIds();
    }
}
