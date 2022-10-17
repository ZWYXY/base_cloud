package com.zr.config.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Order(1) // 数字越小优先级越高
@Aspect
//@Component
public class ControllerAspect {

    /*
      注意：举例：A的切面范围比B大，A、B同时存在， B会被优先执行，之后才是A
      由于使用@Around需要放行，放行时会抛出异常，所以在用切面时，最好少使用@Around

     */

    // PointCut A
    // 拦截所有Controller层方法，打印请求参数和返回结果
    @Pointcut("execution(* ogr.spring.controller..*.*(..))")
    public void controllerCut() {}
    @Around("controllerCut()")
    public Object methodAround (ProceedingJoinPoint pjp) throws Throwable {
        System.err.println("package controller around");
        return pjp.proceed();
    }

    // PointCut B
    // 不需要获取注解内容，只对使用了注解的方法进行拦截
    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void gmAnnotationCut() {}
    @Around("gmAnnotationCut()")
    public Object geMethodAround(ProceedingJoinPoint pjp) throws Throwable {
        System.err.println("GetMappring Around");
        return pjp.proceed();
    }

    // PointCut C
    // 需要获取注解内容，并且拦截被注解的方法
    @Pointcut("@annotation(postMapping)")
    public void pmAnnotationCut(PostMapping postMapping) {}
    @Around("pmAnnotationCut(postMapping)")
    public Object pmMethodAround(PostMapping postMapping, ProceedingJoinPoint pjp) throws Throwable {
        System.err.println("PostMapping around");
        System.err.println(Arrays.toString(postMapping.value()));
        return pjp.proceed();
    }



}
