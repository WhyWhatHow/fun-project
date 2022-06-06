package com.fun.common.core.domain;

/**
 * 响应码 枚举,
 * 参考 {link org.springframework.http.HttpStatus}
 */
public enum RCode {

    /**
     * 规范响应体中的响应码和响应信息
     */
    SUCCESS(200, "操作成功"),

    RESOURCES_NOT_FOUND(404, "资源未找到"),


    VALIDATE_FAILED(501, "参数校验失败"),

    ERROR(502, "未知错误"),

    PARAMENT_ERROR(400, "参数校验未通过"),

    SERVER_EXCEPTION(500, "服务器异常，请稍后再试！"),
    /**
     * 熔断
     */
    FALLBACK(500, "当前服务不可用,请过段时间在尝试!"),

    /**
     * 登录相关的错误提示
     */
    ERROR_LOGIN_NOT_EMPTY(510, "账号或密码不能为空"),

    ERROR_LOGIN_FEIGN(500, "用户登陆失败,请校验日志-feign"),

    CLIENT_INFO_NOT_FOUND(500, "客户端信息不存在||未注册"),
    ERROR_CAPTCHA(510, "验证码校验失败,请重新登录"),

    ERROR_LOGIN_LOCK(511, "密码输错次数过多,已被锁定30分钟"),

    ERROR_LOGIN_FAILED(512, "用户名密码错误"),

    USER_NOT_EXIST(513, "用户不存在"),

    ROLE_NOT_SET_YET(514, "未给当前用户分配角色"),

    ROLE_NOT_EXIST(515, "角色不存在"),


    ;

    RCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code;
    public String msg;
}
