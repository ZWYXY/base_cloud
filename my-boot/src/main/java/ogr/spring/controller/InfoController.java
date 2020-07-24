package ogr.spring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "初次使用测试用例")
public class InfoController {

    @ApiOperation(value = "测试spring-boot", notes = "哈哈")
    @ApiImplicitParam(name = "args", value = "随便输入点啥···")
    @GetMapping("/dealInfo")
    public String dealInfo(String args) {
        System.err.println("deal....");
        return "dealResult:" + args;
    }
    @ApiOperation(value = "随便点", notes = "嘻嘻")
    @GetMapping("/dealInfo1")
    public String dealInfo1() {
        System.err.println("some info");
        return "info";
    }

}
