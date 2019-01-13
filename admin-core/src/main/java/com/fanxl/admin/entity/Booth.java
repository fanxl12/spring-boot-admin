package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 明星摊位
 * @author: fanxl
 * @date: 2018/12/28 0028 19:35
 */
@Table(name = "admin_booth")
@Data
public class Booth extends IdNameEntity {

    private Long marketRegionId;

    private Long categoryId;

    private String url;

    private Integer star;

    /**
     * 位置
     */
    private String position;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 许可证
     */
    private String license;


    /**
     * 健康证
     */
    private String heathLicense;

}
