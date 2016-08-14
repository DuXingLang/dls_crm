<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邓林森版客户关系管理系统</title>
</head>
<body background="<%=request.getContextPath()%>/images/2.jpg">
<center>
		<h1>邓林森版客户关系管理系统</h1>
		<hr>
		<br>
		<% 
			if(session.getAttribute("userName")!=null){
				//用户已登录 
		%>
		<h2>登录成功</h2>
		<h2>欢迎
			<font color="blue" size="12"><%=session.getAttribute("userName") %></font>
			光临邓林森版客户关系管理系统
		</h2>
		<br>
		<h3><a href="/dls_crm/List_noteServlet">进入客户信息管理页面</a></h3>
		<%
			}else{
				//用户未登录，提示用户登录并跳转 
				response.setHeader("refresh", "2;URL=login.jsp");
		%>
			<h1>你还未登录，请先登录！！！
			<br>
			两秒后自动跳转到登录窗口！！！
			<br>
			如果没有跳转，请按
			<a href="login.jsp">这里</a></h1>
			<br>
		<%
		}
		%>

</center>
</body>
</html>