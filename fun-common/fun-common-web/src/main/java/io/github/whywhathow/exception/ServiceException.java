package io.github.whywhathow.exception;

import io.github.whywhathow.domain.RCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: fun-project
 * @description: 业务模块 -- 异常类
 * @author: WhyWhatHow
 * @create: 2022-02-17 15:48
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException {
    public Integer code;
    public String msg;

    public ServiceException(RCode code) {
        this.code = code.code;
        this.msg = code.msg;
    }

}
