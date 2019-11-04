package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;
import java.util.Date;

/**
 * @description 农残检测
 * @author: fanxl
 * @date: 2018/12/28 0028 17:23
 */
@Table(name = "admin_pesticide_check")
@Data
public class PesticideCheck extends IdEntity {

    private Date checkDate;

    private String booth;

    private String category;

    private String checkProject;

    private String place;

    private Double checkValue;

    private String result;

    private String checkUser;

    private String recheckUser;

    private String checkOrg;

    private String remark;

}
