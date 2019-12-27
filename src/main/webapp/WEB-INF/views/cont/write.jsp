<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 컨텐츠</title>
	
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
				* 컨텐츠 관리 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/event/write" method="post">
			<input type="hidden" name="contId" id="contId" value="${info.contId }">
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
					<td>제목</td>
					<td><input type="text" name="contTitle" id="contTitle" style="width:100%;" value="${info.contTitle }"></td>
				</tr>
				<tr>
					<td>카테고리</td>
					<td><input type="text" name="contCtgr" id="contCtgr" style="width:100%;" value="${info.contCtgr }"></td>
				</tr>
				<tr>
					<td>리스트타입</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="listType" value="L" <c:if test="${info.listType == 'L'}">checked</c:if><c:if test="${empty info.listType}">checked</c:if>> 리스트
						<input type="radio" style="margin: 4px 4px 0px;" name="listType" value="S" <c:if test="${info.listType == 'S'}">checked</c:if>> 슬라이드
					</td>
				</tr>
				<tr>
					<td>컨텐츠 상태</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="contSt" value="Y" <c:if test="${info.contSt == 'Y'}">checked</c:if><c:if test="${empty info.contSt}">checked</c:if>> 사용
						<input type="radio" style="margin: 4px 4px 0px;" name="contSt" value="N" <c:if test="${info.contSt == 'N'}">checked</c:if>> 정지
					</td>
				</tr>
				<tr>
					<td>사용기간</td>
					<td style="text-align:left;"><input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${info.startDt } - ${info.endDt }" /></td>
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
			
			<c:if test="${result == '2' }">
				<table class="list_table">
					<colgroup>
						<col style="width:5%"/>
						<col style="width:17%"/>
						<col style="width:17%"/>
						<col style="width:10%"/>
						<col style="width:17%"/>
						<col style="width:17%"/>
						<col style="width:17%"/>
					</colgroup>
					<tr>
						<td></td>
						<td>제목</td>
						<td>부제</td>
						<td>색상</td>
						<td>이미지</td>
						<td>경로</td>
						<td>표시기간</td>
					</tr>
					<c:forEach var="items" items="${contList }">
						<tr>
							<td><input type="radio" style="margin: 4px 4px 0px;" name="clVal" value="${items.clId }"></td>
							<td><nobr><font title="${items.clTitle }">${items.clTitle }</font></nobr></td>
							<td><nobr><font title="${items.clSubTitle }">${items.clSubTitle }</font></nobr></td>
							<td><nobr><font title="${items.clColor }">${items.clColor }</font></nobr></td>
							<td></td>
							<td><nobr><font title="${items.clUrl }">${items.clUrl }</font></nobr></td>
							<td><nobr><font title="${items.startDt } ~ ${items.endDt }">${items.startDt } ~ ${items.endDt }</font></nobr></td>
						</tr>
					</c:forEach>
				</table>
				
				<div class="title">
				</div>
				<div class="refresh" style="margin-top:10px;">
					<input type="button" class="btn btn-success" onclick="addCL('A')" value="추가">
					<input type="button" class="btn btn-success" onclick="addCL('U')" value="수정">
					<input type="button" class="btn btn-success" onclick="modiCL('D')" value="삭제">
					<input type="button" class="btn btn-success" onclick="modiCL('P')" value="위로">
					<input type="button" class="btn btn-success" onclick="modiCL('W')" value="아래로">
				</div>
			</c:if>
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

function addCL(tp){
	var contId = $('#contId').val();
	if(tp == 'A'){
		location.href='${pageContext.request.contextPath}/api/cont/list/write?contId=' + contId;
	} else {
		var clId = $("input[name=clVal]:checked").val();
		location.href='${pageContext.request.contextPath}/api/cont/list/write?contId=' + contId + '&id=' + clId;
	}
}

function modiCL(tp){
	
	var clId = $("input[name=clVal]:checked").val();
	
	var data = {};
	data["tp"] = tp;
	data["clId"] = clId;
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/cont/updateContList",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			
			var result = result.result;
			if(result == 'success'){
				alert('변경되었습니다.');
				location.reload(true);
			} else {
				alert('실패');
			}
			
		},
		error : function(result){
			alert('업데이트 실패, 에러');
		}
	});
}

function writeAction(action){
	
	var FORM_BODY = $('form[name=formBody]');

	var url = '${pageContext.request.contextPath}/api/cont/';
	FORM_BODY.attr('action', url + action);
	FORM_BODY.submit();

}
</script>
</html>