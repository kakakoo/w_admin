<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 멤버십</title>
	
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
				* 멤버십 결제 관리
			</div>
			<div class="refresh">
				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/api/member/write'" value="등록">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/member" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			<table class="list_table">
				<colgroup>
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:15%"/>
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>타이틀</td>
					<td>타이틀(영문)</td>
					<td>결제금액</td>
					<td>한도금액</td>
					<td>적용기간(개월)</td>
					<td>색상</td>
					<td>상태</td>
					<td>관리</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td>${items.payText }</td>
						<td>${items.payTextEng }</td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.payAmnt }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.payLimit }" groupingUsed="true"/></td>
						<td>${items.period }</td>
						<td>${items.color }</td>
						<td>${items.paySt }</td>
						<td><a style="cursor:pointer" onclick="modifyUnit('${items.payId }')">수정</a></td>
					</tr>
				</c:forEach>
				
			</table>
				
				<jsp:include page="../common/paging.jsp" flush="true">
				    <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
				    <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
				    <jsp:param name="startPageNo" value="${paging.startPageNo}" />
				    <jsp:param name="pageNo" value="${paging.pageNo}" />
				    <jsp:param name="endPageNo" value="${paging.endPageNo}" />
				    <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
				    <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
				</jsp:include>
		</div>
		
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

function modifyUnit(payId){

	location.href='${pageContext.request.contextPath}/api/member/write?id=' + payId;
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/member');
	FORM_BODY.submit();
}

</script>
</html>