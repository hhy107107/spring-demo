package me.smallyellow.hhy.im.command;

import org.tio.core.ChannelContext;
import org.tio.im.common.packets.Command;
import org.tio.im.common.packets.FriendReqBody;
import org.tio.im.common.packets.FriendResult;
import org.tio.im.common.packets.User;
import org.tio.im.server.command.CommandManager;
import org.tio.im.server.command.handler.LoginReqHandler;
import org.tio.im.server.command.handler.proc.friend.FriendCmdHandlerIntf;

import me.smallyellow.hhy.im.service.FriendsService;
import me.smallyellow.hhy.im.service.UserServiceHandler;
import me.smallyellow.hhy.utils.SpringUtil;

/**
 * 好友处理器
 * @author smallYellow
 *
 */
public class FriendHandler implements FriendCmdHandlerIntf{

	private FriendsService friendsService = SpringUtil.getBean(FriendsService.class);
	
	@Override
	public boolean isProtocol(ChannelContext channelContext) {
		return true;
	}

	@Override
	public String name() {
		return null;
	}

	@Override
	public FriendResult handleFriendRequest(FriendReqBody friendReqBody, ChannelContext channelContext) {
		User user = friendReqBody.getUser();
		User friendUser = friendReqBody.getFriendUser();
		boolean result = friendsService.requestFriend(user.getId(), friendUser.getId());
		LoginReqHandler loginReqHandler = CommandManager.getInstance().getCommand(Command.COMMAND_LOGIN_REQ,LoginReqHandler.class);
		UserServiceHandler userServiceHandler = (UserServiceHandler) loginReqHandler.getProcCmdHandler("userServiceHandler");
		userServiceHandler.updateUserMap(friendReqBody.getUser().getId());
		userServiceHandler.updateUserMap(friendReqBody.getFriendUser().getId());
		if (result) {
			return FriendResult.FRIEND_RESULT_OK;
		} else {
			return FriendResult.FRIEND_RESULT_NOT_EXIST;
		}
	}

}
