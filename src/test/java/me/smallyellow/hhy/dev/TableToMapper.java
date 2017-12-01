package me.smallyellow.hhy.dev;

import me.smallyellow.base.boot.utils.TableUtils;

/**
 * 表结构转换成实体类
 */
public class TableToMapper {
	
	public static void main(String[] args) {
		TableUtils.runToMapper("jdbc:mysql://localhost:3306/test", "root", "", "me.smallyellow.hhy", "mapper", "*");
		//TableUtils.runToMapper("jdbc:mysql://localhost:3306/test2", "root", "", "me.smallyellow.hhy", "mapper", "*");
	}

}
