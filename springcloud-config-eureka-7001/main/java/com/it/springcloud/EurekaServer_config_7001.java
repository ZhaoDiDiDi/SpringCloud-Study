package com.it.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer     //服务端的启动类，可以接受别注册进来
public class EurekaServer_config_7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_config_7001.class, args);
    }
}
