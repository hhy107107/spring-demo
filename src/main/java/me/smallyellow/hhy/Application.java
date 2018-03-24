package me.smallyellow.hhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tio.im.common.ImConfig;
import org.tio.im.common.packets.Command;
import org.tio.im.server.ImServerStarter;
import org.tio.im.server.command.CommandManager;
import org.tio.im.server.command.handler.HandshakeReqHandler;
import org.tio.im.server.command.handler.LoginReqHandler;

import com.jfinal.kit.PropKit;

import me.smallyellow.base.boot.web.EnableWebPath;
import me.smallyellow.hhy.im.command.WsHandshakeHandler;
import me.smallyellow.hhy.im.init.HttpServerInit;
import me.smallyellow.hhy.im.listener.ImDemoAioListener;
import me.smallyellow.hhy.im.service.UserServiceHandler;

@SpringBootApplication(scanBasePackages = "me.smallyellow", exclude = {DataSourceAutoConfiguration.class})
@EnableWebPath
@ServletComponentScan("me.smallyellow")
@EnableTransactionManagement
public class Application {
    public static void main( String[] args ) {
    	SpringApplication.run(Application.class, args);
		try {
			PropKit.use("application.properties");
			int port = PropKit.getInt("port");//启动端口
			ImConfig imConfig = new ImConfig(null, port);
			HttpServerInit.init(imConfig);
			//ImgMnService.start();//启动爬虫爬取模拟在线人头像;
			ImServerStarter imServerStarter = new ImServerStarter(imConfig,new ImDemoAioListener());
			HandshakeReqHandler handshakeReqHandler = CommandManager.getInstance().getCommand(Command.COMMAND_HANDSHAKE_REQ,HandshakeReqHandler.class);
			handshakeReqHandler.addProcCmdHandler(new WsHandshakeHandler());//添加自定义握手处理器;
			LoginReqHandler loginReqHandler = CommandManager.getInstance().getCommand(Command.COMMAND_LOGIN_REQ,LoginReqHandler.class);
			loginReqHandler.addProcCmdHandler(new UserServiceHandler());//添加登录业务处理器;
			imServerStarter.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
    }
}
