<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 버전관리</title>
	
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
				* 버전관리
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/version/write" method="post">
			<input type="hidden" name="os" id="os">
			<table class="list_table">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:40%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>OS</td>
					<td>강제 업데이트 버전</td>
					<td>마켓 버전</td>
					<td>URL</td>
					<td>관리</td>
				</tr>
				<tr>
					<td>아이폰</td>
					<td id="ios_version">${ios.version }</td>
					<td id="ios_market">${ios.market }</td>
					<td id="ios_url">${ios.url }</td>
					<td id="ios"><a onclick="changeVersion('ios', '${ios.version }', '${ios.market }', '${ios.url }')">수정</a></td>
					<input type="hidden" id="ios_hidden_version" value="${ios.version }">
					<input type="hidden" id="ios_hidden_market" value="${ios.market }">
					<input type="hidden" id="ios_hidden_url" value="${ios.url }">
				</tr>
				<tr>
					<td>안드로이드</td>
					<td id="aos_version">${aos.version }</td>
					<td id="aos_market">${aos.market }</td>
					<td id="aos_url">${aos.url }</td>
					<td id="aos"><a onclick="changeVersion('aos', '${aos.version }', '${aos.market }', '${aos.url }')">수정</a></td>
					<input type="hidden" id="aos_hidden_version" value="${aos.version }">
					<input type="hidden" id="aos_hidden_market" value="${aos.market }">
					<input type="hidden" id="aos_hidden_url" value="${aos.url }">
				</tr>
			</table>
			</form>
			
			<div class="title">
				* 담당자 관리
			</div>
			<div class="refresh">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/version/mng/write'" class="btn btn-success" value="담당자 등록">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:25%"/>
					<col style="width:25%"/>
					<col style="width:25%"/>
					<col style="width:25%"/>
				</colgroup>
				<tr>
					<td>이름</td>
					<td>연락처</td>
					<td>수신여부</td>
					<td>등록일</td>
				</tr>
				<c:forEach var="items" items="${mngList }">
					<tr onclick="location.href='${pageContext.request.contextPath}/api/version/mng/write?id=${items.mgId }'">
						<td>${items.mgNm }</td>
						<td>${items.mgTel }</td>
						<td>${items.mgSt }</td>
						<td>${items.regDttm }</td>
					</tr>
				</c:forEach>
			</table>
			
			<div class="title">
				* SMS 관리
			</div>
			<div class="refresh" style="padding: 5px 0px;">
				<input type="button" onclick="location.href='${pageContext.request.contextPath}/api/version/sms/write'" class="btn btn-success" value="SMS 등록">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:25%"/>
					<col style="width:75%"/>
				</colgroup>
				<tr>
					<td>제목</td>
					<td>내용</td>
				</tr>
				<c:forEach var="items" items="${smsList }">
					<tr onclick="location.href='${pageContext.request.contextPath}/api/version/sms/write?id=${items.smsId }'">
						<td>${items.smsTitle }</td>
						<td>${items.smsText }</td>
					</tr>
				</c:forEach>
			</table>
			
			<form name="logBody" action="${pageContext.request.contextPath}/api/version" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			<div class="title">
				* 로그관리
			</div>
			<div class="refresh">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:60%"/>
					<col style="width:25%"/>
				</colgroup>
				<tr>
					<td>아이디</td>
					<td>IP 주소</td>
					<td>접속일자</td>
				</tr>
				<c:forEach var="items" items="${logList }">
					<tr>
						<td>${items.adminId }</td>
						<td>${items.ipAddr }</td>
						<td>${items.regDttm }</td>
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
var flag = false;
function changeVersion(os, version, market, url){
	if(flag){
		alert("수정중인 버전이 있습니다.");
		return;
	}
	flag = true;
	$('#os').val(os);
	$('#' + os + '_version').html('<input type="text" id="version" name="version" value="' + version + '">');
	$('#' + os + '_market').html('<input type="text" id="market" name="market" value="' + market + '">');
	$('#' + os + '_url').html('<input type="text" style="width:100%;" id="url" name="url" value="' + url + '">');
	$('#' + os).html("<input type='button' class='btn btn-danger' onclick='writeAction(\"cancel\", \"" + os + "\")' value='취소'><input type='button' class='btn btn-success' onclick='writeAction(\"update\", \"" + os + "\")' value='저장'>");
}

function writeAction(status, os){
	var version = $('#version').val();
	var market = $('#market').val();
	var url = $('#url').val();

	if(status == 'cancel'){
		$('#' + os + '_version').html($('#' + os + '_hidden_version').val());
		$('#' + os + '_market').html($('#' + os + '_hidden_market').val());
		$('#' + os + '_url').html($('#' + os + '_hidden_url').val());
		$('#' + os).html("<a onclick='changeVersion(\"" + os + "\", \"" + $('#' + os + '_hidden_version').val() + "\", \"" + $('#' + os + '_hidden_market').val() + "\", \"" + $('#' + os + '_hidden_url').val() + "\")'>수정</a>");
	} else {
		if(version == ''){
			alert('버전을 입력해 주세요.');
			$('#version').focus();
			return;
		}
		if(market == ''){
			alert('마켓 버전을 입력해 주세요.');
			$('#market').focus();
			return;
		}
		if(url == ''){
			alert('경로를 입력해 주세요.');
			$('#url').focus();
			return;
		}
		
		if(confirm("수정하시겠습니까?")){
			var FORM_BODY = $('form[name=formBody]');
			FORM_BODY.submit();
		} else {
			$('#' + os + '_version').html($('#' + os + '_hidden_version').val());
			$('#' + os + '_market').html($('#' + os + '_hidden_market').val());
			$('#' + os + '_url').html($('#' + os + '_hidden_url').val());
			$('#' + os).html("<a onclick='changeVersion(\"" + os + "\", \"" + $('#' + os + '_hidden_version').val() + "\", \"" + $('#' + os + '_hidden_market').val() + "\", \"" + $('#' + os + '_hidden_url').val() + "\")'>수정</a>");
		}
	}
	flag = false;
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=logBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/version');
	FORM_BODY.submit();
}
</script>
</html>