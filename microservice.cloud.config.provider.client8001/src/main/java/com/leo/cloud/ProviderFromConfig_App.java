package com.leo.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProviderFromConfig_App {

    public static void main(String[] args){
        SpringApplication.run(ProviderFromConfig_App.class,args);
    }
}
