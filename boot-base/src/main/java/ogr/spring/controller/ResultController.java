package ogr.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import ogr.spring.pojo.UserVO;
import ogr.spring.result.Result;
import ogr.spring.result.ResultUtil;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api("测试统一返回结果接口")
@RestController
@RequestMapping("/resultController")
public class ResultController {

    @ApiOperation("直接获取result")
    @GetMapping("/getRes")
    public <T> Result<T> getRes() {
        return ResultUtil.success();
    }

    @ApiOperation("直接获取封装了user信息的result")
    @GetMapping("/getUser")
    public Result<UserVO> getUser(@RequestParam @ApiParam(value = "用户唯一标识", example = "1") Long userId)
    {
        return ResultUtil.success( new UserVO(userId,"h","1234"));
    }

    @PostMapping("/getUserList")
//    @ApiParam("用户信息")
    public Result<List<UserVO>> getUserList(@RequestBody  UserVO userVO) {
        List<UserVO> userVOList = new ArrayList<>();
        userVOList.add(new UserVO(1L,null,"1234"));
        return ResultUtil.success(userVOList);
    }

    // @Api 注解用于类上，表示标识这个类是 swagger 的资源。
    // @ApiOperation 注解用于方法，表示一个 http 请求的操作
    // @ApiParam 注解用于参数上，用来标明参数信息。需要注意的是加上 example 属性
}
