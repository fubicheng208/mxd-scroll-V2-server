package com.fubic.scrollservercalculateservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
@MapperScan(basePackages = "com.fubic.scrollservercalculateservice.mapper")
public class ScrollServerCalculateServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrollServerCalculateServiceApplication.class, args);
    }

}
