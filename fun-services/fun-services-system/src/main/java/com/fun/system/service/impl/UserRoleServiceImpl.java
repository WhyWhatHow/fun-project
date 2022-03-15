package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.mapper.UserRoleMapper;
import com.fun.system.entity.UserRole;
import com.fun.system.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author whywhathow
 * @since 2022-03-15 13:08:15
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}

