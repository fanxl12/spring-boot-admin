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
     * 最低价
     */
    private BigDecimal guidePrice;



    private Long id;

    private String categoryCode;

}
