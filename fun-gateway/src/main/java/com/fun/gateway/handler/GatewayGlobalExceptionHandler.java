package com.fun.gateway.handler;

import com.fun.common.core.domain.R;
import com.fun.common.core.domain.RCode;
import com.fun.common.core.utils.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: fun-project
 * @description: SpringMVC 全局异常处理器
 * @author: WhyWhatHow
 * @create: 2022-02-17 15:42
 **/
@RestControllerAdvice
@Slf4j
public class GatewayGlobalExceptionHandler {
    /**
     * validation exception
     * 处理request  get 请求
     * 触发条件, Controller 类上添加@validated注解, 以及method  parameter 中添加 约束
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn("[ConstraintViolationException ]- 异常信息{}", ex);
        return RUtils.createFail(RCode.PARAMENT_ERROR.code,ex.getMessage());
    }

    /**
     * 参数校验异常处理
     * post ,put  request 异常处理
     * 触发条件:  post method  中parameter 添加 @validated||@valid 注解 并对DTO对象添加约束条件
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.warn("[Method Argument Not Valid Exception] - 捕获到异常信息{}", ex);
        List<String> list = ex.getBindingResult().getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        return RUtils.createFail(RCode.PARAMENT_ERROR, list);
    }


    /**
     * // TODO: 2022/2/17  R: code, msg = ex.getmsg()  ,思考一下,当前的code 应该怎么给
     * * consider: 是不是可以参考 {@link HttpStatus}
     * 处理业务校验过程中碰到的非法参数异常 该异常基本由{@link Assert}抛出
     *
     * @param ex 参数校验异常
     * @return API返回结果对象包装后的错误输出结果
     * @see Assert#hasLength(String, String)
     * @see Assert#hasText(String, String)
     * @see Assert#isTrue(boolean, String)
     * @see Assert#isNull(Object, String)
     * @see Assert#notNull(Object, String)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.OK)
    public R handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("[Service-Exception]--非法参数,ex = {}", ex.getMessage(), ex);
        return RUtils.createFail(RCode.SERVER_EXCEPTION.code, ex.getMessage());
    }

    /**
     * 业务模块异常处理机制
     * 处理业务代码抛出来的异常
     * @param e 业务模块的异常类
     * @return 请求调用失败的返回结果
     */
    @ExceptionHandler(RuntimeException.class)
    public R handleServiceException(RuntimeException e) {
        log.error("[Service-Exception]-- 捕获到业务模块异常信息!");
        log.error("[service-exception]-- 异常信息{}", e);
        return RUtils.createFail(RCode.ERROR_CAPTCHA.code,e.getMessage());
    }


}
