package com.fanxl.admin.dao;

import com.fanxl.admin.entity.Category;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface CategoryDao extends Mapper<Category> {

}
