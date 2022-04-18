package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.Role;
import com.fun.system.api.mapper.RoleMapper;
import com.fun.system.service.RoleMenuService;
import com.fun.system.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * (Role)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:16
 */
@Service("roleService")
@Slf4j
@AllArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    RoleMapper roleMapper;

    RoleMenuService roleMenuService;

    @Override
    public Boolean removeById(Long roleId) {
        boolean res = super.removeById(roleId);
        if (res) {
           res= res && roleMenuService.removeByRoleId(roleId);
        }
        return res;


    }
}

