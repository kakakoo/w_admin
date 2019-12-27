<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
		

        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 메니저 공지사항
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/banner" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			
			<div class="title" style="padding-left: 0;" >
				<input type="button" onclick="goStaff()" class="btn btn-success" style="color:#000;background-color:#fff;" value="매니저 관리">
				<input type="button" class="btn btn-success" value="담당자 공지">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/staff/notice/write'" class="btn btn-success" value="공지사항 등록">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:70%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
				</colgroup>
				<tr>
					<td>제목</td>
					<td>상태</td>
					<td>날짜</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr onclick="goDetail(${items.anId })">
						<td>${items.anTitle }</td>
						<td>${items.anSt }</td>
						<td>${items.regDttm }</td>
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


$(function () {
	
});

function goStaff(){
	location.href = '${pageContext.request.contextPath}/api/staff';
}

function goDetail(anId){
	location.href = '${pageContext.request.contextPath}/api/staff/notice/write?id=' + anId;
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/staff/notice');
	FORM_BODY.submit();
}

</script>
</html>