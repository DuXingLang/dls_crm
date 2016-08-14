package common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * µÇÂ¼¼ìÑé¹ýÂËÆ÷
 * @author jayce
 *
 */
public class LoginCheckFilter implements Filter{

public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session=((HttpServletRequest)request).getSession();
		String url=((HttpServletRequest)request).getRequestURI();

			String userName=(String)session.getAttribute("userName");
			if(null==userName||"".equals(userName)){
				if( !url.contains("lojin")){
					request.getRequestDispatcher("/lojin.jsp").forward(request,response);
					return;
				}
			}
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}

