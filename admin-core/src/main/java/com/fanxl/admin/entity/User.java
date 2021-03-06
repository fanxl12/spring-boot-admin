package com.fanxl.admin.entity;

import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:19
 */
@Data
public class User extends StringIdEntity {

    private String username;

    private String password;

    private String phone;

    private String salt;

    private String head;

    private String email;

    private Date lastLoginTime;

    private Date accountExpired;

}
