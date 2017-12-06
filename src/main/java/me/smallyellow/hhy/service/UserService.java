package me.smallyellow.hhy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.mail.smtp.SMTPAddressFailedException;

import me.smallyellow.base.boot.web.exception.WebException;
import me.smallyellow.base.core.operator.AESOperator;
import me.smallyellow.base.core.operator.mail.MailOperator;
import me.smallyellow.base.core.operator.mail.config.MailConfig;
import me.smallyellow.base.core.utils.StringUtils;
import me.smallyellow.hhy.mapper.UserInfoMapper;
import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.model.dto.UserInfoDTO;

/**
 * 用户相关
 * @author hhy
 * 2017年11月22日下午1:38:56
 */
@Service
public class UserService {
	
	@Autowired
	private MailConfig mailConfig;
	
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
		if (result.getStatus().equals((short) 2)) {
			throw new WebException("账号未验证，请登录邮箱验证");
		}
		if (result.getStatus().equals((short) 3)) {
			throw new WebException("账号已锁定，请联系管理员");
		}
		return result;
	}
	
	/**
	 * 获取用户
	 * @param id
	 * @return
	 * @throws WebException
	 */
	public UserInfo getUser(Long id) throws WebException {
		UserInfo user = userInfoMapper.selectByPrimaryKey(id);
		if (user == null) {
			throw new WebException("用户不存在");
		}
		return user;
	}
	
	/**
	 * 获取用户，加上等级
	 * @param id
	 * @return
	 */
	public UserInfoDTO getUserWithGrade(Long id) {
		UserInfoDTO dto = userInfoMapper.selectUserInfoWithGrade(id);
		if (StringUtils.isEmpty(dto.getNoteGradeName())) {
			dto.setNoteGradeName("暂无等级");
		}
		return dto;
	}
	
	/**
	 * 插入用户
	 * @param info
	 * @throws WebException
	 */
	@Transactional
	public void insertUser(UserInfo info) throws WebException {
		UserInfo user = userInfoMapper.selectUserByusernameOrEmail(info.getUsername(), info.getEmail());
		if (user != null) {
			String exceptionMessage = "";
			if (user.getUsername().equals(info.getUsername())) {
				exceptionMessage = "用户名已经存在;";
			}
			if (user.getEmail().equals(info.getEmail())) {
				exceptionMessage += "邮箱已经存在;";
			}
			throw new WebException(exceptionMessage);
		}
		int result = userInfoMapper.insert(info);
		// 发送邮箱
		try {
			MailOperator.getInstance(mailConfig).sendEmail(info.getEmail());
		} catch (SMTPAddressFailedException e) {
			e.printStackTrace();
			throw new WebException("邮箱不存在");
		} catch (Exception e) {
			e.printStackTrace();
			throw new WebException("发送邮件失败");
		}
		if (result <= 0) {
			throw new WebException("插入失败");
		}
	}
	
	/**
	 * 修改用户
	 * @param info
	 * @throws WebException
	 */
	public void updateUser(UserInfo info) throws WebException {
		int result = userInfoMapper.updateByPrimaryKeySelective(info);
		if (result <= 0) {
			throw new WebException("修改失败");
		}
	}
	
	/**
	 * 修改用户密码
	 * @param info 用户（新密码
	 * @param oldPwd 旧密码
	 */
	public void updateUserPwd(UserInfo info, String oldPwd) throws WebException {
		int result = userInfoMapper.updateByPrimaryKeySelective(info);
		if (result <= 0) {
			throw new WebException("修改密码失败");
		}
	}
	
	/**
	 * 激活用户
	 * @param accessToken
	 */
	public void activateUser(String accessToken) {
		String email = AESOperator.getInstance().decrypt(accessToken);
		UserInfo info = new UserInfo();
		info.setEmail(email);
		UserInfo user = userInfoMapper.selectOne(info);
		if (user != null) {
			user.setStatus((short)1);
			userInfoMapper.updateByPrimaryKeySelective(user);
		}
	}

}
