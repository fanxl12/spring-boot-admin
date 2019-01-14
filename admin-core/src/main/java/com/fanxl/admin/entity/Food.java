package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 食物
 * @author: fanxl
 * @date: 2018/12/28 0028 17:36
 */
@Table(name = "admin_food")
@Data
public class Food extends IdNameEntity {

    private Long categoryId;

}
