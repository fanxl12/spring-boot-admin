package com.fanxl.admin.rest;

import com.fanxl.admin.service.GuidePriceService;
import com.fanxl.admin.utils.ResultUtil;
import com.fanxl.admin.vo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/10 0010 13:20
 */
@RestController
@RequestMapping(value = "/api/v1/guide-price")
public class GuidePriceRestController {

    @Autowired
    private GuidePriceService guidePriceService;

    @GetMapping("{categoryId}")
    public ApiResponse get(@PathVariable Long categoryId, @PageableDefault(page = 1) Pageable pageable) {
        return ResultUtil.success(guidePriceService.getList4Api(pageable, categoryId));
    }
}
