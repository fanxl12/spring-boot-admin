package com.fanxl.admin.excel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 17:44
 */
@Data
public class StockInExcelBean {

    @ExcelProperty(index = 0)
    private String shopCode;

    @ExcelProperty(index = 1)
    private String goodsCode;

    @ExcelProperty(index = 2)
    private String goodsNumber;

    @ExcelProperty(index = 3)
    private String goodsName;

    @ExcelProperty(index = 4)
    private String purchaseType;

    @ExcelProperty(index = 5)
    private double purchaseTotal;

    @ExcelProperty(index = 6)
    @NumberFormat("#.##")
    private BigDecimal purchaseMoney;

    @ExcelProperty(index = 7)
    @NumberFormat("#.##")
    private BigDecimal purchasePrice;

    @ExcelProperty(index = 8)
    private String purchaseBatch;

    @ExcelProperty(index = 9)
    private String supplierName;

    @ExcelProperty(index = 10)
    private String brandName;

    @ExcelProperty(index = 11)
    private String productPlace;

    @ExcelProperty(index = 12)
    @DateTimeFormat("yyyy-MM-dd HH:mm:ss")
    private Date purchaseDate;

    @ExcelProperty(index = 13)
    private String purchaseStatus;
}
