<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
	table{
		width: 100%;
		text-align: center;
	}
</style>
<div style="width: 100%;">
	<c:choose>
		<c:when test="${empty resultList}">
			<h3 style="padding: 20px;"><font color="red"> * </font>아직 시작한 이번달 스터디 일정이 없습니다.</h3>
		</c:when>
		<c:otherwise>
			<c:if test="${not empty myPenalty}">
				<div align="right">
					<h2>지각비 합계 : <font color="red"><fmt:formatNumber value="${myPenalty}" pattern="#,###.##" /></font>원</h2>
				</div>
			</c:if>
			<c:forEach var="resultMap" items="${resultList }">
				<c:forEach var="val" items="${resultMap }">
					<h2><font color="red">*</font> ${val.key }</h2>
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
									<td><fmt:formatNumber value="${obj.penalty }" pattern="#,###.##" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:forEach>
			</c:forEach>
			<c:if test="${not empty myPenalty}">
				<div align="right">
					<h2>지각비 합계 : <font color="red"><fmt:formatNumber value="${myPenalty}" pattern="#,###.##" /></font>원</h2>
				</div>
			</c:if>
		</c:otherwise>
	</c:choose>
</div>