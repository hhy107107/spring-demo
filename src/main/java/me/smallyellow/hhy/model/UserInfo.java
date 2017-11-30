/*
 * 文件名称：UserInfo.java
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
 @Table(name="user_info")
public class UserInfo extends BaseLongEntity {

	private static final long serialVersionUID = 1L;
	/**构造函数**/
	public UserInfo() {}
	public UserInfo(Long id,String username,String password,String name,String usertype,Integer enabled,String qq,String email,String tel,Short sex,String signature,String address,Date birthday,Short reason,String userface) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.name=name;
		this.usertype=usertype;
		this.enabled=enabled;
		this.qq=qq;
		this.email=email;
		this.tel=tel;
		this.sex=sex;
		this.signature=signature;
		this.address=address;
		this.birthday=birthday;
		this.reason=reason;
		this.userface=userface;
	}
	public UserInfo setNotNull(Long id,String username,String password,String name,Short reason) {
		this.id=id;
		this.username=username;
		this.password=password;
		this.name=name;
		this.reason=reason;
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
	private String usertype;// 用户类型 1-普通用户 2-管理员
	@Column(name = "enabled")
	private Integer enabled;// 是否可用
	@Column(name = "qq")
	private String qq;// QQ
	@Column(name = "email")
	private String email;// 邮箱
	@Column(name = "tel")
	private String tel;// 联系电话
	@Column(name = "sex")
	private Short sex;// 性别 1-男 2-女
	@Column(name = "signature")
	private String signature;// 个性签名
	@Column(name = "address")
	private String address;// 地址
	@Column(name = "birthday")
	private Date birthday;// 
	@Column(name = "reason")
	private Short reason;// 来到小黄平台的原因 1-崇拜小黄 2-崇拜统子 3-想了解bjhj 4-被小黄无形之中的气质所吸引（非空） 
	@Column(name = "userface")
	private String userface;// 头像

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
	public Short getSex() {
		return sex;
	}
	public void setSex(Short sex) {
		this.sex = sex;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Short getReason() {
		return reason;
	}
	public void setReason(Short reason) {
		this.reason = reason;
	}
	public String getUserface() {
		return userface;
	}
	public void setUserface(String userface) {
		this.userface = userface;
	}

}