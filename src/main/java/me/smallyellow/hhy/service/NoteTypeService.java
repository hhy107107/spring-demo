package me.smallyellow.hhy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.mapper.NoteTypeMapper;
import me.smallyellow.hhy.model.NoteType;
import tk.mybatis.mapper.entity.Example;

/**
 * 笔记类型
 * @author hhy
 * 2017年11月26日上午10:48:12
 */
@Service
public class NoteTypeService {

	@Autowired
	private NoteTypeMapper noteTypeMapper;
	
	/**
	 * 获取类型列表
	 * @param userId
	 * @return
	 */
	public List<NoteType> selectNoteTypeList(Long userId) {
		Example example = new Example(NoteType.class);
		example.createCriteria().andEqualTo("userId", userId);
		List<NoteType> list = noteTypeMapper.selectByExample(example);
		return list;
	}
	
	/**
	 * 插入类型
	 * @param noteType
	 * @throws WebException
	 */
	public void insertNoteType(NoteType noteType) throws WebException{
		int result = noteTypeMapper.insert(noteType);
		if (result <= 0) {
			throw new WebException("插入失败");
		}
	}
	
	/**
	 * 删除类型
	 * @param noteType
	 * @throws WebException
	 */
	public void deleteNoteType(Long userId, Long id) throws WebException{
		Example example = new Example(NoteType.class);
		example.createCriteria().andEqualTo("userId", userId)
		.andEqualTo("id", id);
		int result = noteTypeMapper.deleteByExample(example);
		if (result <= 0) {
			throw new WebException("删除失败");
		}
	}
}
