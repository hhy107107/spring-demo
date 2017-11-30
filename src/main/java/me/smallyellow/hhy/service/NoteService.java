package me.smallyellow.hhy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.smallyellow.base.boot.web.bean.Page;
import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.constant.CommonConst;
import me.smallyellow.hhy.mapper.NoteMapper;
import me.smallyellow.hhy.model.Note;
import me.smallyellow.hhy.model.dto.NoteDTO;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * 新增文章
 * @author hhy
 * 2017年11月27日上午10:00:15
 */
@Service
public class NoteService {
	
	@Autowired
	private NoteMapper noteMapper;
	
	/**
	 * 新增文章
	 * @param note
	 * @throws WebException
	 */
	public void insertNote(Note note) throws WebException{
		int result = noteMapper.insert(note);
		if (result <= 0) {
			throw new WebException("增加文章失败");
		}
	}
	
	/**
	 * 文章列表
	 * @param typeId
	 * @param typeId 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<Note> selectNoteList(Long userId, Long typeId, Integer pageNo, Integer pageSize) {
		Example example = new Example(Note.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("userId", userId);
		if (typeId != null) {
			criteria.andEqualTo("typeId", typeId);
		}
		if (pageSize == null) {
			pageSize = CommonConst.PAGE_SIZE;
		}
		List<Note> list = noteMapper.selectNoteList((pageNo - 1) * pageSize, pageSize, typeId, userId);
		Integer count = noteMapper.selectNoteCount(typeId, userId);
		Page<Note> page = new Page<>();
		page.setList(list);
		page.setTotalCount(count);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}

	/**
	 * 获取文章详情
	 * @param userId
	 * @param id
	 * @return
	 */
	public NoteDTO selectNoteDetail(Long userId, Long id) {
		NoteDTO record = noteMapper.selectNoteDetail(id, userId);
		return record;
	}

}
