package com.fanxl.admin.dao;

import com.fanxl.admin.entity.GuidePrice;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/9 0009 21:49
 */
@Repository
public interface GuidePriceDao extends Mapper<GuidePrice> {

    /**
     * 批量保存
     * @param list
     * @return
     */
    int saveList(List<GuidePrice> list);

    /**
     * 获取上一次的价格日期的价格
     * @param lastPriceDate
     * @return
     */
    List<GuidePrice> getLastGuidePrice(Date lastPriceDate);

}
