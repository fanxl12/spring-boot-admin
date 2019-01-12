package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 首页底部图
 * @author: fanxl
 * @date: 2018/12/28 0028 19:35
 */
@Table(name = "admin_home_picture")
@Data
public class HomePicture extends IdEntity {

    private String url;

    private Integer width;

    private Integer height;

}
