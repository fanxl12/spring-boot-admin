package com.fanxl.admin.utils;


import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.vo.ApiResponse;

/**
 * @description Rest Api接口返回工具类
 * @author: fanxl
 * @date: 2017/11/17 10:41
 */
public class ResultUtil {

    public static ApiResponse success(Object object){
        ApiResponse response = new ApiResponse();
        response.setStatus(ResultEnum.SUCCESS.getCode());
        response.setMessage(ResultEnum.SUCCESS.getMsg());
        response.setResult(object);
        return response;
    }

    public static ApiResponse success(){
        return success(null);
    }

    public static ApiResponse error(String msg){
        ApiResponse response = new ApiResponse();
        response.setMessage(msg);
        return response;
    }

    public static ApiResponse error(String msg, int errorCode){
        ApiResponse response = new ApiResponse();
        response.setMessage(msg);
        response.setStatus(errorCode);
        return response;
    }

}
