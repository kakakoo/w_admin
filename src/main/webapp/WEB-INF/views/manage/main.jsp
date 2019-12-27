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
	<title>WEYS 지점</title>
	
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
				* 거래 현황 
			</div>
			<div class="refresh">
			</div>
			
			<input type="hidden" id="storeId" value="${storeId }">
			<form name="formBody" action="${pageContext.request.contextPath}/api/manage/main" method="post">
				<table class="search">
					<colgroup>
						<col style="width:30%"/>
						<col style="width:70%"/>
					</colgroup>
					<tr>
						<td>일자</td>
						<td>
							<input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${paging.startDt } - ${paging.endDt }" />
						</td>
					</tr> 
				</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="goPage(0, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>

			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:25%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
				</colgroup>
				<tr>
					<td rowspan="2">구분</td>
					<td rowspan="2">통화</td>
					<td colspan="2">거래건수</td>
					<td colspan="2">거래금액</td>
				</tr>
				<tr>
					<td style="background-color: #EDEDED;font-weight: 700;">구매</td>
					<td style="background-color: #EDEDED;font-weight: 700;">판매</td>
					<td style="background-color: #EDEDED;font-weight: 700;">구매</td>
					<td style="background-color: #EDEDED;font-weight: 700;">판매</td>
				</tr>
				<c:forEach var="items" items="${memList }" varStatus="status">
					<tr>
						<c:if test="${status.index == 0}"><td rowspan="${memCnt }">거래</td></c:if>
						<td>${items.unitNm }</td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.bCnt }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.sCnt }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.payAmnt }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.getAmnt }" groupingUsed="true"/></td>
					</tr>
				</c:forEach>
			</table>
			
			
			<table class="list_table" style="margin-top:10px;">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:25%"/>
					<col style="width:30%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<td>구분</td>
					<td>통화</td>
					<td>거래건수</td>
					<td>거래금액</td>
				</tr>
				<c:forEach var="items" items="${pointlist }" varStatus="status">
					<tr>
						<c:if test="${status.index == 0}"><td rowspan="${pointCnt }">포인트</td></c:if>
						<td>${items.unitNm }</td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.sCnt }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.getAmnt }" groupingUsed="true"/></td>
					</tr>
				</c:forEach>
			</table>
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
	websocket.close();
	var FORM_BODY = $('form[name=formBody]');
	FORM_BODY.submit();
}
</script>
</html>