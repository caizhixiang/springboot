package com.caizhixiang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.caizhixiang.SpringBootProviderApplication;
@SpringBootApplication
@MapperScan("com.caizhixiang.mapper")
public class SpringBootProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootProviderApplication.class, args);
    }

}