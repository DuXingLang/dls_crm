<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<table style="width:80%" class="table table-hover table-bordered">
		<tr>
			<th>序号</th><th>URI</th><th>次数</th>
		</tr>
		
		<c:forEach items="${accessStatistics}" var="accessStatistic" varStatus="status">
		<tr>
			<td>${status.count}</td>
			<td>${accessStatistic.key}</td>
			<td>${accessStatistic.value}</td>
		</tr>
		</c:forEach>
		</table>
		</center>
</body>
</html>