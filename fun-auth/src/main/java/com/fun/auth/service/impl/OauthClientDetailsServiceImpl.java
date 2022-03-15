package com.fun.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.auth.mapper.OauthClientDetailsMapper;
import com.fun.auth.entity.OauthClientDetails;
import com.fun.auth.service.OauthClientDetailsService;
import org.springframework.stereotype.Service;

/**
 * (OauthClientDetails)表服务实现类
 *
 * @author whywhathow
 * @since 2022-03-15 22:43:13
 */
@Service("oauthClientDetailsService")
public class OauthClientDetailsServiceImpl extends ServiceImpl<OauthClientDetailsMapper, OauthClientDetails> implements OauthClientDetailsService {

}

