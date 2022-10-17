package com.zr.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Slf4j
@Component
public class ControllerAspect2 { // 记录方法执行时间

    // PointCut A
    // 拦截所有Controller层方法，打印请求参数和返回结果
    @Pointcut("execution(* ogr.spring.controller..*.*(..))")
    public void controllerCut() {
    }

    @Around("controllerCut()")
    public Object getMethodExecuteTime(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            // 获取一下请求参数
            log.debug("\n执行方法: {}.{} 耗时: {} ms, 请求参数: {}",
                    pjp.getSignature().getDeclaringTypeName(),
                    pjp.getSignature().getName(), (endTime - beginTime), parseParameter(pjp.getArgs()));
        }
    }

    private String parseParameter(Object[] args) {
        if (null == args || args.length <= 0) {
            return "该方法没有参数";
        }
        StringBuilder param = new StringBuilder("## 个:[");
        for (Object obj : args) {
            try{
                param.append(obj.toString()).append(", ");
            } catch (NullPointerException e){
                log.warn("参数可能为空，请注意");
            }
        }
        return param.deleteCharAt(param.lastIndexOf(","))
               .deleteCharAt(param.lastIndexOf(" "))
               .append("]").toString().replace("##", String.valueOf(args.length));
    }

}
