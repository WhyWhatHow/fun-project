package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.RoleMenu;
import com.fun.system.api.mapper.RoleMenuMapper;
import com.fun.system.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * (RoleMenu)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:16
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

}

