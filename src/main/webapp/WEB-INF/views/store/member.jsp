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
				<input type="button" onclick="goTab('coin', '${storeId }')" class="btn btn-success" style="color:#000;background-color:#fff;" value="보유액 현황">
				<input type="button" class="btn btn-success" value="거래 상세 내역(멤버십)">
				<input type="button" onclick="goTab('point', '${storeId }')" class="btn btn-success" style="color:#000;background-color:#fff;" value="거래 상세 내역(포인트)">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
				<input type="button" onclick="goExcel()" class="btn btn-success" value="엑셀 다운로드">
			</div>
			
			<form name="excelBody" action="${pageContext.request.contextPath}/api/store/${storeId }/excel" method="post">
				<input type="hidden" name="reservation" id="reservation_excel">
			</form>
				
			<form name="formBody" action="${pageContext.request.contextPath}/api/store/${storeId }/member" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
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
				<input type="button" class="btn btn-success" onclick="goPage(${paging.pageNo}, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>
			
			
			<table class="list_table">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:7%"/>
					<col style="width:7%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:16%"/>
				</colgroup>
				<tr>
					<td>거래일</td>
					<td>거래상세</td>
					<td>통화</td>
					<td>수령금액</td>
					<td>매매기준율</td>
					<td>지급금액</td>
					<td>닉네임</td>
					<td>담당자</td>
					<td>신청서</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td>${items.regDttm }</td>
						<td>${items.tp }</td>
						<td>${items.unit }</td>
						<td style="text-align:right;">${items.getAmnt }</td>
						<td style="text-align:right;">${items.basicRate }</td>
						<td style="text-align:right;">${items.payAmnt }</td>
						<td>${items.usrNick }</td>
						<td>${items.adminName }</td>
						<td>${items.paper }</td>
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

function goExcel(){
	$('#reservation_excel').val($('#reservation').val());
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