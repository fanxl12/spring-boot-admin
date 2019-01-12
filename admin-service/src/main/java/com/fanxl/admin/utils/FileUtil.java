package com.fanxl.admin.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @description 文件帮助类
 * @author: Fanxl
 * @date: Created in 2017/8/7 11:41
 */
public class FileUtil {

	/**
	 * 获取文件后缀
	 * @param originalFilename
	 * @return
	 */
	public static String getFileType(String originalFilename) {
		if (StringUtils.isNotEmpty(originalFilename)){
			int dotPosition = originalFilename.lastIndexOf(".");
			return (dotPosition == -1) ? null : originalFilename.substring(dotPosition).toLowerCase();
		}
		return null;
	}
}
