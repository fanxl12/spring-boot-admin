package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.UserDao;
import com.fanxl.admin.entity.User;
import com.fanxl.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:56
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean create(User user) {
        return userDao.create(user)>0;
    }
}
