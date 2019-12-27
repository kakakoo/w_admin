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
	<title>WEYS 무통장입금</title>
	
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
				* 무통장입금 입금현황
			</div>
			<div class="refresh" style="width:70%;">
				<input type="button" class="btn btn-success" onclick="incomeChk()" value="입금확인">
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/rsv/vbank" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="${paging.pageNo}">
			<input type="hidden" name="isSearch" id="isSearch">
			<table class="search">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>입금 일자</td>
					<td>
						<input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${paging.startDt } - ${paging.endDt }" />
					</td>
				</tr> 
				<tr>
					<td>상태</td>
					<td>
						<input type="checkbox" name="rsvSt" id="allCheckSt" value="t" <c:if test="${empty paging.listData}">checked</c:if>>전체
						<input type="checkbox" name="rsvSt" value="A" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'A')}">checked</c:if>> 자동확인
						<input type="checkbox" name="rsvSt" value="H" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'H')}">checked</c:if>> 수동확인
						<input type="checkbox" name="rsvSt" value="N" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'N')}">checked</c:if>> 미확인
					</td>
				</tr>
				<tr>
					<td>입금자 이름</td>
					<td>
						<input type="text" style="width: 200px;display:initial;" name="searchText" id="searchText" class="form-control" value="${paging.searchText }" />
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
					<col style="width:5%"/>
					<col style="width:19%"/>
					<col style="width:19%"/>
					<col style="width:19%"/>
					<col style="width:19%"/>
					<col style="width:19%"/>
				</colgroup>
				<tr>
					<td></td>
					<td>입금자명</td>
					<td>금액</td>
					<td>메세지</td>
					<td>상태</td>
					<td>등록시간</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr <c:choose>
							<c:when test="${items.CHK_ST == 'N' }">style="background-color:rgba(255,151,151,0.2);"</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>>
						<td>
							<c:if test="${items.CHK_ST == 'N'}">
								<input type="checkbox" class="checkId" name="vId" value="${items.V_ID }">
							</c:if>
						</td>
						<td><nobr><font title="${items.ICM_NM }">${items.ICM_NM }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.GET_AMNT }"><fmt:formatNumber value="${items.GET_AMNT }" groupingUsed="true"/></font></nobr></td>
						<td><nobr><font title="${items.MSG }">${items.MSG }</font></nobr></td>
						<td>
							<c:choose>
								<c:when test="${items.CHK_ST == 'A' }">자동확인</c:when>
								<c:when test="${items.CHK_ST == 'H' }">수동확인(${items.ADMIN_NAME })</c:when>
								<c:otherwise>미확인</c:otherwise>
							</c:choose>
						</td>
						<td><nobr><font title="${items.REG_DTTM }">${items.REG_DTTM }</font></nobr></td>
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

<div class="dialog_back"></div>
<div class="dialog" id="dialog_excel">
	<div class="dialog_body" style="height:240px;margin-top:-120px;">
		<div class="dialog_title">예약 정보 출력</div>
		
		<form name="excelBody" action="${pageContext.request.contextPath}/api/rsv/excel" method="post">
		<table>
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>예약 날짜 선택</td>
					<td><input type="text" style="width:240px;display:initial;" name="reservation" id="res" class="form-control" value="${excelDt}" /></td>
				</tr>
				<tr>
					<td>출력 타입</td>
					<td>
						<input type="radio" name="excelType" style="width:11px;margin: 4px 4px 0px;" value="N" checked> 전체 
						<input type="radio" name="excelType" style="width:11px;margin: 4px 4px 0px;" value="Y"> 인수제외
					</td>
				</tr>
		</table>
		</form>
		<div class="dialog_button">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="goExcel()" value="저장">
		</div>
	</div>
</div>

</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

$(function () {

	$('input[id="res"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    },function(start, end, label) {});

	$('.dropdown-menu').css('width', '240px');

	$('#allCheckSt').click(function(){
		if($("#allCheckSt").prop("checked")) {
			$("input[name=rsvSt]").prop("checked",true);
		} else {
			$("input[name=rsvSt]").prop("checked",false);
		}
	});

	$("input[name=rsvSt]").change(function() {
        if($('input[name=rsvSt]:checked').length == 8){
        	$('#allCheckSt').prop("checked",true);
        } else if($('input[name=rsvSt]:checked').length == 7 && !$("#allCheckSt").prop("checked")){
        	$('#allCheckSt').prop("checked",true);
        } else {
        	$('#allCheckSt').prop("checked",false);
        }
    });


});

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/rsv/vbank');
	FORM_BODY.submit();
}

function incomeChk(){
	
	if(confirm('입금확인하시겠습니까?')){
		var vId = 0;
		$(":checkbox[name='vId']:checked").each(function(index, item){
			vId = $(item).val();
		});
		
		var data = {};
		data["vId"] = vId
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/api/rsv/vbank/chk",
			data: JSON.stringify(data),
			async : false,
			contentType: "application/json",
			success : function(result){
				
				var result = result.res;
				
				if(result == 'suc'){
					alert('업데이트 되었습니다.');

					location.reload(true);
				}

				
			},
			error : function(result){
				alert('서버 에러');
			}
		});
	}
}



</script>
</html>