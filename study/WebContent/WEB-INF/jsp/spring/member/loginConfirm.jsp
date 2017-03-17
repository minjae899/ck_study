<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.container{
	text-align: center;
	margin-top: 15%;
}
</style>
</head>
<body>
<div class="container">
	<h1>${message } </h1>
	<a href="<c:url value='/index.html' />">홈으로</a>
</div>
</body>
</html>