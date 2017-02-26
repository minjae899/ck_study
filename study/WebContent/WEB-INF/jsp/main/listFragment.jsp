<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
	table{
		width: 100%;
		text-align: center;
	}
</style>
<div style="width: 100%;">
	<c:forEach var="resultMap" items="${resultList }">
		<c:forEach var="val" items="${resultMap }">
			<h1><font color="red">*</font> ${val.key }</h1>
			<table class="table table-striped">
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
						<th>출석일</th>
						<th>출석시간</th>
						<th>지각비</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="obj" items="${val.value }" varStatus="status">
						<tr>
							<td>${obj.name }</td>
							<td>${obj.id }</td>
							<td>${obj.checkDate }</td>
							<td>${obj.checkTime }</td>
							<td>${obj.penalty }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:forEach>
	</c:forEach>
</div>