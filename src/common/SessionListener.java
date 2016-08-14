package common;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session������
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
		System.out.println("������������ SessionListener.sessionCreated()�������� ʱ����"+d.toLocaleString()
				+" session id="+session.getId());
		//ͨ��javascript�򹤾߿�һ�����������cookie��JSSIONID��ֵ������Ĵ�ӡ����id���ݱȽ�һ�¡�
		activeSessions++ ;
	}

	@SuppressWarnings("deprecation")
	public void sessionDestroyed(HttpSessionEvent httpsessionevent) {
		HttpSession session = httpsessionevent.getSession();
		Date d = new Date();
		System.out.println("�������߻�session��ʱ�ˣ�SessionListener.sessionDestroyed() ʱ����="+d.toLocaleString()
				+" session id="+session.getId());
		if(activeSessions > 0)activeSessions--;
	}
	public int getActiveSessions(){
		return activeSessions;
	}
	
}

