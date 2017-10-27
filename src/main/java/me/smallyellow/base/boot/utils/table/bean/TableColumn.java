package me.smallyellow.base.boot.utils.table.bean;


/**
 * 表结构字段
 *
 */
public class TableColumn {
	
	private String name;// 表字段
	private String property;// 对应javaBean属性名称
	private String type;// 类型
	private String caption;// 描述
	private int length;// 类型长度
	private int digits;// 小数点长度
	private boolean notnull;// 是否为空

	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getDigits() {
		return digits;
	}
	public void setDigits(int digits) {
		this.digits = digits;
	}
	public boolean isNotnull() {
		return notnull;
	}
	public void setNotnull(boolean notnull) {
		this.notnull = notnull;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
}
