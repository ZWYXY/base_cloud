package ogr.spring.config.aspect;

import ogr.spring.annotation.LogUserAction;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(2) // 数字越小优先级越高
@Aspect
@Component
public class ControllerAspect3 {

    @Pointcut("@annotation(logUserAction)")
    public void gmAnnotationCut(LogUserAction logUserAction) {}

    @Before(value = "gmAnnotationCut(logUserAction)", argNames = "logUserAction")
    public void geMethodAround(LogUserAction logUserAction) {
        System.err.println( "用户操作:" + logUserAction.value());
    }
}
