package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description 今日特价
 * @author: fanxl
 * @date: 2018/12/28 0028 17:23
 */
@Table(name = "admin_today_price")
@Data
public class TodayPrice extends IdEntity {

    private String food;

    private String booth;

    private Date priceDate;

    /**
     * 现价
     */
    private BigDecimal price;

    /**
     * 特价
     */
    private BigDecimal specialPrice;
}
