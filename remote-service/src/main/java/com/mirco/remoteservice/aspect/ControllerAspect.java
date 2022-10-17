package com.mirco.remoteservice.aspect;


import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class ControllerAspect {


    // 构建切入点
    @Pointcut("execution( public * com.mirco.remoteservice.controller..*.*(..) )")
    public void pointCut() {}

    // 简单记录请求信息
    @Before("pointCut()")
    public void printReqInfo(JoinPoint joinPoint) {

        // 获取 HttpServletRequest
        HttpServletRequest request = getReqOrResp().getRequest();

        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();

        // 获取请求参数
        Object[] args = joinPoint.getArgs();
        Object[] arguments  = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse
                    || args[i] instanceof MultipartFile) {
                /*
                ServletRequest不能序列化，从入参里排除，否则报异常：
                    java.lang.IllegalStateException: It is illegal to call this method
                    if the current request is not in asynchronous mode (i.e. isAsyncStarted() returns false)
                ServletResponse不能序列化 从入参里排除，否则报异常：
                    java.lang.IllegalStateException: getOutputStream() has already been called for this response
                 */
                continue;
            }
            arguments[i] = args[i];
        }
        String parameter ;
        try {
            parameter = JSONObject.toJSONString(arguments);
        } catch (Exception e) {
            parameter = Arrays.toString(arguments);
        }
        log.info("\n\t请求开始，各个参数，url:{}, method:{}, uri:{}, \n\tqueryString:{}", url, method, uri, parameter);
    }


    // 获取request/response对象
    private ServletRequestAttributes getReqOrResp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }

}
