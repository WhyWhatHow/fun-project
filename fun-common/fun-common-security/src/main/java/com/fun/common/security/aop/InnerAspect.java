package com.fun.common.security.aop;

import com.fun.common.core.constants.InnerConstants;
import com.fun.common.core.utils.HttpUtils;
import com.fun.common.security.annotation.Inner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: fun-project
 * @description: 切面增强 对Inner 注解方法做增强处理
 * @author: WhyWhatHow
 * @create: 2022-06-07 13:31
 **/
@Aspect
@Slf4j
public class InnerAspect {


    @SneakyThrows
    @Around(" @within(inner) || @annotation(inner)")
    public Object aroundInner(ProceedingJoinPoint point, Inner inner) {
        log.info("[inner - annotation - around] ---start --- ");
        if (inner == null) {
            Class<?> aClass = point.getTarget().getClass();
            Inner annotation = AnnotationUtils.findAnnotation(aClass, Inner.class);
        }
//        if (inner != null) {
        HttpServletRequest request = HttpUtils.getHttpServletRequest();
        String header = request.getHeader(HttpHeaders.FROM);

        if (inner.value() && !StringUtils.equals(header, InnerConstants.IN)) {
            log.warn("[access - denied!] --> uri: ->{}", request.getRequestURI());
            throw new AccessDeniedException("no authority !");
//            }
        }

        return point.proceed();
    }
}
