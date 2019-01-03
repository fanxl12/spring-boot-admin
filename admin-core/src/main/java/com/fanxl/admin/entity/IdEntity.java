package com.fanxl.admin.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @description
 * @author: Fanxl
 * @date: Created in 2017/8/7 11:41
 */
@Data
public abstract class IdEntity extends BaseEntity{

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;
}
