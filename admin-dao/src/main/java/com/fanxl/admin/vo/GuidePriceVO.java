package com.fanxl.admin.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/17 0017 12:10
 */
@Data
public class GuidePriceVO {

    private String name;

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
