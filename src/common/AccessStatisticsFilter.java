package common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 访问统计
 * @author jayce
 *
 */
public class AccessStatisticsFilter implements Filter{
    HashMap<String,Integer> accessStatistics = new HashMap<String, Integer>();
	
	public void destroy() {
		
	}
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String uri=((HttpServletRequest)request).getRequestURI();
		Integer clicks = accessStatistics.get(uri);
		if(clicks == null)clicks=0;
		clicks++;
		accessStatistics.put(uri, clicks);
		if(uri.endsWith("/fangwentongji")){
			//这里未进行排序
			request.setAttribute("accessStatistics", accessStatistics);
			request.getRequestDispatcher("/access_statistics_list.jsp").forward(request, response);
			return;
		}

		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

