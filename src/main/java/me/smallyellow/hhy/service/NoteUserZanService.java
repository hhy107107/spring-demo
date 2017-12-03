package me.smallyellow.hhy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.smallyellow.base.core.utils.CollectionUtils;
import me.smallyellow.hhy.mapper.NoteMapper;
import me.smallyellow.hhy.mapper.NoteUserZanMapper;
import me.smallyellow.hhy.model.Note;
import me.smallyellow.hhy.model.NoteUserZan;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户赞笔记的情况
 * @author hhy
 * 2017年12月2日上午11:54:54
 */
@Service
public class NoteUserZanService {
	
	@Autowired
	NoteUserZanMapper noteUserZanMapper;
	
	@Autowired
	NoteMapper noteMapper;
	
	/**
	 * 更新用户文章点赞情况
	 * @param noteId 笔记id
	 * @param userId 用户id
	 * @param zan 赞情况 1-赞 2-没赞
	 */
	public void updateNoteZan(Long noteId, Long userId, Short zan) {
		NoteUserZan noteUserZan = new NoteUserZan();
		noteUserZan.setNotNull(null, noteId, userId, zan);
		NoteUserZan old = getNoteZanByNoteIdAndUserId(noteId, userId);
		if (old != null) {
			// 有数据就更新
			old.setZan(zan);
			noteUserZanMapper.updateByPrimaryKeySelective(old);
		} else {
			// 没数据就添加
			noteUserZanMapper.insert(noteUserZan);
		}
		//修改文章点赞数
		if (old != null && !old.getZan().equals(zan) || old == null) {
			Note note = noteMapper.selectByPrimaryKey(noteId);
			Integer oldZanNum = note.getZanNum();
			if (oldZanNum == null) oldZanNum = 0;
			if (old != null && zan.equals((short) 2) && oldZanNum > 0) {
				//减了一个赞
				note.setZanNum(oldZanNum - 1);
			} else {
				//增加了一个赞
				note.setZanNum(oldZanNum + 1);
			}
			noteMapper.updateByPrimaryKeySelective(note);
		}
	}
	
	/**
	 * 根据用户id和笔记id 获取用户笔记点赞情况
	 * @param noteId 笔记id
	 * @param userId 用户id
	 * @return
	 */
	public NoteUserZan getNoteZanByNoteIdAndUserId(Long noteId, Long userId) {
		Example example = new Example(NoteUserZan.class);
		example.createCriteria().andEqualTo("noteId", noteId).andEqualTo("userId", userId);
		List<NoteUserZan> list = noteUserZanMapper.selectByExample(example);
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}
}
