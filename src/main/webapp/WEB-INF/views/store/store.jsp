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
	
		<jsp:include page="../common/left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		
        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 지점 현황 
			</div>
			<div class="refresh">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/store/write'" class="btn btn-success" value="지점 등록">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/store/staff'" class="btn btn-success" value="담당자 관리">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/store" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			
			<table class="list_table">
				<colgroup>
					<col style="width:18%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:8%"/>
				</colgroup>
				<tr>
					<td rowspan="2">지점명</td>
					<td rowspan="2">지점명(영문)</td>
					<td rowspan="2">당일 예약</td>
					<td rowspan="2">예약 완료</td>
					<td colspan="2">오늘 예약</td>
					<td rowspan="2">내일 예약</td>
					<td rowspan="2">수정</td>
				</tr>
				<tr>
					<td style="background-color:#EDEDED;font-weight:700;">남은 거래</td>
					<td style="background-color:#EDEDED;font-weight:700;">접수</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td><a style="cursor:pointer" onclick="detailStore('${items.storeId }')">${items.storeNm }</a></td>
						<td>${items.storeNmEng }</td>
						<td>
							<c:if test="${items.todayRsv == 'Y'}">당일함</c:if>
							<c:if test="${items.todayRsv == 'N'}">안함</c:if>
						</td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.rsvDone }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.rsvToday }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.getToday }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.rsvTomorrow }" groupingUsed="true"/></td>
						<td><a style="cursor:pointer" onclick="modifyStore('${items.storeId }')">수정</a></td>
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

function changeStatus (storeId, storeSt){
	
	if(confirm("상태 변경 하시겠습니까?")){
		var data = {};
		data["storeId"] = storeId;
		data["storeSt"] = storeSt;
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/store/changeStat",
			data:JSON.stringify(data),
			success : function(result){
				var res = result.result;
				if(res == 'success'){
					if(storeSt == 'ON')
						$('#' + storeId).html("<a onclick=\"changeStatus('" + storeId + "', 'OFF')\">OFF</a>");
					else
						$('#' + storeId).html("<a onclick=\"changeStatus('" + storeId + "', 'ON')\">ON</a>");
				} else {
					alert('업데이트 실패');
				}
			},
			error : function(result){
				alert('업데이트 실패, 에러');
			}
		});
	}
}

function modifyStore(storeId){
	location.href='${pageContext.request.contextPath}/api/store/write?id=' + storeId;
}
 
function detailStore(storeId){
	location.href='${pageContext.request.contextPath}/api/store/' + storeId + '/coin';
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.submit();
}
</script>
</html>