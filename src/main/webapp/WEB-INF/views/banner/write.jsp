<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 배너</title>
	
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
				* 배너 관리 > 시작 배너 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/banner/write" method="post">
			<input type="hidden" name="bannerId" value="${info.bannerId }">
			<input type="hidden" name="bannerUrl" id="bannerUrl" value="${info.bannerUrl }">
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
					<td>타이틀</td>
					<td><input type="text" name="bannerNm" id="bannerNm" style="width:100%;" value="${info.bannerNm }"></td>
				</tr>
				<tr>
					<td>적용기간</td>
					<td style="text-align:left;"><input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${info.startDt } - ${info.endDt }" /></td>
				</tr>
				<tr>
					<td>이미지</td>
					<td>
						<div id="imgView" style="text-align:left;">
							<c:if test="${not empty info.bannerUrl}">
								<img width="150px;" src="${pageContext.request.contextPath}/imgView/${info.bannerUrl}"><a style="cursor:pointer" onclick="removeImg(this)" > X </a>
							</c:if>
						</div>	
						<input type="file" id="imgFile" <c:if test="${not empty info.bannerUrl}">style="display:none"</c:if>>
					</td>
				</tr>
				<tr>
					<td rowspan="2">이동화면</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="redirectType" value="A" <c:if test="${info.redirectType == 'A'}">checked</c:if><c:if test="${empty info.redirectType}">checked</c:if>> APP내 화면
						<select name="redirectApp" id="redirectApp" style="margin-left:10px;width:200px;" >
							<c:forEach var="list" items="${appList }">
								<option value="${list.key }" <c:if test="${info.redirectApp == list.key}">selected</c:if>>${list.value }</option>
							</c:forEach>
						</select>
						<select name="eventId" id="eventId" style="margin-left:10px;width:200px;display:none;" >
							<c:forEach var="list" items="${eventList }">
								<option value="${list.EVENT_ID }" <c:if test="${info.eventId == list.EVENT_ID}">selected</c:if>>${list.EVENT_TITLE }</option>
							</c:forEach>
						</select>
						<select name="noticeId" id="noticeId" style="margin-left:10px;width:200px;display:none;" >
							<c:forEach var="list" items="${noticeList }">
								<option value="${list.NOTICE_ID }" <c:if test="${info.noticeId == list.NOTICE_ID}">selected</c:if>>${list.NOTICE_TITLE }</option>
							</c:forEach>
						</select>
						<select name="contId" id="contId" style="margin-left:10px;width:200px;display:none;" >
							<c:forEach var="list" items="${contList }">
								<option value="${list.CL_URL }" <c:if test="${info.contId == list.CL_URL}">selected</c:if>>${list.CL_TITLE }</option>
							</c:forEach>
						</select>
						<select name="unitCd" id="unitId" style="margin-left:10px;width:200px;display:none;" >
							<c:forEach var="list" items="${unitList }">
								<option value="${list.UNIT_CD }" <c:if test="${info.unitCd == list.UNIT_CD}">selected</c:if>>${list.UNIT_NM }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="redirectType" value="U" <c:if test="${info.redirectType == 'U'}">checked</c:if>> APP외 URL
						<input type="text" name="redirectUrl" id="redirectUrl" style="margin-left:10px;width:400px;" value="${info.redirectUrl }">
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
	    data.append('file', $('input[type=file]')[0].files[0]);
	    data.append('dir', 'banner'); //dir은 이미지저장 경로를 구분하기 위함. /img/banner 
	    
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
				    	$('#bannerUrl').val(filePath);
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

	$("#redirectApp").change(function(){
		var val = $(this).val();
		if( val == 'event'){
			$('#eventId').css('display','initial');
			$('#noticeId').css('display','none');
			$('#contId').css('display','none');
			$('#unitId').css('display','none');
		} else if( val == 'notice'){
			$('#noticeId').css('display','initial');
			$('#eventId').css('display','none');
			$('#contId').css('display','none');
			$('#unitId').css('display','none');
		} else if( val == 'cont'){
			$('#contId').css('display','initial');
			$('#noticeId').css('display','none');
			$('#eventId').css('display','none');
			$('#unitId').css('display','none');
		} else if( val == 'rsv'){
			$('#unitId').css('display','initial');
			$('#contId').css('display','none');
			$('#noticeId').css('display','none');
			$('#eventId').css('display','none');
		} else {
			$('#noticeId').css('display','none');
			$('#eventId').css('display','none');
			$('#contId').css('display','none');
			$('#unitId').css('display','none');
		}
	});
	
	
});

function removeImg(xBut){
	
	var imgVar = $(xBut).parent().find('img');

	$(xBut).css('display', 'none');
	$(imgVar).css('display', 'none');

	$('#imgFile').css('display', 'block');
	$('#bannerUrl').val('');
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
		var FORM_BODY = $('form[name=formBody]');

		var url = '${pageContext.request.contextPath}/api/banner/';
		FORM_BODY.attr('action', url + action);
		FORM_BODY.submit();
	}

}
</script>
</html>