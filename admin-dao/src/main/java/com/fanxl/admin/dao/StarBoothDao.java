package com.fanxl.admin.dao;

import com.fanxl.admin.dto.StarBoothDTO;
import com.fanxl.admin.entity.StarBooth;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface StarBoothDao extends Mapper<StarBooth> {


    /**
     * 获取web页面上显示的列表
     * @return
     */
    List<StarBoothDTO> getWebList();


}
