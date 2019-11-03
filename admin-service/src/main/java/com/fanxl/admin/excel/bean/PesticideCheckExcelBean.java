package com.fanxl.admin.excel.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/28 0028 17:44
 */
@Data
public class PesticideCheckExcelBean extends BaseRowModel {

    @ExcelProperty(index = 0)
    private Integer index;

    @ExcelProperty(index = 1)
    private Date checkDate;

    @ExcelProperty(index = 2)
    private String booth;

    @ExcelProperty(index = 3)
    private String category;

    @ExcelProperty(index = 4)
    private String checkProject;

    @ExcelProperty(index = 5)
    private String place;

    @ExcelProperty(index = 6)
    private String checkValueStr;

    @ExcelProperty(index = 7)
    private String result;

    @ExcelProperty(index = 8)
    private String checkUser;

    @ExcelProperty(index = 9)
    private String recheckUser;

    @ExcelProperty(index = 10)
    private String checkOrg;

    @ExcelProperty(index = 11)
    private String remark;
}
