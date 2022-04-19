package com.fun.system.api.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * (OauthClientDetails)表实体类
 *
 * @author whywhathow
 * @since 2022-04-08 21:37:30
 */
@EqualsAndHashCode
@Data
@TableName("sys_oauth_client_details")
@Schema(description = "OauthClientDetails实体类")
@SuppressWarnings("serial")
public class OauthClientDetails extends BaseEntity {

    /**
     * 客户端ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "客户端ID")
    private String clientId;
    /**
     * 客户端可以访问的资源服务器集合, 以,分割
     */
    @NotBlank
    @Schema(description = "客户端可以访问的资源服务器集合, 以,分割")
    private String resourceIds;
    /**
     * 客户端密码
     */
    @NotBlank
    @Schema(description = "客户端密码")
    private String clientSecret;
    /**
     * 权限范围
     */
    @NotBlank
    @Schema(description = "权限范围")
    private String scope;
    /**
     * 授权码模式:authorization_code密码模式password客户端模式client_credentials隐式授权模式implicit
     */
    @NotBlank
    @Schema(description = "授权码模式:authorization_code密码模式password客户端模式client_credentials隐式授权模式implicit")
    private String authorizedGrantTypes;
    /**
     * 重定向地址
     */
    @NotBlank
    @Schema(description = "重定向地址")
    private String webServerRedirectUri;
    /**
     * client拥有的权限
     */
    @NotBlank
    @Schema(description = "client拥有的权限")
    private String authorities;
    /**
     * access_token 有效时间
     */
    @Schema(description = "access_token 有效时间")
    private Integer accessTokenValidity;
    /**
     * refresh_token 有效时间
     */
    @Schema(description = "refresh_token 有效时间")
    private Integer refreshTokenValidity;
    /**
     * 预留字段,可以为null, 格式为json
     */
    @Schema(description = "预留字段,可以为null, 格式为json")
    private String additionalInformation;
    /**
     * 用户是否自动配置，默认为“false”，可选值为scope属性中定义的值,只用于授权码模式
     */
    @Schema(description = "用户是否自动配置，默认为“false”，可选值为scope属性中定义的值,只用于授权码模式")
    private String autoapprove;
}
