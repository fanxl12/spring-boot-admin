package com.fanxl.admin.excel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
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
public class GuidePriceExcelBean {

    @ExcelProperty(index = 0)
    private Long foodId;

    @ExcelProperty(index = 1)
    @DateTimeFormat("yyyy-MM-dd")
    private Date priceDate;

    @ExcelProperty(index = 2)
    private BigDecimal maxPrice;

    @ExcelProperty(index = 3)
    private BigDecimal averagePrice;

    @ExcelProperty(index = 4)
    private BigDecimal lowPrice;
}
