<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 제휴사 배너</title>
	
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
				* 제휴사 배너 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/cop/banner/write" method="post">
			<input type="hidden" name="cbId" id="cbId" value="${info.cbId }">
			<input type="hidden" name="cbSImg" id="cbSImg" value="${info.cbSImg }">
			<input type="hidden" name="cbBImg" id="cbBImg" value="${info.cbBImg }">
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
					<td>배너명</td>
					<td><input type="text" name="cbNm" id="cbNm" style="width:100%;" value="${info.cbNm }"></td>
				</tr>
				<tr>
					<td>모달 타이틀</td>
					<td><input type="text" name="modalNm" id="modalNm" style="width:100%;" value="${info.modalNm }"></td>
				</tr>
				<tr>
					<td>버튼명</td>
					<td><input type="text" name="btnNm" id="btnNm" style="width:100%;" value="${info.btnNm }"></td>
				</tr>
				<tr>
					<td>배너 상태</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="cbSt" value="Y" <c:if test="${info.cbSt == 'Y'}">checked</c:if><c:if test="${empty info.cbSt}">checked</c:if>> 진행중
						<input type="radio" style="margin: 4px 4px 0px;" name="cbSt" value="N" <c:if test="${info.cbSt == 'N'}">checked</c:if>> 종료
					</td>
				</tr>
				<tr>
					<td>배너 URL</td>
					<td><input type="text" name="cbUrl" id="cbUrl" style="width:100%;" value="${info.cbUrl }"></td>
				</tr>
				<tr>
					<td>배너 작은 이미지</td>
					<td>
						<div id="imgView" style="text-align:left;">
							<c:if test="${not empty info.cbSImg}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.cbSImg}"><a style="cursor:pointer" onclick="removeImg(this)" > X </a>
							</c:if>
						</div>	
						<input type="file" id="imgFile" <c:if test="${not empty info.cbSImg}">style="display:none"</c:if>>
					</td>
				</tr>
				<tr>
					<td>배너 큰 이미지</td>
					<td>
						<div id="imgView2" style="text-align:left;">
							<c:if test="${not empty info.cbBImg}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.cbBImg}"><a style="cursor:pointer" onclick="removeImgBnr(this)" > X </a>
							</c:if>
						</div>	
						<input type="file" id="imgFile2" <c:if test="${not empty info.cbBImg}">style="display:none"</c:if>>
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

	// 쿠폰이미지 업로드 
	$('#imgFile').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile')[0].files[0]);
	    data.append('dir', 'banner'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
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
				    	$('#cbSImg').val(filePath);
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
	    data.append('dir', 'banner'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
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
				    	$('#cbBImg').val(filePath);
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

	$('#imgFile').css('display', 'block');
	$('#cbSImg').val('');
}

function removeImgBnr(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile2').css('display', 'block');
	$('#cbBImg').val('');
}

function writeAction(action){
	
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			var cbId = $('#cbId').val();
			location.href='${pageContext.request.contextPath}/api/cop/banner/delete?id=' + cbId;
			return;
		}
	}

	var FORM_BODY = $('form[name=formBody]');

	var url = '${pageContext.request.contextPath}/api/cop/banner/';
	FORM_BODY.attr('action', url + action);
	FORM_BODY.submit();

}
</script>
</html>