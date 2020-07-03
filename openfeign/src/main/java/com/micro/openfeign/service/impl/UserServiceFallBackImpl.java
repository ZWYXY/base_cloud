package com.micro.openfeign.service.impl;


import com.micro.openfeign.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallBackImpl implements UserService {

    @Override
    public Long getUserId() {
        return -100L;
    }
}
