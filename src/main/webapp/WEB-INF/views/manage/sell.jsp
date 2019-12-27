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
				* 고객이 외화 판매
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/manage/sell" method="post">
			<input type="hidden" name="storeId" id="storeId" value="${storeId }">
			<input type="hidden" name="basicRate" id="basicRate" value="${basicRate }">
			<input type="hidden" name="basicRateUser" id="basicRateUser" value="${basicRateUser }">
			<input type="hidden" name="getAmnt" id="getAmnt">
			<input type="hidden" name="payAmnt" id="payAmnt">
			<input type="hidden" name="totalAmnt" id="totalAmnt" value="${totalAmnt }">
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
					<td>이름</td>
					<td style="text-align:left;"><input type="text" id="usrNm" style="width:100%;"></td>
				</tr>
				<tr>
					<td>주민번호 <input type="checkbox" id="idChk" checked="checked"></td>
					<td style="text-align:left;"><input type="text" id="usrNmId" style="width:100%;"></td>
				</tr>
				<tr>
					<td rowspan="4">통화</td>
					<td style="text-align:left;">
						<select name="storeUnit" id="storeUnit">
							<c:forEach var="items" items="${unitList }">
								<option value="${items.unitCd }">${items.unitNm }
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;" id="inputRateWeys">${basicRateText }</td>
				</tr>
				<tr>
					<td style="text-align:left;font-weight:700;" id="inputRate">${basicRateText }</td>
				</tr>
				<tr>
					<td style="text-align:left;" id="inputRateBank">${basicRateText }</td>
				</tr>
				<tr>
					<td rowspan="2">통화 계산</td>
					<td style="text-align:left;">외화 : <input type="text" style="width:150px;" id="unitVal"> <-> 한화 : <input type="text" style="width:150px;" id="korVal"></td>
				</tr>
				<tr>
					<td id="useTxt" style="text-align:left;"></td>
				</tr>
				<tr>
					<td>줘야할 금액(원)</td>
					<td style="text-align:left;"><input type="text" style="width:150px;" id="calcMoney"><span id="calcResult"></span></td>
				</tr>
				<tr>
					<td>지점 보유 한화</td>
					<td style="text-align:left;"><fmt:formatNumber value="${totalAmnt }" groupingUsed="true"/></td>
				</tr>
				<tr>
					<td>신청서 번호</td>
					<td style="text-align:left;"><input type="number" name="tradePaper" id="tradePaper"></td>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<input type="button" class="btn btn-success" onclick="writeAction('write')" value="등록">
					</td>
				</tr>
			</table>
			</form>
		</div>
		
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>

<div class="dialog_back"></div>
<div class="modal" style="display:none;text-align:center;margin:auto;box-sizing:border-box;width:300px;height:150px;overflow: auto;background-color:white;border:3px solid;">
	<p style="margin-top:10px;">바코드 조회</p>
	<input type="text" id="input_barcode" style="margin:10px;" onkeypress="if( event.keyCode==13 ){writeBarcode();}" placeholder="바코드 번호 입력" value=""><br>
	<input type="button" class="btn btn-success" onclick="closdModal()" value="취소">
	<input type="button" class="btn btn-success" onclick="writeBarcode()" value="조회">
</div>

</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">
var isApp = false;
var isCertify = false;
$(function () {
	$('#storeUnit').change(function(){
		changeStatus($(this).val());
		$('#unitVal').val('');
		$('#korVal').val('');
	});
	
	$('#unitVal').keyup(function(){
		unitValCalculate();
		$('#calcMoney').val(0);
	});
	
	$('#korVal').keyup(function(){
		korValCalculate();
		$('#calcMoney').val(0);
	});
	
	$('#calcMoney').keyup(function(){
		calcReturn();
	});
	
	openModal();
});

function calcReturn(){
	var get = $('#calcMoney').val().replace(/,/g, '');
	var money = $('#korVal').val().replace(/,/g, '');
	
	var ret = get - money;
	$('#calcResult').html(' 받아야 할 금액 : ' + ret.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$('#calcMoney').val(get.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
}

function closdModal(){
	$('.modal').css('display', 'none');
	$('.dialog_back').css('display', 'none');
	changeStatus('USD');
}

function openModal(){
	$('.modal').css('display', 'block');
	$('.dialog_back').css('display', 'block');
	$('#input_barcode').focus();
}

function writeBarcode(){
	var barcode = $('#input_barcode').val();
	if(barcode == ''){
		alert('바코드를 입력해 주세요.');
		$('#input_barcode').focus();
		return;
	}
	
	var data = {};
	data["barcode"] = barcode;
	
	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/manage/chkBarcode",
		data:JSON.stringify(data),
		success : function(result){
			var res = result.flag;
			if(res == 'suc'){
				var nm = result.USR_NM;
				var nmId = result.USR_NM_ID;

				if(nm != null && nm != ''){
					$('#usrNm').val(nm);
					$('#usrNmId').val(nmId);
					$('#usrNm').attr("readonly", true);
					$('#usrNmId').attr("readonly", true);
					isCertify = true;
				}
				
				isApp = true;
			} else {
				alert('존재하지 않는 회원입니다.');
			}
			
			closdModal();
			changeStatus('USD');
		},
		error : function(result){
			alert('업데이트 실패, 에러');
		}
	});
}

function unitValCalculate(){
	var val = $('#unitVal').val();
	var basicRate = $('#basicRate').val();
	var unit = $('#storeUnit').val();

	val = val.replace(/,/g, '');

	var cost = val * basicRate;
	if(unit == 'JPY'){
		cost = cost / 100;
	}
	cost = Math.ceil(cost / 10.0);
	
	cost = cost * 10;
	
	var txt = val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " (" + basicRate.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ")";
	$('#needToPay').html(cost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$('#unitVal').val(val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$('#korVal').val(cost.toFixed(0).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));

	$('#getAmnt').val(val);
	$('#payAmnt').val(cost);
	$('#useTxt').html(txt);
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
	cost = Math.ceil(cost);

	var txt = cost.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + " (" + basicRate.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + ")";
	$('#korVal').val(val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$('#unitVal').val(cost.toFixed(0).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$('#needToPay').html(val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));

	$('#getAmnt').val(cost);
	$('#payAmnt').val(val);
	$('#useTxt').html(txt);
}

function changeStatus(unitCd){
	var storeId = $('#storeId').val();
	
	var data = {};
	data["unitCd"] = unitCd;
	data["storeId"] = storeId;
	data["isApp"] = isApp;
	
	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/manage/sellUnit",
		data:JSON.stringify(data),
		success : function(result){
			var res = result.result;
			if(res == 'success'){
				var basicRateText = result.basicRateText;
				var basicRate = result.basicRateWeys;
				var basicRateWeys = result.basicRate;
				var basicRateBank = result.bankSell;
				
				$('#inputRateWeys').html('매매기준율 : ' + basicRateWeys.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
				$('#inputRate').html('웨이즈기준율 : ' + basicRate.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
				$('#inputRateBank').html('은행기준율 : ' + basicRateBank.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
				$('#basicRate').val(basicRate);

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

function writeAction(action){

	if($('#usrNm').val() == ''){
		alert('이름을 입력해주세요.');
		$('#usrNm').focus();
		return;
	}
	if($('#usrNmId').val() == ''){
		alert('이름을 입력해주세요.');
		$('#usrNmId').focus();
		return;
	}
	if($('#tradePaper').val() == ''){
		alert('신청서 번호를 입력해주세요.');
		$('#tradePaper').focus();
		return;
	}
	
	var tot = parseInt($('#totalAmnt').val());
	var pay = parseInt($('#payAmnt').val());
	
	if(tot < pay){
		alert('돈이 부족합니다.');
		return;
	}
	

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
			return;
		}
	});
	
	data["usrNm"] = $('#usrNm').val();
	data["usrNmId"] = $('#usrNmId').val();
	data["unit"] = $('#storeUnit').val();
	data["basicRate"] = $('#basicRate').val();
	data["getAmnt"] = $('#getAmnt').val();
	data["payAmnt"] = $('#payAmnt').val();
	data["storeId"] = $('#storeId').val();
	
	if(isApp == true){
		data["barcode"] = $('#input_barcode').val();
		data["idChk"] = $("#idChk").is(":checked");
		if(isCertify){
			data["certify"] = 'T';
		}
	}
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/manage/sell",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			var res = result.result;
			if(res == 'success'){
				alert('등록되었습니다.');
				location.href = "${pageContext.request.contextPath}/api/manage/active";
			} else if(res == 'uncertify'){
				alert('인증되지 않은 정보입니다.');
			} else {
				alert('등록되지 않았습니다.');
			}
		},
		error : function(result){
			flag = false;
			alert('업데이트 실패, 에러');
			return;
		}
	});
	
}
</script>
</html>