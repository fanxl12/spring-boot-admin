package com.fanxl.admin.rest;

import com.fanxl.admin.exception.AdminException;
import com.fanxl.admin.service.AdvertImageService;
import com.fanxl.admin.service.CouponExerciseService;
import com.fanxl.admin.service.WheelPictureService;
import com.fanxl.admin.utils.ResultUtil;
import com.fanxl.admin.vo.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description
 * @author: fanxl
 * @date: 2018/12/10 0010 13:20
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/v1/home")
public class HomeRestController {

    @Autowired
    private WheelPictureService wheelPictureService;

    @Autowired
    private AdvertImageService advertImageService;

    @Autowired
    private CouponExerciseService couponExerciseService;

    @GetMapping("wheel")
    public ApiResponse getWheel() {
        return ResultUtil.success(wheelPictureService.getWheel());
    }

    @GetMapping("advert")
    public ApiResponse advert() {
        return ResultUtil.success(advertImageService.getAdvertList());
    }

    @GetMapping("coupon")
    public ApiResponse coupon() {
        return ResultUtil.success(couponExerciseService.getCouponUrl());
    }

    @GetMapping("test")
    public ApiResponse test() {
        log.error("这个是error级别日志");
        log.warn("这个是warn级别日志");
        log.info("这个是info级别日志");
        log.debug("这个是debug级别日志");
        return ResultUtil.success("连接正常");
    }


}
