package com.zr.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface LogUserAction {
    String value() default ""; // 用户操作
    String module() default "";// 所属模块
    String reqUrl() default "";// 请求url
}
