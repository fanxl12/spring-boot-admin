package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 城市
 * @author: fanxl
 * @date: 2019/1/13 0013 11:24
 */
@Table(name = "admin_city")
@Data
public class City extends IdNameEntity {

    private String code;

}
