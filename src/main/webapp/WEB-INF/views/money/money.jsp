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
	<title>WEYS 외화관리</title>
	
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
				* 외화관리 
			</div>
			<div class="refresh">
<!-- 				<input type="button" class="btn btn-success" onclick="openDialog()" value="등록"> -->
			</div>

			
			<form name="formBody" action="${pageContext.request.contextPath}/api/money" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>

			<div class="title">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
			</div>
			
			<table class="list_table" style="font-size:12px;">
				<colgroup>
					<col style="width:9.8%"/>
					<col style="width:9.8%"/>
					<col style="width:9.8%"/>
					<col style="width:9.8%"/>
					<col style="width:1%"/>
					<col style="width:9.8%"/>
					<col style="width:9.8%"/>
					<col style="width:9.8%"/>
					<col style="width:9.8%"/>
					<col style="width:1%"/>
					<col style="width:9.8%"/>
					<col style="width:9.8%"/>
				</colgroup>
				<tr>
					<td>매입일</td>
					<td>통화</td>
					<td>매입금액</td>
					<td>기준율</td>
					<td></td>
					<td>판매외화</td>
					<td>한화금액</td>
					<td>평균기준율</td>
					<td>기준율차이</td>
					<td></td>
					<td>남은금액</td>
					<td>남은기준율</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td><nobr><font title="${items.buyDttm }">${items.buyDttm }</font></nobr></td>
						<td><nobr><font title="${items.unit }">${items.unit }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.buyAmnt }"><fmt:formatNumber value="${items.buyAmnt }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.buyRate }"><fmt:formatNumber value="${items.buyRate }" groupingUsed="true"/></font></nobr></td>
						<td></td>
						<td style="text-align:right;"><nobr><font title="${items.sellAmnt }"><fmt:formatNumber value="${items.sellAmnt }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.sellKor }"><fmt:formatNumber value="${items.sellKor }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.avgRate }"><fmt:formatNumber value="${items.avgRate }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.diffRate }"><fmt:formatNumber value="${items.diffRate }" groupingUsed="true"/></font></nobr></td>
						<td></td>
						<td style="text-align:right;"><nobr><font title="${items.buyAmnt - items.sellAmnt }"><fmt:formatNumber value="${items.buyAmnt - items.sellAmnt }" groupingUsed="true"/></font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.leftRate }"><fmt:formatNumber value="${items.leftRate }" groupingUsed="true"/></font></nobr></td>
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
	<div class="dialog_body" style="height:280px;margin-top:-140px;">
		<div class="dialog_title">외화 추가</div>
		
		<table>
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>외화</td>
					<td><input type="text" id="unit"></td>
				</tr>
				<tr>
					<td>매매기준율</td>
					<td><input type="number" id="basicRate"></td>
				</tr>
				<tr>
					<td>액수</td>
					<td><input type="number" id="amnt"></td>
				</tr>
		</table>
		<div class="dialog_button">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="addUnit()" value="저장">
		</div>
	</div>
</div>


</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

$(function () {
	
});


function openDialog(){
	$('.dialog_back').css('display', 'block');
	$('#dialog_mng').css('display', 'block');
}

function closeDialog(){
	$('.dialog_back').css('display', 'none');
	$('.dialog').css('display', 'none');
}

function addUnit(){
	
	var unit = $('#unit').val();
	
	if(unit == 'JPY' || unit == 'USD' || unit == 'HKD' || unit == 'TWD' || unit == 'CNY'){
	} else {
		alert('외화를 잘 입력해주세요');
		return;
	}
	
	var data = {};
	data["unit"] = unit;
	data["basicRate"] = $('#basicRate').val();
	data["amnt"] = $('#amnt').val();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/money/addUnit",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){

			var rs = result.res;
			
			if(rs == 'suc'){
				alert('등록되었습니다.');
				location.reload(true);
			} else {
				alert('오류.');
			}
		},
		error : function(result){
			alert('서버 에러, 다시 시도해주세요.');
		}
	});
}


function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/money');
	FORM_BODY.submit();
}



</script>
</html>