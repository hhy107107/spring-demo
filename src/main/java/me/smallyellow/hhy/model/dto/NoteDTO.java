package me.smallyellow.hhy.model.dto;

import me.smallyellow.hhy.model.Note;

/**
 * 文章扩展类
 * @author hhy
 * 2017年11月27日下午4:51:10
 */
public class NoteDTO extends Note{

	private static final long serialVersionUID = 9145342528343502722L;
	
	private String typeName; //类型名称

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
