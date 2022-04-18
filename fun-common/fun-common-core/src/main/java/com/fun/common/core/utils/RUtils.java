package com.fun.common.core.utils;

import com.fun.common.core.domain.R;
import com.fun.common.core.domain.RCode;

/**
 * @program: fun-project
 * @description: R的工具集
 * @author: WhyWhatHow
 * @create: 2022-02-17 14:00
 **/
public class RUtils<T> {
    /**
     * 创建 result 响应对象
     *
     * @param data 响应的数据
     * @param msg  响应消息
     * @param code 响应码
     * @param <T>
     * @return
     */
    public static <T> R create(T data, String msg, Integer code) {
        return new R(code, msg, data);
    }

    /**
     * 请求成功, 返回一个 success  result对象
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R createSucc(T data) {
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
    public static <T> R createFail(Integer code, String msg) {
        return new R(code, msg, null);
    }

    /**
     * 利用ResultCode 构建失败 响应
     * @param code
     * @param <T>
     * @return
     */
    public static <T> R createFail(RCode code) {
        return new R(code, null);
    }

    /**
     * 利用ResultCode , 携带响应数据 构建失败 响应
     * @param code
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R createFail(RCode code,T data) {
        return new R(code, data);
    }


}
