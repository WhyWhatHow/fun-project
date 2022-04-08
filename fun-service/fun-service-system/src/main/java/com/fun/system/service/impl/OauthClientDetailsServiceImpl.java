package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.OauthClientDetails;
import com.fun.system.api.mapper.OauthClientDetailsMapper;
import com.fun.system.service.OauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * (OauthClientDetails)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:15
 */
@Service("oauthClientDetailsService")
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService {

}

