package com.fanxl.admin.utils;

import com.fanxl.admin.dto.ImageDTO;
import com.fanxl.admin.enums.ResultEnum;
import com.fanxl.admin.exception.AdminException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

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
	public static ImageDTO saveWidthAndHeightFile(MultipartFile multipartFile, String folderName) throws IOException {
		BufferedImage sourceImg = ImageIO.read(multipartFile.getInputStream());
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setWidth(sourceImg.getWidth());
		imageDTO.setHeight(sourceImg.getHeight());
		imageDTO.setName(saveFile(multipartFile, folderName));
		return imageDTO;
	}

	public static String saveFile(MultipartFile multipartFile, String folderName) throws IOException {
		String originalFilename = multipartFile.getOriginalFilename();
		String fileType = getFileType(originalFilename);
		String fileName = System.currentTimeMillis() + fileType;

		File file = new File(getFolderPath(folderName) + "/" +fileName);
		multipartFile.transferTo(file);
		return fileName;
	}

	private static String getFolderPath(String folderName) {
		File folder = new File(folderName);
		if (!folder.exists() && !folder.mkdirs()) {
			log.error("文件夹创建失败");
			throw new AdminException(ResultEnum.FILE_CREATE_FAIL);
		}
		return folder.getAbsolutePath();
	}

	/**
	 * 下载并保存文件
	 * @param imageUrl
	 * @param folderName
	 * @return
	 */
	public static ImageDTO downLoadImage(String imageUrl, String folderName) throws Exception {
		URL url = new URL(imageUrl);
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		if (con.getResponseCode() != HttpsURLConnection.HTTP_OK) {
			throw new AdminException(ResultEnum.FILE_DOWNLOAD_FAIL);
		}
		InputStream is = con.getInputStream();

		String fileType = imageUrl.substring(imageUrl.lastIndexOf("."));
		String fileName = System.currentTimeMillis() + fileType;
		File file = new File(getFolderPath(folderName) + "/" +fileName);

		byte[] bs = new byte[1024];
		int len;
		OutputStream os = new FileOutputStream(file);
		while ((len = is.read(bs)) != -1) {
			os.write(bs, 0, len);
		}
		os.close();
		is.close();
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setName(fileName);

		BufferedImage sourceImg = ImageIO.read(url);
		imageDTO.setWidth(sourceImg.getWidth());
		imageDTO.setHeight(sourceImg.getHeight());
		return imageDTO;
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
