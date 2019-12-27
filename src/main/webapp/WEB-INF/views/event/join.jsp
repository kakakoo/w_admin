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
	<title>WEYS 이벤트</title>
	
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
				* 참여 이벤트 현황 
			</div>
			<div class="refresh">
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/event/join" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
				<table class="search">
					<colgroup>
						<col style="width:30%"/>
						<col style="width:70%"/>
					</colgroup>
					<tr>
						<td>일자</td>
						<td>
							<input type="text" style="width: 200px;display:initial;" name="reservation" id="reservation" class="form-control" value="${paging.startDt } - ${paging.endDt }" />
						</td>
					</tr> 
				</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="goPage(${paging.pageNo}, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:5%"/>
					<col style="width:60%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:5%"/>
				</colgroup>
				<tr>
					<td>고객명</td>
					<td>건수</td>
					<td>이벤트 값</td>
					<td>등록일</td>
					<td>지급일</td>
					<td></td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td>${items.USR_NM }</td>
						<td>${items.CNT }</td>
						<td><nobr><font title="${items.EVENT_VAL }">${items.EVENT_VAL }</font></nobr></td>
						<td>${items.REG_DTTM }</td>
						<td>${items.CHK_DTTM }</td>
						<c:choose>
					       <c:when test="${items.CHK_CNT == 0 }">
					           <td onclick="sendCoupon('${items.USR_ID }', '${items.EVENT_ID }', '${items.JOIN_ID }')">지급</td>
					       </c:when>
					       <c:otherwise>
					           <td></td>
					       </c:otherwise>
					   </c:choose>
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
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/event/join');
	FORM_BODY.submit();
}

function sendCoupon(usrId, eventId, joinId){
	
	if(confirm('쿠폰을 지급하시겠습니까?')){
		var data = {};
		data["eventId"] = eventId;
		data["usrId"] = usrId;
		data["joinId"] = joinId;
		
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/api/event/join/sendCoupon",
			data: JSON.stringify(data),
			async : false,
			contentType: "application/json",
			success : function(result){
				var val = result.result;
				if(val == 'success'){
					alert('쿠폰이 발급되었습니다.');
					location.reload(true);
				} else {
					alert('다시 시도해주세요.');
				}
			},
			error : function(result){
				flag = false;
				alert('업데이트 실패, 에러');
				return;
			}
		});
	}
	
	
}
</script>
</html>