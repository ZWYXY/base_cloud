package ogr.spring.controller;

import ogr.spring.pojo.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AnnotationController")
public class AnnotationController {

    @GetMapping("/get")
    public String getGetMapping() {
        return "getGetMapping" ;
    }

    @PostMapping("/post")
    public String postMapping(@Validated UserVO userVO) {
        return "/postMapping";
    }
}
