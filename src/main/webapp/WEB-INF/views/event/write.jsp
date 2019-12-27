<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 이벤트</title>
	
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
				* 이벤트 관리 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/event/write" method="post">
			<input type="hidden" name="eventId" id="eventId" value="${info.eventId }">
			<input type="hidden" name="eventImg" id="eventImg" value="${info.eventImg }">
			<input type="hidden" name="eventBnr" id="eventBnr" value="${info.eventBnr }">
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
					<td>이벤트타입</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="eventTp" value="M" <c:if test="${info.eventTp == 'M'}">checked</c:if><c:if test="${empty info.eventTp}">checked</c:if>> 멤버십
						<input type="radio" style="margin: 4px 4px 0px;" name="eventTp" value="D" <c:if test="${info.eventTp == 'D'}">checked</c:if>> 배송료
						<input type="radio" style="margin: 4px 4px 0px;" name="eventTp" value="J" <c:if test="${info.eventTp == 'J'}">checked</c:if>> 참여 이벤트
						<input type="radio" style="margin: 4px 4px 0px;" name="eventTp" value="E" <c:if test="${info.eventTp == 'E'}">checked</c:if>> 이벤트
					</td>
				</tr>
				<tr>
					<td>이벤트 상태</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="eventSt" value="Y" <c:if test="${info.eventSt == 'Y'}">checked</c:if><c:if test="${empty info.eventSt}">checked</c:if>> 진행
						<input type="radio" style="margin: 4px 4px 0px;" name="eventSt" value="N" <c:if test="${info.eventSt == 'N'}">checked</c:if>> 종료
					</td>
				</tr>
				<tr>
					<td>이벤트 제목</td>
					<td><input type="text" name="eventTitle" id="eventTitle" style="width:100%;" value="${info.eventTitle }"></td>
				</tr>
				<tr>
					<td>이벤트 표시 내용</td>
					<td><input type="text" name="eventDesc" id="eventDesc" style="width:100%;" value="${info.eventDesc }"></td>
				</tr>
				<tr id="coup_tr" style="display:none;">
					<td>쿠폰</td>
					<td style="text-align:left;">
						<select name="couponId" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;">
								<option value="0">선택하세요.</option>
							<c:forEach var="items" items="${coupList }">
								<option value="${items.couponId }" <c:if test="${info.couponId == items.couponId}">selected</c:if>>${items.couponNm }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>한도액수</td>
					<td><input type="number" name="memberPoint" id="memberPoint" style="width:100%;" value="${info.memberPoint }"></td>
				</tr>
				<tr>
					<td>사용기간</td>
					<td><input type="number" name="memberPeriod" id="memberPeriod" style="width:100%;" value="${info.memberPeriod }"></td>
				</tr>
				<tr>
					<td>사용제한</td>
					<td><input type="number" name="eventLimit" id="eventLimit" style="width:100%;" value="${info.eventLimit }"></td>
				</tr>
				<tr>
					<td>유효기간</td>
					<td style="text-align:left;"><input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${info.startDt } - ${info.endDt }" /></td>
				</tr>
				<tr>
					<td>버튼위치</td>
					<td><input type="number" name="btnPst" id="btnPst" style="width:100%;" value="${info.btnPst }"></td>
				</tr>
				<tr>
					<td>약관 사항</td>
					<td><textarea style="width:100%;" class="ckeditor" name="eventTxt">${info.eventTxt }</textarea></td>
				</tr>
				<tr>
					<td>이미지</td>
					<td>
						<div id="imgText" style="text-align:left;">${info.eventImg }</div>
						<div id="imgView" style="text-align:left;">
						<c:if test="${not empty info.eventImg}">
							<c:forEach var="items" items="${info.imgList }">
								<div style="text-align:left;">
									<img width="150px;" src="${pageContext.request.contextPath}/imgView/${items}"><a style="cursor:pointer" onclick="removeImg(this)" > X </a>
								</div>
							</c:forEach>
						</c:if>
						</div>
						<input type="file" id="imgFile">
					</td>
				</tr>
				<tr>
					<td>이미지 배너</td>
					<td>
						<div id="imgView2" style="text-align:left;">
							<c:if test="${not empty info.eventBnr}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.eventBnr}"><a style="cursor:pointer" onclick="removeImgBnr(this)" > X </a>
							</c:if>
						</div>	
						<input type="file" id="imgFile2" <c:if test="${not empty info.eventBnr}">style="display:none"</c:if>>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<c:choose>
					       <c:when test="${result == '2' }">
					           <input type="button" class="btn btn-success" onclick="writeAction('update')" value="수정">
<!-- 					           <input type="button" class="btn btn-danger" onclick="writeAction('delete')" value="삭제"> -->
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

	$("input[name=eventTp]").change(function(){
		var val = $(this).val();
		
		if( val == 'D' || val == 'J'){
			$('#coup_tr').css('display','table-row');
		} else {
			$('#coup_tr').css('display','none');
		}
	});
	
	// 이벤트 업로드 
	$('#imgFile').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile')[0].files[0]);
	    data.append('dir', 'event'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
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
				    	var imgText = $('#imgText').html();
				    	if(imgText == ''){
				    		$('#imgText').html(filePath);
				    		$('#eventImg').val(filePath);
				    	} else {
				    		$('#imgText').html(imgText + ',' + filePath);
				    		$('#eventImg').val(imgText + ',' + filePath);
				    	}
				    	var html = '<div style="text-align:left;"><img width="150px;" src="${pageContext.request.contextPath}/imgView/' + filePath +'"><a style="cursor:pointer" onclick="removeImg(this)" > X </a></div>';
				    	$('#imgView').append(html);
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

	// 이벤트 배너 이미지 업로드 
	$('#imgFile2').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile2')[0].files[0]);
	    data.append('dir', 'event'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
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
				    	$('#eventBnr').val(filePath);
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
});

function removeImg(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');
	var filePath = $(imgVar).attr('src');
	filePath = filePath.replace('${pageContext.request.contextPath}/imgView/', '');
	
	var imgText = $('#imgText').html();
	if(filePath == imgText){
		imgText = '';
	} else if (imgText.includes(filePath + ',')) {
		imgText = imgText.replace(filePath + ',', '');
	} else {
		imgText = imgText.replace(',' + filePath, '');
	}
	$('#imgText').html(imgText);
	$('#eventImg').val(imgText);
}
function removeImgBnr(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile2').css('display', 'block');
	$('#eventBnr').val('');
}

function writeAction(action){
	
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			var couponId = $('#couponId').val();
			location.href='${pageContext.request.contextPath}/api/coupon/delete?id=' + couponId;
			return;
		}
	}
	var eventTitle = $('#eventTitle').val();
	if(eventTitle == ''){
		alert('쿠폰명을 등록해주세요.');
		$('#couponNm').focus();
		return;
	}
	var FORM_BODY = $('form[name=formBody]');

	var url = '${pageContext.request.contextPath}/api/event/';
	FORM_BODY.attr('action', url + action);
	FORM_BODY.submit();

}
</script>
</html>