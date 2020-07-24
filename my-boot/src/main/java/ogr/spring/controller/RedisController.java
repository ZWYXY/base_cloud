package ogr.spring.controller;

import ogr.spring.result.Result;
import ogr.spring.result.ResultUtil;
import ogr.spring.service.RedisService;
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
