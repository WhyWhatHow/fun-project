package com.fun.system.api.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * (UserRole)表实体类
 *
 * @author whywhathow
 * @since 2022-04-08 21:37:30
 */
@EqualsAndHashCode
@Data
@TableName("sys_user_role")
@Schema(description = "UserRole实体类")
@RequiredArgsConstructor
@SuppressWarnings("serial")
public class UserRole {

    /**
     * userId
     */
    @TableId
    @NotNull
    @Schema(description = "userId")
    private Long userId;
    /**
     * roleId
     */
    @NotNull
    @Schema(description = "roleId")
    private Long roleId;

    public UserRole(Long userId, Long roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }
}
