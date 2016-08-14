package common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	
public class LojinServlet extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("进入servlet");
		String url      = "jdbc:mysql://localhost/dls_crm"; 
        String user     = "root";
        String password = "root";
        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

		boolean flag=false;
		String id=request.getParameter("id");
		String mima=request.getParameter("password");
	

	String sql="select name from user where id=? and password=?";
	try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
		 conn=DriverManager.getConnection(url, user, password);
		 pstmt=conn.prepareStatement(sql);
		 pstmt.setString(1, id);
		 pstmt.setString(2, mima);
		 rs=pstmt.executeQuery();
		 if(rs.next()){
			 //用户合法 
			 flag=true; 
			 //将用户名保存到session中 
			 request.getSession().setAttribute("userName", rs.getString("name"));
		 }else{
			 //保存错误信息
			 request.setAttribute("err","错误的用户名及密码！");
		 }
		 rs.close();
		 pstmt.close();
		 conn.close();
	} catch (SQLException SE) {
		System.out.println("打开数据库失败!");
		SE.printStackTrace();
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	if(flag==true){
		request.getRequestDispatcher("/login_success.jsp").forward(request, response);
	}else{
		request.getRequestDispatcher("/lojin.jsp").forward(request, response);
	}
	
	}

}
