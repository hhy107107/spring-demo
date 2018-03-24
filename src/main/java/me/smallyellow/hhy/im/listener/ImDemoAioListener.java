/**
 * 
 */
package me.smallyellow.hhy.im.listener;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tio.core.Aio;
import org.tio.core.ChannelContext;
import org.tio.core.intf.Packet;
import org.tio.im.common.ImPacket;
import org.tio.im.common.ImSessionContext;
import org.tio.im.common.packets.ChatBody;
import org.tio.im.common.packets.Command;
import org.tio.im.common.packets.Group;
import org.tio.im.common.packets.User;
import org.tio.im.common.tcp.TcpPacket;
import org.tio.im.common.ws.WsResponsePacket;
import org.tio.im.server.command.handler.ChatReqHandler;
import org.tio.im.server.command.handler.JoinGroupReqHandler;
import org.tio.im.server.listener.ImServerAioListener;

import com.alibaba.fastjson.JSONObject;

/**
 * @author WChao
 *
 */
public class ImDemoAioListener extends ImServerAioListener{
	
	private Logger log = LoggerFactory.getLogger(ImDemoAioListener.class);
	
	@Override
	public void onAfterSent(ChannelContext channelContext, Packet packet, boolean isSentSuccess) {
		ImPacket imPacket = (ImPacket)packet;
		if(imPacket.getCommand() == Command.COMMAND_LOGIN_RESP || imPacket.getCommand() == Command.COMMAND_HANDSHAKE_RESP){
//			ImSessionContext imSessionContext = (ImSessionContext) channelContext.getAttribute();
//			User user = imSessionContext.getClient().getUser();
//			ChatBody chatBody = new ChatBody()
//					.setFrom(channelContext.getId())
//					.setTo(channelContext.getId())
//					.setMsgType("text")
//					.setContent("普通Socket客户端转Ws消息测试!");
//			WsResponsePacket chatPacket = new WsResponsePacket();
//			chatPacket.setCommand(Command.COMMAND_CHAT_REQ);
//			chatPacket.setBody(JSONObject.toJSONBytes(chatBody));
//			Aio.send(channelContext, chatPacket);
//			try {
//				new ChatReqHandler().handler(chatPacket, channelContext);
//			} catch (Exception e) {
//				log.error(e.toString(), e);
//			}
		}
		/*if(imPacket.getCommand() == Command.COMMAND_LOGIN_RESP || imPacket.getCommand() == Command.COMMAND_HANDSHAKE_RESP){//首次登陆;
			ImSessionContext imSessionContext = (ImSessionContext)channelContext.getAttribute();
			User user = imSessionContext.getClient().getUser();
			if(user.getGroups() != null){
				for(Group group : user.getGroups()){//绑定群组并发送加入群组通知
					ImPacket groupPacket = new ImPacket(Command.COMMAND_JOIN_GROUP_REQ,JSONObject.toJSONBytes(group));
					try {
						new JoinGroupReqHandler().handler(groupPacket, channelContext);
					} catch (Exception e) {
						log.error(e.toString(),e);
					}
				}
			}
		}*/
	}
}
