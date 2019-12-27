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
	<title>WEYS 통계</title>
	
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
				* 통계 상세
			</div>
			<div class="refresh">
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/analysis/detail" method="post">
			<input type="hidden" name="pageNo" id="pageNo">
			<input type="hidden" name="isSearch" id="isSearch">
			<table class="search">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>기간</td>
					<td>
						<select name="trdTp" style="border-color: #a7a9aa !important;width:200px%;margin:auto;height:22px;">
							<option value="%Y.%m" <c:if test="${paging.trdTp == '%Y.%m'}">selected</c:if>>월간</option>
							<option value="%Y.%U" <c:if test="${paging.trdTp == '%Y.%U'}">selected</c:if>>주간</option>
							<option value="%Y.%m.%d" <c:if test="${paging.trdTp == '%Y.%m.%d'}">selected</c:if>>일간</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>지점선택</td>
					<td>
						<select name="storeId" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;width:200px;">
							<option value="0" <c:if test="${paging.storeId == '0'}">selected</c:if>>전체</option>
							<c:forEach var="store" items="${storeList}" varStatus="status">
								<option value="${store.STORE_ID }" <c:if test="${paging.storeId == store.STORE_ID}">selected</c:if>>${store.STORE_NM }</option>
							</c:forEach>
						</select>
					</td>
				</tr> 
				<tr>
					<td>통화</td>
					<td>
						<select name="trdUnit" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;width:200px;">
							<option value="ALL" <c:if test="${paging.trdUnit == 'ALL'}">selected</c:if>>전체</option>
							<c:forEach var="unit" items="${unitList}" varStatus="status">
								<option value="${unit.UNIT_CD }" <c:if test="${paging.trdUnit == unit.UNIT_CD}">selected</c:if>>${unit.UNIT_NM }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="goPage(${paging.pageNo}, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<td>기간</td>
					<td>건수</td>
					<td>배송비(총/건당)</td>
					<td>수수료(총/건당)</td>
					<td>입금금액(총/건당)</td>
				</tr>
				<c:forEach var="items" items="${rsvList }">
					<tr>
						<td>${items.dttm }</td>
						<td><fmt:formatNumber value="${items.CNT }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.CMS }" groupingUsed="true"/> / <fmt:formatNumber value="${items.CMS/items.CNT }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.GET_AMNT - items.WON_BILL - items.CMS }" groupingUsed="true"/> / <fmt:formatNumber value="${(items.GET_AMNT - items.WON_BILL - items.CMS)/items.CNT }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.GET_AMNT }" groupingUsed="true"/> / <fmt:formatNumber value="${items.GET_AMNT/items.CNT }" groupingUsed="true"/></td>
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
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/analysis/detail');
	FORM_BODY.submit();
}
</script>
</html>