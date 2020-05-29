package ogr.spring.config.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspect {

    // 拦截所有Controller层方法，打印请求参数和返回结果

}
