package com.ljx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class springbootEureka {
    public static void main(String[] args) {
        SpringApplication.run(springbootEureka.class,args);
    }
}
