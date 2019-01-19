package com.fanxl.admin;

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
@tk.mybatis.spring.annotation.MapperScan("com.fanxl.admin.dao")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }
}
