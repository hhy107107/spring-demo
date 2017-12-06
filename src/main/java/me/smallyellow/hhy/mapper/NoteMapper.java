/*
 * 文件名称：NoteMapper.java
 */
package me.smallyellow.hhy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.smallyellow.base.boot.mybatis.bean.MyMapper;
import me.smallyellow.hhy.model.Note;
import me.smallyellow.hhy.model.dto.NoteDTO;

/**
 * 名称：
 * 模块描述：数据库表对应dao操作类
 * 作者：系统自动生成
 */
public interface NoteMapper extends MyMapper<Note> {

	/**
	 * 分页查询文章列表
	 * @param pageNo
	 * @param pageSize
	 * @param typeId
	 * @param userId
	 * @return
	 */
	public List<NoteDTO> selectNoteList(@Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize,
			@Param("typeId") Long typeId, @Param("userId") Long userId);
	
	/**
	 * 用户文章总数
	 * @param typeId
	 * @param userId
	 * @return
	 */
	public Integer selectNoteCount(@Param("typeId") Long typeId, @Param("userId") Long userId);
	
	/**
	 * 文章详情
	 * @param id 文章id
	 * @param userId 用户id
	 * @return
	 */
	public NoteDTO selectNoteDetail(@Param("id") Long id, @Param("userId") Long userId);
	
}