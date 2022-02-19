package com.fun.common.web.exception;

import com.fun.common.core.domain.R;
import com.fun.common.core.domain.RCode;
import com.fun.common.core.utils.RUtils;
import com.fun.common.web.utils.HTTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: fun-project
 * @description: SpringMVC 全局异常处理器
 * @author: WhyWhatHow
 * @create: 2022-02-17 15:42
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * validation exception
     * 处理request  get 请求
     * 触发条件, Controller 类上添加@validated注解, 以及method  parameter 中添加 约束
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R handleConstraintViolationException(ConstraintViolationException ex) {
        log.warn("[ConstraintViolationException ]- 异常信息{}", ex);
        return RUtils.createFail(RCode.PARAMENT_ERROR);
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
     * 处理 spring boot 参数校验失败
     * // TODO: 2022/2/18  需要是全部的参数校验失败的信息么,是不是可以只返回第一个失败的参数校验信息?
     * 1. 将校验失败的参数信息 组装成 data 返回给 client
     * 2. 日志记录 ( 级别, 不应该是error,应该是warning,info
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public R handleBindException(BindException e) {
        log.info("[BindException]- 捕获到参数校验异常信息 ->{}", e);
        List<String> list = e.getBindingResult().getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.toList());
        return RUtils.createFail(RCode.PARAMENT_ERROR, list);
    }

    /**
     * 业务模块异常处理机制
     * 处理业务代码抛出来的异常
     * // TODO: 2022/2/17  异常信息思考
     *
     * @param e 业务模块的异常类
     * @return 请求调用失败的返回结果
     */
    @ExceptionHandler(ServiceException.class)
    public R handleServiceException(ServiceException e) {
        log.error("[Service-Exception]-- 捕获到业务模块异常信息!");
        log.error("[service-exception]-- 异常信息{}", e.getStackTrace());
        return RUtils.createFail(e.getCode(), e.getMsg());
    }

    /**
     * // TODO: 2022/2/17  R: code, msg = ex.getmsg()  ,思考一下,当前的code 应该怎么给
     * * consider: 是不是可以参考 {@link HttpStatus}
     * 处理业务校验过程中碰到的非法参数异常 该异常基本由{@link org.springframework.util.Assert}抛出
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
     * 异常默认处理器
     * 处理所有的异常信息,maybe , 把runtimeException, 以及一般的runtimeException区分开会好一点
     * @param ex 异常
     * @return
     */
    @ExceptionHandler
    public R handleThrowable(Throwable ex) {
        //1. 获取request
        HttpServletRequest request = HTTPUtils.getHttpServletRequest();
        //2. 将当前request的url 记录到日志中
        String url = request.getRequestURI().toString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.error("[Global-Exception]--捕获到异常信息");
        log.error("[Global-Exception]-- 请求URL为 {}", url);
        log.error("[Global-Exception]-- 请求参数为{}", parameterMap);
        log.error("[Global-Exception]-- 异常信息->{}", ex);
        return RUtils.createFail(RCode.SERVER_EXCEPTION);
    }

}
