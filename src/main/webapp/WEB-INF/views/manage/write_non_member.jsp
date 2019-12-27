<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				* 거래 
				<c:choose>
			       <c:when test="${result == '2' }">
			           수정
			       </c:when>
			       <c:otherwise>
			           등록 
			       </c:otherwise>
			   </c:choose>
			</div>
			<div class="refresh">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:13%"/>
					<col style="width:13%"/>
					<col style="width:14%"/>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:20%"/>
				</colgroup>
				<tr>
					<td>닉네임</td>
					<td>메일</td>
					<td>이름</td>
					<td>멤버십가입일</td>
					<td>잔여한도</td>
					<td>만료기간</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><input type="text" id="input_usrNm" style="width:100%;"></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/manage/0000/write" method="post">
			<input type="hidden" name="storeId" id="storeId" value="${storeId }">
			<input type="hidden" name="writeType" id="writeType" value="none">
			<input type="hidden" name="basicRate" id="basicRate" value="${basicRate }">
			<input type="hidden" name="getAmnt" id="getAmnt">
			<input type="hidden" id="barcode" value="0000">
			<input type="hidden" name="payAmnt" id="payAmnt">
			<input type="hidden" name="maxValue" id="maxValue" value="${maxValue}">
			<input type="hidden" name="usrNm" id="usrNm">
			<input type="hidden" id="usrCost" value="0">
			<table class="list_table" style="margin-top:10px;">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:80%"/>
				</colgroup>
				<tr>
					<td>구분</td>
					<td>내용</td>
				</tr>
				<tr>
					<td>거래유형</td>
					<td style="text-align:left;">일반 환전</td>
				</tr>
				<tr>
					<td rowspan="2">통화</td>
					<td style="text-align:left;">
						<select name="storeUnit" id="storeUnit">
							<c:forEach var="items" items="${unitList }">
								<option value="${items.unitCd }">${items.unitNm }
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;" id="inputRate">${basicRateText }</td>
				</tr>
				<tr>
					<td>통화 계산</td>
					<td style="text-align:left;">외화 : <input type="text" style="width:150px;" id="unitVal"> <-> 한화 : <input type="text" style="width:150px;" id="korVal"></td>
				</tr>
				<tr>
					<td>거스름</td>
					<td style="text-align:left;" id="money_exchange">0</td>
				</tr>
				<tr>
					<td>거래구분</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="tradeType" value="B" <c:if test="${paging.tradeType == 'B'}">checked</c:if><c:if test="${empty paging.tradeType}">checked</c:if>> 구매
						<input type="radio" style="margin: 4px 4px 0px;" name="tradeType" value="S" <c:if test="${paging.tradeType == 'S'}">checked</c:if>> 판매
					</td>
				</tr>
				<tr>
					<td>지점 가능 환전 금액</td>
					<td style="text-align:left;" id="inputMaxAmnt">${totalAmnt }</td>
				</tr>
				<tr>
					<td>수령금액<p id="getTotalAmnt"></p></td>
					<td style="text-align:left;overflow-x: auto;">
						<table id="getListTable">
							<tr>
							<c:forEach var="items" items="${getList }">
								<td>${items.COIN_NM }</td>
							</c:forEach>
							</tr>
							<tr>
							<c:forEach var="items" items="${getList }" varStatus="status">
								<td><input type="number" class="getAmnts" name="GET_AMNT${status.count }" style="width:150px;" data-basic="${items.COIN_BASIC }" id="${items.COIN_ID }" value="0"></td>
							</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>지급할 금액</td>
					<td style="text-align:left;" id="needToPay"></td>
				</tr>
				<tr>
					<td>지급금액<p id="payTotalAmnt"></p></td>
					<td style="text-align:left;overflow-x: auto;">
						<table id="payListTable">
							<tr>
							<c:forEach var="items" items="${payList }">
								<td>${items.COIN_NM }</td>
							</c:forEach>
							</tr>
							<tr>
							<c:forEach var="items" items="${payList }" varStatus="status">
								<td><input type="number" class="payAmnts" name="PAY_AMNT${status.count }" style="width:150px;" data-basic="${items.COIN_BASIC }" id="${items.COIN_ID }" value="0"></td>
							</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>신청서 번호</td>
					<td style="text-align:left;"><input type="number" name="tradePaper" id="tradePaper"></td>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<c:choose>
					       <c:when test="${result == '2' }">
					           <input type="button" class="btn btn-success" onclick="writeAction('update')" value="수정">
					           <input type="button" class="btn btn-danger" onclick="writeAction('delete')" value="삭제">
					       </c:when>
					       <c:otherwise>
					           <input type="button" class="btn btn-success" onclick="writeAction('write', '0000')" value="등록">
					       </c:otherwise>
					   </c:choose>
					</td>
				</tr>
			</table>
			</form>
		</div>
		
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">
$(function () {
	$('#storeUnit').change(function(){
		var tradeType = $(":input:radio[name=tradeType]:checked").val();
		changeStatus($(this).val(), tradeType);
		$('#unitVal').val('');
		$('#korVal').val('');
	});
	
	$(":input:radio[name=tradeType]").change(function(){
		var unitCd = $('#storeUnit').val();
		changeStatus(unitCd, $(this).val());
	});
	
	$('.getAmnts').blur(function(){
		calGetTotalAmnt(this);
	});
	$('.payAmnts').blur(function(){
		calPayTotalAmnt(this);
	});

	$('#unitVal').keyup(function(){
		unitValCalculate();
	});
	
	$('#korVal').keyup(function(){
		korValCalculate();
	});
});

function unitValCalculate(){
	var val = $('#unitVal').val();
	var basicRate = $('#basicRate').val();
	var unit = $('#storeUnit').val();
	var tradeType = $(":input:radio[name=tradeType]:checked").val();

	val = val.replace(/,/g, '');
	
	if(tradeType == 'B'){
		var max = $('#maxValue').val();

		if(parseInt(val) > max){
			alert('2,000 달러 금액을 초과하는 외화는 판매할 수 없습니다.');
			$('#unitVal').val(0);
			$('#korVal').val(0);
			return;
		}
	}
	
	var cost = val * basicRate;
	if(unit == 'JPY'){
		cost = cost / 100;
	}
	
	if(tradeType == 'B'){
		cost = Math.floor(cost / 10.0);
	} else {
		cost = Math.ceil(cost / 10.0);
	}
	cost = cost * 10;

	if(tradeType == 'B'){
		$('#needToPay').html(val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	} else {
		$('#needToPay').html(cost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
	$('#unitVal').val(val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$('#korVal').val(cost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}

function korValCalculate(){
	var val = $('#korVal').val();
	var basicRate = $('#basicRate').val();
	var unit = $('#storeUnit').val();
	var tradeType = $(":input:radio[name=tradeType]:checked").val();

	val = val.replace(/,/g, '');
	
	var cost = val / basicRate;
	if(unit == 'JPY'){
		cost = cost * 100;
	}
	if(tradeType == 'B'){
		cost = Math.floor(cost);
	} else {
		cost = Math.ceil(cost);
	}

	if(tradeType == 'B'){
		$('#needToPay').html(cost.toFixed(0).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	} else {
		$('#needToPay').html(val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
	$('#korVal').val(val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$('#unitVal').val(cost.toFixed(0).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}

function changeStatus(unitCd, tradeType){
	var storeId = $('#storeId').val();
	
	var data = {};
	data["unitCd"] = unitCd;
	data["storeId"] = storeId;
	data["tradeType"] = tradeType;
	
	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/manage/changeStatus_nomem",
		data:JSON.stringify(data),
		success : function(result){
			var res = result.result;
			if(res == 'success'){
				var basicRateText = result.basicRateText;
				var basicRate = result.basicRate;
				var totalAmnt = result.totalAmnt;
				var payList = result.payList;
				var getList = result.getList;
				var maxValue = result.maxValue;
				
				$('#inputRate').html(basicRateText);
				$('#inputMaxAmnt').html(totalAmnt);
				$('#basicRate').val(basicRate);
				$('#maxValue').val(maxValue);

				// 지불 금액 
				var html = '<tr>';
				var payNm = '';
				var payInput = '';
				for(i=0 ; i<payList.length ; i++){
					payNm += '<td>' + payList[i].COIN_NM + '</td>';
					payInput += '<td><input type="number" class="payAmnts" name="PAY_AMNT' + (Number(i)+Number(1)) +'" style="width:150px;" data-basic="' + payList[i].COIN_BASIC + '" id="' + payList[i].COIN_ID + '" value="0"></td>';
				}
				html += payNm + '</tr><tr>' + payInput + '</tr>';
				$('#payListTable').html(html);
				
				// 수령 금액 
				html = '<tr>';
				var getNm = '';
				var getInput = '';
				for(i=0 ; i<getList.length ; i++){
					getNm += '<td>' + getList[i].COIN_NM + '</td>';
					getInput += '<td><input type="number" class="getAmnts" name="GET_AMNT' + (Number(i)+Number(1)) +'" style="width:150px;" data-basic="' + getList[i].COIN_BASIC + '" id="' + getList[i].COIN_ID + '" value="0"></td>';
				}
				html += getNm + '</tr><tr>' + getInput + '</tr>';
				$('#getListTable').html(html);

				$('#needToPay').html('0');
				$('#getTotalAmnt').html('0');
				$('#payTotalAmnt').html('0');
				$('.getAmnts').blur(function(){
					calGetTotalAmnt(this);
				});
				$('.payAmnts').blur(function(){
					calPayTotalAmnt(this);
				});

				$('#korVal').val('');
				$('#unitVal').val('');

			} else {
				alert('업데이트 실패');
			}
		},
		error : function(result){
			alert('업데이트 실패, 에러');
		}
	});
}

function removeImg(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile').css('display', 'block');
	$('#bannerUrl').val('');
}

function calGetTotalAmnt(tmp){
	var total = 0;
	var tradeType = $(":input:radio[name=tradeType]:checked").val();
	$(tmp).parents().find('.getAmnts').each(function(i, e){
		var basic = $(this).attr("data-basic");
		var value = $(this).val();
		
		total = total + (basic * value);
	});
	$('#getAmnt').val(total);
	$('#getTotalAmnt').html(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	// money_exchange
	if(tradeType == 'B'){
		var val = $('#korVal').val();
		val = val.replace(/,/g, '');
		var diff = val - total;
		$('#money_exchange').html(diff.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	} else {
		var val = $('#unitVal').val();
		val = val.replace(/,/g, '');
		var diff = val - total;
		$('#money_exchange').html(diff.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	}
// 	needToPay(total);
}
function needToPay(total){
	
	var unit = $('#storeUnit').val();
	var tradeType = $(":input:radio[name=tradeType]:checked").val();
	var basicRate = $('#basicRate').val();
	
	var val = 0;
	if(tradeType == 'B'){
		val = total / basicRate;
		if(unit == 'JPY'){
			val = val * 100;
		}
		val = Math.floor(val);
	} else {
		val = total * basicRate;
		if(unit == 'JPY'){
			val = val / 100;
		}
		val = Math.ceil(val);
	}
	
	$('#needToPay').html(val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}
function calPayTotalAmnt(tmp){
	var total = 0;
	$(tmp).parents().find('.payAmnts').each(function(i, e){
		var basic = $(this).attr("data-basic");
		var value = $(this).val();
		
		total = total + (basic * value);
	});
	$('#payAmnt').val(total);
	$('#payTotalAmnt').html(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}

function writeAction(action, barcode){
	
	if($('#input_usrNm').val() == ''){
		alert('이름을 입력해주세요.');
		$('#input_usrNm').focus();
		return;
	}
	if($('#tradePaper').val() == ''){
		alert('신청서 번호를 입력해주세요.');
		$('#tradePaper').focus();
		return;
	}

	if($('#money_exchange').html() != '0'){
		alert('거스름돈을 확인해 주세요.');
		return;
	}
	if($('#needToPay').html() != $('#payTotalAmnt').html()){
		alert('지급금액을 확인해주세요.');
		return;
	}

	
	var flag = false;
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			flag = true;
		}
	} else {
		flag = true;
	}
	
	if(action == 'write'){

		var data = {};
		data["paper"] = $('#tradePaper').val();
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/api/manage/checkPaper",
			data: JSON.stringify(data),
			async : false,
			contentType: "application/json",
			success : function(result){
				var val = result.result;
				if(val > 0){
					alert('중복된 신청서 번호입니다.');
					flag = false;
					return;
				}
			},
			error : function(result){
				flag = false;
				alert('업데이트 실패, 에러');
			}
		});

		var cost = 0;
		var barcode = $('#barcode').val();
		var tradeType = $(":input:radio[name=tradeType]:checked").val();
		if(tradeType == 'B'){
			cost = $('#getAmnt').val();
		} else {
			cost = $('#payAmnt').val();
		}
		
		if(cost == 0){
			alert('거래할 금액을 입력해주세요.');
			return;
		}

	}
	
	$('#usrNm').val($('#input_usrNm').val());
	
	if(flag){
		var FORM_BODY = $('form[name=formBody]');

		var url = '${pageContext.request.contextPath}/api/manage/' + barcode + '/';
		FORM_BODY.attr('action', url + action);
		FORM_BODY.submit();
	}

}
</script>
</html>