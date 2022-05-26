package com.fun.auth.service.impl;

import com.fun.common.core.domain.R;
import com.fun.common.core.domain.RCode;
import com.fun.common.web.exception.ServiceException;
import com.fun.system.api.entity.OauthClientDetails;
import com.fun.system.api.feign.RemoteClientService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: fun-project
 * @description: ClientDetailsService 实现类 -> 用于获取client 信息
 * @author: WhyWhatHow
 * @create: 2022-03-15 22:00
 **/
@Slf4j
@Deprecated
@AllArgsConstructor
public class FunClientDetailServiceImpl implements ClientDetailsService {

    final RemoteClientService remoteClientService;

    AtomicInteger cnt = new AtomicInteger(0);

    /**
     * OPTIMIZE [whywhathow] [2022/3/15]  [opt]  自己实现 数据库查询 -> 结果转换,
     *  添加缓存, 避免client 结果重复
     *  cache [must] : oauth/token 过程中会频繁调用 此方法
     * [warn]
     * <p>
     * 通过clientID 获取client 信息
     *
     * @param clientId 客户端Id
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.warn("[clientUserDetailsService -- ] doing->{}", clientId);

        R res = remoteClientService.getById(clientId);
        cnt.getAndDecrement();

        return createClientDetails(clientId, res);

    }

    private ClientDetails createClientDetails(String clientId, R res) {
        if (res == null || res.getData() == null) {
            throw new ServiceException(RCode.CLIENT_INFO_NOT_FOUND);
        }
        OauthClientDetails clientDetails = (OauthClientDetails) res.getData();
        return (ClientDetails) clientDetails;
    }


}
