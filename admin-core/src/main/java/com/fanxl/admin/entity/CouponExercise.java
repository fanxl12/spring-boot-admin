package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 优惠图
 * @author: fanxl
 * @date: 2018/12/28 0028 19:35
 */
@Table(name = "admin_coupon_exercise")
@Data
public class CouponExercise extends IdEntity {

    private String url;

    private Integer width;

    private Integer height;

}
