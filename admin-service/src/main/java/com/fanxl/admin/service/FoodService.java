package com.fanxl.admin.service;

import com.fanxl.admin.entity.Food;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface FoodService {

    /**
     * 导入数据
     * @param file
     * @return
     */
    boolean importData(MultipartFile file);

    /**
     * 查询所有分类 分页
     * @param pageable
     * @return
     */
    PageInfo<Food> getList(Pageable pageable);

    /**
     * 检查是否存在该食物
     * @param name
     * @param categoryId
     * @return
     */
    Long checkFood(String name, Long categoryId);

}
