package com.leo.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class Consumer_Dashboard_App {

    public static void main(String[] args){

        SpringApplication.run(Consumer_Dashboard_App.class,args);
        }
}
