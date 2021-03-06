package com.example.springbootquang;

import com.example.springbootquang.entity.CategoryEntity;
import com.example.springbootquang.entity.NewEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })// loại bỏ Remove “Using default security password” on Spring Boot
public class SpringBootQuangApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuangApplication.class, args);
    }
}
