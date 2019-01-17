package com.fanxl.admin.dao;

import com.fanxl.admin.entity.AdvertImage;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface AdvertImageDao extends Mapper<AdvertImage> {

    /**
     * 获取广告
     * @return
     */
    List<AdvertImage> getAdvert();

}
