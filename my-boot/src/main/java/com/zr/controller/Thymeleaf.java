package com.zr.controller;

import com.zr.pojo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
public class Thymeleaf {

    @GetMapping("/notFoundPage")
    public String _404NotFound() {
        return "404";
    }
    @GetMapping("/internalError")
    public String _internalError() {
        return "500";
    }
    @GetMapping("/getHh")
    public String getSomething(Model model) {
        UserVO ss = new UserVO(1L, "hh", "123", new String[]{"11"} );
        model.addAttribute("ss",ss);
        return "hhh";
    }




}
