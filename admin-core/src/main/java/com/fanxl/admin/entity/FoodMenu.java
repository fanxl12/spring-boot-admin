package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/15 0015 22:11
 */
@Table(name = "admin_food_menu")
@Data
public class FoodMenu extends IdNameEntity {

    private Long foodMenuCategoryId;

    private String url;

    private Integer width;

    private Integer height;

    /**
     * 本周流行 默认0不流行 1流行
     */
    private Boolean popular;

    /**
     * 难度 默认0 简单 1普通 2困难
     */
    private Integer difficulty;

    /**
     * 主料
     */
    private String mainMaterial;

    /**
     * 辅料
     */
    private String secondMaterial;

    /**
     * 调料
     */
    private String pickMaterial;

    /**
     * 功效
     */
    private String effect;

    /**
     * 步骤
     */
    private String step;

    /**
     * 菜系
     */
    private String cuisine;

    /**
     * 烹饪方式
     */
    private String cooking;

}
