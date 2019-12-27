<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 통화</title>
	
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
				* 지점별 통화 관리
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/version/write" method="post">
			
			</form>
			
			<div class="title" style="padding-left: 0;" >
				<input type="button" class="btn btn-success" value="일반 통화">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/unit/rsv'" class="btn btn-success" style="color:#000;background-color:#fff;" value="예약 통화">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:17%"/>
					<col style="width:9%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:9%"/>
				</colgroup>
				<tr>
					<td>통화</td>
					<td>예약상태</td>
					<td>예약단위</td>
					<td>최소금액</td>
					<td>웨이즈수수료</td>
					<td>은행수수료</td>
					<td>공항수수료</td>
					<td>관리</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td>${items.unitNm } ${items.unitCd }</td>
						<td id="rsvSt_${items.unitId }">
							<c:choose>
								<c:when test="${items.rsvSt == 'Y' }">예약중</c:when>
								<c:when test="${items.rsvSt == 'R' }">준비중</c:when>
								<c:otherwise>불가</c:otherwise>
							</c:choose>
						</td>
						<td id="unitSize_${items.unitId }"><fmt:formatNumber value="${items.unitSize }" groupingUsed="true"/></td>
						<td id="unitMin_${items.unitId }"><fmt:formatNumber value="${items.unitMin }" groupingUsed="true"/></td>
						<td id="weysCommis_${items.unitId }"><fmt:formatNumber value="${items.weysCommis }" groupingUsed="true"/></td>
						<td id="unitCommis_${items.unitId }"><fmt:formatNumber value="${items.unitCommis }" groupingUsed="true"/></td>
						<td id="airCommis_${items.unitId }"><fmt:formatNumber value="${items.airCommis }" groupingUsed="true"/></td>
						<td><a style="cursor:pointer" onclick="modifyUnit(this, '${items.unitId }', '${items.rsvSt }', '${items.unitSize }', '${items.unitMin }', '${items.weysCommis }', '${items.unitCommis }', '${items.airCommis }')">수정</a></td>
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

function modifyUnit(modi, unitId, rsvSt, unitSize, unitMin, weysCommis, unitCommis, airCommis){
	if(flag){
		alert("수정중인 버전이 있습니다.");
		return;
	}
	flag = true;

	var optionHtml = '<select name="rsvSt" id="rsvSt">';
	if(rsvSt == 'Y'){
		optionHtml += '<option value="Y" selected>예약중</option>';
	} else {
		optionHtml += '<option value="Y">예약중</option>';
	}
	if(rsvSt == 'R'){
		optionHtml += '<option value="R" selected>준비중</option>';
	} else {
		optionHtml += '<option value="R">준비중</option>';
	}
	if(rsvSt == 'N'){
		optionHtml += '<option value="N" selected>불가</option>';
	} else {
		optionHtml += '<option value="N">불가</option>';
	}
	optionHtml += '</select>';
	
	$('#rsvSt_' + unitId).html(optionHtml);
	$('#unitSize_' + unitId).html('<input type="text" id="unitSize" name="unitSize" value="' + unitSize + '">');
	$('#unitMin_' + unitId).html('<input type="text" id="unitMin" name="unitMin" value="' + unitMin + '">');
	$('#weysCommis_' + unitId).html('<input type="text" id="weysCommis" name="weysCommis" value="' + weysCommis + '">');
	$('#unitCommis_' + unitId).html('<input type="text" id="unitCommis" name="unitCommis" value="' + unitCommis + '">');
	$('#airCommis_' + unitId).html('<input type="text" id="airCommis" name="airCommis" value="' + airCommis + '">');
	
	$(modi).parent().html('<a href="#" style="cursor:pointer" onclick="location.reload()">취소</a> | <a href="#" style="cursor:pointer" onclick="modify(' + unitId + ')">수정</a>');
}

function modify(unitId){
	
	if(confirm('정말로 수정하시겠습니까?')){
		
		var data = {};
		data["unitId"] = unitId;
		data["rsvSt"] = $('#rsvSt').val();
		data["unitSize"] = $('#unitSize').val();
		data["unitMin"] = $('#unitMin').val();
		data["weysCommis"] = $('#weysCommis').val();
		data["unitCommis"] = $('#unitCommis').val();
		data["airCommis"] = $('#airCommis').val();
		
		
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/unit/update",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == 'success'){
					location.href='${pageContext.request.contextPath}/api/unit';
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