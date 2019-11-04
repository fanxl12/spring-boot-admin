package com.fanxl.admin.vo;

import lombok.Data;

import java.util.Date;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/17 0017 12:10
 */
@Data
public class PesticideCheckVO {

    private Date checkDate;

    private String booth;

    private String category;

    private String checkProject;

    private double checkValue;

    private String result;

}
