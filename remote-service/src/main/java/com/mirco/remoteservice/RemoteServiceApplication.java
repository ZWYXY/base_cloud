package com.mirco.remoteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class RemoteServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemoteServiceApplication.class, args);
    }

}
