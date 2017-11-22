package me.smallyellow.hhy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.hhy.mapper.UserInfoMapper;
import me.smallyellow.hhy.model.UserInfo;

/**
 * 用户相关
 * @author hhy
 * 2017年11月22日下午1:38:56
 */
@Service
public class UserService {
	
	@Autowired
	private UserInfoMapper userInfoMapper; 

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 * @throws WebException
	 */
	public UserInfo login(String username, String password) throws WebException{
		UserInfo record = new UserInfo();
		record.setUsername(username);
		UserInfo result = userInfoMapper.selectOne(record);
		if(result == null){
			throw new WebException("用户名不存在");
		}
		record.setPassword(password);
		result = userInfoMapper.selectOne(record);
		if(result == null){
			throw new WebException("密码错误");
		}
		return result;
	}
	
	/**
	 * 插入用户
	 * @param info
	 * @throws WebException
	 */
	public void insertUser(UserInfo info) throws WebException{
		int result = userInfoMapper.insert(info);
		if(result <= 0) {
			throw new WebException("插入失败");
		}
	}
	
}
