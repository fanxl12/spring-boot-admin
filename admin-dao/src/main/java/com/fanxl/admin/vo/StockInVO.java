package com.fanxl.admin.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/17 0017 12:10
 */
@Data
public class StockInVO {

    private String shopCode;

    private String supplierName;

    private Date purchaseDate;

    private String goodsName;

}
