package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 进货录入
 * @author: fanxl
 * @date: 2018/12/28 0028 17:23
 */
@Table(name = "admin_stock_in")
@Data
public class StockIn extends IdEntity {

    private Date purchaseDate;

    private String shopCode;

    private String goodsCode;

    private String goodsNumber;

    private String goodsName;

    private String purchaseType;

    private Double purchaseTotal;

    private BigDecimal purchaseMoney;

    private BigDecimal purchasePrice;

    private String purchaseBatch;

    private String supplierName;

    private String brandName;

    private String productPlace;

    private String purchaseStatus;

}
