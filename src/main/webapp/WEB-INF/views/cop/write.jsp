<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 제휴사</title>
	
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
				* 제휴사 관리 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/cop/write" method="post">
			<input type="hidden" name="copId" id="copId" value="${info.copId }">
			<input type="hidden" name="copLogo" id="copLogo" value="${info.copLogo }">
			<input type="hidden" name="copLogoB" id="copLogoB" value="${info.copLogoB }">
			<input type="hidden" name="copBg" id="copBg" value="${info.copBg }">
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
					<td>업체명</td>
					<td><input type="text" name="copNm" id="copNm" style="width:100%;" value="${info.copNm }"></td>
				</tr>
				<tr>
					<td>관리자 아이디</td>
					<td><input type="text" name="copAdm" id="copAdm" style="width:100%;" <c:if test="${not empty info.copAdm }">readonly="readonly"</c:if> value="${info.copAdm }"></td>
				</tr>
				<tr>
					<td>업체코드</td>
					<td><input type="text" readonly="readonly" style="width:100%;" value="${info.copCd }"></td>
				</tr>
				<tr>
					<td>매인 로고</td>
					<td>
						<div id="imgView" style="text-align:left;">
							<c:if test="${not empty info.copLogo}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.copLogo}"><a style="cursor:pointer" onclick="removeLogo(this)" > X </a>
							</c:if>
						</div>
						<input type="file" id="imgFile" <c:if test="${not empty info.copLogo}">style="display:none"</c:if>>
					</td>
				</tr>
				<tr>
					<td>검은 로고</td>
					<td>
						<div id="imgView1" style="text-align:left;">
							<c:if test="${not empty info.copLogoB}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.copLogoB}"><a style="cursor:pointer" onclick="removeLogoB(this)" > X </a>
							</c:if>
						</div>
						<input type="file" id="imgFile1" <c:if test="${not empty info.copLogoB}">style="display:none"</c:if>>
					</td>
				</tr>
				<tr>
					<td>배경 이미지</td>
					<td>
						<div id="imgView2" style="text-align:left;">
							<c:if test="${not empty info.copBg}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.copBg}"><a style="cursor:pointer" onclick="removeBg(this)" > X </a>
							</c:if>
						</div>	
						<input type="file" id="imgFile2" <c:if test="${not empty info.copBg}">style="display:none"</c:if>>
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
	
$('#imgFile').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile')[0].files[0]);
	    data.append('dir', 'cop'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
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
						$('#copLogo').val(filePath);
				    	var html = '<div style="text-align:left;"><img width="150px;" src="${pageContext.request.contextPath}/imgView/' + filePath +'"><a style="cursor:pointer" onclick="removeLogo(this)" > X </a></div>';
				    	$('#imgView').append(html);
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

$('#imgFile1').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile1')[0].files[0]);
	    data.append('dir', 'cop'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
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
						$('#copLogoB').val(filePath);
				    	var html = '<div style="text-align:left;"><img width="150px;" src="${pageContext.request.contextPath}/imgView/' + filePath +'"><a style="cursor:pointer" onclick="removeLogoB(this)" > X </a></div>';
				    	$('#imgView1').append(html);
				    	$('#imgFile1').css('display', 'none');
				    } else {
				    	alert('이미지 업로드 실패. 다시 시도해 주세요.');
				    }
				},
				error:function(result, status) {
					alert(result.RESULT_CODE);
				}
			});
		  $('#imgFile1').val('');
	});
	
	// 이벤트 배너 이미지 업로드 
	$('#imgFile2').change(function(){
		
		var data = new FormData();
	    data.append('file', $('#imgFile2')[0].files[0]);
	    data.append('dir', 'cop'); //dir은 이미지저장 경로를 구분하기 위함. /img/coupon 
	    
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
				    	var html = '<div style="text-align:left;"><img width="150px;" src="${pageContext.request.contextPath}/imgView/' + filePath +'"><a style="cursor:pointer" onclick="removeBg(this)" > X </a></div>';
				    	$('#imgView2').append(html);
				    	$('#copBg').val(filePath);
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


function removeLogo(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile').css('display', 'block');
	$('#copLogo').val('');
}

function removeLogoB(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile1').css('display', 'block');
	$('#copLogoB').val('');
}

function removeBg(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile2').css('display', 'block');
	$('#copBg').val('');
}

function writeAction(action){
	
	if(action == 'delete'){
		if(confirm("정말 삭제하시겠습니까?")){
			var copId = $('#copId').val();
			location.href='${pageContext.request.contextPath}/api/cop/delete?id=' + copId;
			return;
		}
	}
	var copNm = $('#copNm').val();
	if(copNm == ''){
		alert('업체명을 등록해주세요.');
		$('#copNm').focus();
		return;
	}

	var FORM_BODY = $('form[name=formBody]');

	var url = '${pageContext.request.contextPath}/api/cop/';
	FORM_BODY.attr('action', url + action);
	FORM_BODY.submit();

}
</script>
</html>