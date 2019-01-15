package com.fanxl.admin.excel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
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
public class TodayPriceExcelBean extends BaseRowModel {

    @ExcelProperty(index = 0)
    private Long foodId;

    @ExcelProperty(index = 1)
    private Long boothId;

    @ExcelProperty(index = 2)
    private Date priceDate;

    @ExcelProperty(index = 3)
    private BigDecimal price;

    @ExcelProperty(index = 4)
    private BigDecimal specialPrice;
}
