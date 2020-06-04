package ogr.spring.controller;


import ogr.spring.pojo.po.TestUser;
import ogr.spring.result.Result;
import ogr.spring.result.ResultUtil;
import ogr.spring.service.TestUserService;
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

}
