package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.UserRole;
import com.fun.system.api.mapper.UserRoleMapper;
import com.fun.system.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:14
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

