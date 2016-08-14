<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邓林森版客户关系管理系统退出</title>
</head>
<body>
<%
          if(session.getAttribute("userName")!=null){
            session.invalidate();
            out.println("<script language='javascript'>alert('确定要退出吗？');</script>");
          }
      %>
</body>
</html>