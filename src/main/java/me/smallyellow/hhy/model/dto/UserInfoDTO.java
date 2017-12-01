package me.smallyellow.hhy.model.dto;

import me.smallyellow.hhy.model.UserInfo;

/**
 * 用户，扩展了博客等级
 * @author hhy
 * 2017年12月1日下午5:20:04
 */
public class UserInfoDTO extends UserInfo{

	private static final long serialVersionUID = 2289215277910514541L;
	
	private String noteGradeName; //博客等级

	public String getNoteGradeName() {
		return noteGradeName;
	}

	public void setNoteGradeName(String noteGradeName) {
		this.noteGradeName = noteGradeName;
	}
	
}
