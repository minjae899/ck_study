<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet">
<link href="resources/css/common.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(function(){
	//var $m = $('#popupArea').modal(), api = $m.data('modal');
});
function check(){
	$('#command').val('check');
	$('#mainForm').submit();
}

function register(){
	alert("register");
}

function list(){
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
		url : "/study/mainServlet.do?command=listAjax",
		data : {},
		dataType : "html",
		success : function(data){
			/* alert(data);
			$('#ajaxArea').append(data); */
			//alert(data);
			alert("hi");
			$('#modalArea').modal(data);
		},
		error : function( jqXRH, textStatus, errorThrown){
			/*
				status : http 오류 번호 를 출력 합니다. (500, 404 등)
				statusText : 오류 내용 텍스트 -> 세번째 인자 errorThrown과 동일 합니다.
				responseText : url의 response full text를 출력 합니다.
				readyState : ajax readyState를 출력 합니다. (readyState는 부록 1 참조)
			*/
			alert("status : " + jqXHR.status + " \n"
					+ "status text : " + jqXHR.statusText + " \n"
					+ "response text : " + jqXHR.responseText + " \n"
					+ "ready state : " + jqXHR.readyState + "\n"
					+ "textStatus : " + textStatus + "\n"
					+ "errorThrown : " + errorThrown);
		}
	});
	
	//$('#command').val("list");
	//$('#mainForm').submit();
}
</script>
</head>
<body>
<form id="mainForm" action="/study/mainServlet.do" method="post">
<input type="hidden" name="command" id="command" value="">
<div class="login_container">
	<div>
		<input type="text" class="input_text" name="user_id" placeholder="input your id" style="margin-bottom: 8px; font-size: 25px;" /><br/>
		<input type="password" class="input_pass" name="user_pw" placeholder="input your password" style="margin-bottom: 8px; font-size: 25px;" /><br/>
	</div>
	<div>
		<a class="ckbtn blue" href="javascript:check();">check</a>
		<a class="ckbtn red" href="javascript:register();">register</a>
		<!-- <a class="ckbtn orange" href="javascript:list();" >list</a> -->
		<a class="ckbtn orange" href="javascript:list();" data-toggle="modal" data-target="#myModal">list</a>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title">출석 리스트</h4>
			</div>
			<div class="modal-body">
				<table>
					<colgroup>
						<col width="*">
						<col width="11%">
						<col width="11%">
						<col width="11%">
						<col width="11%">
						<col width="11%">
						<col width="11%">
						<col width="11%">
						<col width="11%">
					</colgroup>
					<thead>
						<tr>
							<th>날짜</th>
							<th>김준성</th>
							<th>김새봄</th>
							<th>최민재</th>
							<th>이기백</th>
							<th>오형석</th>
							<th>박정하</th>
							<th>이상철</th>
							<th>박민호</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>17.02.18</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>1000</td>
						</tr>
						<tr>
							<td>합계</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>0</td>
							<td>1000</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<!-- <style type="text/css">
	table{
		width: 100%;
	}
</style> -->

</form>
</body>
</html>