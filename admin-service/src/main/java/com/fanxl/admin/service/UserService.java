package com.fanxl.admin.service;

import com.fanxl.admin.entity.User;

import java.util.List;

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

    /**
     * 查询用户
     * @param id
     * @return
     */
    User get(String id);

    /**
     * 查找用户
     * @return
     */
    List<User> findAll();

    /**
     * 更新
     * @param user
     * @return
     */
    boolean update(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean delete(String id);

    /**
     * 用户名查找
     * @param username
     * @return
     */
    User findByUsername(String username);



}
