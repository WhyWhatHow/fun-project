package com.fun.system.api.feign;

import com.fun.common.core.constants.ServiceNamesConstant;
import com.fun.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.constraints.NotBlank;

@FeignClient(contextId = "remoteClientService", value = ServiceNamesConstant.SERVICE_SYSTEM)
public interface RemoteClientService {

    /**
     * 通过clientId查询oauthClientDetails
     *
     * @param clientId id
     * @return R
     */
    @GetMapping("/client/{clientId}")
    R getById(@PathVariable("clientId") @NotBlank String clientId, @RequestHeader(HttpHeaders.FROM) String from);
}
