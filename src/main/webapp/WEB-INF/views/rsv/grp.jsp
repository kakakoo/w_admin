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
	<title>WEYS 예약</title>
	
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
			<div class="title" style="width:30%;">
				* 지점 예약 내역
			</div>
			<div class="refresh" style="width:70%;">
<!-- 				<input type="button" class="btn btn-dark" onclick="viewPdf()" value="내역 보기"> -->
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/rsv/grp" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="${paging.pageNo}">
			<input type="hidden" name="isSearch" id="isSearch">
			<table class="search">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>예약 일자</td>
					<td>
						<input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${paging.startDt } - ${paging.endDt }" />
					</td>
				</tr> 
				<tr>
					<td>지점선택</td>
					<td>
						<select name="searchType" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;width:200px;">
							<option value="t" <c:if test="${paging.searchType == 't'}">selected</c:if>>전체</option>
							<c:forEach var="store" items="${storeList}" varStatus="status">
								<option value="${store.STORE_ID }" <c:if test="${paging.searchType == store.STORE_NM}">selected</c:if>>${store.STORE_NM }</option>
							</c:forEach>
						</select>
					</td>
				</tr> 
			</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="goPage(${paging.pageNo}, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>
			
			<div class="title">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
			</div>
			
			<table class="list_table" style="font-size:12px;">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:15%"/>
					<col style="width:17.5%"/>
					<col style="width:17.5%"/>
					<col style="width:15%"/>
					<col style="width:5%"/>
				</colgroup>
				<tr>
					<td>지점명</td>
					<td>수송일</td>
					<td>예약건(회수건)</td>
					<td>배송타입</td>
					<td>인수상태</td>
					<td>보기</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr <c:if test="${items.grpCnt > 0  and (items.groupSt == 'P' or items.groupSt == 'D') }">
						<c:choose>
								<c:when test="${items.groupTp == 'D' }">
									style="background-color:rgba(135,249,151,0.2);"
								</c:when>
								<c:otherwise>
									style="background-color:rgba(255,151,151,0.2);"
								</c:otherwise>
							</c:choose></c:if>>
						<td><nobr><font title="${items.storeNm }">${items.storeNm }</font></nobr></td>
						<td><nobr><font title="${items.rsvDt }">${items.rsvDt }</font></nobr></td>
						<td><nobr><font title="${items.grpCnt }">
							<c:choose>
								<c:when test="${items.groupTp == 'D' }">${items.grpCnt }</c:when>
								<c:otherwise>(${items.grpCnt })</c:otherwise>
							</c:choose>
						</font></nobr></td>
						<td>
							<c:choose>
								<c:when test="${items.groupTp == 'D' }">수송</c:when>
								<c:otherwise>회수</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${items.groupTp == 'D' }">
									<c:choose>
										<c:when test="${items.groupSt == 'P' }">확인서생성</c:when>
										<c:when test="${items.groupSt == 'D' }">수송자인계</c:when>
										<c:otherwise>지점인계</c:otherwise>
									</c:choose>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${items.groupSt == 'P' }">확인서생성</c:when>
										<c:otherwise>회수완료</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</td>
						<td <c:if test="${items.grpCnt > 0 }">onclick="viewPdf('${items.groupId}')"</c:if>>
							<c:if test="${items.grpCnt > 0 }">보기</c:if>
						</td>
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
		
		<form name="grpBody" action="${pageContext.request.contextPath}/api/rsv/viewPdf" method="post">
			<input type="hidden" name="groupId" id="groupId">
		</form>
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
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/rsv/grp');
	FORM_BODY.submit();
}


function viewPdf(groupId){

	$('#groupId').val(groupId);
	
	var FORM_BODY = $('form[name=grpBody]');
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/rsv/viewPdf');
	FORM_BODY.submit();
}


</script>
</html>