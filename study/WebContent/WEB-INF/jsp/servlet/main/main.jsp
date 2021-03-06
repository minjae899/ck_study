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
	$('#command').val('member');
	$('#resultPg').val('login');
	$('#mainForm').submit();
}

function register(){
	alert("register");
}

function listModal(){
	/*
				- jquery properties
				
		url : 요청이 전송되는 URL이 포함된 문자열 입니다.
		type : Http요청방식 입니다.(get/post)
		timeout : Http요청에 대한 제한 시간을 지정합니다. ( 단위 : ms )
		success : Http요청 성공시 이벤트 핸들러 입니다.
		error : Http요청 실패시 이벤트 핸들러 입니다.
		complete : Http요청 완료시 이벤트 핸들러 입니다.
		data : Http요청 후 return 하는 값 입니다.
		dataType : Http요청 후 return 하는 데이터의 Type을 지정합니다.(xml, html, script, json, jsonp, text)
		async : 요청시 동기 유무를 선택할 수 있습니다. ( True / False )
		cache : 브라우저에 의해 요청되는 페이지를 캐시할 수 있습니다 ( True / False )
		beforeSend : Http 요청 전에 발생하는 이벤트 핸들러 입니다.
		global : 전역함수 활성 여부를 설정합니다.
	
	*/
	$.ajax({
		type : "POST",
		url : "<c:url value = '/DispatcherServlet?command=member&&resultPg=listAjax' />",
		data : {},
		dataType : "html",
		success : function(data){
			/* alert(data);
			$('#ajaxArea').append(data); */
			//alert(data);
			$('.modal-body').html(data);
		},
		error : function( jqXRH, textStatus, errorThrown){
			/*
				status : http 오류 번호 를 출력 합니다. (500, 404 등)
				statusText : 오류 내용 텍스트 -> 세번째 인자 errorThrown과 동일 합니다.
				responseText : url의 response full text를 출력 합니다.
				readyState : ajax readyState를 출력 합니다. (readyState는 부록 1 참조)
			*/
			alert("status : " + jqXRH.status + " \n"
					+ "status text : " + jqXRH.statusText + " \n"
					+ "response text : " + jqXRH.responseText + " \n"
					+ "ready state : " + jqXRH.readyState + "\n"
					+ "textStatus : " + textStatus + "\n"
					+ "errorThrown : " + errorThrown);
		}
	});
	//$('#command').val("list");
	//$('#mainForm').submit();
}

function queryModal(){
	$.ajax({
		type : "POST",
		url : "<c:url value='/queryServlet.do' />",
		data : {},
		dataType : "html",
		error : function(jqXRH, textStatus, errorThrown){
			alert("status : " + jqXRH.status + " \n"
					+ "status text : " + jqXRH.statusText + " \n"
					+ "response text : " + jqXRH.responseText + " \n"
					+ "ready state : " + jqXRH.readyState + "\n"
					+ "textStatus : " + textStatus + "\n"
					+ "errorThrown : " + errorThrown);
		},
		success : function(data){
			alert(data);
			$("#queryModal").html(data);
		}
	});
}
</script>
</head>
<body>
<!-- servlet 버전 -->
<form id="mainForm" action="/study/DispatcherServlet" method="post">
	<input type="hidden" name="command" id="command" value="">
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