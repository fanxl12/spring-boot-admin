package com.fanxl.admin.security;

import com.fanxl.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @description 配置排除地址
 * @author: fanxl
 * @date: 2017/11/17 16:15
 */
@Component
@Order(90000)
public class WebAuthorizeConfig implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(HttpMethod.GET,"/resources/**").permitAll()
                .antMatchers(HttpMethod.GET,"/error/**").permitAll()
                .antMatchers(HttpMethod.GET,"/static/**").permitAll()
                .anyRequest();
        return false;
    }
}
