package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.Role;
import com.fun.system.api.mapper.RoleMapper;
import com.fun.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * (Role)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:16
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

