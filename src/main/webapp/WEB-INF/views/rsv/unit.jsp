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
	<title>WEYS 예약 지점 통화 관리</title>
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
	
		<jsp:include page="../common/left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		<form name="formBody" style="margin: 0;" action="${pageContext.request.contextPath}/api/manage/setting/excel" method="post">
		</form>
		
        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 예약 지점 통화 관리
			</div>
			<div class="refresh">
			</div>
			<form name="formBody" action="${pageContext.request.contextPath}/api/version/write" method="post">
			
			</form>
			
			<div class="title" style="padding-left: 0;" >
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/unit'" class="btn btn-success" style="color:#000;background-color:#fff;" value="일반 통화">
				<input type="button" class="btn btn-success" value="예약 통화">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
			</div>
			
			<input type="hidden" id="ruId" value="">
			
			<table class="list_table">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:14%"/>
					<col style="width:14%"/>
				</colgroup>
				<tr>
					<td>지점</td>
					<td>통화명</td>
					<td>예약 상태</td>
					<td>화폐 거래 단위</td>
					<td>최소 금액</td>
					<td>관리</td>
				</tr>
				<c:set value="" var="unit"/>
				<c:forEach var="items" items="${resultList }" varStatus="cnt">
						<tr>
							<c:if test="${unit != items.storeNm }"><td rowspan="${items.storeCnt }">${items.storeNm }</td></c:if>
							<td>${items.unitCd }</td>
							<input type="hidden" id="unitSt_val_${items.ruId }" value="${items.unitSt }">
							<input type="hidden" id="unitSize_val_${items.ruId }" value="${items.unitSize }">
							<input type="hidden" id="unitMin_val_${items.ruId }" value="${items.unitMin }">
							<td id="unitSt_${items.ruId }"><c:if test="${'Y' == items.unitSt }">가능</c:if><c:if test="${'R' == items.unitSt }">준비중</c:if></td>
							<td id="unitSize_${items.ruId }"><fmt:formatNumber value="${items.unitSize }" groupingUsed="true"/></td>
							<td id="unitMin_${items.ruId }"><fmt:formatNumber value="${items.unitMin }" groupingUsed="true"/></td>
							<td id="rsv_${items.ruId }"><a style="cursor:pointer" onclick="modifyUnit('${items.ruId }')">수정</a></td>
						</tr>
						<c:set value="${items.storeNm }" var="unit"/>
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
	<input type="text" id="input_barcode" style="margin:10px;" placeholder="바코드 번호 입력" value=""><br>
	<input type="button" class="btn btn-success" onclick="closdModal()" value="취소">
	<input type="button" class="btn btn-success" onclick="writeBarcode()" value="조회">
</div>

</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">
var flag = false;

function modifyUnit(ruId){
	if(flag){
		alert("수정중인 작업이 있습니다.");
		return;
	}
	flag = true;

	var data = {};
	var optionHtml = '';
	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/store/storeList",
		data:JSON.stringify(data),
		success : function(result){
			var list = result.list;

			$('#ruId').val(ruId);
			var stVal = $('#unitSt_val_' + ruId).val();
			optionHtml += '<select style="width:100%;" id="unitSt">';
			optionHtml += '<option value="Y"';
			if(stVal == 'Y'){
				optionHtml += ' selected ';
			}
			optionHtml += '>가능</option>'; 
			optionHtml += '<option value="R"';
			if(stVal == 'R'){
				optionHtml += ' selected ';
			}
			optionHtml += '>준비중</option>'; 
			
			optionHtml += '</select>';
			$('#unitSt_' + ruId).html(optionHtml);
			$('#unitSize_' + ruId).html('<input type="text" style="width:100%;" id="unitSize" value="' + $('#unitSize_val_' + ruId).val() + '">');
			$('#unitMin_' + ruId).html('<input type="text" style="width:100%;" id="unitMin" value="' + $('#unitMin_val_' + ruId).val() + '">');
			$('#rsv_' + ruId).html('<a href="#" style="cursor:pointer" onclick="location.reload()">취소</a> | <a href="#" style="cursor:pointer" onclick="updateRsvUnit()">수정</a>');
		},
		error : function(result){
			
		}
	});
}

function updateRsvUnit(){
	
	if(confirm('정말로 수정하시겠습니까?')){
		var ruId = $('#ruId').val();
		var unitSt = $('#unitSt').val();
		var unitSize = $('#unitSize').val();
		var unitMin = $('#unitMin').val();

		var data = {};
		data["ruId"] = ruId;
		data["unitSt"] = unitSt;
		data["unitSize"] = unitSize;
		data["unitMin"] = unitMin;
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/rsv/updateRsvUnit",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == 'success'){
					alert('변경되었습니다.');
					location.reload(true);
				} else {
					alert('다시 시도해 주세요.');
				}
			},
			error : function(result){
				alert('다시 시도해 주세요.');
			}
		});
	}
	
	flag = false;

}

</script>
</html>