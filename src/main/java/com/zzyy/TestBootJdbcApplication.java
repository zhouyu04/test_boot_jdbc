package com.zzyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.zzyy"})
@MapperScan("com.zzyy.mapper")
public class TestBootJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestBootJdbcApplication.class, args);
    }
}
