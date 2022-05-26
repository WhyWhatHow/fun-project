package com.fun.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.common.core.domain.RCode;
import com.fun.common.web.exception.ServiceException;
import com.fun.system.api.dto.UserInfo;
import com.fun.system.api.dto.UserRequest;
import com.fun.system.api.entity.User;
import com.fun.system.api.mapper.UserMapper;
import com.fun.system.api.mapper.UserRoleMapper;
import com.fun.system.service.MenuService;
import com.fun.system.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (User)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:15
 */
@Slf4j
@AllArgsConstructor
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    final UserMapper userMapper;
    final MenuService menuService;
    //TODO [whywhathow] [2022/4/18]  [opt]  think 是否需要 创建 userRoleService 用来实现
    final UserRoleMapper userRoleMapper;


    /**
     * TODO [whywhathow] [2022/4/11] [must] 添加缓存
     * 根据用户名获取用户基本信息, 权限信息, 角色信息</br>
     * 1. 根据用户名获取用户信息</br>
     * 2. 根据用户名获取roleIds</br>
     * 3. 根据roleId获取权限信息</br>
     * </br>
     * 不选择多表查询的原因:</br>
     * 1.写性能: 多表查询锁表, 其他用户不能执行表修改操作.</br>
     * 2.扩展性: 多表查询不利于后期维护,此项目中可以忽略掉</br>
     * 3.读性能: 多表查询读性能+缓存与join查询大致相当
     *
     * @param username 用户名, 也可以是手机号
     * @return UserInfo
     */
////
    @Override
    public UserInfo getUserInfoByUsername(String username) {

        User user = userMapper.selectOne(new QueryWrapper(new User(username)));

        if (user == null) {
            throw new ServiceException(RCode.USER_NOT_EXIST);
        }
        UserInfo info = new UserInfo();
        info.setUser(user);
        List<Long> roleIds = selectRoleIdsByUserId(user.getUserId());

        if (roleIds.isEmpty()) {
            throw new ServiceException(RCode.ROLE_NOT_SET_YET);
        }

        info.setRolesId(roleIds);

        Set<String> permissions = roleIds.stream()
                .map(roleId -> menuService.listByRoleId(roleId))
                .flatMap(Collection::stream)
                .filter(menu -> menu.isButton())
                .map(menu -> menu.getPermission())
                .collect(Collectors.toSet());
        info.setPermissions(permissions);
        return info;
    }

    @Override
    public List<Long> selectRoleIdsByUserId(Long userId) {
        List<Long> roleIds = userRoleMapper.selectRoleIdsByUserId(userId);
        return roleIds;
    }

    @Override
    public Boolean saveUser(UserRequest user) {
        boolean res = false;

        res = this.save(user);

        res = updateUserRoleTable(user, res);
        return res;
    }

    /**
     * 更新sys_user_role 关系表
     *
     * @param user user_role
     * @param res  res
     * @return res
     */
    private boolean updateUserRoleTable(UserRequest user, boolean res) {
        if (!user.isEmptyRoleIds()) {
            int batch = userRoleMapper.insertBatch(user.getUserRoles());
            res = batch > 0;
        }
        return res;
    }

    @Override
    public UserInfo getUserDetailsByUsername(String username) {
        return this.getUserInfoByUsername(username);
    }

    /**
     * 根据user删除user
     * 用户表假删除
     * user_role表真删除
     *
     * @param userId userid
     * @return boolean
     */
    @Override
    public Boolean removeByUserId(Long userId) {
        boolean res = super.removeById(userId);
        if (res) {
            res = res && userRoleMapper.delByUserId(userId);
        }
        return res;


    }

    /**
     * 更新用户信息
     * 1. 用户密码修改
     * 2. roleIds 是否不为空 , 不为空的话, 先删除, 后插入实现role角色更新.
     * todo
     *
     * @param user
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean update(UserRequest user) {
        boolean res = false;
        String newPwd = user.getNewPwd();
        if (!ObjectUtils.isEmpty(newPwd)) {
            user.setPassword(newPwd);
        }
        res = super.updateById(user);
        res = updateRoleId(user);
        return res;

    }

    /**
     * 更新 sys_user_role 表, 先del,在insert
     * TODO [whywhathow] [2022/4/19] [opt] 缓存添加, 避免对sys_user_role表进行重复的插入删除操作.
     *
     * @param user user
     * @return boolean
     */
    @Transactional(rollbackFor = Exception.class)
    boolean updateRoleId(UserRequest user) {
        if (!user.isEmptyRoleIds()) {
            boolean res = userRoleMapper.delByUserId(user.getUserId());
            res = res && userRoleMapper.insertBatch(user.getUserRoles()) > 0;
            return res;
        }
        return true;
    }
}

