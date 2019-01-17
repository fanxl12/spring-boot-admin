package com.fanxl.admin.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/17 0017 12:10
 */
@Data
public class TodayPriceVO {

    private String boothName;

    private String foodName;

    private BigDecimal price;

    private BigDecimal specialPrice;

}
