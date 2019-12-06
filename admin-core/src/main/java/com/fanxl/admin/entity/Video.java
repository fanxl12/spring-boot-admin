package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 视频
 * @author: fanxl
 * @date: 2018/12/28 0028 19:35
 */
@Table(name = "admin_video")
@Data
public class Video extends IdNameEntity {

    private String url;

}
