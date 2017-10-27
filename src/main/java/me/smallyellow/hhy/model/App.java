/*
 * 文件名称：App.java
 */
package me.smallyellow.hhy.model;

import javax.persistence.Table;

import java.io.Serializable;

import javax.persistence.Column;


/**
 * 名称：
 * 模块描述：数据库表对应实体类
 * 作者：系统自动生成
 */
 @Table(name="app")
public class App implements Serializable{

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public App() {}
	public App(Long appId,String appName) {
		this.appId=appId;
		this.appName=appName;
	}
	public App setNotNull(Long appId,String appName) {
		this.appId=appId;
		this.appName=appName;
		return this;
	}
	/**属性**/
	@Column(name = "app_id")
	private Long appId;// （非空） 
	@Column(name = "app_name")
	private String appName;// （非空） 

	/**属性Get、Set函数**/
	public Long getAppId() {
		return appId;
	}
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}

}