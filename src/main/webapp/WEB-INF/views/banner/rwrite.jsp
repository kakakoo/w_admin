<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 배너</title>
	
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
				* 배너 관리 > 예약 배너 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/banner/rsv/write" method="post">
			<input type="hidden" name="bnrId" value="${info.bnrId }">
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
					<td>타입</td>
					<td><input type="text" name="bnrTp" id="bnrTp" style="width:100%;" value="${info.bnrTp }"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><input type="text" name="bnrCont" id="bnrCont" style="width:100%;" value="${info.bnrCont }"></td>
				</tr>
				<tr>
					<td>상태</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="bnrSt" value="Y" <c:if test="${info.bnrSt == 'Y'}">checked</c:if><c:if test="${empty info.bnrSt}">checked</c:if>> 진행중
						<input type="radio" style="margin: 4px 4px 0px;" name="bnrSt" value="N" <c:if test="${info.bnrSt == 'N'}">checked</c:if>> 종료
						<input type="radio" style="margin: 4px 4px 0px;" name="bnrSt" value="O" <c:if test="${info.bnrSt == 'O'}">checked</c:if>> 임시
					</td>
				</tr>
				<tr>
					<td>적용기간</td>
					<td style="text-align:left;"><input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${info.startDt } - ${info.endDt }" /></td>
				</tr>
				<tr>
					<td rowspan="2">이동화면</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="redirectType" value="A" <c:if test="${info.redirectType == 'A'}">checked</c:if><c:if test="${empty info.redirectType}">checked</c:if>> APP내 화면
						<select name="redirectApp" id="redirectApp" style="margin-left:10px;width:400px;" >
							<c:forEach var="list" items="${appList }">
								<option value="${list.key }" <c:if test="${info.redirectApp == list.key}">selected</c:if>>${list.value }</option>
							</c:forEach>
						</select>
						<select name="eventId" id="eventId" style="margin-left:10px;width:200px;display:none;" >
							<c:forEach var="list" items="${eventList }">
								<option value="${list.EVENT_ID }" <c:if test="${info.eventId == list.EVENT_ID}">selected</c:if>>${list.EVENT_TITLE }</option>
							</c:forEach>
						</select>
						<select name="noticeId" id="noticeId" style="margin-left:10px;width:200px;display:none;" >
							<c:forEach var="list" items="${noticeList }">
								<option value="${list.NOTICE_ID }" <c:if test="${info.noticeId == list.NOTICE_ID}">selected</c:if>>${list.NOTICE_TITLE }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="redirectType" value="U" <c:if test="${info.redirectType == 'U'}">checked</c:if>> APP외 URL
						<input type="text" name="redirectUrl" id="redirectUrl" style="margin-left:10px;width:400px;" value="${info.redirectUrl }">
					</td>
				</tr>
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

	$("#redirectApp").change(function(){
		var val = $(this).val();
		if( val == 'event'){
			$('#eventId').css('display','initial');
			$('#noticeId').css('display','none');
		} else if( val == 'notice'){
			$('#noticeId').css('display','initial');
			$('#eventId').css('display','none');
		} else {
			$('#noticeId').css('display','none');
			$('#eventId').css('display','none');
		}
	});
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

		var url = '${pageContext.request.contextPath}/api/banner/rsv/';
		FORM_BODY.attr('action', url + action);
		FORM_BODY.submit();
	}

}
</script>
</html>