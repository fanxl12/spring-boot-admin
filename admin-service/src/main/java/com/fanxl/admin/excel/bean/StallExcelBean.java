package com.fanxl.admin.excel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 17:44
 */
@Data
public class StallExcelBean {

    @ExcelProperty(index = 0)
    private Long stallIndex;

    @ExcelProperty(index = 1)
    private String categoryName;

    @ExcelProperty(index = 2)
    private String name;

    @ExcelProperty(index = 3)
    private String code;

    @ExcelProperty(index = 4)
    private String businessLicense;


}
