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
     * 登录相关的错误提示
     */
    ERROR_LOGIN_NOT_EMPTY(510, "账号或密码不能为空"),

    ERROR_LOGIN_LOCK(511, "密码输错次数过多,已被锁定30分钟");

    RCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer code;
    public  String msg;
}
