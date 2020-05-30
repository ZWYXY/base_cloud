package ogr.spring.config.aspect;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Aspect
public class ControllerAspect2 {

    /*
      注意：低粒度切面会覆盖高粒度切面   举例：A的切面范围比B大，A、B同时存在，A会被放弃执行只执行B
     */

    // PointCut A
    // 拦截所有Controller层方法，打印请求参数和返回结果
    @Pointcut("execution(* ogr.spring.controller..*.*(..))")
    public void controllerCut() {}
    @Around("controllerCut()")
    public void methodAround () {
        System.err.println("package controller around");
    }

    // PointCut B
    // 不需要获取注解内容，只对使用了注解的方法进行拦截
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void gmAnnotationCut() {}
    @Around("gmAnnotationCut()")
    public void geMethodAround() {
        System.err.println("GetMappring Around");
    }

    // PointCut C
    // 需要获取注解内容，并且拦截被注解的方法
    @Pointcut("@annotation(postMapping)")
    public void pmAnnotationCut(PostMapping postMapping) {}
    @Around("pmAnnotationCut(postMapping)")
    public void pmMethodAround(PostMapping postMapping) {
        System.err.println("PostMapping around");
        System.err.println(Arrays.toString(postMapping.value()));
    }



}
