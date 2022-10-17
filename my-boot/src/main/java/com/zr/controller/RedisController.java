package com.zr.controller;

import com.zr.result.Result;
import com.zr.result.ResultUtil;
import com.zr.service.RedisService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RedisController {

    @Resource
    private RedisService redisService;

    @GetMapping("getValue")
    public Result<String> getKey() {
        return ResultUtil.success(redisService.getString("IDE"));
    }


}
