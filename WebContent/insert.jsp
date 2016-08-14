<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*;" %>
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
		<h1>邓林森版客户关系管理系统</h1>
		<hr>
		<br>
		<%
			if(session.getAttribute("userName")!=null){
				//用户已登录 
		%>
			<form action="/dls_crm/List_noteServlet?method=save" method="post">
				<table width="80%" border="1">
					<tr>
						<td colspan="2" align="char">
							<h3>客户信息编辑</h3>
						</td>
					</tr>
					<tr>
						<td>
							客户名称:
						</td>
						<td>
							<input type="hidden" name="id" value=${note.getId() }>
							<input type="text" name="name" value=${note.getName() }>
						</td>
					</tr>
					<tr>
						<td>
							类型:
						</td>
						<td>
							<input type="text" name="type" value=${note.getType() }>
						</td>
					</tr>
					<tr>
						<td>
							省份:
						</td>
						<td>
							<input type="text" name="province" value=${note.getProvince() }>
						</td>
					</tr>
					<tr>
						<td>
							城市:
						</td>
						<td>
							<input type="text" name="city" value=${note.getCity() }>
						</td>
					</tr>
					<tr>
						<td>
							联系人:
						</td>
						<td>
							<input type="text" name="linkman" value=${note.getLinkman() }>
						</td>
					</tr>
					<tr>
						<td>
							备注:
						</td>
						<td>
							<textarea name="remark" cols="70" rows="6">${note.getRemark() }</textarea>
						</td>
					</tr>
					<tr>
						<td>
							维护人:
						</td>
						<td>
							<input type="text" name="person" value=${note.getPerson() }>
						</td>
					</tr>
					<tr>
						<td>
							维护时间:
						</td>
						<td>
							<input type="text" name="date" value=${note.getDate() }>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="添加">
							<input type="reset" value="重置">
						</td>
					</tr>
				</table>
			</form>
			<h3>
				<a href="list_notes.jsp">回到客户信息列表页面</a>
			</h3>		
	<%
			}else{
				//用户未登录，提示用户登录并跳转 
				response.setHeader("refresh", "2;URL=login.jsp");
		%>
			<h1>你还未登录，请先登录！！！
			<br>
			两秒后自动跳转到登录窗口！！！
			<br>
			如果没有跳转，请点击
			<a href="login.jsp">这里</a></h1>
			<br>
	<%
			}
	%>
		
		</center>
</body>
</html>