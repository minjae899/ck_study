<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%--

1.스크립틀릿 : 자바코드를 쓰는곳.
<% 자바코드가 들어간다. %>

2.익스프레션 : servlet  out.print(); 
<%= 스크립틀릿 변수  %>

--%>
</head>
<body>
	<%= request.getParameter("name") %>
</body>
</html>