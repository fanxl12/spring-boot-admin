package com.fanxl.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @description
 * @author: fanxl
 * @date: 2019/1/13 0013 10:51
 */
@Data
public class MarketRegionVO {

    private Long id;

    private String name;

    private List<MarketRegionItemVO> childList;

}
