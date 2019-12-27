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
				* 사용자 공지사항
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/notice" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			
			<div class="title">
				<input type="button" class="btn btn-success" style="color:#000;background-color:#fff;" value="사용자 공지">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/notice/admin'" class="btn btn-success" value="담당자 공지">
			</div>
			<div class="refresh">
				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/api/notice/write?tp=client'" value="등록">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:50%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>등록일</td>
					<td>수정일</td>
					<td>제목</td>
					<td>상태</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td><nobr><font title="${items.noticeRegDttm }">${items.noticeRegDttm }</font></nobr></td>
						<td><nobr><font title="${items.noticeModDttm }">${items.noticeModDttm }</font></nobr></td>
						<td><a onclick="modifyNotice('${items.noticeId }')">${items.noticeTitle }</a></td>
						<td id="${items.noticeId }"><a onclick="changeStatus('${items.noticeId }', '${items.noticeSt }')">${items.noticeSt }</a></td>
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

function changeStatus (noticeId, noticeSt){
	
	if(confirm("상태 변경 하시겠습니까?")){
		var data = {};
		data["noticeId"] = noticeId;
		data["noticeSt"] = noticeSt;
		data["tp"] = "client";
		
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/notice/changeStat",
			data:JSON.stringify(data),
			success : function(result){
				var res = result.result;
				if(res == 'success'){
					if(noticeSt == 'ON')
						$('#' + noticeId).html("<a onclick=\"changeStatus('" + noticeId + "', 'OFF')\">OFF</a>");
					else
						$('#' + noticeId).html("<a onclick=\"changeStatus('" + noticeId + "', 'ON')\">ON</a>");
				} else {
					alert('업데이트 실패');
				}
			},
			error : function(result){
				alert('업데이트 실패, 에러');
			}
		});
	}
	
	
}

function modifyNotice(noticeId){
	location.href='${pageContext.request.contextPath}/api/notice/modify?tp=client&id=' + noticeId;
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.submit();
}
</script>
</html>