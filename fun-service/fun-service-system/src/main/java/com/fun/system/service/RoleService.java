package com.fun.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fun.system.api.entity.Role;

/**
 * @author whywhathow
 * @since 2022-04-08 21:53:16
 */
public interface RoleService extends IService<Role> {


    /**
     * 根据roleId删除用户角色, role, role_menu 两张表
     * @param roleId roleId
     * @return true|false
     */
    Boolean removeById(Long roleId);
}

