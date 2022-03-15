package com.fun.system.entity;


import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (User)表实体类
 * 
 * @author whywhathow
 * @since 2022-03-15 13:08:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "User实体类")
@SuppressWarnings("serial")
public class User extends  BaseEntity {
    
            /**
        * 用户id
        */
                        @TableId
                @ApiModelProperty("用户id")
        private Long userId;
                 /**
        * 用户登录名
        */
                        @ApiModelProperty("用户登录名")
        private String username;
                 /**
        * 用户密码
        */
                        @ApiModelProperty("用户密码")
        private String password;
                 /**
        * 邮箱
        */
                        @ApiModelProperty("邮箱")
        private String email;
                 /**
        * 用户昵称
        */
                        @ApiModelProperty("用户昵称")
        private String nickname;
                 /**
        * gitee 账号
        */
                        @ApiModelProperty("gitee 账号")
        private String gitee;
                 /**
        * github账号
        */
                        @ApiModelProperty("github账号")
        private String github;
                         /**
        * 状态位
        */
                        @ApiModelProperty("状态位")
        private Integer status;
         }
