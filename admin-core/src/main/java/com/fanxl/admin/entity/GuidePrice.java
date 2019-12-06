package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 指导菜价
 * @author: fanxl
 * @date: 2018/12/28 0028 17:23
 */
@Table(name = "admin_guide_price")
@Data
public class GuidePrice extends IdEntity {

    private String food;

    private Date priceDate;

    /**
     * 平均价
     */
    private BigDecimal averagePrice;

    /**
     * 最高价趋势 默认0无变化 1走高 -1走低
     */
    private Integer maxPriceTrend;

    /**
     * 最高价
     */
    private BigDecimal maxPrice;

    /**
     * 最低价
     */
    private BigDecimal lowPrice;

    /**
     * 最低价趋势 默认0无变化 1走高 -1走低
     */
    private Integer lowPriceTrend;

}
