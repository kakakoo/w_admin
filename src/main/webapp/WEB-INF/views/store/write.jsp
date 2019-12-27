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
	
		<jsp:include page="../common/left.jsp"/>
		<jsp:include page="../common/header.jsp"/>
		<input type="hidden" id="alarm" value="${result }">

        <div class="right_col" role="main">
			<!-- 내용 시작  -->
		<div class="my_user_content">
			<div class="title">
				* 지점 현황 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/store/write" method="post">
			<input type="hidden" name="storeId" value="${info.storeId }">
			<input type="hidden" name="storeImg" id="storeImg" value="${info.storeImg }">
			<input type="hidden" name="rsvImg" id="rsvImg" value="${info.rsvImg }">
			<input type="hidden" name="closeDt" id="closeDt" value="${info.closeDt }">
			<input type="hidden" name="openSt" id="openSt">
			<input type="hidden" name="startTm" id="startTm">
			<input type="hidden" name="endTm" id="endTm">
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
					<td>노출여부</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="storeSt" value="N" <c:if test="${info.storeSt == 'N'}">checked</c:if><c:if test="${empty info.storeSt}">checked</c:if>> OFF
						<input type="radio" style="margin: 4px 4px 0px;" name="storeSt" value="R" <c:if test="${info.storeSt == 'R'}">checked</c:if>> READY
						<input type="radio" style="margin: 4px 4px 0px;" name="storeSt" value="Y" <c:if test="${info.storeSt == 'Y'}">checked</c:if>> ON
					</td>
				</tr>
				<tr>
					<td>지점명</td>
					<td><input type="text" name="storeNm" style="width:100%;" value="${info.storeNm }"></td>
				</tr>
				<tr>
					<td>지점명(영문)<br>8자 까지</td>
					<td><input type="text" name="storeNmEng" style="width:100%;" value="${info.storeNmEng }"></td>
				</tr>
				<tr>
					<td>지점명(영수증)<br>8자 까지</td>
					<td><input type="text" name="displayNm" style="width:100%;" value="${info.displayNm }"></td>
				</tr>
				<tr>
					<td>예약여부</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="storeRsv" value="N" <c:if test="${info.storeRsv == 'N'}">checked</c:if><c:if test="${empty info.storeRsv}">checked</c:if>> 불가
						<input type="radio" style="margin: 4px 4px 0px;" name="storeRsv" value="Y" <c:if test="${info.storeRsv == 'Y'}">checked</c:if>> 가능
					</td>
				</tr>
				<tr>
					<td>당일예약여부</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="todayRsv" value="N" <c:if test="${info.todayRsv == 'N'}">checked</c:if><c:if test="${empty info.todayRsv}">checked</c:if>> 불가
						<input type="radio" style="margin: 4px 4px 0px;" name="todayRsv" value="Y" <c:if test="${info.todayRsv == 'Y'}">checked</c:if>> 가능
					</td>
				</tr>
				<tr>
					<td>공항여부</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="storeAir" value="N" <c:if test="${info.storeAir == 'N'}">checked</c:if><c:if test="${empty info.storeAir}">checked</c:if>> 아님
						<input type="radio" style="margin: 4px 4px 0px;" name="storeAir" value="Y" <c:if test="${info.storeAir == 'Y'}">checked</c:if>> 맞음
					</td>
				</tr>
				<tr>
					<td>배달여부</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="deliverSt" value="N" <c:if test="${info.deliverSt == 'N'}">checked</c:if><c:if test="${empty info.deliverSt}">checked</c:if>> 불가
						<input type="radio" style="margin: 4px 4px 0px;" name="deliverSt" value="Y" <c:if test="${info.deliverSt == 'Y'}">checked</c:if>> 가능
					</td>
				</tr>
				<tr>
					<td>당일배달여부</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="deliverToday" value="N" <c:if test="${info.deliverToday == 'N'}">checked</c:if><c:if test="${empty info.deliverToday}">checked</c:if>> 불가
						<input type="radio" style="margin: 4px 4px 0px;" name="deliverToday" value="Y" <c:if test="${info.deliverToday == 'Y'}">checked</c:if>> 가능
					</td>
				</tr>
				<tr>
					<td>예약 수수료</td>
					<td><input type="number" name="rsvCommis" style="width:100%;" value="${info.rsvCommis }"></td>
				</tr>
				<tr>
					<td>배달 수수료</td>
					<td><input type="number" name="deliverCms" style="width:100%;" value="${info.deliverCms }"></td>
				</tr>
				<tr>
					<td>배달 시간 텀(시간)</td>
					<td><input type="number" name="deliverTime" style="width:100%;" value="${info.deliverTime }"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="storeAddr" style="width:100%;" value="${info.storeAddr }"></td>
				</tr>
				<tr>
					<td>주소URL</td>
					<td><input type="text" name="storeUrl" style="width:100%;" value="${info.storeUrl }"></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="storeTel" style="width:100%;" value="${info.storeTel }"></td>
				</tr>
				<tr>
					<td>영업시간</td>
					<td><input type="text" name="storeOpentime" style="width:100%;" value="${info.storeOpentime }"></td>
				</tr>
				<c:if test="${result == '2' }">
				<tr>
					<td>영업요일</td>
					<td>
						<table>
							<colgroup>
								<col style="width:10%"/>
								<col style="width:10%"/>
								<col style="width:10%"/>
								<col style="width:10%"/>
								<col style="width:10%"/>
								<col style="width:10%"/>
								<col style="width:10%"/>
							</colgroup>
							<tr>
								<c:forEach var="items" items="${openList }">
									<td>${items.dayString }<input type="checkbox" id="openSt_${items.DAY }" <c:if test="${items.OPEN_ST == 'Y' }">checked</c:if>></td>
								</c:forEach>
							</tr>
							<tr>
								<c:forEach var="items" items="${openList }">
									<td>
										<input style="width:80px;" type="text" id="start_${items.DAY }" value="${items.START_TM }"> ~ 
										<input style="width:80px;" type="text" id="end_${items.DAY }" value="${items.END_TM }">
									</td>
								</c:forEach>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>휴일관리</td>
					<td style="text-align:left;">
						<c:forEach var="items" items="${info.closeList }">
							<div id="closeText" style="text-align:left;">${items }<a style="cursor:pointer" onclick="removeText(this)" > X </a></div>
						</c:forEach>
						<div id="closeNew"></div>
						<input type="text" id="closeDtBtn" placeholder="yyyy.MM.dd"><input type="button" value="추가" onclick="addCloseDt()">
					</td>
				</tr>
				</c:if>
				<tr>
					<td>지점 이미지</td>
					<td>
						<div id="imgText" style="text-align:left;">${info.storeImg }</div>
						<div id="imgView" style="text-align:left;">
						<c:if test="${not empty info.imgList}">
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
					<td>예약 이미지</td>
					<td>
						<div id="imgTextRsv" style="text-align:left;">${info.rsvImg }</div>
						<div id="imgViewRsv" style="text-align:left;">
						<c:if test="${not empty info.rsvImg}">
							<div style="text-align:left;">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.rsvImg}"><a style="cursor:pointer" onclick="removeImgRsv(this)" > X </a>
							</div>
						</c:if>
						</div>
						<input type="file" id="imgFileRsv">
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
	var alarm = $('#alarm').val();
	if(alarm == '1'){
		alert('등록에 실패하였습니다.');
	}
	
	
	$('#imgFile').change(function(){
		
		var data = new FormData();
	    data.append('file', $('input[type=file]')[0].files[0]);
	    data.append('dir', 'store'); //dir은 이미지저장 경로를 구분하기 위함. /img/store 
	    
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
				    		$('#storeImg').val(filePath);
				    	} else {
				    		$('#imgText').html(imgText + ',' + filePath);
				    		$('#storeImg').val(imgText + ',' + filePath);
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
	
	$('#imgFileRsv').change(function(){
		
		var data = new FormData();
	    data.append('file', $('input[type=file]')[1].files[0]);
	    data.append('dir', 'store'); //dir은 이미지저장 경로를 구분하기 위함. /img/store 
	    
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
				    	var imgText = $('#imgTextRsv').html();
				    	if(imgText == ''){
				    		$('#imgTextRsv').html(filePath);
				    		$('#rsvImg').val(filePath);
				    	} else {
				    		$('#imgTextRsv').html(imgText + ',' + filePath);
				    		$('#rsvImg').val(imgText + ',' + filePath);
				    	}
				    	var html = '<div style="text-align:left;"><img width="150px;" src="${pageContext.request.contextPath}/imgView/' + filePath +'"><a style="cursor:pointer" onclick="removeImg(this)" > X </a></div>';
				    	$('#imgViewRsv').append(html);
				    } else {
				    	alert('이미지 업로드 실패. 다시 시도해 주세요.');
				    }
				},
				error:function(result, status) {
					alert(result.RESULT_CODE);
				}
			});
		  $('#imgFileRsv').val('');
	});
});

function addCloseDt(){
	var dt = $('#closeDtBtn').val();
	var closeDt = $('#closeDt').val();
	if(closeDt == ''){
		closeDt = dt;
	} else {
		closeDt = closeDt + ',' + dt;
	}
	$('#closeDt').val(closeDt);
	var html = '<div id="closeText" style="text-align:left;">' + dt + '<a style="cursor:pointer" onclick="removeText(this)" > X </a></div>';
	$('#closeNew').append(html);
	$('#closeDtBtn').val('');
}

function removeText(xBut){
	
	var div = $(xBut).parent().find('#closeText');
	var day = div.context.parentNode.innerText.replace(' X', '');
	$(div.context.parentNode).remove();
	var closeDt = $('#closeDt').val();
	if(closeDt == day){
		closeDt = '';
	} else if(closeDt.includes(day + ',')){
		closeDt = closeDt.replace(day + ',', '');
	} else {
		closeDt = closeDt.replace(',' + day, '');
	}
	$('#closeDt').val(closeDt);
	console.log(closeDt);
}

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
	$('#storeImg').val(imgText);
}
function removeImgRsv(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');
	var filePath = $(imgVar).attr('src');
	filePath = filePath.replace('${pageContext.request.contextPath}/imgView/', '');
	
	var imgText = $('#imgTextRsv').html();
	if(filePath == imgText){
		imgText = '';
	} else if (imgText.includes(filePath + ',')) {
		imgText = imgText.replace(filePath + ',', '');
	} else {
		imgText = imgText.replace(',' + filePath, '');
	}
	$('#imgTextRsv').html(imgText);
	$('#rsvImg').val(imgText);
}

function writeAction(action){
	
	var flag = false;
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			flag = true;
		}
	} else {
		flag = true;
	}
	
	if(flag){
		
		if(action == 'update'){
			var st = new Array(7);
			var start = new Array(7);
			var end = new Array(7);
			
			for(day = 0 ; day < 7 ; day++){
				st[day] = $('#openSt_' + day).is(":checked");
				start[day] = $('#start_' + day).val();
				end[day] = $('#end_' + day).val();
			}
			$('#openSt').val(st);
			$('#startTm').val(start);
			$('#endTm').val(end);
		}
		
		var FORM_BODY = $('form[name=formBody]');

		var url = '${pageContext.request.contextPath}/api/store/';
		FORM_BODY.attr('action', url + action);
		FORM_BODY.submit();
	}
	
}
</script>
</html>