package io.github.whywhathow.exception;

import io.github.whywhathow.domain.R;
import io.github.whywhathow.domain.RCode;
import io.github.whywhathow.utils.HTTPUtils;
import io.github.whywhathow.utils.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: fun-project
 * @description: 全局异常处理器
 * @author: WhyWhatHow
 * @create: 2022-02-17 15:42
 **/
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务模块异常处理机制
     * 处理业务代码抛出来的异常
     * // TODO: 2022/2/17  异常信息思考 
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
     * 处理业务校验过程中碰到的非法参数异常 该异常基本由{@link org.springframework.util.Assert}抛出
     * // TODO: 2022/2/17  R: code, msg = ex.getmsg()  ,思考一下,当前的code 应该怎么给
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
        log.error("非法参数,ex = {}", ex.getMessage(), ex);
        return RUtils.createFail(RCode.SERVER_EXCEPTION.code, ex.getMessage());
    }

    /**
     * 异常默认处理器
     *
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
        log.error("[Global-Exception]-- 异常信息", ex);
        return RUtils.createFail(RCode.SERVER_EXCEPTION);
    }

}
