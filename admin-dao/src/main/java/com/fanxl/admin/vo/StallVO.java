package com.fanxl.admin.vo;

import lombok.Data;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/17 0017 12:10
 */
@Data
public class StallVO {

    private Long stallIndex;

    private String name;

    private String categoryName;

    private String code;

    /**
     * 营业执照
     */
    private String businessLicense;

}
