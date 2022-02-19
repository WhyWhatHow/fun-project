package com.fun.common.web.valid.handler;

import com.fun.common.web.valid.annotation.FunValid;

public interface FunValidHandler<T> {
    /**
     * 基于spring validation 的二次开发, 实现参数校验,
     * 类,method, parameter 参数校验的核心逻辑.
     * Ps: 你只需要实现此方法,就可以实现 自定义参数校验
     * 与SpringValidation 对比: 省略了自定义注解的过程.
     *
     * @param value
     * @param funValid
     * @return
     */
    boolean valid(T value, FunValid funValid);

}
