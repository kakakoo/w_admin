<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 로그</title>
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
</head>
<body class="nav-md">
    <div class="container body">
      <div class="main_container">
	
		<jsp:include page="../common/manage_left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		

        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 로그 내역
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/manage/log" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			
			<table class="list_table">
				<colgroup>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
				</colgroup>
				<tr>
					<td>외화</td>
					<td>지점</td>
					<td>내역</td>
					<td>받은돈</td>
					<td>준돈</td>
					<td>이전금액</td>
					<td>이후금액</td>
					<td>시간</td>
				</tr>
				<c:forEach var="items" items="${logList }">
					<tr>
						<td><nobr><font title="${items.unit }">${items.unit }</font></nobr></td>
						<td><nobr><font title="${items.type }">${items.type }</font></nobr></td>
						<td><nobr><font title="${items.st }">${items.st }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.getAmnt }"><fmt:formatNumber value="${items.getAmnt }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.payAmnt }"><fmt:formatNumber value="${items.payAmnt }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.befAmnt }"><fmt:formatNumber value="${items.befAmnt }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.aftAmnt }"><fmt:formatNumber value="${items.aftAmnt }" groupingUsed="true"/></font></nobr></td>
						<td><nobr><font title="${items.regDttm }">${items.regDttm }</font></nobr></td>
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

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/manage/log');
	FORM_BODY.submit();
}
</script>
</html>