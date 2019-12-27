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
	<title>WEYS 사용자</title>
	
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
				* 사용자 현황 
			</div>
			<div class="refresh">
			</div>
			
		<form name="formBody" action="${pageContext.request.contextPath}/api/user" method="post">
			<input type="hidden" name="pageNo" id="pageNo">
			<input type="hidden" name="isSearch" id="isSearch">
			<input type="hidden" name="grade" id="grade">
			<input type="hidden" name="dateType" value="J">
			<table class="search">
				<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>가입 일자</td>
					<td>
<%-- 						<input type="radio" style="margin: 4px 4px 0px;" name="dateType" value="J" <c:if test="${paging.dateType == 'J'}">checked</c:if>> 가입 --%>
						<input type="text" style="width: 200px;margin-left:10px;display:initial;" name="reservation" id="reservation" class="form-control" value="${paging.startDt } - ${paging.endDt }" />
					</td>
				</tr> 
				<tr>
					<td>사용자 등급</td>
					<td>
						<input type="checkbox" name="usrGrade" id="allCheck" value="T" <c:if test="${empty paging.listData}">checked</c:if>>전체
						<input type="checkbox" name="usrGrade" value="N" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'N')}">checked</c:if>> 일반
						<input type="checkbox" name="usrGrade" value="S" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'S')}">checked</c:if>> 특별
						<input type="checkbox" name="usrGrade" value="B" <c:if test="${empty paging.listData}">checked</c:if><c:if test="${fn:contains(paging.listData, 'B')}">checked</c:if>> 불량
					</td>
				</tr> 
				<tr>
					<td>검색</td>
					<td>
						<select name="searchType" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;">
							<option value="t" <c:if test="${paging.searchType == 't'}">selected</c:if>>전체</option>
							<option value="n" <c:if test="${paging.searchType == 'n'}">selected</c:if>>이름</option>
							<option value="e" <c:if test="${paging.searchType == 'e'}">selected</c:if>>이메일</option>
							<option value="p" <c:if test="${paging.searchType == 'p'}">selected</c:if>>전화번호</option>
						</select>
						<input type="text" name="searchText" style="border-color: #a7a9aa !important;width:50%;margin:auto;height:22px;" value="${paging.searchText }">
					</td>
				</tr>
			</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="goPage(${paging.pageNo}, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>
			
			<div class="title">
			</div>
			<div class="refresh" style="padding:1% 0 1% 1%;">
				<input type="button" onclick="goExcel()" class="btn btn-success" value="엑셀 다운로드">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:12.5%"/>
					<col style="width:7.5%"/>
					<col style="width:12.5%"/>
					<col style="width:22.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>가입일</td>
					<td>플렛폼</td>
					<td>이름</td>
					<td>이메일</td>
					<td>연락처</td>
					<td>예약(중/완료)</td>
					<td>보유쿠폰</td>
					<td>회원등급</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr onclick="location.href='${pageContext.request.contextPath}/api/user/${items.usrId }/detail'">
						<td><nobr><font title="${items.regDttm }">${items.regDttm }</font></nobr></td>
						<td><nobr><font title="${items.usrFrom }">${items.usrFrom }</font></nobr></td>
						<td><nobr><font title="${items.usrNm }">${items.usrNm }</font></nobr></td>
						<td><nobr><font title="${items.usrEmail }">${items.usrEmail }</font></nobr></td>
						<td><nobr><font title="${items.usrTel }">${items.usrTel }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.rCnt } / ${items.rdCnt }">${items.rCnt } / ${items.rdCnt }</font></nobr></td>
						<td style="text-align:right;"><nobr><font title="${items.cCnt }">${items.cCnt }</font></nobr></td>
						<td><nobr><font title="${items.usrGrade }">${items.usrGrade }</font></nobr></td>
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
	$('#allCheck').click(function(){
		if($("#allCheck").prop("checked")) {
			$("input[name=usrGrade]").prop("checked",true);
		} else {
			$("input[name=usrGrade]").prop("checked",false);
		}
	});
	
	$("input[name=usrGrade]").change(function() {
        if($('input[name=usrGrade]:checked').length == 4){
        	$('#allCheck').prop("checked",true);
        } else if($('input[name=usrGrade]:checked').length == 3 && !$("#allCheck").prop("checked")){
        	$('#allCheck').prop("checked",true);
        } else {
        	$('#allCheck').prop("checked",false);
        }
    });
});

function goExcel(){
	var FORM_BODY = $('form[name=formBody]');

	var grade = '';
	$('input:checkbox[name="usrGrade"]').each(function() {
		if(this.checked){//checked 처리된 항목의 값
			if(grade == ''){
				grade = this.value;
			}else if(grade == 'T'){}
			else{
				grade = grade + "," + this.value;
			}
		}
	});

	$('#grade').val(grade);
	$('#pageNo').val(0);
	$('#isSearch').val(0);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/user/excel');
	FORM_BODY.submit();
}

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	var grade = '';
	$('input:checkbox[name="usrGrade"]').each(function() {
		if(this.checked){//checked 처리된 항목의 값
			if(grade == ''){
				grade = this.value;
			}else if(grade == 'T'){}
			else{
				grade = grade + "," + this.value;
			}
		}
	});

	$('#grade').val(grade);
	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/user');
	FORM_BODY.submit();
}
</script>
</html>