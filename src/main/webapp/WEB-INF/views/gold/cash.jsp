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
	<title>WEYS 시재정보</title>
	
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
				* 시재 날짜
			</div>
			<div class="refresh">
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/gold/krw" method="post">
			<table class="search">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>시재 일자</td>
					<td>
						<input type="text" style="width: 200px;margin-left:10px;display:initial;" name="startDt" id="startDt" class="form-control" value="${paging.startDt }" />
					</td>
				</tr>
			</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="goPage(${paging.pageNo}, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
				</colgroup>
				<tr>
					<td>통화</td>
					<td>A금고</td>
					<td>B금고</td>
					<td>매매기준율</td>
					<td>금고한화</td>
					<td>예약</td>
					<td>예약한화</td>
					<td>총 금액</td>
				</tr>
				<c:forEach var="items" items="${cashList }">
					<tr>
						<td>${items.UNIT }</td>
						<td><fmt:formatNumber value="${items.A_CAGE }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.B_CAGE }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.BASIC_RATE }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.CAGE_KOR }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.RSV_AMNT }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.RSV_KOR }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.TOTAL }" groupingUsed="true"/></td>
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

$(function () {

	$('input[id="startDt"]').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true
    },function(start, end, label) {});


	$("#startDt").change(function() {
		goPage();
    });

});


function goPage(){
	var FORM_BODY = $('form[name=formBody]');
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/gold/krw');
	FORM_BODY.submit();
}
</script>
</html>