<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.notice-footer .button{
	cursor: pointer;
}
</style>
<form id="doQueryFrom" name="doQueryFrom" method="post">
	<div class="modal-dialog">
		<div class="modal-content" style="z-index:10000; min-height: 700px; padding: 10px;">
			<div class="modal-header tabmnu">
				<span style="font-weight:bold; font-size:18px; color:#2b9cc9;">문의하기</span>
				<button type="button" class="close cyan marginTopMinus5 fontSize31" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body people_popup_body padding0">
				<div class="notice-contents">
					<table class="formWrap">
						<tbody>
							<tr>
								<th><label for="title">제목</label></th>
								<td><input type="text" id="title" name="title" class="formWidth" /></td>
							</tr>
							<tr>
								<th><label for="content">내용</label></th>
								<td><textarea id="content" name="content" class="formWidth"></textarea></td>
							</tr>
							<tr class="last">
								<th><label for="fileAdd">파일첨부</label></th>
								<td><input type="file" id="fileAdd" name="fileAdd" /></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="notice-footer">
				<span class="button"><a href="#;" onclick="queryRegister(); return false;" >등록</a></span>
				<span class="button" data-dismiss="modal" aria-hidden="true" ><a href="#;" onclick="return false;" >취소</a></span>
			</div>
		</div> 
	</div>
</form>