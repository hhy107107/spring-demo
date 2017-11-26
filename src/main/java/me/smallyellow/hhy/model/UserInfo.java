/*
 * 文件名称：UserInfo.java
 */
package me.smallyellow.hhy.model;

import javax.persistence.Table;
import javax.persistence.Column;
import me.smallyellow.base.boot.mybatis.bean.BaseLongEntity;


/**
 * 名称：
 * 模块描述：数据库表对应实体类
 * 作者：系统自动生成
 */
 @Table(name="user_info")
public class UserInfo extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public UserInfo() {}
	public UserInfo(Long id,String username,String password,String name,String usertype,Integer enabled,String realname,String qq,String email,String tel) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.name=name;
		this.usertype=usertype;
		this.enabled=enabled;
		this.realname=realname;
		this.qq=qq;
		this.email=email;
		this.tel=tel;
	}
	public UserInfo setNotNull(Long id,String username,String password,String name) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.name=name;
		return this;
	}
	/**属性**/
	@Column(name = "username")
	private String username;// 用户名（非空） 
	@Column(name = "password")
	private String password;// 密码（非空） 
	@Column(name = "name")
	private String name;// 用户名字（非空） 
	@Column(name = "usertype")
	private String usertype;// 用户类型
	@Column(name = "enabled")
	private Integer enabled;// 是否可用
	@Column(name = "realname")
	private String realname;// 真实姓名
	@Column(name = "qq")
	private String qq;// QQ
	@Column(name = "email")
	private String email;// 
	@Column(name = "tel")
	private String tel;// 联系电话

	/**属性Get、Set函数**/
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}