package com.fun.system.api.feign;

import com.fun.common.core.constants.ServiceNamesConstant;
import com.fun.common.core.domain.R;
import com.fun.system.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = ServiceNamesConstant.SERVICE_SYSTEM)
public interface RemoteUserService {

    //Done [whywhathow] [2022/6/7] [must] 打开
    @GetMapping("/user/details/{username}")
//    @Operation(summary = "通过用户名获取用户所有信息", description = "通过用户名获取用户所有信息")
    R<UserInfo> getUserDetailsByUsername(@PathVariable("username") String username
            , @RequestHeader(HttpHeaders.FROM) String headerFrom
    );

    //    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/user/{userId}")
    R getById(@PathVariable("userId") Long userId);


    //    @Operation(description = "通过用户名获取用户权限信息")
    @GetMapping("/user/info/{username}")
    R getUserInfoByUsername(@PathVariable("username") String username);
}
