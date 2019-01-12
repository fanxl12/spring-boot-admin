package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 首页轮播图
 * @author: fanxl
 * @date: 2018/12/28 0028 19:35
 */
@Table(name = "admin_wheel_picture")
@Data
public class WheelPicture extends IdEntity {

    private String url;

    private Integer width;

    private Integer height;

}
