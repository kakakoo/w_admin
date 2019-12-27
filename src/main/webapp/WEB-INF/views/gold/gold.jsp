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
	<title>WEYS 시제 현황</title>
	
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
			<form name="formBody" action="${pageContext.request.contextPath}/api/gold/m" method="post">
			<div class="title">
				* 시제 현황 - 
				<select id="storeId" name="storeId">
					<c:if test="${adminTp == 'S'}">
						<option value="1">본사 금고</option>
					</c:if>
					<option value="5">배송 금고</option>
				</select>
			</div>
			<div class="refresh">
				<c:if test="${adminTp == 'S'}">
					<input type="button" class="btn btn-success" onclick="goMoney()" value="환율관리">
				</c:if>
				<input type="button" class="btn btn-success" onclick="goManage()" value="시제관리">
			</div>
			</form>
			
			<table class="list_table" style="margin-top:10px;">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:40%"/>
				</colgroup>
				<tbody id="goldInfoHtml">
				</tbody>
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

	$("#storeId").change(function(){
		
		getStoreGold();
	});
	getStoreGold();
});

function getStoreGold(){
	
	var data = {};
	data["storeId"] = $("#storeId").val();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/gold/getStoreGold",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){

			var goldList = result.goldList;

			$('#goldInfoHtml').empty();
			var goldHtml = '<tr style="border-top:3px solid #000;"><td>외화</td><td>권종</td><td>보유현황</td><td>총 금액</td></tr>';

			var unit = '';
			for(i=0 ; i<goldList.length ; i++){
				goldHtml += '<tr ';
				if(unit != goldList[i].unit){
					goldHtml += 'style="border-top:3px solid #000;"';
				}
				goldHtml += '>';
				if(unit != goldList[i].unit){
					goldHtml += '<td rowspan="' + goldList[i].rowSpan + '">' + goldList[i].unit + '</td>';
				}
				goldHtml += '<td style="text-align:right;">' + goldList[i].unitDp + ' ' + goldList[i].paper.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
				goldHtml += '<td style="text-align:right;">' + goldList[i].amnt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';

				if(unit != goldList[i].unit){
					goldHtml += '<td rowspan="' + goldList[i].rowSpan + '">' + goldList[i].unitDp + ' ' + goldList[i].sAmnt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
					unit = goldList[i].unit;
				}
				
				goldHtml += '</tr>';
			}
			
			$('#goldInfoHtml').append(goldHtml);
		},
		error : function(result){
			

		}
	});
}

function goManage(){
	var FORM_BODY = $('form[name=formBody]');
	FORM_BODY.submit();
}

function goMoney(){
	location.href='${pageContext.request.contextPath}/api/money';
}

</script>
</html>