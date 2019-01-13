package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 明星摊位
 * @author: fanxl
 * @date: 2018/12/28 0028 19:35
 */
@Table(name = "admin_star_booth")
@Data
public class StarBooth extends IdNameEntity {

    private Long categoryId;

    private String url;

    private Integer star;

}
