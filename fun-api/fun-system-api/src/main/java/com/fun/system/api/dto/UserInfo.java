package com.fun.system.api.dto;

import com.fun.system.api.entity.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

/**
 * @program: fun-project
 * @description: userInfo ,roles, menus
 * @author: WhyWhatHow
 * @create: 2022-03-20 14:24
 **/
@Data
@RequiredArgsConstructor
public class UserInfo {
    /**
     * 用户信息
     */
    User user;
    /**
     * 角色信息
     */
    List<Role> roles;
    /**
     * 权限信息
     */
    Set<String> permissions;

}
