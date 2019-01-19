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

    private Integer businessWidth;

    private Integer businessHeight;

    private Integer licenseWidth;

    private Integer licenseHeight;

    private Integer heathWidth;

    private Integer heathHeight;
}
