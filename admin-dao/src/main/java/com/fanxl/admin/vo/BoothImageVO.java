package com.fanxl.admin.vo;

import lombok.Data;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/17 0017 12:10
 */
@Data
public class BoothImageVO {

    private String marketName;

    private String marketRegionName;

    /**
     * 位置
     */
    private String position;

    private String licenseUrl;
}
