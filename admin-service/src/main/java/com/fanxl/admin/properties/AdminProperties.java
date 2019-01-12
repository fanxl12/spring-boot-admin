package com.fanxl.admin.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/12 0012 11:14
 */
@Data
@Configuration
@EnableConfigurationProperties(AdminProperties.class)
@ConfigurationProperties(prefix = "admin")
public class AdminProperties {

    private String fileUpload;

}

