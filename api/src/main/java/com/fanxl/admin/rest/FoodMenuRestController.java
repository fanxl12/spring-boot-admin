package com.fanxl.admin.rest;

import com.fanxl.admin.form.FoodMenuForm;
import com.fanxl.admin.service.FoodMenuService;
import com.fanxl.admin.utils.ResultUtil;
import com.fanxl.admin.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/10 0010 13:20
 */
@RestController
@RequestMapping(value = "/api/v1/booth")
public class FoodMenuRestController {

    @Autowired
    private FoodMenuService foodMenuService;

    @GetMapping()
    public ApiResponse get(@PageableDefault Pageable pageable, @RequestParam FoodMenuForm form) {
        return ResultUtil.success(foodMenuService.getList(form, pageable));
    }

    @GetMapping("{id}")
    public ApiResponse get(@PathVariable Long id) {
        return ResultUtil.success(foodMenuService.getDetail(id));
    }




}
