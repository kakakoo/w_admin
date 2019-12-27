<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 결제 내역</title>
	
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
				* 멤버십 결제 내역
			</div>
			<div class="refresh">
<%-- 				<input type="button" class="btn btn-success" onclick="location.href='${pageContext.request.contextPath}/api/pay/cancel'" value="취소"> --%>
			</div>
			
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/pay" method="post">
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
							<input type="text" style="width: 200px;margin-left:10px;display:initial;" name="reservation" id="reservation" class="form-control" value="${paging.startDt } - ${paging.endDt }" />
						</td>
					</tr> 
					<tr>
						<td>검색</td>
						<td>
							<select name="searchType" style="border-color: #a7a9aa !important;width:15%;margin:auto;height:22px;">
								<option value="t" <c:if test="${paging.searchType == 't'}">selected</c:if>>전체</option>
								<option value="n" <c:if test="${paging.searchType == 'n'}">selected</c:if>>닉네임</option>
								<option value="e" <c:if test="${paging.searchType == 'e'}">selected</c:if>>이메일</option>
							</select>
							<input type="text" name="searchText" style="border-color: #a7a9aa !important;width:50%;margin:auto;height:22px;" value="${paging.searchText }">
						</td>
					</tr> 
				</table>
			</form>
			<div class="search_button">
				<input type="button" class="btn btn-success" onclick="goPage(${paging.pageNo}, 1)" style="width:20%;font-weight:700;" value="조회">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:15%"/>
					<col style="width:5%"/>
				</colgroup>
				<tr>
					<td>닉네임</td>
					<td>이메일</td>
					<td>결제내역</td>
					<td>결제금액</td>
					<td>결제상태</td>
					<td>결제수단</td>
					<td>결제일(취소일)</td>
					<td>취소</td>
				</tr>
				<c:forEach var="items" items="${resultList }">
					<tr>
						<td>${items.usrNick }</td>
						<td>${items.usrEmail }</td>
						<td>${items.payText }</td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.payAmnt }" groupingUsed="true"/></td>
						<td>${items.paySt }</td>
						<td>${items.payMethod }</td>
						<td>${items.regDttm }</td>
						<td>
							<c:if test="${items.paySt =='완료' }">
								<a style="cursor:pointer;" onclick="cancelMemberCheck('${items.id }', '${items.usrNick }')">취소</a>
							</c:if>
							<c:if test="${items.paySt =='취소대기' }">
								<a style="cursor:pointer;color:blue;" onclick="returnMemberCheck('${items.id }', '${items.usrNick }', '${items.rbankNm }', '${items.rbankCd }', '${items.rbankHolder }', '${items.cancelDttm }')">환불</a>
							</c:if>
						</td>
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
<input type="hidden" id="pay_id">
<div class="dialog_back"></div>
<div class="dialog" id="input_info">
	<div class="dialog_body">
		<div class="dialog_title">환불 정보 입력</div>
		<table>
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>닉네임</td>
					<td id="input_dialog_nick"></td>
				</tr>
				<tr>
					<td>환불받을 은행</td>
					<td><input type="text" id="return_bank"></td>
				</tr>
				<tr>
					<td>은행 계좌</td>
					<td><input type="text" id="return_bank_cd"></td>
				</tr>
				<tr>
					<td>입금주명</td>
					<td><input type="text" id="return_bank_holder"></td>
				</tr>
		</table>
		<div class="dialog_button">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="cancelMember()" value="환불 등록">
		</div>
	</div>
</div>
<div class="dialog" id="display_info">
	<div class="dialog_body">
		<div class="dialog_title">환불 정보</div>
		<table style="margin-top:30px;">
			<colgroup>
					<col style="width:30%"/>
					<col style="width:70%"/>
				</colgroup>
				<tr>
					<td>닉네임</td>
					<td id="dis_dialog_nick"></td>
				</tr>
				<tr>
					<td>환불받을 은행</td>
					<td id="dis_rbank_nm"></td>
				</tr>
				<tr>
					<td>은행 계좌</td>
					<td id="dis_rbank_cd"></td>
				</tr>
				<tr>
					<td>입금주명</td>
					<td id="dis_rbank_holder"></td>
				</tr>
				<tr>
					<td>취소 요청일</td>
					<td id="dis_cancel_dttm"></td>
				</tr>
		</table>
		<div class="dialog_button"  style="margin-top:25px;">
			<input type="button" class="btn btn-dark" onclick="closeDialog()" value="취소">
			<input type="button" class="btn btn-success" onclick="returnComplete()" value="환불 완료">
		</div>
	</div>
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">

function goPage(pageNo, isSearch){
	var FORM_BODY = $('form[name=formBody]');

	$('#pageNo').val(pageNo);
	$('#isSearch').val(isSearch);
	FORM_BODY.attr('action', '${pageContext.request.contextPath}/api/pay');
	FORM_BODY.submit();
}

function cancelMemberCheck(id, nick){
	if(confirm("정말 취소하시겠습니까?")){
		var data = {};
		data["id"] = id;
		
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/pay/cancelCheck",
			data:JSON.stringify(data),
			success : function(result){
				if(result.flag == 'T'){
					$('#pay_id').val(id);
					$('#input_dialog_nick').html(nick);
					$('.dialog_back').css('display', 'block');
					$('#input_info').css('display', 'block');
				} else {
					alert('멤버십 포인트가 사용되었습니다.');
					return ;
				}
			},
			error : function(result){
				alert('다시 시도해 주세요.');
				return ;
			}
		});
	}
}

function cancelMember(){
	var id = $('#pay_id').val();
	var bankNm = $('#return_bank').val();
	var bankCd = $('#return_bank_cd').val();
	var bankHolder = $('#return_bank_holder').val();
	
	var data = {};
	data["id"] = id;
	data["bankNm"] = bankNm;
	data["bankCd"] = bankCd;
	data["bankHolder"] = bankHolder;
	
	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/pay/cancelMember",
		data:JSON.stringify(data),
		success : function(result){
			if(result.result == 'success'){
				location.reload(true);
			} else {
				alert('다시 시도해 주세요.');
				return ;
			}
		},
		error : function(result){
			alert('다시 시도해 주세요.');
			return ;
		}
	});
}

function returnMemberCheck(id, nick, rbankNm, rbankCd, rbankHolder, cancelDttm){
	$('#pay_id').val(id);
	$('#dis_dialog_nick').html(nick);
	$('#dis_rbank_nm').html(rbankNm);
	$('#dis_rbank_cd').html(rbankCd);
	$('#dis_rbank_holder').html(rbankHolder);
	$('#dis_cancel_dttm').html(cancelDttm);

	$('.dialog_back').css('display', 'block');
	$('#display_info').css('display', 'block');
}

function returnComplete(){
	if(confirm("입금 완료 하셨습니까?")){
		var data = {};
		data["id"] = $('#pay_id').val();
		
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/pay/returnComplete",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == 'success'){
					location.reload(true);
				} else {
					alert('다시 시도해 주세요.');
					return ;
				}
			},
			error : function(result){
				alert('다시 시도해 주세요.');
				return ;
			}
		});
	}
}

function closeDialog(){
	$('.dialog_back').css('display', 'none');
	$('.dialog').css('display', 'none');
}
</script>
</html>