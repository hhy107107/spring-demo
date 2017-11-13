package me.smallyellow.base.core.lang;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import com.alibaba.druid.util.StringUtils;

/**
 * 文件处理工具类
 * @author hhy
 * 2017年11月13日上午10:10:46
 */
public class FileUtis {
	
	/**
	 * 保存文件
	 * @param inputStream 文件流
	 * @param realUploadPath 文件上传保存路径
	 * @param fileName 文件名[可选，文件名需要包含后缀]
	 * @param ext 文件后缀[可选，传了文件名，后缀不需要传]
	 * @return 
	 * @throws FileNotFoundException IOException
	 */
	public static String saveFile(InputStream inputStream, String realUploadPath, String fileName, String ext) throws FileNotFoundException, IOException{
		//文件名，如果没指定就自动生成
		if(StringUtils.isEmpty(fileName)){
			fileName = UUID.randomUUID().toString().replace("_", "") + ext;
			fileName = fileName.toLowerCase();
		}
		//文件路径
		File uploadPathFile = new File(realUploadPath);
		if (!uploadPathFile.exists()) {
			uploadPathFile.mkdirs();
		}
		//开始执行
		OutputStream os = null;
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流保存到本地文件
		os = new FileOutputStream(uploadPathFile.getPath() + File.separator + fileName);
		// 开始读取
		while ((len = inputStream.read(bs)) != -1) {
		  os.write(bs, 0, len);
		}
		os.close();
        inputStream.close();
	    return fileName;
	}
}
