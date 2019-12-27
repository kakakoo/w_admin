<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 쿠폰</title>
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	

</head>
<body class="nav-md">
    <div class="container body">
      <div class="main_container">
	
		<jsp:include page="../common/left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		<input type="hidden" id="alarm" value="${result }">

        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 쿠폰 관리 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/coupon/write" method="post">
			<input type="hidden" name="couponId" id="couponId" value="${info.couponId }">
			<input type="hidden" name="couponImg" id="couponImg" value="${info.couponImg }">
			<input type="hidden" name="couponBnr" id="couponBnr" value="${info.couponBnr }">
			<input type="hidden" id="couponCnt" name="couponCnt" value="${info.couponCnt }">
			<table class="list_table">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:80%"/>
				</colgroup>
				<tr>
					<td>구분</td>
					<td>내용</td>
				</tr>
				<tr>
					<td>쿠폰명</td>
					<td><input type="text" name="couponNm" id="couponNm" style="width:100%;" value="${info.couponNm }"></td>
				</tr>
				<tr>
					<td>쿠폰 용도</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="couponTp" value="B" <c:if test="${info.couponTp == 'B'}">checked</c:if><c:if test="${empty info.couponTp}">checked</c:if>> 음료
						<input type="radio" style="margin: 4px 4px 0px;" name="couponTp" value="DR" <c:if test="${info.couponTp == 'DR'}">checked</c:if>> 예약 배송비 우대
						<input type="radio" style="margin: 4px 4px 0px;" name="couponTp" value="DD" <c:if test="${info.couponTp == 'DD'}">checked</c:if>> 주소지 배송비 우대
						<input type="radio" style="margin: 4px 4px 0px;" name="couponTp" value="C" <c:if test="${info.couponTp == 'C'}">checked</c:if>> 센터 수수료 우대
						<input type="radio" style="margin: 4px 4px 0px;" name="couponTp" value="M" <c:if test="${info.couponTp == 'M'}">checked</c:if>> 보너스
						<input type="radio" style="margin: 4px 4px 0px;" name="couponTp" value="P" <c:if test="${info.couponTp == 'P'}">checked</c:if>> 우대 쿠폰
					</td>
				</tr>
				<tr id="cost_tr" style="display:none;">
					<td>한도 / 퍼센트</td>
					<td><input type="number" name="memberCost" id="memberCost" style="width:100%;" value="${info.memberCost }"></td>
				</tr>
				<tr id="desc_tr" style="display:none;">
					<td>적용외화(,구분)</td>
					<td><input type="text" name="couponDesc" id="couponDesc" style="width:100%;" value="${info.couponDesc }"></td>
				</tr>
				<tr>
					<td>사용 기간</td>
					<td>
						<input type="radio" style="margin: 4px 4px 0px;" name="periodTp" value="P" <c:if test="${info.periodTp == 'P'}">checked</c:if><c:if test="${empty info.periodTp}">checked</c:if>> 발급기준
						<input type="radio" style="margin: 4px 4px 0px;" name="periodTp" value="D" <c:if test="${info.periodTp == 'D'}">checked</c:if>> 표시기준
						<input type="number" name="memberPeriod" id="memberPeriod" value="${info.memberPeriod }">
					</td>
				</tr>
				<tr>
					<td>쿠폰 적용</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="sendTp" value="R" <c:if test="${info.sendTp == 'R'}">checked</c:if><c:if test="${empty info.sendTp}">checked</c:if>> 즉시적용
						<input type="radio" style="margin: 4px 4px 0px;" name="sendTp" value="I" <c:if test="${info.sendTp == 'I'}">checked</c:if>> 입력사용
					</td>
				</tr>
				<tr id="target_tr">
					<td rowspan="2">발급대상</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="couponTarget" value="T" <c:if test="${info.couponTarget == 'T'}">checked</c:if><c:if test="${empty info.couponTarget}">checked</c:if>> 전체 
						<input type="radio" style="margin: 4px 4px 0px;" name="couponTarget" value="S" <c:if test="${info.couponTarget == 'S'}">checked</c:if>> 지정
						<input type="file" id="excelFile" value="엑셀업로드" style="display: inline;">
						<input type="hidden" id="excelFilePath" name="excelFilePath" value="${info.excelFilePath }">
					</td>
				</tr>
				<tr id="cnt_tr">
					<td id="targetCnt" style="text-align:left;">
						${info.couponCnt }명
					</td>
				</tr>
				<tr id="limit_tr" style="display:none;">
					<td>참여 인원수(무제한 : 0)</td>
					<td><input type="number" name="couponLimit" id="couponLimit" style="width:100%;" value="${info.couponLimit }"></td>
				</tr>
				<tr id="code_tr" style="display:none;">
					<td>쿠폰코드</td>
					<td><input type="text" name="couponCode" id="couponCode" style="width:100%;" value="${info.couponCode }" placeholder="최대 20자리 코드 입력"></td>
				</tr>
<!-- 				<tr> -->
<!-- 					<td>쿠폰설명</td> -->
<%-- 					<td><input type="text" name="couponDesc" id="couponDesc" style="width:100%;" value="${info.couponDesc }"></td> --%>
<!-- 				</tr> -->
<!-- 				<tr> -->
<!-- 					<td>쿠폰사용장소</td> -->
<%-- 					<td><input type="text" name="couponStore" id="couponStore" style="width:100%;" value="${info.couponStore }"></td> --%>
<!-- 				</tr> -->
				<tr>
					<td>푸시메시지</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="pushType" value="N" <c:if test="${info.pushType == 'N'}">checked</c:if><c:if test="${empty info.pushType}">checked</c:if>> 비발송
						<input type="radio" style="margin: 4px 4px 0px;" name="pushType" value="Y" <c:if test="${info.pushType == 'Y'}">checked</c:if>> 발송
						<input type="text" name="pushDesc" id="pushDesc" style="width:500px;" value="${info.pushDesc }">
					</td>
				</tr>
				<tr>
					<td>쿠폰표시기간</td>
					<td style="text-align:left;"><input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${info.startDt } - ${info.endDt }" /></td>
				</tr>
				<tr>
					<td>쿠폰이미지</td>
					<td>
						<div id="imgView" style="text-align:left;">
							<c:if test="${not empty info.couponImg}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.couponImg}"><a style="cursor:pointer" onclick="removeImg(this)" > X </a>
							</c:if>
						</div>	
						<input type="file" id="imgFile" <c:if test="${not empty info.couponImg}">style="display:none"</c:if>>
					</td>
				</tr>
				<tr>
					<td>배너이미지</td>
					<td>
						<div id="imgView2" style="text-align:left;">
							<c:if test="${not empty info.couponBnr}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.couponBnr}"><a style="cursor:pointer" onclick="removeImgBnr(this)" > X </a>
							</c:if>
						</div>	
						<input type="file" id="imgFile2" <c:if test="${not empty info.couponBnr}">style="display:none"</c:if>>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<c:choose>
					       <c:when test="${result == '2' }">
					           <input type="button" class="btn btn-success" onclick="writeAction('update')" value="수정">
					           <input type="button" class="btn btn-danger" onclick="writeAction('delete')" value="삭제">
					       </c:when>
					       <c:otherwise>
					           <input type="button" class="btn btn-success" onclick="writeAction('write')" value="등록">
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

	$("input[name=couponTarget]").change(function(){
		var val = $(this).val();
		
		if(val == 'T' || val == 'M'){
		    var data = {};
			data["type"] = val;
		    
			$.ajax({
				contentType : "application/json",
				dataType : "json",
				type:"post",
				url:"${pageContext.request.contextPath}/api/coupon/getTargetCnt",
				data:JSON.stringify(data),
				success : function(result){
					var resCnt = result.resCnt;
					$('#targetCnt').html(resCnt + '명');
					$('#couponCnt').val(resCnt);
				},
				error : function(result){
				}
			});
		} else {
			$('#targetCnt').html('0명');
		}
	});
	
	$("input[name=couponTp]").change(function(){
		var val = $(this).val();
		
		if( val == 'B'){
			$('#cost_tr').css('display','none');
			$('#desc_tr').css('display','none');
		} else {
			$('#cost_tr').css('display','table-row');
			$('#desc_tr').css('display','table-row');
		}
	});
	
	$("input[name=sendTp]").change(function(){
		var val = $(this).val();
		
		if( val == 'I'){
			$('#limit_tr').css('display','table-row');
			$('#code_tr').css('display','table-row');

			$('#target_tr').css('display','none');
			$('#cnt_tr').css('display','none');
		} else {
			$('#target_tr').css('display','table-row');
			$('#cnt_tr').css('display','table-row');
			
			$('#limit_tr').css('display','none');
			$('#code_tr').css('display','none');
		}
	});

	// 쿠폰이미지 업로드 
	$('#imgFile').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile')[0].files[0]);
	    data.append('dir', 'coupon'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
	    isUploading = true;
	    
		  $.ajax({
				url : '${pageContext.request.contextPath}/imgUpload',
				type: 'POST',
				data: data,
				dataType: 'text',
				processData: false,
				contentType: false,
				success: function(result, status) {
					var jsonResult = JSON.parse(result);
					if(jsonResult.result == 'success'){
//	 			    	alert('이미지 업로드 완료.');
				    	// 서버에서 이미지저장 후 리턴하는 저장경로를 hidden필드에 저장함.
				    	var filePath = jsonResult.filePath;
				    	var html = '<div style="text-align:left;"><img width="150px;" src="${pageContext.request.contextPath}/imgView/' + filePath +'"><a style="cursor:pointer" onclick="removeImg(this)" > X </a></div>';
				    	$('#imgView').append(html);
				    	$('#couponImg').val(filePath);
				    	$('#imgFile').css('display', 'none');
				    } else {
				    	alert('이미지 업로드 실패. 다시 시도해 주세요.');
				    }
				},
				error:function(result, status) {
					alert(result.RESULT_CODE);
				}
			});
		  $('#imgFile').val('');
	});

	// 쿠폰이미지 업로드 
	$('#imgFile2').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile2')[0].files[0]);
	    data.append('dir', 'coupon'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
	    isUploading = true;
	    
		  $.ajax({
				url : '${pageContext.request.contextPath}/imgUpload',
				type: 'POST',
				data: data,
				dataType: 'text',
				processData: false,
				contentType: false,
				success: function(result, status) {
					var jsonResult = JSON.parse(result);
					if(jsonResult.result == 'success'){
//	 			    	alert('이미지 업로드 완료.');
				    	// 서버에서 이미지저장 후 리턴하는 저장경로를 hidden필드에 저장함.
				    	var filePath = jsonResult.filePath;
				    	var html = '<div style="text-align:left;"><img width="150px;" src="${pageContext.request.contextPath}/imgView/' + filePath +'"><a style="cursor:pointer" onclick="removeImgBnr(this)" > X </a></div>';
				    	$('#imgView2').append(html);
				    	$('#couponBnr').val(filePath);
				    	$('#imgFile2').css('display', 'none');
				    } else {
				    	alert('이미지 업로드 실패. 다시 시도해 주세요.');
				    }
				},
				error:function(result, status) {
					alert(result.RESULT_CODE);
				}
			});
		  $('#imgFile2').val('');
	});
	
	// 엑셀 업로드 
	$('#excelFile').change(function(){

		var type = $(':radio[name="couponTarget"]:checked').val();
		if(type != 'S'){
			alert('발급대상이 지정이 아닙니다.');
			$('#excelFile').val('');
			return;
		}
		
		var data = new FormData();
	    data.append('file', $('#excelFile')[0].files[0]);
	    data.append('dir', 'excel'); //dir은 이미지저장 경로를 구분하기 위함. /excel
	    
	    isUploading = true;
	    
		  $.ajax({
				url : '${pageContext.request.contextPath}/imgUpload',
				type: 'POST',
				data: data,
				dataType: 'text',
				processData: false,
				contentType: false,
				success: function(result, status) {
					var jsonResult = JSON.parse(result);
					if(jsonResult.result == 'success'){
//	 			    	alert('이미지 업로드 완료.');
				    	// 서버에서 이미지저장 후 리턴하는 저장경로를 hidden필드에 저장함.
				    	var filePath = jsonResult.filePath;
				    	$('#excelFilePath').val(filePath);
				    	
				    	var data = {};
						data["type"] = 'S';
						data["excel"] = filePath;
					    
						$.ajax({
							contentType : "application/json",
							dataType : "json",
							type:"post",
							url:"${pageContext.request.contextPath}/api/coupon/getTargetCnt",
							data:JSON.stringify(data),
							success : function(result){
								var resCnt = result.resCnt;
								$('#targetCnt').html(resCnt + '명');
								$('#couponCnt').val(resCnt);
							},
							error : function(result){
								
							}
						});
				    } else {
				    	alert('이미지 업로드 실패. 다시 시도해 주세요.');
				    }
				},
				error:function(result, status) {
					alert(result.RESULT_CODE);
				}
			});
		  $('#excelFile').val('');
	});
});

function removeImg(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile').css('display', 'block');
	$('#couponImg').val('');
}

function removeImgBnr(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile2').css('display', 'block');
	$('#couponBnr').val('');
}

function writeAction(action){
	
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			var couponId = $('#couponId').val();
			location.href='${pageContext.request.contextPath}/api/coupon/delete?id=' + couponId;
			return;
		}
	}
	var couponNm = $('#couponNm').val();
	if(couponNm == ''){
		alert('쿠폰명을 등록해주세요.');
		$('#couponNm').focus();
		return;
	}
	var couponDesc = $('#couponDesc').val();
	if(couponDesc == ''){
		alert('쿠폰 설명을 등록해주세요.');
		$('#couponDesc').focus();
		return;
	}
	var couponStore = $('#couponStore').val();
	if(couponStore == ''){
		alert('사용장소를 등록해주세요.');
		$('#couponStore').focus();
		return;
	}
	var couponImg = $('#couponImg').val();
	if(couponImg == ''){
		alert('쿠폰 이미지를 올려주세요.');
		return;
	}
	var FORM_BODY = $('form[name=formBody]');

	var url = '${pageContext.request.contextPath}/api/coupon/';
	FORM_BODY.attr('action', url + action);
	FORM_BODY.submit();

}
</script>
</html>