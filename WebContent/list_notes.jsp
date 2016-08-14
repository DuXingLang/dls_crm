<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="common.SessionListener" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${ctx}/bootstrap/css/docs.min.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="${ctx}/bootstrap/css/patch.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邓林森版客户关系管理系统</title>
</head>
<body>
   <center>
		<h1><font size=4><a href="${ctx }/List_noteServlet"><input type="button" value="客户信息管理"></a>&nbsp;&nbsp;
		<a href="${ctx }/Link_noteServlet"><input type="button" value="联系人信息管理"></a>&nbsp;&nbsp;
		<a href="${ctx }/Activity_noteServlet"><input type="button" value="活动信息管理"></a></font></h1>
		当前在线人数：<%=SessionListener.activeSessions %><br/>
				<a href="/dls_crm/fangwentongji">访问统计</a><br/>
					<a href=exit.jsp>安全退出</a>
		<hr>
		<br>
			<form action="/dls_crm/List_noteServlet" method="post">
				请输入要查询的信息：
				<input type="text" name="keyword">
				<input type="submit" name="查询">
			</form>
			<h3>
				<a href="${ctx }/List_noteServlet?method=createForm">新增客户信息</a>
			</h3>		
	  	    <table width="80%" border="1">
			<tr>
			<th>序号</th>
			<th>客户ID</th>
			<th>客户名称</th>
			<th>类型</th>
			<th>省份</th>
			<th>城市</th>
			<th><a href="${ctx }/Link_noteServlet">联系人</a></th>
			<th>备注</th>
			<th>维护人</th>
			<th>维护时间</th>
			<th>修改</th>
			<th>删除</th>
		</tr>
		
		<c:forEach items="${notes}" var="note" varStatus="status"> 
		<tr>
			<td>${status.count}</td>
			<td>${note.getId()}</td>
			<td>${note.getName()}</td>
			<td>${note.getType()}</td>
			<td>${note.getProvince()}</td>
			<td>${note.getCity()}</td>
			<td>${note.getLinkman()}</td>
			<td>${note.getRemark()}</td>
			<td>${note.getPerson()}</td>
			<td>${note.getDate()}</td>
			<td><a href="${ctx }/List_noteServlet?method=editForm&id=${note.getId()}">修改</a></td>
			<td><a href="${ctx }/List_noteServlet?method=delete&id=${note.getId()}">删除</a></td>
		</tr>
		</c:forEach>
		</table>
        </center>
</body>
</html>