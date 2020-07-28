// 就是介绍Spring gateway 如何使用javaBean 配置
//package com.mirco.mygateway.configuration;
//
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class GatewayConfig {
//
//    @Bean
//    public RouteLocator customRouterLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("path_route2", r -> r.path("/user/{userId}")
//                .uri("http://localhost:8101"))
//                .route("path_route3", r -> r.path("/user/config")
//                .uri("http://localhost:8101/user/config"))
//                .build();
//
//    }
//
//}
