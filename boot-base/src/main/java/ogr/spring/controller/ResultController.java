package ogr.spring.controller;

import ogr.spring.pojo.UserVO;
import ogr.spring.result.Result;
import ogr.spring.result.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resultController")
public class ResultController {

    @GetMapping("/getRes")
    public <T> Result<T> getRes() {
        return ResultUtil.success();
    }

    @GetMapping("/getUser")
    public Result<UserVO> getUser() {
        return ResultUtil.success( new UserVO(1L,"h","1234"));
    }

    @GetMapping("/getUserList")
    public Result<List<UserVO>> getUserList() {
        List<UserVO> userVOList = new ArrayList<>();
        userVOList.add(new UserVO(1L,null,"1234"));
        return ResultUtil.success(userVOList);
    }
}
