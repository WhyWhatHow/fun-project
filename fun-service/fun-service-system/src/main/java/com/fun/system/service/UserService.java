package com.fun.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.system.api.dto.UserInfo;
import com.fun.system.api.dto.UserRequest;
import com.fun.system.api.entity.User;

import java.util.List;

/**
 * @author whywhathow
 * @since 2022-04-08 21:53:15
 */
public interface UserService extends IService<User> {
    /**
     * 通过用户名返回用户信息
     * @param username username
     * @return userinfo
     */
    UserInfo getUserInfoByUsername(String username);

    /**
     * 通过用户名返回用户信息
     * @param username username
     * @return userinfo
     */
    Object getUserDetailsByUsername(String username);

    /**
     *  根据用户名获取用户对应的roleIds
     * @param userId  username
     * @return roleIds
     */
    List<Long> selectRoleIdsByUserId(Long userId) ;

    /**
     * 将用户信息存入到db中</br>
     * 1.保存user ->sys_user表中</br>
     * 2.遍历roleIds , 保存userRole-> sys_user_role</br>
     * @param user user
     * @return boolean
     */
    Boolean saveUser(UserRequest user);

    Boolean removeByUserId(Long userId);

    Boolean update(UserRequest user);
}

