package com.fanxl.admin.dao;

import com.fanxl.admin.entity.FoodMenu;
import com.fanxl.admin.vo.FoodMenuItemVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface FoodMenuDao extends Mapper<FoodMenu> {

    /**
     * 获取列表
     * @param param
     * @return
     */
    List<FoodMenuItemVO> getList(Map<String, Object> param);

    /**
     * 获取流行菜单
     * @param keyword
     * @return
     */
    List<FoodMenuItemVO> getPopularList(String keyword);

}
