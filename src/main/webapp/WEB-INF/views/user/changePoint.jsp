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
	<title>WEYS 사용자</title>
	
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
				* 사용자 현황 > ${info.usrNick }
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/user/${info.usrId}/membership" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			<form name="excelBody" action="${pageContext.request.contextPath}/api/user/${info.usrId}/excel" method="post">
			</form>
			
			<div class="title" style="padding-left: 0;" >
				<input type="button" onclick="goMembership('${info.usrId}')" class="btn btn-success" style="color:#000;background-color:#fff;" value="멤버십 내역">
				<input type="button" class="btn btn-success" value="포인트 내역(전환)">
				<input type="button" onclick="goUsePoint('${info.usrId}')" class="btn btn-success" style="color:#000;background-color:#fff;" value="포인트 내역(소비)">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
				<input type="button" onclick="goExcel()" class="btn btn-success" value="엑셀 다운로드">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
				</colgroup>
				<tr>
					<td>닉네임</td>
					<td>메일</td>
					<td>이름</td>
					<td>멤버십가입일</td>
					<td>잔여한도</td>
					<td>만료기간</td>
					<td>포인트</td>
				</tr>
				<tr>
					<td>${info.usrNick }</td>
					<td>${info.usrEmail }</td>
					<td>${info.usrNm }</td>
					<td>${info.startDt }</td>
					<td style="text-align:right;"><fmt:formatNumber value="${info.cost }" groupingUsed="true"/></td>
					<td>${info.endDt }</td>
					<td style="text-align:right;"><fmt:formatNumber value="${info.usrPoint }" groupingUsed="true"/></td>
				</tr>
			</table>
			
			<table class="list_table" style="margin-top:30px;">
				<colgroup>
					<col style="width:16%"/>
					<col style="width:12%"/>
					<col style="width:12%"/>
					<col style="width:12%"/>
					<col style="width:12%"/>
					<col style="width:12%"/>
					<col style="width:12%"/>
					<col style="width:12%"/>
				</colgroup>
				<tr>
					<td>거래일</td>
					<td>통화</td>
					<td>수령금액</td>
					<td>매매기준율</td>
					<td>지급 포인트</td>
					<td>신청서</td>
					<td>지점</td>
					<td>담당자</td>
				</tr>
				<c:if test="${empty resultList }">
				<tr>
					<td colspan="8">포인트 전환 이력이 없습니다.</td>
				</tr>
				</c:if>
				<c:if test="${not empty resultList }">
					<c:forEach var="items" items="${resultList }">
						<tr>
							<td><nobr><font title="${items.regDttm }">${items.regDttm }</font></nobr></td>
							<td><nobr><font title="${items.unit }">${items.unit }</font></nobr></td>
							<td style="text-align:right;"><nobr><font title="${items.getAmnt }"><fmt:formatNumber value="${items.getAmnt }" groupingUsed="true"/></font></nobr></td>
							<td style="text-align:right;"><nobr><font title="${items.basicRate }"><fmt:formatNumber value="${items.basicRate }" groupingUsed="true"/></font></nobr></td>
							<td style="text-align:right;"><nobr><font title="${items.point }"><fmt:formatNumber value="${items.point }" groupingUsed="true"/></font></nobr></td>
							<td><nobr><font title="${items.paper }">${items.paper }</font></nobr></td>
							<td><nobr><font title="${items.storeNm }">${items.storeNm }</font></nobr></td>
							<td><nobr><font title="${items.adminName }">${items.adminName }</font></nobr></td>
						</tr>
					</c:forEach>
				</c:if>
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
	var FORM_BODY = $('form[name=excelBody]');
	FORM_BODY.submit();
}

function goMembership(usrId){
	location.href='${pageContext.request.contextPath}/api/user/' + usrId + '/membership';
}

function goUsePoint(usrId){
	location.href='${pageContext.request.contextPath}/api/user/' + usrId + '/upoint';
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');
	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.submit();
}
</script>
</html>