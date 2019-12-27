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
	<title>WEYS 거래글</title>
	
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
				* 거래 현황 
			</div>
			<div class="refresh">
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/trade" method="post">
			<input type="hidden" name="pageNo" id="pageNo">
			<input type="hidden" name="isSearch" id="isSearch">
			<input type="hidden" name="trdUnit" id="trdUnit">
			<input type="hidden" name="trdTp" id="trdTp">
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
					<td>통화</td>
					<td>
						<input type="checkbox" name="tradeUnit" id="allCheckUnit" value="t" <c:if test="${empty paging.listData}">checked</c:if>>전체
						<input type="checkbox" name="tradeUnit" value="USD" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'USD')}">checked</c:if>> 미국
						<input type="checkbox" name="tradeUnit" value="JPY" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'JPY')}">checked</c:if>> 일본
						<input type="checkbox" name="tradeUnit" value="EUR" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'EUR')}">checked</c:if>> 유럽
						<input type="checkbox" name="tradeUnit" value="CNY" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'CNY')}">checked</c:if>> 중국
						<input type="checkbox" name="tradeUnit" value="NZD" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'NZD')}">checked</c:if>> 뉴질랜드
						<input type="checkbox" name="tradeUnit" value="TWD" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'TWD')}">checked</c:if>> 대만
						<input type="checkbox" name="tradeUnit" value="KRW" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'KRW')}">checked</c:if>> 대한민국
						<input type="checkbox" name="tradeUnit" value="RUB" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'RUB')}">checked</c:if>> 러시아
						<input type="checkbox" name="tradeUnit" value="MYR" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'MYR')}">checked</c:if>> 말레이시아
						<input type="checkbox" name="tradeUnit" value="MXN" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'MXN')}">checked</c:if>> 멕시코
						<input type="checkbox" name="tradeUnit" value="BRL" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'BRL')}">checked</c:if>> 브라질<br>
						<input type="checkbox" name="tradeUnit" value="CHF" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'CHF')}">checked</c:if>> 스위스
						<input type="checkbox" name="tradeUnit" value="SGD" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'SGD')}">checked</c:if>> 싱가폴
						<input type="checkbox" name="tradeUnit" value="AED" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'AED')}">checked</c:if>> 아랍에미리트
						<input type="checkbox" name="tradeUnit" value="GBP" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'GBP')}">checked</c:if>> 영국
						<input type="checkbox" name="tradeUnit" value="INR" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'INR')}">checked</c:if>> 인도
						<input type="checkbox" name="tradeUnit" value="CAD" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'CAD')}">checked</c:if>> 캐나다
						<input type="checkbox" name="tradeUnit" value="THB" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'THB')}">checked</c:if>> 태국
						<input type="checkbox" name="tradeUnit" value="TRY" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'TRY')}">checked</c:if>> 터키
						<input type="checkbox" name="tradeUnit" value="PHP" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'PHP')}">checked</c:if>> 필리핀
						<input type="checkbox" name="tradeUnit" value="AUD" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'AUD')}">checked</c:if>> 호주
						<input type="checkbox" name="tradeUnit" value="HKD" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'HKD')}">checked</c:if>> 홍콩
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
							<option value="n" <c:if test="${paging.searchType == 'n'}">selected</c:if>>닉네임</option>
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
			
			<div class="title">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
				<input type="button" onclick="goExcel()" class="btn btn-success" value="엑셀 다운로드">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:9%"/>
					<col style="width:9%"/>
					<col style="width:11%"/>
					<col style="width:18%"/>
					<col style="width:11%"/>
					<col style="width:11%"/>
					<col style="width:11%"/>
					<col style="width:9%"/>
				</colgroup>
				<tr>
					<td>등록일</td>
					<td>거래구분</td>
					<td>통화</td>
					<td>금액</td>
					<td>등록내용</td>
					<td>지역</td>
					<td>등록자 이메일</td>
					<td>닉네임</td>
					<td>거래상태</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td><nobr><font title="${items.tradeDt }">${items.tradeDt }</font></nobr></td>
						<td><nobr><font title="${items.tradeTp }">${items.tradeTp }</font></nobr></td>
						<td><nobr><font title="${items.tradeUnit }">${items.tradeUnit }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.tradeAmnt }"><fmt:formatNumber value="${items.tradeAmnt }" groupingUsed="true"/></font></nobr></td>
						<td><nobr><font title="${items.tradeText }">${items.tradeText }</font></nobr></td>
						<td><nobr><font title="${items.tradeSido }">${items.tradeSido }</font></nobr></td>
						<td><nobr><font title="${items.usrEmail }">${items.usrEmail }</font></nobr></td>
						<td><nobr><font title="${items.usrNick }">${items.usrNick }</font></nobr></td>
						<td><nobr><font title="${items.tradeSt }">${items.tradeSt }</font></nobr></td>
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


function goExcel(){
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
	$('#pageNo').val(0);
	$('#isSearch').val(0);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/trade/excel');
	FORM_BODY.submit();
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