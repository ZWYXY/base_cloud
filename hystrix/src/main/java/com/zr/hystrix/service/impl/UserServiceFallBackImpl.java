package com.zr.hystrix.service.impl;


import com.zr.hystrix.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallBackImpl implements UserService {

    @Override
    public Long getUserId(Long userId) {
        return userId;
    }
}
