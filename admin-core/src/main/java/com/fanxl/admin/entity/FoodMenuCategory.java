package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description 菜单分类
 * @author: fanxl
 * @date: 2018/12/28 0028 19:35
 */
@Table(name = "admin_food_menu_category")
@Data
public class FoodMenuCategory extends IdNameEntity {

}
