package com.zr.cloud.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class DemoController {

    @GetMapping("/1")
    public String test1() {
        //
        return "123";
    }
}
