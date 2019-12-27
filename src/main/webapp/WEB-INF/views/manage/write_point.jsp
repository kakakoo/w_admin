<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
					<td>${info.usrNick }</td>
					<td>${info.usrEmail }</td>
					<td><input type="text" id="input_usrNm" style="width:100%;" value="${info.usrNm }"></td>
					<td>${info.startDt }</td>
					<td>${info.limitAmnt }</td>
					<td>${info.endDt }</td>
				</tr>
			</table>
			
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/manage/${barcode }/write" method="post">
			<input type="hidden" name="storeId" id="storeId" value="${storeId }">
			<input type="hidden" name="writeType" id="writeType" value="${info.type }">
			<input type="hidden" name="basicRate" id="basicRate" value="${basicRate }">
			<input type="hidden" name="getAmnt" id="getAmnt">
			<input type="hidden" name="point" id="point">
			<input type="hidden" name="usrNm" id="usrNm">
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
					<td style="text-align:left;">포인트</td>
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
					<td>거래구분</td>
					<td style="text-align:left;">
						<input type="hidden" name="tradeType" value="C">
						포인트 전환 
					</td>
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
					<td>지급 포인트</td>
					<td style="text-align:left;" id="needToPay"></td>
				</tr>
				<tr>
					<td>신청서 번호</td>
					<td style="text-align:left;"><input type="number" id="tradePaper" name="tradePaper"></td>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<c:choose>
					       <c:when test="${result == '2' }">
					           <input type="button" class="btn btn-success" onclick="writeAction('update')" value="수정">
					           <input type="button" class="btn btn-danger" onclick="writeAction('delete')" value="삭제">
					       </c:when>
					       <c:otherwise>
					           <input type="button" class="btn btn-success" onclick="writeAction('write', '${barcode}')" value="등록">
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
		changeStatus($(this).val(), '');
	});
	
	$('.getAmnts').blur(function(){
		calGetTotalAmnt(this);
	});
});

function changeStatus(unitCd, tradeType){
	var writeType = $('#writeType').val();
	var storeId = $('#storeId').val();
	
	var data = {};
	data["writeType"] = writeType;
	data["unitCd"] = unitCd;
	data["storeId"] = storeId;
	data["tradeType"] = tradeType;
	
	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/manage/changeStatus",
		data:JSON.stringify(data),
		success : function(result){
			var res = result.result;
			if(res == 'success'){
				var basicRateText = result.basicRateText;
				var basicRate = result.basicRate;
				var totalAmnt = result.totalAmnt;
				var getList = result.getList;
				
				$('#inputRate').html(basicRateText);
				$('#inputMaxAmnt').html(totalAmnt);
				$('#basicRate').val(basicRate);

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

				$('#getTotalAmnt').html('0');
				$('.getAmnts').blur(function(){
					calGetTotalAmnt(this);
				});
				
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
	$(tmp).parents().find('.getAmnts').each(function(i, e){
		var basic = $(this).attr("data-basic");
		var value = $(this).val();
		
		total = total + (basic * value);
	});
	total = total.toFixed(2);
	$('#getAmnt').val(total);
	$('#getTotalAmnt').html(total.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	needToPay(total);
}
function needToPay(total){
	
	var unit = $('#storeUnit').val();
	var tradeType = $(":input:radio[name=tradeType]:checked").val();
	var basicRate = $('#basicRate').val();
	
	var val = total * basicRate * 0.7;
	if(unit == 'JPY'){
		val = val / 100;
	}
	
	$('#needToPay').html(parseInt(val).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	$('#point').val(parseInt(val));
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

	var flag = false;
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			flag = true;
		}
	} else {
		flag = true;
	}
	
	var cost = $('#getAmnt').val();
	if(cost == 0){
		alert('거래할 금액을 입력해주세요.');
		return;
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
				alert('업데이트 실패, 에러');
				flag = false;
				return;
			}
		});
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