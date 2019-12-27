<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
<!-- start: Meta -->
<meta charset="utf-8">
<title>비밀번호 변경</title>

<!-- start: Mobile Specific -->
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">

			<c:if test="${adminTp == 'S' or adminTp == 'M'}"><jsp:include page="../common/left.jsp" /></c:if>
			<c:if test="${adminTp == 'A'}"><jsp:include page="../common/manage_left.jsp" /></c:if>
			<jsp:include page="../common/header.jsp" />

			<div class="right_col" role="main">
				<!-- 내용 시작  -->
				<div class="my_user_content">

					<table class="password_cp">
						<colgroup>
							<col style="width: 30%" />
							<col style="width: 70%" />
						</colgroup>
						<tr>
							<td>기존 비밀번호</td>
							<td><input type="password" id="old_pwd"></td>
						</tr>
						<tr>
							<td>새로운 비밀번호</td>
							<td><input type="password" id="new_pwd"
								placeholder="영어 대소문자, 숫자, 특수문자 포함 자리 이상"></td>
						</tr>
						<tr>
							<td>새로운 비밀번호 확인</td>
							<td><input type="password" id="new_pwd_chk" onkeypress="if( event.keyCode==13 ){changePwd();}" ></td>
						</tr>
						<tr>
							<td style="text-align: center;" colspan="2"><input
								type="button" class="btn btn-success" onclick="changePwd()" 
								value="변경"></td>
						</tr>
					</table>

				</div>

				<!-- 내용 끝 -->
			</div>
		</div>
	</div>

</body>

<jsp:include page="../common/commonjs.jsp" />
<script type="text/javascript">

$(function() {
	var tover = '${tover}';
	if(tover == 'true'){
		alert('비밀번호 유지기간이 지났습니다. 재설정 해주세요.');
	}
});

function changePwd(){

	var oldPwd = $('#old_pwd').val();
	var pwd = $('#new_pwd').val();
	var pwdChk = $('#new_pwd_chk').val();
	
	if(pwd != pwdChk){
		alert('새로운 비밀번호가 일치하지 않습니다.');
		$('#new_pwd').val('');
		$('#new_pwd_chk').val('');
		$('#new_pwd').focus();
		return;
	}
	
	 var num = pwd.search(/[0-9]/g);
	 var eng = pwd.search(/[a-z]/ig);
	 var spe = pwd.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	 if(pwd.length < 8 || pwd.length > 20){

	  	alert("8자리 ~ 20자리 이내로 입력해주세요.");

		$('#new_pwd').val('');
		$('#new_pwd_chk').val('');
		$('#new_pwd').focus();
	  return;
	 }

	 if(pwd.search(/₩s/) != -1){
	  alert("비밀번호는 공백업이 입력해주세요.");
		$('#new_pwd').val('');
		$('#new_pwd_chk').val('');
		$('#new_pwd').focus();
	  return;

	 } 
	 if(num < 0 || eng < 0 || spe < 0 ){
	  alert("영문,숫자, 특수문자를 혼합하여 입력해주세요.");
		$('#new_pwd').val('');
		$('#new_pwd_chk').val('');
		$('#new_pwd').focus();
	  return;
	 }
	
	var data = {};
	data["oldPwd"] = oldPwd;
	data["pwd"] = pwd;
	
	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/cpchk",
		data:JSON.stringify(data),
		success : function(result){
			
			var res = result.result;
			
			if(res == 'true'){
				alert('변경되었습니다.');
				$('#old_pwd').val('');
				$('#new_pwd').val('');
				$('#new_pwd_chk').val('');
			} else {
				var msg = result.msg;
				alert(msg);
				$('#old_pwd').val('');
				$('#new_pwd').val('');
				$('#new_pwd_chk').val('');
				$('#old_pwd').focus();
			}

		},
		error : function(result){
			
		}
	});
}

</script>
</html>