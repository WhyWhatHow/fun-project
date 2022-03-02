package com.fun.common.web.apiversion;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: fun-project
 * @description: @ApiVersion对应的RequestCondition
 * @author: WhyWhatHow
 * @create: 2022-02-25 09:46
 **/
@Data
@Slf4j
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {
    double value = 1.0d;
    String API_VERSION = "api-version";

    public ApiVersionRequestCondition(double v) {
        this.value = v;
    }

    /**
     * this:当前类上的ApiVersion,
     * other: 类中方法的ApiVersion.
     *
     * @param other method的ApiVersion
     * @return
     */
    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition other) {

        return other;
    }

    /**
     * // 通过 httpHeader , 或者request 参数获取 api-version, 返回匹配的httpCondition
     *
     * @param request
     * @return
     */
    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {
        //1.  获取 api-version
        String apiVersion = request.getHeader(API_VERSION);
        if (ObjectUtils.isEmpty(apiVersion)) {
            apiVersion = request.getParameter(API_VERSION);
        }
        double reqVersion = this.value;
        //2. 赋值
        if (!ObjectUtils.isEmpty(apiVersion)) {
            reqVersion = Double.parseDouble(apiVersion);
        }
        // 3.根据版本号确定http请求的requestCondition,
        if (this.value <= reqVersion) {
//             this.value =3.0 , request=4 -> 3.0
//             this.value =3.0 , request=2 -> null
            return this;
        }
        return null;


    }

    /**
     * 同方法优先级排序, 降序
     *
     * @param other
     * @param request
     * @return
     */
    @Override
    public int compareTo(ApiVersionRequestCondition other, HttpServletRequest request) {
        log.info("当前版本号{}, other版本号", this.value, other.value);
        return Double.compare(other.getValue(), this.getValue());
    }
}
