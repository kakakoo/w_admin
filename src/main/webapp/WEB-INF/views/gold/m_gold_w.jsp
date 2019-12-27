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
	<title>WEYS 시제 등록</title>
	
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
				* 시제 등록 
			</div>
			<div class="refresh">
				<input type="button" class="btn btn-success" onclick="sumitGold()" value="등록">
			</div>
			
			<table class="search">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>예약일</td>
					<td>${dt }</td>
				</tr> 
				<tr>
					<td>예약현황<br><span id="rsvTot"></span></td>
					<td id="rsvDetail"></td>
				</tr> 
				<tr>
					<td>출고 / 입고</td>
					<td>
						<input type="radio" style="margin: 4px 4px 0px;" name="goldTp" value="O" checked> 출고
						<input type="radio" style="margin: 4px 4px 0px;" name="goldTp" value="I" > 입고 / 환불
						<c:if test="${adminTp == 'S'}">
							<input type="radio" style="margin: 4px 4px 0px;" name="goldTp" value="B" > 은행 매입
							<input type="radio" style="margin: 4px 4px 0px;" name="goldTp" value="P" > 기타 매입
							<input type="radio" style="margin: 4px 4px 0px;" name="goldTp" value="S" > 오프라인판매
							<input type="radio" style="margin: 4px 4px 0px;" name="goldTp" value="M" > 이동 -> ${target }
<!-- 							<input type="radio" style="margin: 4px 4px 0px;" name="goldTp" value="S" > 매각 -->
						</c:if>
					</td>
				</tr> 
			</table>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/gold/m" method="post">
				<input type="hidden" id="storeId" name="storeId" value="${storeId }">
			</form>
			<table class="list_table" style="margin-top:10px;">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:40%"/>
				</colgroup>
				<tr>
					<td>외화</td>
					<td>권종</td>
					<td>보유현황</td>
					<td>사용한 외화</td>
					<td>총 금액</td>
				</tr>
				<c:set value="" var="unit"/>
				<c:forEach var="items" items="${goldList }">
					<tr>
						<c:if test="${unit ne items.unit }">
							<td rowspan="${items.rowSpan }"><nobr><font title="${items.unit }">${items.unit }</font></nobr></td>
						</c:if>
						<td style="text-align:right;">${items.unitDp } <fmt:formatNumber value="${items.paper }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.amnt }" groupingUsed="true"/></td>
						<td><input type="number" class="rsvListData" data-unit="${items.unit }" data-paper="${items.paper }" value="0"></td>
						<c:if test="${unit ne items.unit }">
							<td rowspan="${items.rowSpan }"><p id="${items.unit }_tot">0</p>
								<input type="text" style="display:none;" class="rate_box" id="${items.unit }_rate" placeholder="구매/판매 기준율">
							</td>
							<c:set value="${items.unit }" var="unit"/>
						</c:if>
					</tr>
				</c:forEach>
				<tr>
					<td>메모</td>
					<td colspan="4">
						<textarea style="width:100%;" rows="3" id="memoTxt"></textarea>
					</td>
				</tr> 
			</table>
		</div>
		
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

var rsvDt = '${dt }';

$(function () {
	getRsvInfo();
	
	$(".rsvListData").change(function(){
		
		var unit = $(this).attr("data-unit");
		var amnt = 0;

		$(".rsvListData").each(function(index){
			
			var listUnit = $(this).attr("data-unit");
			
			if(unit == listUnit){
				var cnt = $(this).val();
				var paper = $(this).attr("data-paper");
				
				amnt = amnt + (cnt * paper);
			}
		});
		
		$('#' + unit + '_tot').html(amnt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	});
	

	$("input[name=goldTp]").change(function(){
		var val = $(this).val();
		
		if( val == 'B' || val == 'P' || val == 'S'){
			$('.rate_box').css('display','table-row');
		} else {
			$('.rate_box').css('display','none');
		}
	});
	
});

function sumitGold(){

	var goldAry = new Array();
	var idx = 0;
	var storeId = $('#storeId').val();
	var goldTp = $('input[name="goldTp"]:checked').val();
	
	$(".rsvListData").each(function(index){
		
		var cnt = $(this).val();
		
		if(cnt > 0){
			var unit = $(this).attr("data-unit");
			var paper = $(this).attr("data-paper");

			if(goldTp == 'O' || goldTp == 'S' || goldTp == 'M'){
				cnt = cnt * -1;
			}

			var data = {};
			data["unit"] = unit;
			data["paper"] = paper;
			data["cnt"] = cnt;
			data["storeId"] = storeId;

			if(goldTp == 'B' || goldTp == 'P' || goldTp == 'S'){
				data["rate"] = $('#' + unit + '_rate').val();
			}
			
			goldAry[idx] = data;
			idx = idx + 1;
		}
	});
	
	if(idx > 0){
		var data = {};
		
		data["storeId"] = storeId;
		data["rsvDt"] = rsvDt;
		data["goldTp"] = goldTp;
		data["goldAry"] = goldAry;
		data["memo"] = $('#memoTxt').val();
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/api/gold/m/write",
			data: JSON.stringify(data),
			async : false,
			contentType: "application/json",
			success : function(result){
				
				var res = result.result;
				if(res == 'success'){
					alert('인수를 진행해 주세요.');
					var FORM_BODY = $('form[name=formBody]');
					FORM_BODY.submit();
				} else {
					alert('입력내용을 확인해 주세요.');
				}
			},
			error : function(result){
				alert('입력내용을 확인해 주세요.');
			}
		});
	} else {
		alert('사용한 외화 수량을 입력해 주세요.');
	}
}

function getRsvInfo(){
	var data = {};
	data["rsvDt"] = rsvDt;
	data["storeId"] = $('#storeId').val();
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/gold/getRsvInfo",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			
			var list = result.resultList;
			
			var infoHtml = '';
			var tot = 0;
			for(i=0 ; i<list.length ; i++){
				tot = tot + list[i].CNT;
				infoHtml += '<span style="width:15%;    display: inline-block;">' + list[i].UNIT + ' 예약 ' + list[i].CNT + '건</span>' 
				infoHtml += '<span style="width:20%;    display: inline-block;">총 금액 : ' + list[i].AMNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span>'
				infoHtml += '<span style="width:30%;    display: inline-block;">완료 금액 :  ' + list[i].G_AMNT.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ' / ' + list[i].READY.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span>'
				infoHtml += '<span style="width:25%;    display: inline-block;">남은 금액 : ' + list[i].N_READY.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span><br>';
			}
			$('#rsvDetail').html(infoHtml);
			$('#rsvTot').html('총 : ' + tot.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
		},
		error : function(result){
			

		}
	});
}
</script>
</html>