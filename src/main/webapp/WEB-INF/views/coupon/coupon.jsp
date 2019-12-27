<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 쿠폰</title>
	
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
				* 쿠폰 관리
			</div>
			<div class="refresh">
				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/api/coupon/write'" value="등록">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/coupon" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:30%"/>
					<col style="width:15%"/>
					<col style="width:13%"/>
					<col style="width:12%"/>
				</colgroup>
				<tr>
					<td>발급일시</td>
					<td>쿠폰명</td>
					<td>유효기간</td>
					<td>이미지</td>
					<td>사용수/발급수</td>
					<td>관리</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td>${items.regDttm }</td>
						<td>${items.couponNm }</td>
						<td>${items.startDt } ~ ${items.endDt }</td>
						<td><a style="cursor:pointer" onclick="openModal('${items.couponImg }')">이미지 보기</a></td>
						<td>${items.uCnt } / ${items.tCnt }</td>
						<td>
							<c:if test="${items.couponSt =='R' }">
								<a style="cursor:pointer" onclick="makeDone('${items.couponId }')">발급</a> | 
								<a style="cursor:pointer" onclick="modifyCoupon('${items.couponId }')">수정</a> | 
								<a style="cursor:pointer" onclick="deleteCoupon('${items.couponId }')">삭제</a>
							</c:if>
							<c:if test="${items.couponSt =='D' }">
								발급완료
							</c:if>
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

function modifyCoupon(couponId){

	location.href='${pageContext.request.contextPath}/api/coupon/write?id=' + couponId;
}
function makeDone(couponId){
	if(confirm('정말로 발급하시겠습니까?')){
		location.href='${pageContext.request.contextPath}/api/coupon/done?id=' + couponId;
	}
}
function deleteCoupon(couponId){
	if(confirm('정말로 삭제하시겠습니까?')){
		location.href='${pageContext.request.contextPath}/api/coupon/delete?id=' + couponId;
	}
}

function openModal(img){
	$('.modal').css('display', 'block');
	$('#modalImg').attr('src', '${pageContext.request.contextPath}/imgView/' + img);
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/coupon');
	FORM_BODY.submit();
}

</script>
</html>