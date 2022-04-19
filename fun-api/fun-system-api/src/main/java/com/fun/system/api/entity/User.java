package com.fun.system.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * (User)表实体类
 *
 * @author whywhathow
 * @since 2022-04-08 21:37:30
 */
@EqualsAndHashCode
@Data
@TableName("sys_user")
@Schema(description = "User实体类")
@RequiredArgsConstructor
@SuppressWarnings("serial")
public class User extends BaseEntity {


    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "用户id")
    private Long userId;
    /**
     * 用户登录名
     */
    @NotBlank
    @Schema(description = "用户登录名")
    private String username;
    /**
     * 用户密码
     */
    @Schema(description = "用户密码")
    private String password;
    /**
     * 邮箱
     */
    @Email
    @Schema(description = "邮箱")
    private String email;
    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    private String nickname;
    /**
     * gitee 账号
     */
    @Schema(description = "gitee 账号")
    private String gitee;
    /**
     * github账号
     */
    @Schema(description = "github账号")
    private String github;
    /**
     * 用户头像 uri
     */
    @Schema(description = "用户头像 uri")
    private String avatar;
    /**
     * 手机号码
     */
    @Schema(description = "手机号码")
    private String phone;

    public User(String username) {
        this.username = username;
    }
}
