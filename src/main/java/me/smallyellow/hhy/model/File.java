/*
 * 文件名称：File.java
 */
package me.smallyellow.hhy.model;

import javax.persistence.Table;
import javax.persistence.Column;
import me.smallyellow.base.boot.mybatis.bean.BaseLongEntity;

import java.util.Date;

/**
 * 名称：
 * 模块描述：数据库表对应实体类
 * 作者：系统自动生成
 */
 @Table(name="file")
public class File extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public File() {}
	public File(Long id,String path,String name,String suffix,String description,Date uploadTime,Date lastTime,Integer useCount,Long authority) {
		this.id=id;
		this.path=path;
		this.name=name;
		this.suffix=suffix;
		this.description=description;
		this.uploadTime=uploadTime;
		this.lastTime=lastTime;
		this.useCount=useCount;
		this.authority=authority;
	}
	public File setNotNull(Long id,String path,String name,String suffix,Date uploadTime,Date lastTime,Integer useCount,Long authority) {
		this.id=id;
		this.path=path;
		this.name=name;
		this.suffix=suffix;
		this.uploadTime=uploadTime;
		this.lastTime=lastTime;
		this.useCount=useCount;
		this.authority=authority;
		return this;
	}
	/**属性**/
	@Column(name = "path")
	private String path;// 文件路径（非空） 
	@Column(name = "name")
	private String name;// 文件名（非空） 
	@Column(name = "suffix")
	private String suffix;// 文件后缀（非空） 
	@Column(name = "description")
	private String description;// 文件描述
	@Column(name = "upload_time")
	private Date uploadTime;// 上传时间（非空） 
	@Column(name = "last_time")
	private Date lastTime;// 文件最后一次使用时间（非空） 
	@Column(name = "use_count")
	private Integer useCount;// 文件请求次数（非空） 
	@Column(name = "authority")
	private Long authority;// 文件所有者（0-公共  >0 私有[私有者编号]）（非空） 

	/**属性Get、Set函数**/
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Date getLastTime() {
		return lastTime;
	}
	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}
	public Integer getUseCount() {
		return useCount;
	}
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	public Long getAuthority() {
		return authority;
	}
	public void setAuthority(Long authority) {
		this.authority = authority;
	}

}