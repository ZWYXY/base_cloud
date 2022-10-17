package com.zr.controller;


import com.zr.pojo.po.TestUser;
import com.zr.result.Result;
import com.zr.result.ResultUtil;
import com.zr.service.TestUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.SQLException;

@RestController
public class TransactionController {

    @Resource
    private TestUserService testUserService;

    @GetMapping("insertUser")
    public <T> Result<T>  insertUser() throws SQLException {

        TestUser testUser = new TestUser();
        testUser.setTestUsername("兽族");
        testUser.setTestPassword("orc");
        this.testUserService.insert(testUser);

        return ResultUtil.success();
    }

    @GetMapping("insertUserAndUpdate")
    public <T> Result<T>  insertUserAndUpdate() {
        TestUser testUser = new TestUser();
        this.testUserService.insertAndUpdate(testUser);
        return ResultUtil.success();
    }

}
