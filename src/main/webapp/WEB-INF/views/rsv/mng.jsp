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
			<div class="title">
				* 담당자 지정
			</div>
			<div class="refresh">
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/rsv/list" method="post">
			<input type="hidden" name="pageNo" id="pageNo">
			<input type="hidden" name="isSearch" id="isSearch">
			<input type="hidden" name="rsvStVal" id="rsvStVal">
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
								<option value="${store.STORE_NM }" <c:if test="${paging.searchType == store.STORE_NM}">selected</c:if>>${store.STORE_NM }</option>
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
			
			<table class="list_table">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>지점</td>
					<td>예약일</td>
					<td>구분</td>
					<td>통화</td>
					<td>예약외화</td>
					<td>수령인</td>
					<td>담당자</td>
					<td>예약상태</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td><nobr><font title="${items.storeNm }">${items.storeNm }</font></nobr></td>
						<td><nobr><font title="${items.rsvDt }">${items.rsvDt }</font></nobr></td>
						<td><nobr><font title="${items.rsvTp }">${items.rsvTp }</font></nobr></td>
						<td><nobr><font title="${items.unit }">${items.unit }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.rsvAmnt }"><fmt:formatNumber value="${items.rsvAmnt }" groupingUsed="true"/></font></nobr></td>
						<td><nobr><font title="${items.rsvNm }">${items.rsvNm }</font></nobr></td>
						<td><nobr><font title="${items.adminName }">${items.adminName }</font></nobr></td>
						<td><nobr><font title="${items.rsvSt }">${items.rsvSt }</font></nobr></td>
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
        if($('input[name=rsvSt]:checked').length == 10){
        	$('#allCheckSt').prop("checked",true);
        } else if($('input[name=rsvSt]:checked').length == 9 && !$("#allCheckSt").prop("checked")){
        	$('#allCheckSt').prop("checked",true);
        } else {
        	$('#allCheckSt').prop("checked",false);
        }
    });
});

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
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/rsv/list');
	FORM_BODY.submit();
}
</script>
</html>