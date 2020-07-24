package com.micro.openfeign.controller;

import com.micro.openfeign.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/openFeign")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/{userId}")
    public Long getUserId(@PathVariable Long userId) {
        return userService.getUserId(userId);
    }

}
