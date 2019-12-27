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
	<title>WEYS 컨텐츠</title>
	
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
				* 컨텐츠 현황 
			</div>
			<div class="refresh">
				<input type="button" class="btn btn-success" onclick="modiCL('P')" value="위로">
				<input type="button" class="btn btn-success" onclick="modiCL('W')" value="아래로">
				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/api/cont/write'" value="등록">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/cont" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			
			<table class="list_table">
				<colgroup>
					<col style="width:5%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
					<col style="width:10%"/>
					<col style="width:30%"/>
					<col style="width:5%"/>
				</colgroup>
				<tr>
					<td></td>
					<td>카테고리</td>
					<td>제목</td>
					<td>리스트타입</td>
					<td>표시기간</td>
					<td>수정</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td><input type="radio" style="margin: 4px 4px 0px;" name="clVal" value="${items.contId }"></td>
						<td><nobr><font title="${items.contCtgr }">${items.contCtgr }</font></nobr></td>
						<td><nobr><font title="${items.contTitle }">${items.contTitle }</font></nobr></td>
						<td><nobr><font title="${items.listType }">${items.listType }</font></nobr></td>
						<td><nobr><font title="${items.startDt } ~ ${items.endDt }">${items.startDt } ~ ${items.endDt }</font></nobr></td>
						<td onclick="location.href='${pageContext.request.contextPath}/api/cont/write?id=${items.contId }'">수정</td>
					</tr>
				</c:forEach>
			</table>
			
			<jsp:include page="../common/paging.jsp" flush="true">
			    <jsp:param name="firstPageNo" value="${paging.firstPageNo}" />
			    <jsp:param name="prevPageNo" value="${paging.prevPageNo}" />
			    <jsp:param name="startPageNo" value="${paging.startPageNo}" />
			    <jsp:param name="pageNo" value="${paging.pageNo}" />
			    <jsp:param name="endPageNo" value="${paging.endPageNo}" />
			    <jsp:param name="nextPageNo" value="${paging.nextPageNo}" />
			    <jsp:param name="finalPageNo" value="${paging.finalPageNo}" />
			</jsp:include>
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

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/cont');
	FORM_BODY.submit();
}

function modiCL(tp){
	
	var contId = $("input[name=clVal]:checked").val();
	
	var data = {};
	data["tp"] = tp;
	data["contId"] = contId;
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/api/cont/updateContSeq",
		data: JSON.stringify(data),
		async : false,
		contentType: "application/json",
		success : function(result){
			
			var result = result.result;
			if(result == 'success'){
				alert('변경되었습니다.');
				location.reload(true);
			} else {
				alert('실패');
			}
			
		},
		error : function(result){
			alert('업데이트 실패, 에러');
		}
	});
}

</script>
</html>