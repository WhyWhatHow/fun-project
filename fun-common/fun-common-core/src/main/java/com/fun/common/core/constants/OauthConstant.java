package com.fun.common.core.constants;

/**
 *
 */
public interface OauthConstant {

    String ACCESS = "access";
    /**
     * implicit 模式
     */
    String GRANT_IMPLICIT = "implicit";
    /**
     * password 授权模式
     */
    String GRANT_PASSWORD = "password";

    /**
     * 客户端模式
     */
    String CLIENT_CREDENTIALS = "client_credentials";

    /**
     * 用户信息
     */
    String DETAILS_USER = "user_info";

    /**
     * 协议字段
     */
    String DETAILS_LICENSE = "license";

    /**
     * project license
     */
    String PROJECT_LICENSE = "made by [whywhathow.fun@gmail.com]";
    /**
     * 验证码有效期,默认 60秒
     */
//    long CODE_TIME = 60;

    /**
     * 验证码长度
     */
//    String CODE_SIZE = "6";


    /**
     * 客户端ID
     */
    String CLIENT_ID = "clientId";

}



interface SecurityConstants {

    /**
     * 角色前缀
     */
    String ROLE = "ROLE_";

    /**
     * 前缀
     */
    String PROJECT_PREFIX = "fun_";

    /**
     * 项目的license
     */
    String PROJECT_LICENSE = "made by whywhathow";

    /**
     * 内部
     */
    String FROM_IN = "Y";

    /**
     * 标志
     */
    String FROM = "from";

    /**
     * 默认登录URL
     */
    String OAUTH_TOKEN_URL = "/oauth/token";

    /**
     * grant_type
     */
    String REFRESH_TOKEN = "refresh_token";

    /**
     * 手机号登录
     */
    String APP = "app";

    /**
     * {bcrypt} 加密的特征码
     */
    String BCRYPT = "{bcrypt}";

    /**
     * sys_oauth_client_details 表的字段，不包括client_id、client_secret
     */
    String CLIENT_FIELDS = "client_id, CONCAT('{noop}',client_secret) as client_secret, resource_ids, scope, "
            + "authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, "
            + "refresh_token_validity, additional_information, autoapprove";

    /**
     * JdbcClientDetailsService 查询语句
     */
    String BASE_FIND_STATEMENT = "select " + CLIENT_FIELDS + " from sys_oauth_client_details";

    /**
     * 默认的查询语句
     */
    String DEFAULT_FIND_STATEMENT = BASE_FIND_STATEMENT + " order by client_id";

    /**
     * 按条件client_id 查询
     */
    String DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where client_id = ?";

    /***
     * 资源服务器默认bean名称
     */
    String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";


}

