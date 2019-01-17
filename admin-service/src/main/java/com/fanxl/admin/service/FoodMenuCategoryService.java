package com.fanxl.admin.service;

import com.fanxl.admin.entity.FoodMenuCategory;
import com.fanxl.admin.vo.FoodMenuCategoryVO;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:55
 */
public interface FoodMenuCategoryService {

    /**
     * 查询所有分类
     * @return
     */
    List<FoodMenuCategory> getAll();

    /**
     * 创建分类
     * @param foodMenuCategory
     * @return
     */
    boolean create(FoodMenuCategory foodMenuCategory);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 更新
     * @param foodMenuCategory
     * @return
     */
    boolean update(FoodMenuCategory foodMenuCategory);

    /**
     * 查询详情
     * @param id
     * @return
     */
    FoodMenuCategory getById(Long id);

    /**
     * 获取分类
     * @return
     */
    List<FoodMenuCategoryVO> getCategoryList();

}
