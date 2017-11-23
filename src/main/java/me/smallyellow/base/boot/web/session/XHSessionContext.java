package me.smallyellow.base.boot.web.session;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * session统一管理
 * @author hhy
 * 2017年11月23日上午9:43:17
 */
public class XHSessionContext {
	private static HashMap<String, HttpSession> mymap = new HashMap<String, HttpSession>();

    public static synchronized void addSession(HttpSession session) {
        if (session != null) {
            mymap.put(session.getId(), session);
        }
    }

    public static synchronized void delSession(HttpSession session) {
        if (session != null) {
            mymap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
        return null;
        return (HttpSession) mymap.get(session_id);
    }
}
