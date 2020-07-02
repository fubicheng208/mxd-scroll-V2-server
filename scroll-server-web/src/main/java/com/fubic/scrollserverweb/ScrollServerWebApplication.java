package com.fubic.scrollserverweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDubbo
@ComponentScan(basePackages = "com.fubic")
public class ScrollServerWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(ScrollServerWebApplication.class, args);
    }
}
