<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 공지사항</title>
	
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
<!-- 	<script src="https://cloud.tinymce.com/stable/tinymce.min.js?apiKey=66x4f6ahlx5tkob6i1v85imdb1nhxtcimzhfld476ch2m2h3"></script> -->
<!-- 	<script>tinymce.init({ selector:'textarea' });</script> -->

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
				* 공지사항 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/notice/write" method="post">
			<input type="hidden" name="noticeId" value="${info.noticeId }">
			<input type="hidden" name="imgPath" id="imgPath">
			<input type="hidden" name="tp" value="${tp }">
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
					<td><input type="text" name="noticeTitle" style="width:100%;" value="${info.noticeTitle }"></td>
				</tr>
				<tr>
					<td>내용<br>이미지 넣을 부분에 WEYS_IMG</td>
<!-- 					<td>내용</td> -->
					<td>
						<textarea style="width:100%;" class="ckeditor" name="noticeContent">${info.noticeContent }</textarea>
<%-- 						<textarea style="width:100%;" name="noticeContent">${info.noticeContent }</textarea> --%>
					</td>
				</tr>
				<tr>
					<td>이미지</td>
					<td><input type="file" id="imgFile"></td>
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
	var alarm = $('#alarm').val();
	if(alarm == '1'){
		alert('등록에 실패하였습니다.');
	}
	

	$('#imgFile').change(function(){
			
		var data = new FormData();
	    data.append('file', $('input[type=file]')[0].files[0]);
	    data.append('dir', 'notice'); //dir은 이미지저장 경로를 구분하기 위함. /img/notice 
	    
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
				    	// 서버에서 이미지저장 후 리턴하는 저장경로를 hidden필드에 저장함.
				    	var filePath = jsonResult.filePath;
				    	$('#imgPath').val(filePath);
				    } else {
				    	alert('이미지 업로드 실패. 다시 시도해 주세요.');
				    }
				},
				error:function(result, status) {
					alert(result.RESULT_CODE);
				}
			});
	});
});
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
		var FORM_BODY = $('form[name=formBody]');

		var url = '${pageContext.request.contextPath}/api/notice/';
		FORM_BODY.attr('action', url + action);
		FORM_BODY.submit();
	}
	
}
</script>
</html>