package com.fanxl.admin.enums;

/**
 * @description
 * @author: Fanxl
 * @date: Created in 2017/8/7 11:41
 */
public enum ResultEnum {

    UN_KNOW_ERROR(-1, "未知错误"),
    PARAM_ERROR(1, "参数不正确"),
    SYSTEM_ERROR(2, "系统操作异常"),
    SUCCESS(200, "成功"),
    FAIL(201, "失败"),
    NOT_FOUND(202, "数据不存在"),
    FILE_NOT_FOUND(4004, "文件找不到"),
    FILE_NOT_EMPTY(4005, "文件不能为空"),
    FILE_CREATE_FAIL(4006, "文件创建失败"),
    FILE_DOWNLOAD_FAIL(4007, "文件下载失败"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
