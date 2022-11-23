package com.zr.controller;

import com.zr.pojo.pos.MyConfigBean;
import com.zr.pojo.pos.User;
import org.springframework.web.bind.annotation.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{userId}")
    public Long getUserId(@PathVariable Long userId) {
        return userId + 10086 ;
    }

    @GetMapping("/entity")
    public User getUserEntity() {
        return new User("Hello World!");
    }

    @GetMapping("/config")
    public MyConfigBean getMyConfigBean(@RequestParam("userId") Long userId) throws IOException {

        MyConfigBean myConfigBean = new MyConfigBean();


        Properties properties = new Properties();
        properties.load(
                new FileReader(
                        "C:\\IntelliJProject\\base_cloud\\remote-service\\src\\main\\resources\\a.txt",
                        StandardCharsets.UTF_8));
        myConfigBean.setUsername( (String) properties.get("username"));
        myConfigBean.setPassword( (String) properties.get("password"));

        // 使用utf-8写
        properties.setProperty("username","号子");
        properties.setProperty("password","来了老弟");
        properties.store(
                new FileWriter("C:\\IntelliJProject\\base_cloud\\remote-service\\src\\main\\resources\\a.txt",
                        StandardCharsets.UTF_8),
                null);

        myConfigBean.setUserId(userId);
        return myConfigBean;
    }

}
