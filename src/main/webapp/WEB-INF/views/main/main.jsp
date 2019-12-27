<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"/>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS Main</title>
	
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
			<div class="my_main_content">
				<div class="title">
					* 현황 요약 
				</div>
				<div class="refresh">
					<i class="fa fa-refresh"></i><a href="${pageContext.request.contextPath}/api/main"> 새로고침</a> 
				</div>
				<table>
					<colgroup>
						<col style="width:30%"/>
						<col style="width:30%"/>
						<col style="width:40%"/>
					</colgroup>
					<tr>
						<td>구분</td>
						<td>상세</td>
						<td>내용</td>
					</tr>
<!-- 					가입자(명) -->
					<tr>
						<td rowspan="3">가입자(명)</td>
						<td>누적</td>
						<td style="text-align:right;">${totalUser }</td>
					</tr>
					<tr>
						<td>당일</td>
						<td style="text-align:right;">${todayUser }</td>
					</tr>
					<tr>
						<td>어제</td>
						<td style="text-align:right;">${yesUser }</td>
					</tr>
<!-- 					예약 건수 -->
					<tr>
						<td rowspan="5">예약 건수</td>
						<td>예약 완료</td>
						<td style="text-align:right;">${totalRsv }</td>
					</tr>
					<tr>
						<td>어제 예약 건수</td>
						<td style="text-align:right;">${yesRsv }</td>
					</tr>
					<tr>
						<td>오늘 예약 건수</td>
						<td style="text-align:right;">${todayRsvAll }</td>
					</tr>
					<tr>
						<td>오늘 남은 건수</td>
						<td style="text-align:right;">${todayRsvDone }</td>
					</tr>
					<tr>
						<td>오늘 예약 접수</td>
						<td style="text-align:right;">${todayRsv }</td>
					</tr>
				</table>
				
				<br><br>
				<div class="title">
					* 지점별 예약(내일부터 입금 완료건) <c:if test="${remainCnt > 0 }"><span style="color:red;"> * 미 수령건 존재</span></c:if>
				</div>
				<div class="refresh">
					 
				</div>
				<table>
					<colgroup>
						<col style="width:20%"/>
						<col style="width:10%"/>
						<col style="width:10%"/>
						<col style="width:10%"/>
						<col style="width:10%"/>
						<col style="width:10%"/>
						<col style="width:10%"/>
						<col style="width:10%"/>
						<col style="width:10%"/>
					</colgroup>
					<tr>
						<td>지점</td>
						<td>오늘(완/대)</td>
						<c:forEach var="dts" items="${rsvDts }">
							<td>${dts }</td>
						</c:forEach>
					</tr>
					<c:forEach var="items" items="${readyStore }">
						<tr>
							<td>${items.STORE_NM }</td>
							<c:forEach var="rsv" items="${items.rsvList }" varStatus="status">
								<td style="text-align:right;">
									<c:if test="${status.count == 1 }"><fmt:formatNumber value="${rsv.F_CNT }" groupingUsed="true"/> / </c:if>
									<fmt:formatNumber value="${rsv.CNT }" groupingUsed="true"/>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
				</table>
				
				
				<br><br>
				<div class="title">
					* 필요 외화(내일부터 입금 완료건) 
				</div>
				<div class="refresh">
					 
				</div>
				<table>
					<colgroup>
						<col style="width:9%"/>

						<col style="width:8%"/>
						<col style="width:5%"/>
						<col style="width:8%"/>
						<col style="width:5%"/>
						<col style="width:8%"/>
						<col style="width:5%"/>
						<col style="width:8%"/>
						<col style="width:5%"/>
						<col style="width:8%"/>
						<col style="width:5%"/>
						<col style="width:8%"/>
						<col style="width:5%"/>
						<col style="width:8%"/>
						<col style="width:5%"/>
					</colgroup>
					<tr>
						<td>외화</td>
						<c:forEach var="dts" items="${rsvDts }">
							<td colspan=2>${dts }</td>
						</c:forEach>
					</tr>
					<c:forEach var="items" items="${readyUnit }">
						<tr>
							<td>${items.UNIT }<br/><fmt:formatNumber value="${items.TOTAL_AMNT }" groupingUsed="true"/></td>
							<c:forEach var="rsv" items="${items.rsvList }">
								<td style="text-align:right;"><fmt:formatNumber value="${rsv.RSV_AMNT }" groupingUsed="true"/></td>
								<td style="text-align:right;"><fmt:formatNumber value="${rsv.R_CNT }" groupingUsed="true"/></td>
							</c:forEach>
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
</html>