package com.zr.hystrix.controller;

import com.zr.hystrix.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/rpc")
public class RPCController {

    @Resource
    private UserService userService;

    @GetMapping("/{userId}")
    public Long getUserId(@PathVariable Long userId) {
        return userService.getUserId(userId);
    }


}
