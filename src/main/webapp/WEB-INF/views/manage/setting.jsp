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
	<div class="container body">
		<div class="main_container">
	
		<jsp:include page="../common/manage_left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		<form name="formBody" style="margin: 0;" action="${pageContext.request.contextPath}/api/manage/setting/excel" method="post">
		</form>
		
        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 금고 관리
			</div>
			<div class="refresh">
				<input type="button" onclick="goExcel()" class="btn btn-success" value="엑셀 다운로드">
			</div>
			
			<input type="hidden" id="storeId" value="${storeId }">
			
			<table class="list_table">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:25%"/>
					<col style="width:25%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>화폐</td>
					<td>통화명</td>
					<td>금고 수량</td>
					<td>금고 금액</td>
					<td>관리</td>
				</tr>
				<c:set value="" var="unit"/>
				<c:forEach var="items" items="${coinList }" varStatus="cnt">
					<c:if test="${unit == items.UNIT_NM }">
						<c:if test="${items.COIN_ID != 99999 }"><tr></c:if>
						<c:if test="${items.COIN_ID == 99999 }"><tr style="background-color:#B2CCFF;"></c:if>
							<td>${items.COIN_NM }</td>
							<td id="safe_${items.COIN_ID }"><fmt:formatNumber value="${items.SAFE_CNT }" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${items.SAFE_VAL }" groupingUsed="true"/></td>
							<td><a style="cursor:pointer" onclick="modifyUnit(this, '${items.COIN_ID }', '${items.SAFE_CNT }')">수정</a></td>
						</tr>
					</c:if>
					<c:if test="${unit != items.UNIT_NM }">
						<c:if test="${items.COIN_ID != 99999 }"><tr></c:if>
						<c:if test="${items.COIN_ID == 99999 }"><tr style="background-color:#B2CCFF;"></c:if>
							<td rowspan="${items.UNIT_CNT + 1 }">${items.UNIT_NM }<br>${items.UNIT_CD }</td>
							<td>${items.COIN_NM }</td>
							<td id="safe_${items.COIN_ID }"><fmt:formatNumber value="${items.SAFE_CNT }" groupingUsed="true"/></td>
							<td><fmt:formatNumber value="${items.SAFE_VAL }" groupingUsed="true"/></td>
							<td><a style="cursor:pointer" onclick="modifyUnit(this, '${items.COIN_ID }', '${items.SAFE_CNT }')">수정</a></td>
						</tr>
						<c:set value="${items.UNIT_NM }" var="unit"/>
					</c:if>
				</c:forEach>
			</table>

		</div>
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>

</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">
var flag = false;

function modifyUnit(modi, coinId, safeCnt){
	if(flag){
		alert("수정중인 통화가 있습니다.");
		return;
	}
	flag = true;

	$('#safe_' + coinId).html('<input type="number" id="modiSafe" value="'+safeCnt+'">');
	$(modi).parent().html('<a href="#" style="cursor:pointer" onclick="location.reload()">취소</a> | <a href="#" style="cursor:pointer" onclick="modify(' + coinId + ')">수정</a>');
}

function modify(coinId){
	
	if(confirm('정말로 수정하시겠습니까?')){
		var storeId = $('#storeId').val();
		var safeCnt = $('#modiSafe').val();

		var data = {};
		data["storeId"] = storeId;
		data["coinId"] = coinId;
		data["safeCnt"] = safeCnt;
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/manage/coinUpdate",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == '1'){
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

function goExcel(){
	var FORM_BODY = $('form[name=formBody]');
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/manage/setting/excel');
	FORM_BODY.submit();
}

</script>
</html>