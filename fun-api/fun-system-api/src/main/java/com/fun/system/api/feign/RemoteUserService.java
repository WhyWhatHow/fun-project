package com.fun.system.api.feign;

import com.fun.common.core.constants.ServiceNamesConstant;
import com.fun.common.core.domain.R;
import com.fun.system.api.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = ServiceNamesConstant.SERVICE_SYSTEM)
public interface RemoteUserService {

    @GetMapping("/user/details/{username}")
//    @Operation(summary = "通过用户名获取用户所有信息", description = "通过用户名获取用户所有信息")
    R<UserInfo> getUserDetailsByUsername(@PathVariable("username") String username);

    //    @Operation(summary = "通过id查询", description = "通过id查询")
    @GetMapping("/user/{userId}")
    R getById(@PathVariable("userId") Long userId);


    //    @Operation(description = "通过用户名获取用户权限信息")
    @GetMapping("/user/info/{username}")
    public R getUserInfoByUsername(@PathVariable("username") String username);
}
