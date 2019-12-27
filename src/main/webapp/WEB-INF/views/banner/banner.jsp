<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 배너</title>
	
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
				* 배너 관리 > 시작 배너
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/banner" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			
			<div class="title" style="padding-left: 0;" >
				<input type="button" class="btn btn-success" value="시작 배너">
				<input type="button" onclick="goRsvBanner()" class="btn btn-success" style="color:#000;background-color:#fff;" value="예약 배너">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/api/banner/write'" value="등록">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:30%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>등록일</td>
					<td>타이틀</td>
					<td>이동</td>
					<td>이미지</td>
					<td>적용기간</td>
					<td>관리</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td>${items.regDttm }</td>
						<td>${items.bannerNm }</td>
						<td>
							<c:if test="${not empty items.redirectUrl}"><nobr><font title="${items.redirectUrl }">${items.redirectUrl }</font></nobr></c:if>
							<c:if test="${not empty items.redirectApp}"><nobr><font title="${items.redirectApp }">${items.redirectApp }</font></nobr></c:if>
						</td>
						<td><a style="cursor:pointer" onclick="openModal('${items.bannerUrl }')">이미지 보기</a></td>
						<td>${items.startDt } ~ ${items.endDt }</td>
						<td><a style="cursor:pointer" onclick="modifyUnit('${items.bannerId }')">수정</a></td>
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


<div class="modal" style="display:none;text-align:center;padding:100px;box-sizing:border-box;overflow: auto;background-color:rgba(226,226,226,0.5)">
	<div class="close_modal" style="font-size:30px;color:red;">닫기</div>
	<img id="modalImg">
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">


$(function () {
	$('.close_modal').click(function(){
		$('.modal').css('display', 'none');
	});
	
});

function modifyUnit(bannerId){

	location.href='${pageContext.request.contextPath}/api/banner/write?id=' + bannerId;
}

function goRsvBanner(){
	location.href='${pageContext.request.contextPath}/api/banner/rsv';
}

function openModal(img){
	$('.modal').css('display', 'block');
	$('#modalImg').attr('src', '${pageContext.request.contextPath}/imgView/' + img);
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/banner');
	FORM_BODY.submit();
}

</script>
</html>