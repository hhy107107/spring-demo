package me.smallyellow.base.boot.web.session.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import me.smallyellow.base.boot.web.session.XHSessionContext;

/**
 * 监听器，将session 保存到XHSessionContext 中
 * @author hhy
 * 2017年11月23日上午9:40:39
 */
@WebListener
public class XHSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		XHSessionContext.addSession(se.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		XHSessionContext.delSession(se.getSession());
	}

}