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
<input type="hidden" id="fail" value="${fail }">
<input type="hidden" id="upload" value="${upload }">
	<div class="container body">
		<div class="main_container">
	
		<jsp:include page="../common/manage_left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		
        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 거래 현황 상세
			</div>
			<div class="refresh">
				<input type="button" onclick="goExcel()" class="btn btn-success" value="엑셀 다운로드">
				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/api/manage/buy'" value="외화구입">
				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/api/manage/sell'" value="외화판매">
			</div>
			
			<input type="hidden" id="storeId" value="${storeId }">
			<form name="formBody" action="${pageContext.request.contextPath}/api/manage/active" method="post">
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
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>거래일</td>
					<td>거래구분</td>
					<td>거래상세</td>
					<td>통화</td>
					<td>외화</td>
					<td>매매기준율</td>
					<td>금액</td>
					<td>신청서</td>
				</tr>
				<c:forEach var="items" items="${resultList }" varStatus="status">
					<tr>
						<td>${items.regDttm }</td>
						<td>${items.type }</td>
						<td>${items.tp }</td>
						<td>${items.unit }</td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.getAmnt }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.basicRate }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.payAmnt }" groupingUsed="true"/></td>
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

<div class="modal" style="display:none;text-align:center;margin:auto;box-sizing:border-box;width:300px;height:150px;overflow: auto;background-color:white;border:3px solid;">
	<p style="margin-top:10px;">바코드 조회</p>
	<input type="text" id="input_barcode" style="margin:10px;" onkeypress="if( event.keyCode==13 ){writeBarcode();}" placeholder="바코드 번호 입력" value=""><br>
	<input type="button" class="btn btn-success" onclick="closdModal()" value="취소">
	<input type="button" class="btn btn-success" onclick="writeBarcode()" value="조회">
</div>

</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

var wsUri = '${WS_PATH }';
$(function () {
	var fail = $('#fail').val();
	var upload = $('#upload').val();
	if(fail == 'Y')
		alert('바코드를 다시 확인해주세요.');
	
	if(upload == 't')
		alert('등록되었습니다.');
	else if(upload == 'f')
		alert('등록에 실패하였습니다.');
	else if(upload == 'n')
		alert('보유 화폐가 부족합니다. 다시 확인해 주세요.');
	
	websocket = new WebSocket(wsUri);
	websocket.onopen = function(evt) {
		onOpen(evt)
	};
	websocket.onmessage = function(evt) {
		onMessage(evt)
	};
	websocket.onerror = function(evt) {
		onError(evt)
	};
});
function closdModal(){
	$('.modal').css('display', 'none');
}
function openModal(){
	$('.modal').css('display', 'block');
	$('#input_barcode').focus();
}
function onOpen(evt) {
	doSend('NONE', storeId.value);
}
function onMessage(evt) {
	console.log(evt);
	var msg = evt.data;
	
	$('#input_barcode').val(msg);
	openModal();
}
function onError(evt) {
}
function doSend(barcode, storeId) {
	websocket.send(JSON.stringify({
		barcode : barcode
		, storeId : storeId
	}));
}
function goPage(pageNo, isSearch){
	websocket.close();
	var FORM_BODY = $('form[name=formBody]');
	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.submit();
}

function writeBarcode(){
	var barcode = $('#input_barcode').val();
	if(barcode == ''){
		alert('바코드를 입력해 주세요.');
		$('#input_barcode').focus();
		return;
	}
	websocket.close();
	location.href='${pageContext.request.contextPath}/api/manage/' + barcode + '/write';
}

function deleteTrade(paper, tradeType){
	if(confirm('정말로 삭제하시겠습니까?')){
		var data = {};
		data["paper"] = paper;
		data["tradeType"] = tradeType;
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/manage/delete",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == 'success'){
					location.href='${pageContext.request.contextPath}/api/manage/active';
				} else {
					alert('다시 시도해 주세요.');
				}
			},
			error : function(result){
				alert('다시 시도해 주세요.');
			}
		});
	}
}

function goExcel(){
	var FORM_BODY = $('form[name=formBody]');
	$('#pageNo').val(0);
	$('#isSearch').val(0);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/manage/trade/excel');
	FORM_BODY.submit();
}

</script>
</html>