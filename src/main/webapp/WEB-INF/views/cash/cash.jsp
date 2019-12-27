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
	<title>WEYS 현금영수증</title>
	
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
				* 현금영수증 신청 현황 
			</div>
			<div class="refresh">
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/cash" method="post">
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
				<tr>
					<td>거래 구분</td>
					<td>
						<input type="checkbox" name="tradeTp" id="allCheckTp" value="t" <c:if test="${empty paging.listData1}">checked</c:if>>전체
						<input type="checkbox" name="tradeTp" value="b" <c:if test="${empty paging.listData1}">checked</c:if><c:if test="${fn:contains(paging.listData1, 'b')}">checked</c:if>> 구매
						<input type="checkbox" name="tradeTp" value="s" <c:if test="${empty paging.listData1}">checked</c:if><c:if test="${fn:contains(paging.listData1, 's')}">checked</c:if>> 판매
					</td>
				</tr> 
				<tr>
					<td>검색</td>
					<td>
						<select name="searchType" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;">
							<option value="t" <c:if test="${paging.searchType == 't'}">selected</c:if>>전체</option>
							<option value="n" <c:if test="${paging.searchType == 'n'}">selected</c:if>>이름</option>
							<option value="e" <c:if test="${paging.searchType == 'e'}">selected</c:if>>이메일</option>
						</select>
						<input type="text" name="searchText" style="border-color: #a7a9aa !important;width:50%;margin:auto;height:22px;" value="${paging.searchText }">
					</td>
				</tr> 
			</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="goPage(${paging.pageNo}, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>신청일</td>
					<td>구분</td>
					<td>아이템</td>
					<td>성명</td>
					<td>이메일</td>
					<td>금액</td>
					<td>소비자정보</td>
					<td>발행</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td><nobr><font title="${items.regDttm }">${items.regDttm }</font></nobr></td>
						<td><nobr><font title="${items.cashTp }">${items.cashTp }</font></nobr></td>
						<td><nobr><font title="${items.itemName }">${items.itemName }</font></nobr></td>
						<td><nobr><font title="${items.usrNm }">${items.usrNm }</font></nobr></td>
						<td><nobr><font title="${items.usrEmail }">${items.usrEmail }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.amnt }"><fmt:formatNumber value="${items.amnt }" groupingUsed="true"/></font></nobr></td>
						<td><nobr><font title="${items.usrContact }">${items.usrContact }</font></nobr></td>
						<td>
							<c:if test="${items.cashSt == 'R' }">
								<a onclick="openDialogMng('${items.cashId}')">등록</a>
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


<div class="dialog_back"></div>
<div class="dialog" id="dialog_mng">
	<div class="dialog_body" style="height:230px;margin-top:-115px;">
		<div class="dialog_title">현금영수증 발행</div>
		<input type="hidden" id="cashId">
		<table>
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>문서관리번호</td>
					<td><input type="text" id="mgtKey"></td>
				</tr>
				<tr>
					<td>주문번호</td>
					<td><input type="text" id="orderNumber"></td>
				</tr>
		</table>
		<div class="dialog_button">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="registerIssue()" value="발행하기">
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
	
	$('#allCheckUnit').click(function(){
		if($("#allCheckUnit").prop("checked")) {
			$("input[name=tradeUnit]").prop("checked",true);
		} else {
			$("input[name=tradeUnit]").prop("checked",false);
		}
	});
	
	$("input[name=tradeUnit]").change(function() {
        if($('input[name=tradeUnit]:checked').length == 23){
        	$('#allCheckUnit').prop("checked",true);
        } else if($('input[name=tradeUnit]:checked').length == 22 && !$("#allCheckUnit").prop("checked")){
        	$('#allCheckUnit').prop("checked",true);
        } else {
        	$('#allCheckUnit').prop("checked",false);
        }
    });
});

function openDialogMng(cashId){
	$('#cashId').val(cashId);
	$('.dialog_back').css('display', 'block');
	$('#dialog_mng').css('display', 'block');
}


function closeDialog(){
	$('.dialog_back').css('display', 'none');
	$('.dialog').css('display', 'none');
}

function registerIssue(){

	if($('#mgtKey').val() == ''){
		alert('문서관리번호를 입력해주세요.');
		$('#mgtKey').focus();
		return;
	}
	if($('#orderNumber').val() == ''){
		alert('주문번호를 입력해주세요.');
		$('#orderNumber').focus();
		return;
	}

	var data = {};
	data["cashId"] = $('#cashId').val();
	data["mgtKey"] = $('#mgtKey').val();
	data["orderNumber"] = $('#orderNumber').val();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/cash/registerIssue",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			var msg = result.result;

			if(msg == 'success'){
				alert('등록완료');
				location.reload(true);
			}else {
				alert(msg);
			}
			
		},
		error : function(result){
			alert('업데이트 실패, 에러');
		}
	});
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	var trdTp = '';
	var trdUnit = '';
	$('input:checkbox[name="tradeTp"]').each(function() {
		if(this.checked){//checked 처리된 항목의 값
			if(trdTp == ''){
				trdTp = this.value;
			}else if(trdTp == 't'){}
			else{
				trdTp = trdTp + "," + this.value;
			}
		}
	});
	$('input:checkbox[name="tradeUnit"]').each(function() {
		if(this.checked){//checked 처리된 항목의 값
			if(trdUnit == ''){
				trdUnit = this.value;
			}else if(trdUnit == 't'){}
			else{
				trdUnit = trdUnit + "," + this.value;
			}
		}
	});

	$('#trdTp').val(trdTp);
	$('#trdUnit').val(trdUnit);
	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/trade');
	FORM_BODY.submit();
}
</script>
</html>