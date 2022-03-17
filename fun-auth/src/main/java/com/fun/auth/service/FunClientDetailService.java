package com.fun.auth.service;

import com.fun.auth.entity.OauthClientDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @program: fun-project
 * @description: OauthClientDetails 实现类
 * @author: WhyWhatHow
 * @create: 2022-03-15 22:00
 **/
@Slf4j
public class FunClientDetailService implements ClientDetailsService {

    @Autowired
    OauthClientDetailsService service;

    /**
     * OPTIMIZE [whywhathow] [2022/3/15]  [opt]  自己实现 数据库查询 -> 结果转换,
     * [warn]
     * <p>
     * 通过clientID 获取client 信息
     *
     * @param clientId
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.warn("[clientUserDetailsService -- ] doing->{}", clientId);
        OauthClientDetails clientDetails = service.getById(clientId);
        return (ClientDetails) clientDetails;
    }

}
