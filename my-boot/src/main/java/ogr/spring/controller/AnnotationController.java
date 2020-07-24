package ogr.spring.controller;

import ogr.spring.annotation.LogUserAction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annotationController")
public class AnnotationController {

    @LogUserAction(value = "记录用户行为", module = "用户行为记录",reqUrl = "/annotationController/logUserAction")
    @GetMapping("/logUserAction")
    public String getGetMapping() {
        return "用户行为已被记录" ;
    }
}
