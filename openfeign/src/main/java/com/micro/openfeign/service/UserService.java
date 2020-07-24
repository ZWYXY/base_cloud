package com.micro.openfeign.service;

import com.micro.openfeign.service.impl.UserServiceFallBackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "remote-service", fallback = UserServiceFallBackImpl.class)
public interface UserService {

    @GetMapping("/user/{userId}")
    Long getUserId(@PathVariable Long userId);

}
