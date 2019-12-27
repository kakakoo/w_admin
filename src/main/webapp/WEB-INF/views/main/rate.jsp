<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 환율비교</title>
	
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
				* 환율비교
			</div>
			<div class="refresh">
			</div>
			
			<table class="list_table" style="font-size:12px;">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:30%"/>
					<col style="width:30%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<td>통화</td>
					<td>매매기준율</td>
					<td>매입평균기준율</td>
					<td>차이</td>
				</tr>
				<c:forEach var="items" items="${surList }">
					<tr>
						<td>${items.UNIT }</td>
						<td><fmt:formatNumber value="${items.BASIC_RATE}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.RATE}" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.BASIC_RATE - items.RATE}" groupingUsed="true"/></td>
					</tr>
				</c:forEach>
			</table>
			
		</div>
		
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

</script>
</html>