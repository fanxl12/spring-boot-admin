package com.fanxl.admin.dao;

import com.fanxl.admin.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface UserDao {

    /**
     * 创建用户
     * @param user
     * @return
     */
    int create(User user);

    /**
     * 查找用户详情
     * @param id
     * @return
     */
    User get(String id);

}
