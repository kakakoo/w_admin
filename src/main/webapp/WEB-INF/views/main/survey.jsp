<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 설문조사</title>
	
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
				* 설문조사
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/survey" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			
			<table class="list_table" style="font-size:12px;">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:7%"/>
					<col style="width:7%"/>
					<col style="width:20%"/>
					<col style="width:7%"/>
					<col style="width:7%"/>
					<col style="width:7%"/>
					<col style="width:35%"/>
				</colgroup>
				<tr>
					<td>수령시간</td>
					<td>예약번호</td>
					<td>알게된경로</td>
					<td>만족도평가</td>
					<td>안내평가</td>
					<td>과정평가</td>
					<td>배송자평가</td>
					<td>의견</td>
				</tr>
				<c:forEach var="items" items="${surList }">
					<tr>
						<td>${items.MOD_DTTM }</td>
						<td>${items.RSV_NO }</td>
						<td>${items.FIRST_A }</td>
						<td><nobr><font title="${items.SECOND_A }">${items.SECOND_A }</font></nobr></td>
						<td>${items.THIRD_A }</td>
						<td>${items.FOURTH_A }</td>
						<td>${items.FIFTH_A }</td>
						<td>${items.MEMO }</td>
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
	FORM_BODY.submit();
}
</script>
</html>