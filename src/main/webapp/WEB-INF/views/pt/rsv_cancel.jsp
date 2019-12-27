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
	<title>WEYS 예약취소</title>
	
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
				* 예약 취소 현황 
			</div>
			<div class="refresh">
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/rsv/cancel" method="post">
			<input type="hidden" name="pageNo" id="pageNo">
			<input type="hidden" name="isSearch" id="isSearch">
			<input type="hidden" name="rsvStVal" id="rsvStVal">
			<table class="search">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>취소 일자</td>
					<td>
						<input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${paging.startDt } - ${paging.endDt }" />
					</td>
				</tr> 
				<tr>
					<td>예약상태</td>
					<td>
						<input type="checkbox" name="rsvSt" id="allCheckSt" value="t" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 't')}">checked</c:if>>전체
						<input type="checkbox" name="rsvSt" value="C" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'C')}">checked</c:if>> 예약취소
						<input type="checkbox" name="rsvSt" value="CR" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'CR')}">checked</c:if>> 환불대기
						<input type="checkbox" name="rsvSt" value="CF" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'CF')}">checked</c:if>> 환불완료
					</td>
				</tr>
				<tr>
					<td>이름 검색</td>
					<td>
						<input type="text" name="searchText" style="border-color: #a7a9aa !important;width:50%;margin:auto;height:22px;" value="${paging.searchText }">
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
			
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:7.5%"/>
					<col style="width:7.5%"/>
					<col style="width:7.5%"/>
					<col style="width:7.5%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:11%"/>
					<col style="width:8%"/>
					<col style="width:8%"/>
					<col style="width:8%"/>
				</colgroup>
				<tr>
					<td>취소일</td>
					<td>구분</td>
					<td>통화</td>
					<td>예약외화</td>
					<td>입금금액</td>
					<td>예금주</td>
					<td>은행</td>
					<td>계좌</td>
					<td>환불수수료</td>
					<td>환불금액</td>
					<td>예약상태</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td><nobr><font title="${items.cancelDttm }">${items.cancelDttm }</font></nobr></td>
						<td><nobr><font title="${items.rsvTp }">${items.rsvTp }</font></nobr></td>
						<td><nobr><font title="${items.unit }">${items.unit }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.rsvAmnt }"><fmt:formatNumber value="${items.rsvAmnt }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.getAmnt }"><fmt:formatNumber value="${items.getAmnt }" groupingUsed="true"/></font></nobr></td>
						<td><nobr><font title="${items.rsvNm }">${items.rsvNm }</font></nobr></td>
						<td><nobr><font title="${items.retBankNm }">${items.retBankNm }</font></nobr></td>
						<td><nobr><font title="${items.retBankCd }">${items.retBankCd }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.cancelCms }"><fmt:formatNumber value="${items.cancelCms }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.cancelAmnt }"><fmt:formatNumber value="${items.cancelAmnt }" groupingUsed="true"/></font></nobr></td>
						<td>
							<c:if test="${items.rsvSt =='CR' }">
								<nobr><font title="${items.rsvSt }"><a style="cursor:pointer;color:blue;" onclick="returnBill('${items.rsvId }', '${items.rsvNm }', '${items.retBankNm }', '${items.retBankCd }', '<fmt:formatNumber value="${items.cancelAmnt }" groupingUsed="true"/>')">환불대기</a></font></nobr>
							</c:if>
							<c:if test="${items.rsvSt =='CF' }">
								<nobr>환불완료</nobr>
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
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

$(function () {
	$('#allCheckTp').click(function(){
		if($("#allCheckTp").prop("checked")) {
			$("input[name=tradeTp]").prop("checked",true);
		} else {
			$("input[name=tradeTp]").prop("checked",false);
		}
	});
	
	$("input[name=tradeTp]").change(function() {
        if($('input[name=tradeTp]:checked').length == 3){
        	$('#allCheckTp').prop("checked",true);
        } else if($('input[name=tradeTp]:checked').length == 2 && !$("#allCheckTp").prop("checked")){
        	$('#allCheckTp').prop("checked",true);
        } else {
        	$('#allCheckTp').prop("checked",false);
        }
    });
	
	$('#allCheckSt').click(function(){
		if($("#allCheckSt").prop("checked")) {
			$("input[name=rsvSt]").prop("checked",true);
		} else {
			$("input[name=rsvSt]").prop("checked",false);
		}
	});
	
	$("input[name=rsvSt]").change(function() {
        if($('input[name=rsvSt]:checked').length == 4){
        	$('#allCheckSt').prop("checked",true);
        } else if($('input[name=rsvSt]:checked').length == 3 && !$("#allCheckSt").prop("checked")){
        	$('#allCheckSt').prop("checked",true);
        } else {
        	$('#allCheckSt').prop("checked",false);
        }
    });
});

function returnBill(rsvId, rsvNm, retBankNm, retBankCd, cancelAmnt){
	if(confirm('예금주 : ' + rsvNm + '\n' +
			'은행 : ' + retBankNm + '\n' +
			'계좌 : ' + retBankCd + '\n' +
			'환불금액 : ' + cancelAmnt + '원 \n입금되었습니까?')){
		
		
		location.href='${pageContext.request.contextPath}/api/rsv/cancel/' + rsvId;;
	}
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');
	var rsvSt = '';
	$('input:checkbox[name="rsvSt"]').each(function() {
		if(this.checked){//checked 처리된 항목의 값
			if(rsvSt == ''){
				rsvSt = this.value;
			}else if(rsvSt == 't'){}
			else{
				rsvSt = rsvSt + "," + this.value;
			}
		}
	});
	$('#rsvStVal').val(rsvSt);
	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/rsv/cancel');
	FORM_BODY.submit();
}
</script>
</html>