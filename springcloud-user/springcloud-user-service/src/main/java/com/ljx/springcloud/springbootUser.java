package com.ljx.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class springbootUser {

    public static void main(String[] args) {
        SpringApplication.run(springbootUser.class,args);
    }
}
