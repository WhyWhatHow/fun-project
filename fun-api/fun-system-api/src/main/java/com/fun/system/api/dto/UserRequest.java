package com.fun.system.api.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.fun.system.api.entity.User;
import com.fun.system.api.entity.UserRole;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: fun-project
 * @description: user request请求的dto
 * @author: WhyWhatHow
 * @create: 2022-04-18 22:22
 **/
@Data
@RequiredArgsConstructor
public class UserRequest extends User {
    /**
     * 角色信息
     */
    List<Long> rolesId;
    /**
     * 新密码
     */
    String newPwd;

    public UserRequest(String username) {
        super(username);
    }

    /**
     *  判断rolesId 是否非空
     * @return boolean
     */
    public boolean isEmptyRoleIds() {
        return CollectionUtil.isEmpty(rolesId);
    }


    /**
     * 遍历rolesId 创建 userRoles
     *
     * @return userRoles
     */
    public List<UserRole> getUserRoles() {
        Long userId = super.getUserId();
        if (CollectionUtil.isNotEmpty(rolesId)) {
            List<UserRole> collect = rolesId.stream()
                    .map(roleId -> {
                        return new UserRole(userId, roleId);
                    }).collect(Collectors.toList());
            return collect;
        }
        return Collections.emptyList();
    }

}
