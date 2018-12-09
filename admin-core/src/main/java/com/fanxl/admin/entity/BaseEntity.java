package com.fanxl.admin.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description
 * @author: Fanxl
 * @date: Created in 2017/8/7 11:41
 */
@Data
public abstract class BaseEntity implements Serializable {

    private Date createDate;

    private Date updateDate;
}
