<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="width: 100%; text-align: center;">
	<c:forEach var="val" items="${list }">
		<h1>${val.key}</h1>
			<table>
				<colgroup>
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="20%">
					<col width="20%">
				</colgroup>
				<thead>
					<tr>
						<th>이름</th>
						<th>아이디</th>
						<th>전화번호</th>
						<th>출석일</th>
						<th>출석시간</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="obj" items="${val.value }" varStatus="status">
						<tr>
							<td>${obj.name }</td>
							<td>${obj.id }</td>
							<td>${obj.tell }</td>
							<td>${obj.checkDate }</td>
							<td>${obj.checkTime }</td>
						</tr>
					</c:forEach>
					<tr>
						<td>합계</td>
						<td>0</td>
						<td>0</td>
						<td>0</td>
						<td>1000</td>
					</tr>
				</tbody>
			</table>
	</c:forEach>
</div>
</body>
</html>