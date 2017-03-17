<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<link href="resources/css/lobster_font.css" rel="stylesheet">
<link href="resources/css/common.css" rel="stylesheet">
<link href="resources/bootstrap-3.3.2-dist/css/bootstrap.css" rel="stylesheet">
<script src="resources/js/jquery-3.1.1.js"></script>
<script src="resources/bootstrap-3.3.2-dist/js/bootstrap.js"></script>
<script>
$(function(){
	$('#listModal').on('show.bs.modal', function(e){
		listModal();
	});
	
	$('#queryModal').on('show.bs.modal', function(e){
		queryModal();
	});
	
});
function login(){
	$('#mainForm').submit();
}

function register(){
	alert("register");
}

function listModal(){
	$.ajax({
		type : "POST",
		url : "<c:url value = '/showAttendAllMember.do' />",
		data : {},
		dataType : "html",
		success : function(data){
			$('.modal-body').html(data);
		},
		error : function( jqXRH, textStatus, errorThrown){
			alert("status : " + jqXRH.status + " \n"
					+ "status text : " + jqXRH.statusText + " \n"
					+ "response text : " + jqXRH.responseText + " \n"
					+ "ready state : " + jqXRH.readyState + "\n"
					+ "textStatus : " + textStatus + "\n"
					+ "errorThrown : " + errorThrown);
		}
	});
}

</script>
</head>
<body>
<form id="mainForm" action="/login.do" method="post">	
	<input type="hidden" name="resultPg" id="resultPg" value="">
	<div class="top">
		<!-- <a data-toggle="modal" data-target="#queryModal">문의하기</a> -->
	</div>
	<c:if test="${empty sessionScope.loginVO}">
	<div class="login_container">
		<div>
			<input type="text" class="input_text" name="id" placeholder="input your id" style="margin-bottom: 8px; font-size: 25px;" maxlength="12" /><br/>
			<input type="password" class="input_pass" name=pw placeholder="input your password" style="margin-bottom: 8px; font-size: 25px;" maxlength="12" /><br/>
		</div>
		<div>
			<a class="ckbtn blue" href="javascript:login();">check</a>
			<!-- <a class="ckbtn red" href="javascript:register();">register</a> -->
		</div>
	</div>
	</c:if>
	<c:if test="${not empty sessionScope.loginVO}">
	<div class="login_container">
		<div>
			<a class="ckbtn orange" href="#;" data-toggle="modal" data-target="#listModal">list</a>
		</div>
	</div>
	</c:if>
	
	<!--모달. -->
	<div id="listModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h1 class="modal-title" id="myLargeModalLabel">지각비 현황</h1>
				</div>
				<div class="modal-body">로딩중....</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!--모달. -->
	<div id="queryModal" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true"></div>
</form>
</body>
</html>