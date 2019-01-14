package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @description 指导菜价
 * @author: fanxl
 * @date: 2018/12/28 0028 17:23
 */
@Table(name = "admin_guide_price")
@Data
public class GuidePrice extends IdEntity {

    /**
     * 平均价
     */
    private BigDecimal averagePrice;

    /**
     * 最高价
     */
    private BigDecimal maxPrice;

    /**
     * 最低价
     */
    private BigDecimal lowPrice;

}
