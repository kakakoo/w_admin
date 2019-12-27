<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="./common/common.jsp"></jsp:include>

<html>
<head>
<meta charset="utf-8">
<title>WEYS Admin</title>
<!-- start: Mobile Specific -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/img/favicon.ico">
<!-- end: Favicon -->

</head>
<body class="my_user_content" style="background:#fff;">
	<div>
		<table class="list_table">
			<colgroup>
				<col style="width: 10%" />
				<col style="width: 18%" />
				<col style="width: 18%" />
				<col style="width: 18%" />
				<col style="width: 18%" />
				<col style="width: 18%" />
			</colgroup>
			<tr>
				<td>외화</td>
				<td>매매기준율</td>
				<td>살떄</td>
				<td>팔때</td>
				<td>팔때(80%)</td>
				<td>기준시간</td>
			</tr>
			<c:forEach var="items" items="${info }">
				<tr>
					<td>${items.UNIT }</td>
					<td><fmt:formatNumber value="${items.BASIC_RATE }" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${items.BUY }" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${items.SELL }" groupingUsed="true"/></td>
					<td>
						<c:choose>
							<c:when test="${items.UNIT == 'JPY' || items.UNIT == 'USD' }"><fmt:formatNumber value="${items.SELL8 }" groupingUsed="true"/></c:when>
							<c:otherwise>-</c:otherwise>
						</c:choose>
					</td>
					<td>${items.DTTM }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
<jsp:include page="./common/commonjs.jsp"></jsp:include>

<script type="text/javascript">
	$(function() {
		var interval2 = setInterval(function(){
	        location.reload(true);
	    }, 60000);
	});
</script>
</html>
