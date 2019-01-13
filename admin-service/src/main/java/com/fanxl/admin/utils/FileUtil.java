package com.fanxl.admin.utils;

import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @description 文件帮助类
 * @author: Fanxl
 * @date: Created in 2017/8/7 11:41
 */
@Slf4j
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

	/**
	 * 保存文件
	 * @param multipartFile 文件
	 * @param folderName 保存的文件路径
	 * @return
	 * @throws IOException
	 */
	public static String saveFile(MultipartFile multipartFile, String folderName) throws IOException {
		File folder = new File(folderName);
		if (!folder.exists() && !folder.mkdirs()) {
			log.error("文件夹创建失败");
			throw new AdminException(ResultEnum.FILE_CREATE_FAIL);
		}

		String originalFilename = multipartFile.getOriginalFilename();
		String fileType = getFileType(originalFilename);

		String fileName = System.currentTimeMillis() + fileType;

		File file = new File(folder.getAbsolutePath() + "/" +fileName);
		multipartFile.transferTo(file);
		return fileName;
	}

	/**
	 * 删除文件
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			return file.delete();
		}
		return true;

	}

}
