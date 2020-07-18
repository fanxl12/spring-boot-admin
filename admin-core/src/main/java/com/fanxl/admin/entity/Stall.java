package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 摊位信息
 * @author: fanxl
 * @date: 2018/12/28 0028 17:23
 */
@Table(name = "admin_stall")
@Data
public class Stall extends IdNameEntity {

    private Long stallIndex;

    private String categoryName;

    private String code;

    /**
     * 营业执照
     */
    private String businessLicense;

}
