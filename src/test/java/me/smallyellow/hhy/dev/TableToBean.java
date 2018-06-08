package me.smallyellow.hhy.dev;

import me.smallyellow.base.boot.utils.TableUtils;

/**
 * 表结构转换成实体类
 */
public class TableToBean {
	
	public static void main(String[] args) {
		TableUtils.runToBean("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "922107", "me.smallyellow.hhy", "*");
		//TableUtils.runToBean("jdbc:mysql://localhost:3306/test2?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "922107", "me.smallyellow.hhy", "*");
	}
}
