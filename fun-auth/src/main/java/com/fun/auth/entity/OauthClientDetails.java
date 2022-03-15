package com.fun.auth.entity;


import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fun.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.io.Serializable;
import java.util.Set;

/**
 * (OauthClientDetails)表实体类
 *
 * @author whywhathow
 * @since 2022-03-15 22:43:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "OauthClientDetails实体类")
@SuppressWarnings("serial")
public class OauthClientDetails extends BaseEntity {

    /**
     * 客户端ID
     */
    @TableId
    @ApiModelProperty("客户端ID")
    private String clientId;
    /**
     * 客户端可以访问的资源服务器集合, 以,分割
     */
    @ApiModelProperty("客户端可以访问的资源服务器集合, 以,分割")
    private String resourceIds;
    /**
     * 客户端密码
     */
    @ApiModelProperty("客户端密码")
    private String clientSecret;
    /**
     * 权限范围
     */
    @ApiModelProperty("权限范围")
    private String scope;
    /**
     * 授权码模式:authorization_code密码模式password客户端模式client_credentials隐式授权模式implicit
     */
    @ApiModelProperty("授权码模式:authorization_code密码模式password客户端模式client_credentials隐式授权模式implicit")
    private String authorizedGrantTypes;
    /**
     * 重定向地址
     */
    @ApiModelProperty("重定向地址")
    private String webServerRedirectUri;
    /**
     * client拥有的权限
     */
    @ApiModelProperty("client拥有的权限")
    private String authorities;
    /**
     * access_token 有效时间
     */
    @ApiModelProperty("access_token 有效时间")
    private Integer accessTokenValidity;
    /**
     * refresh_token 有效时间
     */
    @ApiModelProperty("refresh_token 有效时间")
    private Integer refreshTokenValidity;
    /**
     * 预留字段,可以为null, 格式为json
     */
    @ApiModelProperty("预留字段,可以为null, 格式为json")
    private String additionalInformation;
    /**
     * 用户是否自动配置，默认为“false”，可选值为scope属性中定义的值,只用于授权码模式
     */
    @ApiModelProperty("用户是否自动配置，默认为“false”，可选值为scope属性中定义的值,只用于授权码模式")
    private String autoapprove;
    /**
     * 状态位
     */
    @ApiModelProperty("状态位")
    private Integer status;

}
