package com.fun.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fun.system.api.entity.User;
import com.fun.system.api.mapper.UserMapper;
import com.fun.system.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)
 *
 * @author whywhathow
 * @since 2022-04-08 21:53:15
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

