<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邓林森版客户关系管理系统</title>
</head>
<body background="<%=request.getContextPath()%>/images/1.jpg">
<center>
		<h1><font size="+4">邓林森版客户关系管理系统登陆</font></h1>
		<hr>
		<br>
		<br>
		<br>
		<br>
		<br>
		<%
			//判断是否有错误信息，如果有则打印 
			//如果没有此段代码，则显示直接打印null
			if (request.getAttribute("err") != null) {
		%>
		<h2><%=request.getAttribute("err")%></h2>
		<%
			}
		%>
		<form action="/dls_crm/lojinServlet" method="post">
			<table width="80%" cellpadding="10"> 
				<tr>
					<td colspan="2" align="center"><font size="+3" face="华文行楷" color="094f3e">用户登录</font></td>
				</tr>
				<tr align="center">
					<td width="80" align="right">账&nbsp;&nbsp;号：</td>
					<td width="100" align="left"><input type="text" name="id"></td>
				</tr>
				<tr align="center">
					<td width="80" align="right">密&nbsp;&nbsp;码：</td>
					<td width="100" align="left"><input type="password" name="password"></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>验证码:</td> -->
<!-- 					<td><input type="text" id="validation_code" name="validation_code" style="width:60px;margin-top:2px size="30" maxlength="30"/> -->
<!-- 					<img id="img_validation_code" src="ValidationCode"/> -->
<!-- 					<input type="button" value="刷新" onclick="refresh()"/> -->
<%-- 					<font color="#FF0000">${requestScope.codeError}</font> --%>
<!-- 					</td> -->
<!-- 				</tr> -->
				<tr>
					<td align="center" colspan="2"><input type="submit" value="登录"> <input
						type="reset" value="重置"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>