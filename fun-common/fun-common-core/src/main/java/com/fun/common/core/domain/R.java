package com.fun.common.core.domain;

import java.io.Serializable;

/**
 * @program: fun-project
 * @description: service统一返回对象 Result ->R
 * @author: WhyWhatHow
 * @create: 2022-02-17 12:58
 **/
public class R<T> implements Serializable {

    /**
     * 响应码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 响应数据
     */
    private T data;


    public R() {
    }

    /**
     * 利用 ResultCode 枚举类创建
     *
     * @param codes
     * @param data
     */
    public R(RCode codes, T data) {
        this.code = codes.code;
        this.msg = codes.msg;
        this.data = data;
    }

    public R(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 创建 result 响应对象
     *
     * @param data 响应的数据
     * @param msg  响应消息
     * @param code 响应码
     * @param <T>
     * @return
     */
    public static <T> R ok(T data, String msg, Integer code) {
        return new R(code, msg, data);
    }

    /**
     * 请求成功, 返回一个 success  result对象
     *
     * @param <T>
     * @return
     */
    public static <T> R ok() {
//        return new R(RCodes.SUCCESS.)
        return new R(RCode.SUCCESS, null);
    }

    /**
     * 请求成功, 返回一个 success  result对象
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R ok(T data) {
//        return new R(RCodes.SUCCESS.)
        return new R(RCode.SUCCESS, data);
    }

    /**
     * 请求响应失败, 创建一个失败请求的result
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> R failed(Integer code, String msg) {
        return new R(code, msg, null);
    }

    /**
     * 利用ResultCode 构建失败 响应
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> R failed(RCode code) {
        return new R(code, null);
    }

    /**
     * 利用ResultCode , 携带响应数据 构建失败 响应
     *
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R failed(RCode code, T data) {
        return new R(code, data);
    }
}
