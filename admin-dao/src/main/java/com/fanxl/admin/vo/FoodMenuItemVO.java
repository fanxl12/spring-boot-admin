package com.fanxl.admin.vo;

import lombok.Data;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/13 0013 10:51
 */
@Data
public class FoodMenuItemVO {

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

}
