<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<jsp:include page="./common/common.jsp"></jsp:include>

<html>
<head>
	<meta charset="utf-8">
	<title>WEYS Admin</title>
	<!-- start: Mobile Specific -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
	<!-- end: Favicon -->
		
</head>
<body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
          <form action="${pageContext.request.contextPath}/login" method="post">
              <h1><img src="${pageContext.request.contextPath}/resources/img/ic_splash_logo.png"></h1>
              <div>
                <input class="form-control" name="username" id="username" type="text" placeholder="아이디" onkeypress="if( event.keyCode==13 ){goPage();}" value="${id }"/>
              </div>
              <div>
                <input class="form-control" name="password" id="password" type="password" placeholder="비밀번호" onkeypress="if( event.keyCode==13 ){goPage();}" value="" />
              </div>
              <div>

                <a class="btn btn-default submit" onclick="goPage()">로그인</a>
              </div>

              <div class="clearfix"></div>
              <div class="separator">

                <div class="clearfix"></div>
                  <input type="checkbox" id="remember"/>아이디 저장
                <br />

                <div>
                  <h1></h1>
                  <p>©2017 All Rights Reserved. </p>
                </div>
              </div>
            </form>
          </section>
        </div>

      </div>
    </div>
  </body>
<jsp:include page="./common/commonjs.jsp"></jsp:include>

<script type="text/javascript">
$(function () {
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/getCookie",
		data:{},
		success : getSuccess
	});
	
	
	$('#username').focus();
});

function getSuccess(result){
	var id = result.id;
	if(id != ''){
		$("input:checkbox[id='remember']").prop("checked", true);
		$("input:checkbox[id='remember']").parents("span").addClass("checked");
		$('#username').val(id);
	}
}

function goPage(){
	var id = $('#username').val();
	var pwd = $('#password').val();
	var chk = $("input:checkbox[id='remember']").is(":checked");
	
	if(id == ''){
		alert('아이디를 입력해주세요.');
		$('#username').focus();
		return;
	}
	if(pwd == ''){
		alert('비밀번호를 입력해주세요.');
		$('#password').focus();
		return;
	}
	
	var data = {};
	data["id"] = id;
	data["pwd"] = pwd;
	data["chk"] = chk;
	
	$.ajax({
		type:"post",
		url:"${pageContext.request.contextPath}/login",
		data: JSON.stringify(data),
		contentType: "application/json",
		success : loginSuccess,
		error : loginError
	});
}

function loginSuccess(result){
	var resultCode = result.resultCode;
	var resultMsg = result.resultMsg;
	
	if(resultCode == 'success'){
		location.href='${pageContext.request.contextPath}/api/main';
	} else if(resultCode == 'success_admin'){
		location.href='${pageContext.request.contextPath}/api/manage/main';
	} else if(resultCode == 'success_manager'){
		location.href='${pageContext.request.contextPath}/api/rsv/list/pt';
	} else {
		alert(resultMsg);
	}
}

function loginError(result){
	alert('다시 시도해 주십시오.');
}

</script>
</html>
