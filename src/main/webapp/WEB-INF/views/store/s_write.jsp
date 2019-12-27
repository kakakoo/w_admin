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
				* 매니저 관리 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/store/staff/write" method="post">
			<input type="hidden" name="adminKey" value="${info.adminKey }">
			<input type="hidden" name="storeId" id="storeId">
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
					<td>담당 지점</td>
					<td style="text-align:left;">
						<c:forEach var="items" items="${storeList }">
							<input type="checkbox" name="store" value="${items.storeId }" <c:if test="${fn:contains(info.storeList, items.storeId)}">checked</c:if>> ${items.storeNm }
						</c:forEach>
						
					</td>
				</tr>
				<tr>
					<td>아이디</td>
					<td><input type="text" name="adminId" style="width:100%;" value="${info.adminId }"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="adminName" style="width:100%;" value="${info.adminName }"></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" name="adminPw" style="width:100%;" value="${info.adminPw }"></td>
				</tr>
				<tr>
					<td>등급</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="adminTp" value="S" <c:if test="${info.adminTp == 'S'}">checked</c:if><c:if test="${empty info.adminTp}">checked</c:if>> 슈퍼관리자
						<input type="radio" style="margin: 4px 4px 0px;" name="adminTp" value="A" <c:if test="${info.adminTp == 'A'}">checked</c:if>> 환전관리자
						<input type="radio" style="margin: 4px 4px 0px;" name="adminTp" value="M" <c:if test="${info.adminTp == 'M'}">checked</c:if>> 배송담당자
					</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="adminTel" style="width:100%;" value="${info.adminTel }"></td>
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
	
});

function writeAction(action){
	
	var storeId = '';
	$(":checkbox[name='store']:checked").each(function(index, item){
		var id = $(item).val();
		if(storeId == ''){
			storeId = id;
		} else {
			storeId = storeId + ',' + id;
		}
	});
	$('#storeId').val(storeId);
	
	var FORM_BODY = $('form[name=formBody]');

	var url = '${pageContext.request.contextPath}/api/store/staff/';
	FORM_BODY.attr('action', url + action);
	FORM_BODY.submit();
	
	
}
</script>
</html>