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
	
		<jsp:include page="../common/left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		

        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 지점 현황 > ${storeNm }
			</div>
			<div class="refresh" style="padding:1%;">
			&nbsp;
			</div>
			
			<div class="title" style="padding-left: 0;" >
				<input type="button" class="btn btn-success" value="보유액 현황">
				<input type="button" onclick="goTab('member', '${storeId }')" class="btn btn-success" style="color:#000;background-color:#fff;" value="거래 상세 내역(멤버십)">
				<input type="button" onclick="goTab('point', '${storeId }')" class="btn btn-success" style="color:#000;background-color:#fff;" value="거래 상세 내역(포인트)">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
			&nbsp;
			</div>
			
			<table class="list_table">
				<tr>
					<td>통화</td>
					<td>총액</td>
					<td colspan="14">상세</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<c:set var="cnt" value="0" />
					<tr>
						<td rowspan="2">${items.unitCd } <br> ${items.unitNm }</td>
						<td rowspan="2" style="text-align:right;"><fmt:formatNumber value="${items.unitTot }" groupingUsed="true"/></td>
						<c:forEach var="coins" items="${items.coinList }" varStatus="status">
							<c:set var="cnt" value="${status.index }" />
							<td>${coins.COIN_NM }</td>
						</c:forEach>
						<c:forEach begin="${cnt }" end="12">
							<td></td>
						</c:forEach>
					</tr>
					<tr>
						<c:forEach var="coins" items="${items.coinList }">
							<td style="text-align:right;"><fmt:formatNumber value="${coins.COIN_CNT }" groupingUsed="true"/></td>
						</c:forEach>
						<c:forEach begin="${cnt }" end="12">
							<td></td>
						</c:forEach>
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

function goExcel(){
	var FORM_BODY = $('form[name=excelBody]');
	FORM_BODY.submit();
}

function goTab(menu, storeId){
	location.href='${pageContext.request.contextPath}/api/store/' + storeId + '/' + menu;
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');
	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.submit();
}
</script>
</html>