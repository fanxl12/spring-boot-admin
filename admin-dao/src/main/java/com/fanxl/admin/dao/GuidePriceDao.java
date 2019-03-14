package com.fanxl.admin.dao;

import com.fanxl.admin.entity.GuidePrice;
import com.fanxl.admin.vo.GuidePriceVO;
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

    /**
     * 获取
     * @return
     */
    Date getLastPriceDate();

    /**
     * 获取分类的指导价格
     * @param categoryId
     * @return
     */
    List<GuidePriceVO> list(Long categoryId);

    /**
     * 指导价格列表
     * @return
     */
    List<GuidePriceVO> all();

}
