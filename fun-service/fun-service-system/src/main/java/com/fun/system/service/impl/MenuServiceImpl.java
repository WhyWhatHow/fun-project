package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.Menu;
import com.fun.system.api.mapper.MenuMapper;
import com.fun.system.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (Menu)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:14
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    /**
     * 通过MenuId 查询当前分类下的所有menu
     * @param menuId 菜单id
     * @return
     */
    @Override
    public List<Menu> getById(Integer menuId) {

        return null;
    }
}

