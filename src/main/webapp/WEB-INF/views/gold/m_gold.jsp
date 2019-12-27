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
	<title>WEYS 시제 관리</title>
	
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
				* 시제 관리 
			</div>
			<div class="refresh">
				<input type="button" class="btn btn-success" onclick="sumitGold()" value="등록">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/gold/m/w" method="post">
				<input type="hidden" name="storeId" id="storeId" value="${storeId }">
				<table class="search">
					<colgroup>
						<col style="width:30%"/>
						<col style="width:70%"/>
					</colgroup>
					<tr>
						<td>예약일</td>
						<td>
							<c:if test="${adminTp == 'S'}">
								<input type="text" style="width:240px;display:initial;" name="rsvDt" id="rsvDt" class="form-control" value="${dt}" />
							</c:if>
							<c:if test="${adminTp == 'M'}">
								<select id="rsvDt" name="rsvDt" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;">
									<c:forEach var="items" items="${dtList }">
										<option value="${items }" >${items }</option>
									</c:forEach>
								</select>
							</c:if>
						</td>
					</tr> 
					<tr>
						<td>예약현황<br><span id="rsvTot"></span></td>
						<td id="rsvDetail"></td>
					</tr> 
				</table>
			</form>
			
			<table class="list_table" style="margin-top:10px;">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:8%"/>
					<col style="width:5%"/>
					<col style="width:30%"/>
					<col style="width:11%"/>
					<col style="width:26%"/>
				</colgroup>
				<tbody id="goldInfoHtml"></tbody>
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

	if('${adminTp == "S"}'){
		$('input[id="rsvDt"]').daterangepicker({
	        singleDatePicker: true,
	        showDropdowns: true
	    },function(start, end, label) {});

	}
	
	$("#rsvDt").change(function(){
		
		getRsvInfo();
	});
	getRsvInfo();
});

function sumitGold(){
	var FORM_BODY = $('form[name=formBody]');
	FORM_BODY.submit();
}

function getRsvInfo(){
	var data = {};
	data["rsvDt"] = $("#rsvDt").val();
	data["storeId"] = $("#storeId").val();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/gold/getRsvInfo",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){

			var list = result.resultList;
			var goldList = result.goldList;
			var changeList = result.changeList;
			var cancelList = result.cancelList;
			
			var infoHtml = '';
			var tot = 0;
			for(i=0 ; i<list.length ; i++){
				
				var gAmnt = list[i].G_AMNT;
				var rAmnt = list[i].READY;
				tot = tot + list[i].CNT;
				
				infoHtml += '<span style="width:15%;    display: inline-block;">' + list[i].UNIT + ' 예약 ' + list[i].CNT + '건</span>' 
				infoHtml += '<span style="width:20%;    display: inline-block;">총 금액 : ' + list[i].AMNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span>'
				if(gAmnt != rAmnt){
					infoHtml += '<span style="color:red;width:30%;    display: inline-block;">완료 금액 :  ' + list[i].G_AMNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' / ' + list[i].READY.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span>'
				} else {
					infoHtml += '<span style="width:30%;    display: inline-block;">완료 금액 :  ' + list[i].G_AMNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' / ' + list[i].READY.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span>'
				}
				infoHtml += '<span style="width:25%;    display: inline-block;">남은 금액 : ' + list[i].N_READY.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span><br>';
			}
			$('#rsvDetail').html(infoHtml);
			$('#rsvTot').html('총 : ' + tot.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
			
			$('#goldInfoHtml').empty();
			var goldHtml = '<tr><td>시간</td><td>담당자</td><td colspan="3">내용</td><td>메모</td></tr>';

			for(i=0 ; i<changeList.length ; i++){
				goldHtml += '<tr>';
				goldHtml += '<td>' + changeList[i].RSV_O_DT + ' -> ' + changeList[i].RSV_DT + '</td>';
				goldHtml += '<td>(' + changeList[i].RSV_NM + ')</td>';
				goldHtml += '<td>' + changeList[i].UNIT + '</td>';
				goldHtml += '<td colspan="2" style="text-align:left;">' + changeList[i].RSV_AMNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
				goldHtml += '<td style="text-align:left;">' + changeList[i].RSV_ST + '</td>';
				goldHtml += '</tr>';
			}

			for(i=0 ; i<cancelList.length ; i++){
				goldHtml += '<tr>';
				goldHtml += '<td>취소 시간 : ' + cancelList[i].CANCEL_DTTM + '</td>';
				goldHtml += '<td>(' + cancelList[i].RSV_NM + ')</td>';
				goldHtml += '<td>' + cancelList[i].UNIT + '</td>';
				goldHtml += '<td colspan="2" style="text-align:left;">' + cancelList[i].RSV_AMNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
				goldHtml += '<td style="text-align:left;">' + cancelList[i].RSV_ST + '</td>';
				goldHtml += '</tr>';
			}
			
			for(i=0 ; i<goldList.length ; i++){
				goldHtml += '<tr>';
				goldHtml += '<td rowspan="' + goldList[i].row +'">' + goldList[i].REG_DTTM + '</td>';
				goldHtml += '<td rowspan="' + goldList[i].row +'">' + goldList[i].ADMIN_NAME + '</td>';
				
				var smryList = goldList[i].smryList;

				for(j=0 ; j<1 ; j++){
					goldHtml += '<td>' + smryList[j].unit + '</td>';
					goldHtml += '<td style="text-align:left;">' + smryList[j].smry + '</td>';
					goldHtml += '<td style="text-align:right;">' + smryList[j].sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
				}
				
				goldHtml += '<td rowspan="' + goldList[i].row +'" style="text-align:left;">' + goldList[i].MEMO + '</td>';
				goldHtml += '</tr>';
				
				if(smryList.length > 1){
					for(j=1 ; j<smryList.length ; j++){
						goldHtml += '<tr>';
						goldHtml += '<td>' + smryList[j].unit + '</td>';
						goldHtml += '<td style="text-align:left;">' + smryList[j].smry + '</td>';
						goldHtml += '<td style="text-align:right;">' + smryList[j].sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
						goldHtml += '</tr>';
					}
				}
			}
			$('#goldInfoHtml').append(goldHtml);
		},
		error : function(result){
			

		}
	});
}
</script>
</html>