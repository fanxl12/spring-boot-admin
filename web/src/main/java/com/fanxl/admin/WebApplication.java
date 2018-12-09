package com.fanxl.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/8 0008 22:50
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.fanxl.admin.dao")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
