package com.zr.controller;

import com.zr.pojo.UserVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/json")
public class JsonController {

    @GetMapping("/user")
    public UserVO getUser() {
        return new UserVO(1L, "hah", "123");
    }

    @GetMapping("/list")
    public List<UserVO> getUserList() {
        List<UserVO> list = new ArrayList<>();
        list.add(new UserVO(1L,"h",null)) ;
        list.add(new UserVO(1L,"h","1234")) ;
        list.add(new UserVO(1L,"h","1234")) ;
        list.add(new UserVO(1L,"h","1234")) ;
        list.add(new UserVO(1L,"h","1234")) ;
        list.add(new UserVO(1L,"h","1234")) ;
        return list;
    }

    @GetMapping("/map")
    public Map<String, UserVO> getMap() {
        Map<String, UserVO> map = new HashMap<>();
        map.put("oneUser", new UserVO(1L, "hah", "123"));
        return map;
    }




}
