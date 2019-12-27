<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 매니저</title>
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	

</head>
<body class="nav-md">
    <div class="container body">
      <div class="main_container">
	
		<jsp:include page="../common/left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		<input type="hidden" id="alarm" value="${result }">

        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 메니저 공지사항 > 
				<c:choose>
			       <c:when test="${result == '2' }">
			           수정
			       </c:when>
			       <c:otherwise>
			           등록 
			       </c:otherwise>
			   </c:choose>
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/staff/notice/write" method="post">
			<input type="hidden" name="anId" value="${info.anId }">
			<table class="list_table">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:80%"/>
				</colgroup>
				<tr>
					<td>구분</td>
					<td>내용</td>
				</tr>
				
				<tr>
					<td>공지사항 제목</td>
					<td><input type="text" name="anTitle" style="width:100%;" value="${info.anTitle }"></td>
				</tr>
				<tr>
					<td>공지사항 내용</td>
					<td><textarea style="width:100%;" cols="5" name="anDesc">${info.anDesc }</textarea></td>
				</tr>
				<c:if test="${result == '2' }">
				<tr>
					<td>노출여부</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="anSt" value="Y" <c:if test="${info.anSt == 'Y'}">checked</c:if><c:if test="${empty info.anSt}">checked</c:if>> ON
						<input type="radio" style="margin: 4px 4px 0px;" name="anSt" value="N" <c:if test="${info.anSt == 'N'}">checked</c:if>> OFF
					</td>
				</tr>
				</c:if>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<c:choose>
					       <c:when test="${result == '2' }">
					           <input type="button" class="btn btn-success" onclick="writeAction('update')" value="수정">
					           <input type="button" class="btn btn-danger" onclick="writeAction('delete')" value="삭제">
					       </c:when>
					       <c:otherwise>
					           <input type="button" class="btn btn-success" onclick="writeAction('write')" value="등록">
					       </c:otherwise>
					   </c:choose>
					</td>
				</tr>
			</table>
			</form>
		</div>
		
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">
$(function () {
	var alarm = $('#alarm').val();
	if(alarm == '1'){
		alert('등록에 실패하였습니다.');
	}
	
});


function writeAction(action){
	
	var flag = false;
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			flag = true;
		}
	} else {
		flag = true;
	}
	
	if(flag){
		
		var FORM_BODY = $('form[name=formBody]');

		var url = '${pageContext.request.contextPath}/api/staff/notice/';
		FORM_BODY.attr('action', url + action);
		FORM_BODY.submit();
	}
	
}
</script>
</html>