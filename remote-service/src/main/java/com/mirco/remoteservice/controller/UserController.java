package com.mirco.remoteservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{userId}")
    public Long getUserId(@PathVariable Long userId) {
        return userId + 10086 ;
    }
}
