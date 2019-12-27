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
		
        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 지점 돈 관리
			</div>
			<div class="refresh">
				
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:25%"/>
					<col style="width:25%"/>
					<col style="width:25%"/>
					<col style="width:25%"/>
				</colgroup>
				<tr>
					<td>용도</td>
					<td>통화명</td>
					<td>금액</td>
					<td>관리</td>
				</tr>
				<c:forEach var="items" items="${coinList }" varStatus="cnt">
					<tr>
						<td><c:if test="${items.TYPE == 'R'}">예약용</c:if><c:if test="${items.TYPE == 'I'}">인천용</c:if><c:if test="${items.TYPE == 'S'}">환전용</c:if></td>
						<td>${items.UNIT_NM } / ${items.UNIT_CD }</td>
						<td id="safe_${items.UNIT_ID }_${items.TYPE }"><fmt:formatNumber value="${items.AMNT }" groupingUsed="true"/></td>
						<td><a style="cursor:pointer" onclick="modifyUnit(this, '${items.UNIT_ID }', '${items.TYPE }', '${items.AMNT }')">수정</a></td>
					</tr>
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

function modifyUnit(modi, unitId, type, amnt){
	if(flag){
		alert("수정중인 통화가 있습니다.");
		return;
	}
	flag = true;

	$('#safe_' + unitId + '_' + type).html('<input type="number" id="modiSafe" value="'+amnt+'">');
	$(modi).parent().html('<a href="#" style="cursor:pointer" onclick="location.reload()">취소</a> | <a href="#" style="cursor:pointer" onclick="modify(' + unitId + ',\'' + type +'\')">수정</a>');
}

function modify(unitId, type){
	
	if(confirm('정말로 수정하시겠습니까?')){
		var amnt = $('#modiSafe').val();

		var data = {};
		data["type"] = type;
		data["unitId"] = unitId;
		data["amnt"] = amnt;
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/manage/moneyUpdate",
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