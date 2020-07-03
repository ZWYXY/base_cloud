package com.micro.openfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "remote-service")
public interface UserService {

    @GetMapping("/user/getUserId")
    Long getUserId();

}
