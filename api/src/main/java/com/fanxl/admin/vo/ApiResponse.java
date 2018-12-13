package com.fanxl.admin.vo;

/**
 * @description 接口数据统一包装类
 * @author: fanxl
 * @date: 2017/11/17 10:41
 */
public class ApiResponse<T> {

	private int status;

	private String message;

	private T result;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	public boolean isSuccess() {
		return this.status == 200;
	}

	@Override
	public String toString() {
		return "ApiResponse{" +
				"status=" + status +
				", message='" + message + '\'' +
				", result=" + result +
				'}';
	}
}