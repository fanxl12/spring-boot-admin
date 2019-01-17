package com.fanxl.admin.rest;

import com.fanxl.admin.service.BoothService;
import com.fanxl.admin.utils.ResultUtil;
import com.fanxl.admin.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/10 0010 13:20
 */
@RestController
@RequestMapping(value = "/api/v1/booth")
public class BoothRestController {

    @Autowired
    private BoothService boothService;

    @GetMapping()
    public ApiResponse get(@PageableDefault Pageable pageable) {
        return ResultUtil.success(boothService.getList(pageable));
    }




}
