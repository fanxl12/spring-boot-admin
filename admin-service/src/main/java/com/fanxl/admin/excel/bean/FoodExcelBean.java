package com.fanxl.admin.excel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 17:44
 */
@Data
public class FoodExcelBean {

    @ExcelProperty(index = 0)
    private Long id;

    @ExcelProperty(index = 2)
    private String name;

    @ExcelProperty(index = 1)
    private Long categoryId;
}
