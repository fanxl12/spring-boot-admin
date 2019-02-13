package com.fanxl.admin.vo;

import lombok.Data;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/13 0013 10:51
 */
@Data
public class FoodMenuDetailVO {

    private Long id;

    private String name;

    private String url;

    /**
     * 菜系
     */
    private String cuisine;

    /**
     * 烹饪方式
     */
    private String cooking;

    /**
     * 主料
     */
    private String mainMaterial;

    /**
     * 辅料
     */
    private String secondMaterial;

    /**
     * 功效
     */
    private String effect;

    /**
     * 步骤
     */
    private String step;

}
