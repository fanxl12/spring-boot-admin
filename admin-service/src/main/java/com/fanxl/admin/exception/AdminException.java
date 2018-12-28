package com.fanxl.admin.exception;


import com.fanxl.admin.enums.ResultEnum;

/**
 * @description 接口异常处理类
 * @author: fanxl
 * @date: 2017/11/16 14:08
 */
public class AdminException extends RuntimeException {

    private Integer code;

    public AdminException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public AdminException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
