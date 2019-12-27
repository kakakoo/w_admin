<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 푸시</title>
	
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
				* 일반 푸시 보내기
			</div>
			<div class="refresh" style="padding:1%;">
				&nbsp;
			</div>
			
			<div class="title" style="padding-left: 0;" >
				<input type="button" class="btn btn-success" style="color:#000;background-color:#fff;" value="일반 푸시">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/push/contents'" class="btn btn-success" value="컨텐츠 푸시">
			</div>
			<div class="refresh" style="padding:1%;">
				&nbsp;
			</div>
			
		
		<form name="formBody" action="${pageContext.request.contextPath}/api/push" method="post">
			<table class="search">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:80%"/>
				</colgroup>
				<tr>
					<td>기기</td>
					<td>
						<input type="checkbox" name="os" value="A" checked> 안드로이드
						<input type="checkbox" name="os" value="I" checked> 아이폰
					</td>
				</tr> 
				<tr>
					<td>푸시 타입</td>
					<td style="text-align:left;">
						<input type="radio" style="margin: 4px 4px 0px;" name="pushType" value="info" checked="checked"> 알림
						<input type="radio" style="margin: 4px 4px 0px;" name="pushType" value="notice" > 공지사항
						<input type="radio" style="margin: 4px 4px 0px;" name="pushType" value="event" > 이벤트
<!-- 						<input type="radio" style="margin: 4px 4px 0px;" name="pushType" value="cont" > 컨텐츠 -->
					</td>
				</tr>
				<tr id="noti_tr" style="display:none;">
					<td>공지사항</td>
					<td style="text-align:left;">
						<select id="noticeId" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;">
							<c:forEach var="items" items="${noticeList }">
								<option value="${items.NOTICE_ID }" >${items.NOTICE_TITLE }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr id="eve_tr" style="display:none;">
					<td>이벤트</td>
					<td style="text-align:left;">
						<select id="eventId" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;">
							<c:forEach var="items" items="${eventList }">
								<option value="${items.EVENT_ID }">${items.EVENT_TITLE }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr id="cont_tr" style="display:none;">
					<td>컨텐츠</td>
					<td style="text-align:left;">
						<select id="contId" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;">
							<c:forEach var="items" items="${contList }">
								<option value="${items.CL_URL }">${items.CL_TITLE }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>제목</td>
					<td>
						<input type="text" id="pushTitle" style="border-color: #a7a9aa !important;width:100%;margin:auto;height:22px;" value="">
					</td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<input type="text" id="pushMsg" style="border-color: #a7a9aa !important;width:100%;margin:auto;height:22px;" value="">
					</td>
				</tr>
			</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="sendPush()" style="width:20%;font-weight:700;" value="전송">
			</div>
			
		</div>
		
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

$(function () {

	$("input[name=pushType]").change(function(){
		var val = $(this).val();
		
		if( val == 'notice'){
			$('#noti_tr').css('display','table-row');
			$('#eve_tr').css('display','none');
			$('#cont_tr').css('display','none');
		} else if( val == 'event'){
			$('#eve_tr').css('display','table-row');
			$('#noti_tr').css('display','none');
			$('#cont_tr').css('display','none');
		} else if( val == 'cont'){
			$('#cont_tr').css('display','table-row');
			$('#eve_tr').css('display','none');
			$('#noti_tr').css('display','none');
		} else {
			$('#noti_tr').css('display','none');
			$('#eve_tr').css('display','none');
			$('#cont_tr').css('display','none');
		}
	});
	
});

function sendPush(){
	var msg = $('#pushMsg').val();
	var pushType = $(':radio[name="pushType"]:checked').val();
	
	var os = '';
	$('input:checkbox[name="os"]').each(function() {
		if(this.checked){//checked 처리된 항목의 값
			if(os == ''){
				os = this.value;
			} else{
				os = os + "," + this.value;
			}
		}
	});
	
	var value = '';
	
	if(pushType == 'event'){
		value = $("#eventId option:selected").val();
	} else if(pushType == 'notice'){
		value = $("#noticeId option:selected").val();
	} else if(pushType == 'cont'){
		value = $("#contId option:selected").val();
	}
	
	var data = {};
	data["title"] = $('#pushTitle').val();
	data["msg"] = msg;
	data["os"] = os;
	data["pushType"] = pushType;
	data["value"] = value;
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/push",
		data: JSON.stringify(data),
		contentType: "application/json",
		success: function(result, status) {
			var result = result.result;
			if(result == 'success'){
				alert('푸시 성공');
			} else {
				alert('실패');
			}
		},
		error:function(result, status) {
			alert('실패');
		}
	});
}
</script>
</html>