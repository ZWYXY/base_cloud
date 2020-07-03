package com.micro.openfeign.service;

import com.micro.openfeign.service.impl.UserServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "remote-service", fallback = UserServiceFallBackImpl.class)
public interface UserService {

    @GetMapping("/user/getUserId")
    Long getUserId();

}
