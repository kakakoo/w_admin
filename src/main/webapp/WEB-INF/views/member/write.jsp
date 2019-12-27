<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 결제 관리</title>
	
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
				* 멤버십 결제 관리 > 
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
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/member/write" method="post">
			<input type="hidden" id="payId" value="${info.payId }">
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
					<td>결제 구분</td>
					<td><input type="text" name="payType" id="payType" style="width:100%;" value="${info.payType }"></td>
				</tr>
				<tr>
					<td>타이틀</td>
					<td><input type="text" name="payText" id="payText" style="width:100%;" value="${info.payText }"></td>
				</tr>
				<tr>
					<td>타이틀(영어)</td>
					<td><input type="text" name="payTextEng" id="payTextEng" style="width:100%;" value="${info.payTextEng }"></td>
				</tr>
				<tr>
					<td>결제금액</td>
					<td><input type="text" name="payAmnt" id="payAmnt" style="width:100%;" value="${info.payAmnt }"></td>
				</tr>
				<tr>
					<td>한도금액</td>
					<td><input type="text" name="payLimit" id="payLimit" style="width:100%;" value="${info.payLimit }"></td>
				</tr>
				<tr>
					<td>적용기간</td>
					<td><input type="text" name="period" id="period" style="width:100%;" value="${info.period }"></td>
				</tr>
				<tr>
					<td>배경색상</td>
					<td><input type="text" name="color" id="color" style="width:100%;" value="${info.color }"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea style="width:100%;resize:none;" rows="7" name="payInfo" id="payInfo">${info.payInfo }</textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="border-bottom:0px;">
						<c:choose>
					       <c:when test="${result == '2' }">
					           <input type="button" class="btn btn-success" onclick="writeActionAjax('update')" value="수정">
					           <input type="button" class="btn btn-danger" onclick="writeAction('delete')" value="삭제">
					       </c:when>
					       <c:otherwise>
					           <input type="button" class="btn btn-success" onclick="writeActionAjax('write')" value="등록">
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
});

function writeActionAjax(action){
	var data = {};
	data["action"] = action;
	data["payType"] = $('#payType').val();
	data["payText"] = $('#payText').val();
	data["payTextEng"] = $('#payTextEng').val();
	data["payAmnt"] = $('#payAmnt').val();
	data["payLimit"] = $('#payLimit').val();
	data["period"] = $('#period').val();
	data["color"] = $('#color').val();
	data["payInfo"] = $('#payInfo').val();
	if(action == 'update'){
		data["payId"] = $('#payId').val();
	}

	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/member/write",
		data:JSON.stringify(data),
		success : function(result){
			if(result.result == 'success'){
				location.href='${pageContext.request.contextPath}/api/member';
			} else {
				var msg = result.msg;
				alert(msg);
			}
		},
		error : function(result){
			alert('다시 시도해 주세요.');
		}
	});
}


function writeAction(action){
	
	if(confirm("정말 삭제하시겠습니까?")){
		var data = {};
		data["payId"] = $('#payId').val();
		
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/member/delete",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == 'success'){
					location.href='${pageContext.request.contextPath}/api/member';
				} else {
					var msg = result.msg;
					alert(msg);
				}
			},
			error : function(result){
				alert('다시 시도해 주세요.');
			}
		});
	}
}
</script>
</html>