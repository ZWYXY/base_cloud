package com.zr.hystrix.service;

import com.zr.hystrix.service.impl.UserServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "remote-service", fallback = UserServiceFallBackImpl.class)
public interface UserService {

    @GetMapping("/user/{userId}")
    Long getUserId(@PathVariable Long userId);

}
