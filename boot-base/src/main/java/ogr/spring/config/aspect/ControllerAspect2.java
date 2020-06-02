package ogr.spring.config.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;

@Order(1)
@Aspect
@Slf4j
@Component
public class ControllerAspect2 { // 记录方法执行时间

    // PointCut A
    // 拦截所有Controller层方法，打印请求参数和返回结果
    @Pointcut("execution(* ogr.spring.controller..*.*(..))")
    public void controllerCut() {}

    @Around("controllerCut()")
    public Object getMethodExecuteTime(ProceedingJoinPoint pjp) throws Throwable {
        long beginTime = System.currentTimeMillis();
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            log.error("获取方法执行切面抛出异常", e);
        } finally {
            long endTime = System.currentTimeMillis();
            log.debug("执行方法: {}.{}, 耗时: {} ms",
                pjp.getSignature().getDeclaringTypeName(),
                pjp.getSignature().getName(), (endTime - beginTime));
        }
        return null;
    }

}
