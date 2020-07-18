package com.fanxl.admin.dao;

import com.fanxl.admin.entity.Stall;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface StallDao extends Mapper<Stall> {

    /**
     * 批量保存
     * @param list
     * @return
     */
    int saveList(List<Stall> list);

    /**
     * 删除所有数据
     * @return
     */
    int deleteAll();

}
