package com.fun.system.service.impl;

import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.Menu;
import com.fun.system.api.mapper.MenuMapper;
import com.fun.system.service.MenuService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * (Menu)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:14
 */
@Service("menuService")
@Slf4j
@AllArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    MenuMapper mapper;

    /**
     * TODO [whywhathow] [2022/4/14]  [opt] mysql 递归查询, 需要添加缓存来处理
     * <p>
     * 通过MenuId查询当前分类下的所有menu
     *
     * @param menuId 菜单id
     * @return
     */
    @Override
    public Menu getById(Integer menuId) {
        return null;
    }


    /**
     * 根据roleId 查询对应的menuIds
     *
     * @param roleId roleId
     * @return menuIds
     */
    //TODO [whywhathow] [2022/4/14] [must] 缓存添加  hint:需要考虑缓存失效问题
    @Override
    public List selectByRoleId(Long roleId) {
        List<Menu> menus = mapper.selectByRoleId(roleId);
        if (CollectionUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }
        return menus.stream().map(Menu::getMenuId).collect(Collectors.toList());
    }

    @Override
    public List menuTree(Integer parentId) {
        if (parentId == null) {
            parentId = -1;
        }
        List<Menu> menus = getBaseMapper().selectList(Wrappers.emptyWrapper());
        if (CollectionUtils.isEmpty(menus)) {
            return Collections.emptyList();
        }
        return buildMenuTree(menus, parentId);

    }

    @Override
    public Boolean removeByMenuId(Integer menuId) {
        return mapper.updateByMenuId(menuId);
    }

    @Override
    public Set<Menu> listByRoleId(Long roleId) {
        return mapper.listByRoleId(roleId);
    }

    private List buildMenuTree(List<Menu> menus, Integer parentId) {
        List<TreeNode<Integer>> collect = new ArrayList<>(menus.size());
        menus.forEach(menu -> {
            if (menu.isAlive()) {
                TreeNode node = new TreeNode<>();
                node.setId(menu.getMenuId());
                node.setParentId(menu.getParentId());
                node.setName(menu.getName());
                node.setWeight(menu.getSortOrder());
                Map<String, Object> map = new HashMap<>();
                map.put("permission", menu.getPermission());
                map.put("icon", menu.getIcon());
                map.put("keepAlive", menu.getKeepAlive());
                map.put("path", menu.getPath());
                map.put("type", menu.getType());
                node.setExtra(map);
                collect.add(node);
            }
        });
        return TreeUtil.build(collect, parentId);
    }


}

