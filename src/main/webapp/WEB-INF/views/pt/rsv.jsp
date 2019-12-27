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
				* 예약 / 배송 현황 
			</div>
			<div class="refresh">
				<input type="button" class="btn btn-warning" onclick="missMatch()" value="정보 불일치">
				<input type="button" class="btn btn-warning" onclick="backRsv()" value="시간초과/환불 복구">
				<input type="button" class="btn btn-success" onclick="openDialog()" value="예약정보 출력">
				<input type="button" class="btn btn-success" onclick="incomeChk()" value="입금 완료">
				<input type="button" class="btn btn-success" onclick="setRsvMiss()" value="입금시간 초과">
<!-- 				<input type="button" class="btn btn-success" onclick="setManager()" value="담당자 지정"> -->
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/rsv/list" method="post">
			<input type="hidden" name="pageNo" id="pageNo" value="${paging.pageNo}">
			<input type="hidden" name="isSearch" id="isSearch">
			<input type="hidden" name="orderVal" id="orderVal" value="0">
			<input type="hidden" name="rsvStVal" id="rsvStVal">
			<input type="hidden" name="orderTp" id="orderTp" value="${paging.orderTp}">
			<input type="hidden" name="descTp" id="descTp" value="${paging.descTp}">
			<input type="hidden" name="preOrder" id="preOrder" value="${paging.preOrder}">
			<input type="hidden" name="rsvFormVal" id="rsvFormVal">
			<table class="search">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>예약 / 배송 일자</td>
					<td>
						<input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${paging.startDt } - ${paging.endDt }" />
					</td>
				</tr> 
				<tr>
					<td>예약 / 배송</td>
					<td>
						<input type="checkbox" name="rsvForm" id="allCheckForm" value="t" <c:if test="${empty paging.listData1}">checked</c:if>>전체
						<input type="checkbox" name="rsvForm" value="R" <c:if test="${empty paging.listData1}">checked</c:if><c:if test="${fn:contains(paging.listData1, 'R')}">checked</c:if>> 예약
						<input type="checkbox" name="rsvForm" value="D" <c:if test="${empty paging.listData1}">checked</c:if><c:if test="${fn:contains(paging.listData1, 'D')}">checked</c:if>> 배송
					</td>
				</tr> 
				<tr>
					<td>상태</td>
					<td>
						<input type="checkbox" name="rsvSt" id="allCheckSt" value="t" <c:if test="${empty paging.listData}">checked</c:if>>전체
						<input type="checkbox" name="rsvSt" value="S" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'S')}">checked</c:if>> 예약완료
						<input type="checkbox" name="rsvSt" value="R" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'R')}">checked</c:if>> 준비완료
						<input type="checkbox" name="rsvSt" value="F" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'F')}">checked</c:if>> 거래완료
						<input type="checkbox" name="rsvSt" value="M" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'M')}">checked</c:if>> 입금시간초과
						<input type="checkbox" name="rsvSt" value="CB" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'CB')}">checked</c:if>> 입금전취소
						<input type="checkbox" name="rsvSt" value="CR" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'CR')}">checked</c:if>> 환불대기
						<input type="checkbox" name="rsvSt" value="CF" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'CF')}">checked</c:if>> 환불완료
					</td>
				</tr>
				<tr>
					<td>통화 선택</td>
					<td>
						<select name="searchType" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;width:200px;">
							<option value="t" <c:if test="${paging.searchType == 't'}">selected</c:if>>전체</option>
							<c:forEach var="unit" items="${unitList}" varStatus="status">
								<option value="${unit.UNIT_CD }" <c:if test="${paging.searchType == unit.UNIT_CD}">selected</c:if>>${unit.UNIT_NM }</option>
							</c:forEach>
						</select>
					</td>
				</tr> 
				<tr>
					<td>예약자 이름</td>
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
					<col style="width:5.8%"/>
					<col style="width:11.8%"/>
					<col style="width:11.8%"/>
					<col style="width:11.8%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.8%"/>
					<col style="width:12.8%"/>
					<col style="width:8.3%"/>
				</colgroup>
				<tr>
					<td></td>
					<td onclick="ordering('store')">지점</td>
					<td onclick="ordering('regdt')">등록일</td>
					<td onclick="ordering('rsvdt')">예약일</td>
					<td>통화</td>
					<td>권종</td>
					<td>예약외화</td>
					
					<td>입금금액</td>
					<td>수령인</td>
					<td>예약상태</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<input type="hidden" id="store_${items.rsvId }" value="${items.storeId }">
					<tr <c:choose>
							<c:when test="${items.rsvSt == 'S' }">style="background-color:rgba(255,151,151,0.2);"</c:when>
							<c:when test="${items.rsvSt == 'P' }">style="color:rgb(255,0,0);"</c:when>
							<c:when test="${items.rsvSt == 'R' and items.adminSt == 'N' }">style="background-color:rgba(135,249,151,0.2);"</c:when>
							<c:when test="${items.rsvSt == 'R' and items.adminSt == 'R' }">style="background-color:rgba(255,228,0,0.2);"</c:when>
							<c:when test="${items.rsvSt == 'R' and items.adminSt == 'Y' }">style="background-color:rgba(77,158,255,0.2);"</c:when>
							<c:when test="${items.rsvSt == 'F' }"></c:when>
							<c:otherwise>style="background-color:rgba(234,234,234,0.2);"</c:otherwise>
						</c:choose>>
						<td>
							<c:if test="${items.rsvSt == 'S' || items.rsvSt == 'P'}">
								<input type="checkbox" class="checkId" data-nm="${items.rsvNm }" name="rsvId" value="${items.rsvId }">
							</c:if>
							<c:if test="${items.rsvSt == 'M' || items.rsvSt == 'CR'  || items.rsvSt == 'CB' }">
								<input type="checkbox" class="checkId" data-nm="${items.rsvNm }" data-st="${items.rsvSt }" name="missId" value="${items.rsvId }">
							</c:if>
						</td>
						<td><nobr><font title="${items.storeNm }">${items.storeNm }</font></nobr></td>
						<td><nobr><font title="${items.regDttm }">${items.regDttm }</font></nobr></td>
						<td onclick="changeDt('${items.rsvId }', '${items.rsvNm }', '${items.rsvDt }')"><nobr><font title="${items.rsvDt }">${items.rsvDt }</font></nobr></td>
						<td><nobr><font title="${items.unit }">${items.unit }</font></nobr></td>
						<td>
							<c:choose>
								<c:when test="${items.rsvPaper == 'S' }">소액</c:when>
								<c:when test="${items.rsvPaper == 'B' }">고액</c:when>
								<c:otherwise>랜덤</c:otherwise>
							</c:choose>
						</td>
						<td style="text-align:right;"><nobr><font title="${items.rsvAmnt }"><fmt:formatNumber value="${items.rsvAmnt }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.getAmnt }"><fmt:formatNumber value="${items.getAmnt }" groupingUsed="true"/></font></nobr></td>
						<c:if test="${items.usrId eq '0' }">
							<td><nobr><font title="${items.rsvNm }(${items.usrTel })"><c:if test="${items.memoCnt ne '0' }">★</c:if>${items.rsvNm }(${items.usrTel })</font></nobr></td>
						</c:if>
						<c:if test="${items.usrId ne '0' }">
							<td onclick="location.href='${pageContext.request.contextPath}/api/user/${items.usrId}/detail'"><nobr><font title="${items.rsvNm }(${items.usrTel })"><c:if test="${items.memoCnt ne '0' }">★</c:if>${items.rsvNm }(${items.usrTel })</font></nobr></td>
						</c:if>
<%-- 						<td><nobr><font title="${items.adminName }">${items.adminName }</font></nobr></td> --%>
						<td onclick="getMemoList('${items.rsvId }', '${items.rsvNm }')">
							<c:choose>
								<c:when test="${items.rsvSt == 'S' || items.rsvSt == 'P' }">예약완료</c:when>
								<c:when test="${items.rsvSt == 'R' and items.adminSt == 'N' }">입금완료</c:when>
								<c:when test="${items.rsvSt == 'R' and items.adminSt == 'R' }">준비완료</c:when>
								<c:when test="${items.rsvSt == 'R' and items.adminSt == 'Y' }">인수완료</c:when>
								<c:when test="${items.rsvSt == 'F' }">거래완료</c:when>
								<c:when test="${items.rsvSt == 'M' }">시간초과</c:when>
								<c:when test="${items.rsvSt == 'CB' }">입금전취소</c:when>
								<c:when test="${items.rsvSt == 'CR' }">환불대기</c:when>
								<c:otherwise>환불완료</c:otherwise>
							</c:choose>
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
<div class="dialog" id="dialog_excel">
	<div class="dialog_body" style="height:220px;margin-top:-110px;">
		<div class="dialog_title">예약 정보 출력</div>
		
		<form name="excelBody" action="${pageContext.request.contextPath}/api/rsv/excel" method="post">
		<table>
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>예약 날짜 선택</td>
					<td><input type="text" style="width:240pxpx;display:initial;" name="reservation" id="res" class="form-control" value="${excelDt}" /></td>
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


<div class="dialog" id="dialog_memo">
	<div class="dialog_body" style="height:400px;width:700px;margin-top:-200px;margin-left:-350px;">
		<div class="dialog_title" id="dialog_memo_title"">예약 메모 내용</div>
		<table>
			<colgroup>
					<col style="width:50%"/>
					<col style="width:25%"/>
					<col style="width:25%"/>
				</colgroup>
				<tbody id="memoHtml"></tbody>
		</table>
		<td colspan="3"><textarea style="width:100%;" rows="3" id="memoTxt"></textarea></td>
		<input type="hidden" id="memoRsvId">
		<input type="hidden" id="memoRsvNm">
		<div class="dialog_button">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="insertRsvMemo()" value="메모등록">
		</div>
	</div>
</div>


<div class="dialog" id="dialog_mng">
	<div class="dialog_body" style="height:230px;margin-top:-115px;">
		<div class="dialog_title">담당자 지정</div>
		
		<table>
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>지점</td>
					<td id="store_nm"></td>
				</tr>
				<tr>
					<td>담당자</td>
					<td id="store_mng"></td>
				</tr>
		</table>
		<div class="dialog_button">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="setMng()" value="저장">
		</div>
	</div>
</div>

<div class="dialog" id="dialog_rsv">
	<div class="dialog_body" style="height:290px;margin-top:-145px;">
		<div class="dialog_title">가상계좌 정보</div>
		<input type="hidden" id="rsvId">
		<table>
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>은행</td>
					<td id="vbankNm"></td>
				</tr>
				<tr>
					<td>계좌번호</td>
					<td id="vbankCd"></td>
				</tr>
				<tr>
					<td>예금주</td>
					<td id="vbankHolder"></td>
				</tr>
				<tr>
					<td>만료시간</td>
					<td id="vbankDue"></td>
				</tr>
		</table>
		<div class="dialog_button">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="updateIncome()" value="입금완료">
		</div>
	</div>
</div>

<div class="dialog" id="dialog_dt">
	<div class="dialog_body" style="height:230px;margin-top:-115px;">
		<div class="dialog_title">예약일 변경</div>
		
		<table>
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>고객명</td>
					<td id="change_dt_nm"></td>
				</tr>
				<tr>
					<td>변경일</td>
					<td><input type="text" style="width:110px;" id="change_dt_dt"> <input type="text" style="width:110px;" id="change_dt_tm"></td>
				</tr>
		</table>
		<div class="dialog_button">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="setRsvDt()" value="변경">
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

	$('#allCheckForm').click(function(){
		if($("#allCheckForm").prop("checked")) {
			$("input[name=rsvForm]").prop("checked",true);
		} else {
			$("input[name=rsvForm]").prop("checked",false);
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

	$("input[name=rsvForm]").change(function() {
        if($('input[name=rsvForm]:checked').length == 3){
        	$('#allCheckForm').prop("checked",true);
        } else if($('input[name=rsvForm]:checked').length == 2 && !$("#allCheckForm").prop("checked")){
        	$('#allCheckForm').prop("checked",true);
        } else {
        	$('#allCheckForm').prop("checked",false);
        }
    });

	$('#checkAll').click(function(){
		if($('#checkAll').prop("checked")){
			$('input[name=rsvId]').prop("checked", true);
		} else {
			$('input[name=rsvId]').prop("checked", false);
		}
	});

	$("input[name=rsvId]").change(function() {
        if($('input[name=rsvId]:checked').length == $('input[name=rsvId]').length){
        	$('#checkAll').prop("checked",true);
        } else {
        	$('#checkAll').prop("checked",false);
        }
    });

});

function setRsvDt(){
	var data = {};
	data["rsvId"] = $('#rsvId').val();
	data["rsvDt"] = $('#change_dt_dt').val();
	data["rsvTm"] = $('#change_dt_tm').val();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/rsv/updateRsvDt",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			
			var result = result.res;
			if(result == 'suc'){
				alert('예약일이 변경되었습니다.');
				location.reload(true);
			} else {
				alert('실패');
			}
			
		},
		error : function(result){
			alert('업데이트 실패, 에러');
		}
	});
}

function missMatch(){
	var rsvId = '';
	var nm = '';
	$(":checkbox[name='rsvId']:checked").each(function(index, item){
		rsvId = $(item).val();
		nm = $(item).attr("data-nm");;
	});
	
	if(confirm(nm + ' 님에게 예약정보 불일치 알림톡을 전송하시겠습니까?')){
		
		var data = {};
		data["rsvId"] = rsvId;
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/api/rsv/missMatch",
			data: JSON.stringify(data),
			async : false,
			contentType: "application/json",
			success : function(result){
				
				var result = result.res;
				if(result == 'suc'){
					alert('예약정보 불일치 알림톡 전송 완료');
				} else if(result == 'sent'){
					alert('이미 전송했습니다.');
				} else {
					alert('실패');
				}
				
			},
			error : function(result){
				alert('전송 실패, 에러');
			}
		});
	}
}

function changeDt(rsvId, usrNm, rsvDt){
	$('#rsvId').val(rsvId);
	$('#change_dt_nm').html(usrNm);
	var rdt = rsvDt.split(' ');
	console.log(rdt);
	var rdate = rdt[0];
	var rtm = rdt[1];

	$('#change_dt_dt').val(rdate);
	$('#change_dt_tm').val(rtm);
	
	$('.dialog_back').css('display', 'block');
	$('#dialog_dt').css('display', 'block');
}

function ordering(type){
	$('#orderTp').val(type);
	$('#orderVal').val(1);
	goPage($('#pageNo').val(), 0);
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');
	var rsvSt = '';
	var rsvForm = '';
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
	$('input:checkbox[name="rsvForm"]').each(function() {
		if(this.checked){//checked 처리된 항목의 값
			if(rsvForm == ''){
				rsvForm = this.value;
			}else if(rsvForm == 't'){}
			else{
				rsvForm = rsvForm + "," + this.value;
			}
		}
	});
	$('#rsvFormVal').val(rsvForm);
	$('#rsvStVal').val(rsvSt);
	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/rsv/list/pt');
	FORM_BODY.submit();
}

function dataExcel(){
	var FORM_BODY = $('form[name=formBody]');
	var rsvSt = '';
	var rsvForm = '';
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
	$('input:checkbox[name="rsvForm"]').each(function() {
		if(this.checked){//checked 처리된 항목의 값
			if(rsvForm == ''){
				rsvForm = this.value;
			}else if(rsvForm == 't'){}
			else{
				rsvForm = rsvForm + "," + this.value;
			}
		}
	});
	$('#rsvFormVal').val(rsvForm);
	$('#rsvStVal').val(rsvSt);
	$('#pageNo').val(0);
	$('#isSearch').val(0);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/rsv/dataExcel');
	FORM_BODY.submit();
}

function openDialog(){
	$('.dialog_back').css('display', 'block');
	$('#dialog_excel').css('display', 'block');
}

function openDialogMng(){
	$('.dialog_back').css('display', 'block');
	$('#dialog_mng').css('display', 'block');
}


function closeDialog(){
	$('.dialog_back').css('display', 'none');
	$('.dialog').css('display', 'none');
}

function goExcel(){
	var FORM_BODY = $('form[name=excelBody]');
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/rsv/excel');
	FORM_BODY.submit();
}

function setRsvMiss(){
	
	var rsvId = '';
	var nm = '';
	$(":checkbox[name='rsvId']:checked").each(function(index, item){
		rsvId = $(item).val();
		nm = $(item).attr("data-nm");;
	});
	
	if(confirm(nm + ' 님의 예약을 시간 초과 처리하시겠습니까?')){
		
		var data = {};
		data["rsvId"] = rsvId;
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/api/rsv/updateMiss",
			data: JSON.stringify(data),
			async : false,
			contentType: "application/json",
			success : function(result){
				
				var result = result.res;
				if(result == 'suc'){
					alert('입금시간 초과 처리');
					location.reload(true);
				} else {
					alert('실패');
				}
				
			},
			error : function(result){
				alert('업데이트 실패, 에러');
			}
		});
	}
	
}


function incomeChk(){

	var rsvId = '';
	var nm = '';
	$(":checkbox[name='rsvId']:checked").each(function(index, item){
		rsvId = $(item).val();
		nm = $(item).attr("data-nm");;
	});
	
	if(confirm(nm + ' 님의 예약을 입금 완료 처리하시겠습니까?')){
		
		var data = {};
		data["rsvId"] = rsvId;
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/api/rsv/updateIncome",
			data: JSON.stringify(data),
			async : false,
			contentType: "application/json",
			success : function(result){
				
				var result = result.res;
				if(result == 'suc'){
					alert('입금확인');
					location.reload(true);
				} else {
					alert('실패');
				}
				
			},
			error : function(result){
				alert('업데이트 실패, 에러');
			}
		});
	}
	
}

function setManager(){
	var storeId = '';
	$(":checkbox[name='rsvId']:checked").each(function(index, item){
		var id = $(item).val();
		if(storeId == ''){
			storeId = $('#store_' + id).val();
		} else {
			if(storeId != $('#store_' + id).val()){
				alert('같은 지점의 예약을 선택해 주세요.');
				return;
			}
		}
	});
	
	if(storeId == ''){
		alert('담당자 지정할 예약을 선택해 주세요.');
		return;
	}
	
	var data = {};
	data["storeId"] = storeId;
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/rsv/getStoreInfo",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			var items = result.list;

			var store = '';
			
			var html = '';
			html += '<select id="setMng">';
			for(i=0 ; i<items.length ; i++){
				var adminNm = items[i].ADMIN_NAME;
				var adminKey = items[i].ADMIN_KEY;
				store = items[i].STORE_NM;
				
				html += '<option value="' + adminKey + '">' + adminNm + '</option>';
				
			}
			html += '</selected>';
			$('#store_mng').append(html);
			$('#store_nm').html(store);
			
		},
		error : function(result){
			alert('업데이트 실패, 에러');
		}
	});
	
	openDialogMng();
}

function setMng(){
	var rsvList = new Array();
	var adminKey = $('#setMng').val();
	$(":checkbox[name='rsvId']:checked").each(function(index, item){
		var id = $(item).val();
		rsvList.push(id);
	});
	
	var data = {};
	data["rsvList"] = rsvList;
	data["adminKey"] = adminKey;
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/rsv/setMng",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			if(result.result == 'success'){
				alert('업데이트 되었습니다.');
				location.reload(true);
			} else {
				alert('업데이트 실패, 다시 시도해주세요.');
			}
		},
		error : function(result){
			alert('서버 에러, 다시 시도해주세요.');
		}
	});
}

function checkVbank(rsvId){
	
	var data = {};
	data["rsvId"] = rsvId;
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/rsv/checkVb",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			$('#rsvId').val(rsvId);
			$('#vbankNm').html(result.VBANK_NM);
			$('#vbankCd').html(result.VBANK_CD);
			$('#vbankHolder').html(result.VBANK_HOLDER);
			$('#vbankDue').html(result.VBANK_DUE);

			$('.dialog_back').css('display', 'block');
			$('#dialog_rsv').css('display', 'block');
		},
		error : function(result){
			alert('서버 에러, 다시 시도해주세요.');
		}
	});
}

function updateIncome(){
	var data = {};
	data["rsvId"] = $('#rsvId').val();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/rsv/updateIncome",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			
			
			$('.dialog_back').css('display', 'block');
			$('#dialog_rsv').css('display', 'block');
		},
		error : function(result){
			alert('서버 에러, 다시 시도해주세요.');
		}
	});
}

function backRsv(){

	var rsvId = '';
	var nm = '';
	var st = '';
	$(":checkbox[name='missId']:checked").each(function(index, item){
		rsvId = $(item).val();
		nm = $(item).attr("data-nm");
		st = $(item).attr("data-st");
	});
	
	var msg = '';
	if(st == 'CR'){
		msg = '환불신청을';
	} else {
		msg = '시간초과를';
	}
	
	if(confirm(nm + ' 님의 ' + msg + ' 복구 처리하시겠습니까?')){
		
		var data = {};
		data["rsvId"] = rsvId;
		data["rsvSt"] = 'M';
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/api/rsv/backRsv",
			data: JSON.stringify(data),
			async : false,
			contentType: "application/json",
			success : function(result){
				
				var result = result.res;
				if(result == 'suc'){
					alert('복구처리');
					location.reload(true);
				} else {
					alert('실패');
					location.reload(true);
				}
				
			},
			error : function(result){
				alert('업데이트 실패, 에러');
			}
		});
	}
	
}


function insertRsvMemo(){
	var data = {};
	data["rsvId"] = $('#memoRsvId').val();
	data["text"] = $('#memoTxt').val();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/rsv/insertRsvMemo",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			
			var result = result.res;
			
			if(result == 'suc'){
				alert('등록되었습니다.');

				$('.dialog_back').css('display', 'none');
				$('#dialog_memo').css('display', 'none');
				
				getMemoList($('#memoRsvId').val(), $('#memoRsvNm').val());
			}

			
		},
		error : function(result){
			alert('서버 에러');
		}
	});
}

function getMemoList(rsvId, rsvNm){

	$('#memoRsvId').val(rsvId);
	$('#memoRsvNm').val(rsvNm);
	var data = {};
	data["rsvId"] = rsvId;
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/rsv/selectRsvMemo",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			
			var result = result.resultList;

			$('#memoHtml').empty();
			var html = '<tr><td style="text-align:center;">상담 내용</td><td style="text-align:center;">상담자</td><td style="text-align:center;">상담 시간</td></tr>';
			
			for(i=0 ; i<result.length ; i++){
				var msg = result[i].MEMO.replace(/\n/g, '<br/>');
				html += '<tr style="border-top:1px solid #000"><td style="text-align:center;">' + msg + '</td><td style="text-align:center;">' + result[i].ADMIN + '</td><td style="text-align:center;">' + result[i].REG_DTTM + '</td></tr>';
			}
			$('#dialog_memo_title').html(rsvNm + '님의 메모 내역');
			$('#memoHtml').append(html);

			$('.dialog_back').css('display', 'block');
			$('#dialog_memo').css('display', 'block');
			
		},
		error : function(result){
			alert('서버 에러');
		}
	});
}


</script>
</html>