package com.fanxl.admin.service;

import com.fanxl.admin.entity.User;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface UserService {

    /**
     * 创建用户
     * @param user
     * @return
     */
    boolean create(User user);

}
