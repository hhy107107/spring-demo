package me.smallyellow.base.boot.web;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 静态资源文件配置
 */
@Configuration
@ConfigurationProperties(prefix = WebPathProperties.STATIC_PREFIX)
public class WebPathProperties {
	
	 public final static String STATIC_PREFIX = "webpath";
	 
	 private String platfromName;// 平台名称
	 private String fileDir;// 文件保存系统路径
	 private String cacheFileDir;// 缓存文件保存路径
	 private String staticUrl;// 静态资源访问路径
	 private String imageUrl;// 图片访问路径
	 private String downloadUrl;// 文件下载路径
	 private String rootUrl;// 主域名
	 private String openUrl;//开放平台地址
	 
	public String getFileDir() {
		return fileDir;
	}
	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
	public String getStaticUrl() {
		return staticUrl;
	}
	public void setStaticUrl(String staticUrl) {
		this.staticUrl = staticUrl;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getRootUrl() {
		return rootUrl;
	}
	public void setRootUrl(String rootUrl) {
		this.rootUrl = rootUrl;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getCacheFileDir() {
		return cacheFileDir;
	}
	public void setCacheFileDir(String cacheFileDir) {
		this.cacheFileDir = cacheFileDir;
	}
	public String getOpenUrl() {
		return openUrl;
	}
	public void setOpenUrl(String openUrl) {
		this.openUrl = openUrl;
	}
	public String getPlatfromName() {
		return platfromName;
	}
	public void setPlatfromName(String platfromName) {
		this.platfromName = platfromName;
	}

}