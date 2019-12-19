package com.fanxl.admin.service.impl;

import com.fanxl.admin.dao.UserDao;
import com.fanxl.admin.entity.User;
import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:56
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean create(User user) {
        user.setId(RandomStringUtils.randomAlphanumeric(32));
        return userDao.create(user)>0;
    }

    @Override
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.selectAll();
    }

    @Override
    public boolean update(User user) {
        if (StringUtils.isEmpty(user.getOldPassword())) {
            throw new AdminException(10030, "请输入旧密码");
        }
        if (!passwordEncoder.matches(user.getOldPassword(), user.getPassword())) {
            throw new AdminException(10031, "旧密码错误");
        }
        if (StringUtils.isEmpty(user.getNewPassword())) {
            throw new AdminException(10030, "新密码不能为空");
        }
        user.setPassword(passwordEncoder.encode(user.getNewPassword()));
        return userDao.updateByPrimaryKeySelective(user)>0;
    }

    @Override
    public boolean delete(String id) {
        return userDao.deleteByPrimaryKey(id)>0;
    }

    @Override
    public User findByUsername(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", username);
        return userDao.selectOneByExample(example);
    }
}
