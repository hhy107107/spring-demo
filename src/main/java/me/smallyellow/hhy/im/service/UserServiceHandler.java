/**
 * 
 */
package me.smallyellow.hhy.im.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.tio.core.ChannelContext;
import org.tio.im.common.Const;
import org.tio.im.common.ImSessionContext;
import org.tio.im.common.http.HttpConst;
import org.tio.im.common.packets.Group;
import org.tio.im.common.packets.LoginReqBody;
import org.tio.im.common.packets.User;
import org.tio.im.common.session.id.impl.UUIDSessionIdGenerator;
import org.tio.im.common.utils.Md5;
import org.tio.im.server.command.handler.proc.login.LoginCmdHandlerIntf;

import me.smallyellow.hhy.model.UserInfo;
import me.smallyellow.hhy.service.UserService;
import me.smallyellow.hhy.utils.SpringUtil;

/**
 * @author WChao
 *
 */
public class UserServiceHandler implements LoginCmdHandlerIntf{

	public static final Map<String, User> tokenMap = new HashMap<>();
	
	private UserService userService = SpringUtil.getBean(UserService.class);
	
	private FriendsService friendsService = SpringUtil.getBean(FriendsService.class);


	/**
	 * 根据用户名和密码获取用户
	 * @param loginname
	 * @param password
	 * @return
	 * @author: WChao
	 */
	public User getUser(String loginname, String password) {
		String text = loginname+password;
		String key = Const.authkey;
		String token = Md5.sign(text, key, HttpConst.CHARSET_NAME);
		User user = getUserByToken(token, loginname, password);
		user.setId(loginname);
		return user;
	}
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 * @author: WChao
	 */
	public User getUserByToken(String token, String loginname, String password) {
		//demo中用map，生产环境需要用cache
		User user = tokenMap.get(token);
		if (user == null) {
			user = new User();
			//user.setId(UUIDSessionIdGenerator.instance.sessionId(null));
			UserInfo userInfo = userService.login(loginname, password);
			user.setId(userInfo.getId().toString());
			user.setNick(userInfo.getName());
			user.setGroups(initGroups(user));
			user.setFriends(initFriends(user));
			user.setAvatar(userInfo.getUserface());
			
			if (tokenMap.size() > 10000) {
				tokenMap.clear();
			}
			tokenMap.put(token, user);
		}
		return user;
	}
	
	public List<Group> initGroups(User user){
		//模拟的群组;正式根据业务去查数据库或者缓存;
		List<Group> groups = new ArrayList<Group>();
		groups.add(new Group("100","tio-im朋友圈"));
		return groups;
	}
	public List<Group> initFriends(User user){
		//UUIDSessionIdGenerator.instance.sessionId(null)
		List<Group> friends = friendsService.listFriends(Long.parseLong(user.getId()));
		return friends;
	}
	
	public String nextImg() {
		return ImgMnService.nextImg();
	}

	public String newToken() {
		return UUID.randomUUID().toString();
	}
	
	@Override
	public User getUser(LoginReqBody loginReqBody , ChannelContext channelContext) {
		String loginname = loginReqBody.getLoginname();
		String password = loginReqBody.getPassword();
		ImSessionContext imSessionContext = (ImSessionContext)channelContext.getAttribute();
		String handshakeToken = imSessionContext.getToken();
		if (!StringUtils.isBlank(handshakeToken)) {
			return this.getUserByToken(handshakeToken, loginname, password);
		}
		return this.getUser(loginname, password);
	}
	
	@Override
	public boolean isProtocol(ChannelContext channelContext) {
		 
		return true;
	}

	@Override
	public String name() {
		
		return "default";
	}
}
