<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 담당자 관리</title>
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	

</head>
<body class="nav-md">
    <div class="container body">
      <div class="main_container">
	
		<jsp:include page="../common/left.jsp"/>
		<jsp:include page="../common/header.jsp"/>

        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 담당자 관리 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/version/mng/write" method="post">
			<input type="hidden" name="mgId" id="mgId" value="${info.mgId }">
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
					<td>담당자 이름</td>
					<td><input type="text" name="mgNm" id="mgNm" style="width:100%;" value="${info.mgNm }"></td>
				</tr>
				<tr>
					<td>담당자 연락처</td>
					<td><input type="text" name="mgTel" id="mgTel" style="width:100%;" value="${info.mgTel }"></td>
				</tr>
				<tr>
					<td>수신여부</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="mgSt" value="Y" <c:if test="${info.mgSt == 'Y'}">checked</c:if><c:if test="${empty info.mgSt}">checked</c:if>> 받음
						<input type="radio" style="margin: 4px 4px 0px;" name="mgSt" value="N" <c:if test="${info.mgSt == 'N'}">checked</c:if>> 안받음
					</td>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<c:choose>
					       <c:when test="${result == '2' }">
					           <input type="button" class="btn btn-success" onclick="writeAction('update')" value="수정">
<!-- 					           <input type="button" class="btn btn-danger" onclick="writeAction('delete')" value="삭제"> -->
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

});

function writeAction(action){
	
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			var mgId = $('#mgId').val();
			location.href='${pageContext.request.contextPath}/api/version/mng/delete?id=' + mgId;
			return;
		}
	}
	
	var FORM_BODY = $('form[name=formBody]');

	var url = '${pageContext.request.contextPath}/api/version/mng/';
	FORM_BODY.attr('action', url + action);
	FORM_BODY.submit();

}
</script>
</html>