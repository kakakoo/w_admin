<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 컨텐츠</title>
	
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
				* 컨텐츠 관리 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/cont/list/write" method="post">
			<input type="hidden" name="contId" id="contId" value="${contId }">
			<input type="hidden" name="clId" id="clId" value="${info.clId }">
			<input type="hidden" name="clImg" id="clImg" value="${info.clImg }">
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
					<td>제목</td>
					<td><textarea rows="5" name="clTitle" id="clTitle" style="width:100%">${info.clTitle }</textarea></td>
				</tr>
				<tr>
					<td>부제</td>
					<td><input type="text" name="clSubTitle" id="clSubTitle" style="width:100%;" value="${info.clSubTitle }"></td>
				</tr>
				<tr>
					<td>색상</td>
					<td><input type="text" name="clColor" id="clColor" style="width:100%;" value="${info.clColor }"></td>
				</tr>
				<tr>
					<td>경로</td>
					<td><input type="text" name="clUrl" id="clUrl" style="width:100%;" value="${info.clUrl }"></td>
				</tr>
				<tr>
					<td>사용기간</td>
					<td style="text-align:left;"><input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${info.startDt } - ${info.endDt }" /></td>
				</tr>
				<tr>
					<td>이미지</td>
					<td>
						<div id="imgText" style="text-align:left;">${info.clImg }</div>
						<div id="imgView" style="text-align:left;">
							<c:if test="${not empty info.clImg}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.clImg}"><a style="cursor:pointer" onclick="removeImg(this)" > X </a>
							</c:if>
						</div>	
						<input type="file" id="imgFile" <c:if test="${not empty info.clImg}">style="display:none"</c:if>>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<c:choose>
					       <c:when test="${result == '2' }">
					           <input type="button" class="btn btn-success" onclick="writeAction('update')" value="수정">
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

	// 이벤트 업로드 
	$('#imgFile').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile')[0].files[0]);
	    data.append('dir', 'cont'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
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
				    		$('#clImg').val(filePath);
				    	} else {
				    		$('#imgText').html(imgText + ',' + filePath);
				    		$('#clImg').val(imgText + ',' + filePath);
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
	$('#clImg').val(imgText);
}

function writeAction(action){
	

	var FORM_BODY = $('form[name=formBody]');

	var url = '${pageContext.request.contextPath}/api/cont/list/';
	FORM_BODY.attr('action', url + action);
	FORM_BODY.submit();

}
</script>
</html>