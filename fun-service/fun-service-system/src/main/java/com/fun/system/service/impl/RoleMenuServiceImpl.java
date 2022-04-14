package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.RoleMenu;
import com.fun.system.api.mapper.RoleMenuMapper;
import com.fun.system.api.vo.RoleVo;
import com.fun.system.service.RoleMenuService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (RoleMenu)表服务实现类
 *
 * @author whywhathow
 * @since 2022-04-14 17:05:09
 */
@Service("roleMenuService")
@AllArgsConstructor
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Resource
    RoleMenuMapper mapper;


    @Override
    public Boolean saveRoleMenus(RoleVo roleVo) {

        if (ObjectUtils.isEmpty(roleVo.getMenuIds())) {
            return true;
        }

        List<RoleMenu> roleMenus =new ArrayList<>(16);
        roleVo.split().forEach(val->{
            RoleMenu roleMenu =new RoleMenu();
            roleMenu.setRoleId(roleVo.getRoleId());
            roleMenu.setMenuId(val);
        });

        return this.saveBatch(roleMenus);

    }

    @Override
    public Boolean removeByRoleId(Long roleId) {
        return mapper.delByRoleId(roleId);
    }


}

