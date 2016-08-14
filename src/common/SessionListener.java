package common;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session监听器
 * @author jayce
 *
 */
public class SessionListener implements HttpSessionListener{

	public static int activeSessions=0;

	@SuppressWarnings("deprecation")
	public void sessionCreated(HttpSessionEvent httpsessionevent) {
		HttpSession session = httpsessionevent.getSession();
		Long creationTime = session.getCreationTime();
		Date d = new Date(creationTime);
		System.out.println("又有人上线了 SessionListener.sessionCreated()被调用了 时间是"+d.toLocaleString()
				+" session id="+session.getId());
		//通过javascript或工具看一下你浏览器的cookie里JSSIONID的值与这里的打印出的id内容比较一下。
		activeSessions++ ;
	}

	@SuppressWarnings("deprecation")
	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		HttpSession session = httpsessionevent.getSession();
		Date d = new Date();
		System.out.println("有人下线或session超时了，SessionListener.sessionDestroyed() 时间是="+d.toLocaleString()
				+" session id="+session.getId());
		if(activeSessions > 0)activeSessions--;
	}
	public int getActiveSessions(){
		return activeSessions;
	}
	
}

