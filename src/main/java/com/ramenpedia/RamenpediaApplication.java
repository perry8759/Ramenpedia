package com.ramenpedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ramenpedia.controller",
        "com.ramenpedia.service",
        "com.ramenpedia.config",
        "com.ramenpedia.interceptor",
        "com.ramenpedia.filter",
        "com.ramenpedia.dao"})
public class RamenpediaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RamenpediaApplication.class, args);
    }

}
