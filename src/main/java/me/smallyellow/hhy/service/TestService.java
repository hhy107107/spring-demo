package me.smallyellow.hhy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.smallyellow.hhy.config.db.OtherDataSource;
import me.smallyellow.hhy.mapper.AppMapper;
import me.smallyellow.hhy.mapper.UserInfoMapper;
import me.smallyellow.hhy.model.App;
import me.smallyellow.hhy.model.UserInfo;

@Service
public class TestService {

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Autowired
	private AppMapper appMapper;
	
	public List<UserInfo> test() {
		List<UserInfo> list= userInfoMapper.selectAll();
		return list;
	}
	
	@OtherDataSource
	public List<App> test2(){
		List<App> list = appMapper.selectAll();
		return list;
	}

}
