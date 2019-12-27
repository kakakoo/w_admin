<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 지점</title>
	
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
				* 매니저 관리
			</div>
			<div class="refresh">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/store/staff/write'" class="btn btn-success" value="담당자 등록">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/store/staff" method="post">
				<input type="hidden" name="pageNo" id="pageNo" value="${paging.pageNo}">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:8%"/>
					<col style="width:32%"/>
				</colgroup>
				<tr>
					<td>ID</td>
					<td>이름</td>
					<td>담당</td>
					<td>전화번호</td>
					<td>상태</td>
					<td>담당 지점명</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr onclick="location.href='${pageContext.request.contextPath}/api/store/staff/write?id=${items.adminKey }'">
						<td><nobr><font title="${items.adminId }">${items.adminId }</font></nobr></td>
						<td><nobr><font title="${items.adminName }">${items.adminName }</font></nobr></td>
						<td><nobr><font title="${items.adminTpText }">${items.adminTpText }</font></nobr></td>
						<td><nobr><font title="${items.adminTel }">${items.adminTel }</font></nobr></td>
						<td><nobr><font title="${items.adminSt }">${items.adminSt }</font></nobr></td>
						<td><nobr><font title="${items.storeNm }">${items.storeNm }</font></nobr></td>
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

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/store/staff');
	FORM_BODY.submit();
}

</script>
</html>