package io.github.whywhathow.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: fun-project
 * @description: Http请求的工具类 ,获取 request ,response 信息
 * @author: WhyWhatHow
 * @create: 2022-02-17 16:19
 **/
public class HTTPUtils {
    /**
     * 返回 HttpServletRequest
     * @return
     */
    public static HttpServletRequest getHttpServletRequest(){
        //1. 通过 RequestContextHolder 获取 requestAttributes信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //2. 获取 当前异常信息的请求信息
        HttpServletRequest request = requestAttributes.getRequest();
        return request;
    }

    /**
     *
     * @return HttpServletResponse
     */
    public static HttpServletResponse getHttpServletResponse(){
        //1. 通过 RequestContextHolder 获取 requestAttributes信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        //2. 获取response
        HttpServletResponse response = requestAttributes.getResponse();
        return response;

    }

}
