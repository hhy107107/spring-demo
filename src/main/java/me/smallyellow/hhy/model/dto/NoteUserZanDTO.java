package me.smallyellow.hhy.model.dto;

import me.smallyellow.hhy.model.NoteUserZan;

/**
 * 用户赞文章情况，扩展了文章总攒书
 * @author hhy
 * 2017年12月4日上午9:12:29
 */
public class NoteUserZanDTO extends NoteUserZan{

	private static final long serialVersionUID = -1375569955522070121L;

	private Integer totalZanNum; //总赞数

	public Integer getTotalZanNum() {
		return totalZanNum;
	}

	public void setTotalZanNum(Integer totalZanNum) {
		this.totalZanNum = totalZanNum;
	}
}
