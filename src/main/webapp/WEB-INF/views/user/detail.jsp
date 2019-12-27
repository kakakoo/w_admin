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
				* 사용자 현황 > ${info.usrNm }
			</div>
			<div class="refresh">
			
			</div>
			
			<form name="formBody" action="${pageContext.request.contextPath}/api/user/${info.usrId}/detail" method="post">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="isSearch" id="isSearch">
			</form>
			<form name="excelBody" action="${pageContext.request.contextPath}/api/user/${info.usrId}/excel" method="post">
			</form>
			
			<table class="list_table">
				<colgroup>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
					<col style="width:12.5%"/>
				</colgroup>
				<tr>
					<td>가입일</td>
					<td>이름</td>
					<td>이메일</td>
					<td>연락처</td>
					<td>예약(중/완료)</td>
					<td>보유한도</td>
					<td>보유쿠폰</td>
					<td>회원등급</td>
				</tr>
				<tr>
					<td>${info.regDttm }</td>
					<td>${info.usrNm }</td>
					<td>${info.usrEmail }</td>
					<td>${info.usrTel }</td>
					<td>${info.rCnt } / ${info.rdCnt }</td>
					<td style="text-align:right;"><fmt:formatNumber>${info.cost }</fmt:formatNumber></td>
					<td style="text-align:right;">${info.cCnt }</td>
					<td>
						<select name="usrGrade" id="usrGrade" style="border-color: #a7a9aa !important;width:50%;margin:auto;height:22px;">
							<option value="N" <c:if test="${info.usrGrade == 'N'}">selected</c:if>>일반</option>
							<option value="S" <c:if test="${info.usrGrade == 'S'}">selected</c:if>>특별</option>
							<option value="B" <c:if test="${info.usrGrade == 'B'}">selected</c:if>>불량</option>
						</select>
					</td>
				</tr>
			</table>
			
			<ul class="tabs">
		        <li class="active" rel="tab1">거래내역</li>
		        <li rel="tab3">쿠폰내역</li>
		        <li rel="tab4">발송내역</li>
		        <li rel="tab5">접속내역</li>
		        <li rel="tab6">상담내역</li>
		        <li rel="tab7">SMS전송</li>
		    </ul>

		    <div class="tab_container">
		        <div id="tab1" class="tab_content">
		           <table class="list_table">
						<colgroup>
							<col style="width:11%"/>
							<col style="width:14%"/>
							<col style="width:9%"/>
							<col style="width:11%"/>
							<col style="width:11%"/>
							<col style="width:11%"/>
							<col style="width:11%"/>
							<col style="width:12%"/>
							<col style="width:10%"/>
						</colgroup>
						<tbody id="tab1_list">
						</tbody>
					</table>
		        </div>
		        <div id="tab3" class="tab_content">
		        	<table class="list_table">
						<colgroup>
							<col style="width:14%"/>
							<col style="width:14%"/>
							<col style="width:14%"/>
							<col style="width:14%"/>
							<col style="width:12%"/>
							<col style="width:12%"/>
						</colgroup>
						<tbody id="tab3_list">
						</tbody>
					</table>
		        </div>
		        <div id="tab4" class="tab_content">
		        	<table class="list_table">
						<colgroup>
							<col style="width:15%"/>
							<col style="width:35%"/>
							<col style="width:25%"/>
							<col style="width:25%"/>
						</colgroup>
						<tbody id="tab4_list">
						</tbody>
					</table>
		        </div>
		        <div id="tab5" class="tab_content">
		        	<table class="list_table">
						<colgroup>
							<col style="width:100%"/>
						</colgroup>
						<tbody id="tab5_list">
						</tbody>
					</table>
		        </div>
		        <div id="tab6" class="tab_content">
		        	<div>
		        		<div><textarea id="memo_txt" style="width:100%;resize:none;" rows="5"></textarea></div>
		        		<div>
		        			<input type="text" id="admin" placeholder="상담자"><input type="button" onclick="insertMemo()" class="btn btn-success" value="상담 등록">
		        		</div>
		        	</div>
		        	<table class="list_table">
						<colgroup>
							<col style="width:60%"/>
							<col style="width:20%"/>
							<col style="width:20%"/>
						</colgroup>
						<tbody id="tab6_list">
						</tbody>
					</table>
		        </div>
		        <div id="tab7" class="tab_content">
		        	<div>
		        		<div><textarea id="sms_txt" style="width:100%;resize:none;" rows="5"></textarea></div>
		        		<div>
		        			<input type="button" onclick="insertSMS()" class="btn btn-success" value="전송">
		        		</div>
		        	</div>
		        	<table class="list_table">
						<colgroup>
							<col style="width:60%"/>
							<col style="width:20%"/>
							<col style="width:20%"/>
						</colgroup>
						<tbody id="tab7_list">
						</tbody>
					</table>
		        </div>
		    </div>
			
			
		</div>
		
			
		<!-- 내용 끝 -->
		</div>
	</div>
</div>
</body>

<jsp:include page="../common/commonjs.jsp"/>
<script type="text/javascript">
var tab1_html = '<tr><td>거래코드</td><td>거래일</td><td>화폐</td><td>외화</td><td>매매기준율</td><td>보너스(%)</td><td>배송료</td><td>결제금액</td><td>상태</td></tr>';
var tab3_html ='<tr><td>쿠폰 종류</td><td>쿠폰명</td><td>상태</td><td>등록일</td><td>사용일</td><td>종료일</td></tr>';
var tab4_html ='<tr><td>발송 종류</td><td>메시지</td><td>템플릿</td><td>등록일</td></tr>';
var tab5_html ='<tr><td>접속 시간</td></tr>';
var tab6_html ='<tr><td>상담 내용</td><td>상담자</td><td>상담 시간</td></tr>';
var tab7_html ='<tr><td>SMS 내용</td><td>전송자</td><td>전송 시간</td></tr>';
$(function () {
	$(".tab_content").hide();
    $(".tab_content:first").show();

    $("ul.tabs li").click(function () {
        $("ul.tabs li").removeClass("active").css("color", "#333");
        //$(this).addClass("active").css({"color": "darkred","font-weight": "bolder"});
        $(this).addClass("active").css("color", "darkred");
        $(".tab_content").hide()
        var activeTab = $(this).attr("rel");
        $("#" + activeTab).fadeIn();
        
        getList(activeTab);
    });
    

	$("#usrGrade").change(function() {
		
		var data = {};
		data["grade"] = this.value;
		data["usrId"] = ${info.usrId};
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/user/change/usrGrade",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == 'success'){
					alert('등급이 변경되었습니다.');
				} else {
					alert('다시 시도해 주세요.');
				}
			},
			error : function(result){
				alert('다시 시도해 주세요.');
			}
		});
    }); 
	
    getList('tab1');
});

function insertSMS(){

	var memo = $('#sms_txt').val();
	
	if(memo != ''){
		var data = {};
		data["memo"] = memo;
		data["usrId"] = ${info.usrId};
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/user/insertSMS",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == 'success'){
					alert('상담내역이 등록되었습니다.');
					getList('tab7');
					$('#sms_txt').val('');
				} else if(result.result == 'no Permission'){
					alert('권한이 없습니다.');
				} else {
					alert('다시 시도해 주세요.');
				}
			},
			error : function(result){
				alert('다시 시도해 주세요.');
			}
		});
	}
}

function insertMemo(){

	var memo = $('#memo_txt').val();
	var admin = $('#admin').val();
	if(admin == ''){
		alert('상담자를 입력해주세요.');
		$('#admin').focus();
		return;
	}
	if(memo != ''){
		var data = {};
		data["memo"] = memo;
		data["admin"] = admin;
		data["usrId"] = ${info.usrId};
		$.ajax({
			contentType : "application/json",
			dataType : "json",
			type:"post",
			url:"${pageContext.request.contextPath}/api/user/insertMemo",
			data:JSON.stringify(data),
			success : function(result){
				if(result.result == 'success'){
					alert('상담내역이 등록되었습니다.');
					getList('tab6');
					$('#memo_txt').val('');
				} else {
					alert('다시 시도해 주세요.');
				}
			},
			error : function(result){
				alert('다시 시도해 주세요.');
			}
		});
	}
}

function getList(activeTab){
	$('#' + activeTab + '_list tr').remove();
	var data = {};
	data["tab"] = activeTab;
	data["usrId"] = ${info.usrId};
	$.ajax({
		contentType : "application/json",
		dataType : "json",
		type:"post",
		url:"${pageContext.request.contextPath}/api/user/detail/list",
		data:JSON.stringify(data),
		success : function(result){
			if(result.result == 'success'){
				var list = result.list;
				
				if(activeTab == 'tab1'){
					listTab1(list);
				} else if(activeTab == 'tab3'){
					listTab3(list);
				} else if(activeTab == 'tab4'){
					listTab4(list);
				} else if(activeTab == 'tab5'){
					listTab5(list);
				} else if(activeTab == 'tab6'){
					listTab6(list);
				} else if(activeTab == 'tab7'){
					listTab7(list);
				}
				
			} else {
				alert('다시 시도해 주세요.');
			}
		},
		error : function(result){
			alert('다시 시도해 주세요.');
		}
	});
}

function listTab1(list){
	var html = tab1_html;
	
	for(i=0 ; i<list.length ; i++){
		var no = list[i].RSV_NO;
		var dt = list[i].RSV_DT;
		var unit = list[i].UNIT;
		var amnt = list[i].RSV_AMNT;
		var rateWeys = list[i].BASIC_RATE_WEYS;
		var weysBonus = list[i].WEYS_BONUS;
		var cms = list[i].CMS;
		var getAmnt = list[i].GET_AMNT;
		var rsvSt = list[i].RSV_ST;
		
		if(rsvSt == 'S'){
			rsvSt = '신청완료';
		} else if(rsvSt == 'R'){
			rsvSt = '준비완료';
		} else if(rsvSt == 'F'){
			rsvSt = '거래완료';
		} else if(rsvSt == 'CB'){
			rsvSt = '입금전 취소';
		}  else if(rsvSt == 'C'){
			rsvSt = '취소신청';
		} else if(rsvSt == 'CR'){
			rsvSt = '취소준비';
		} else if(rsvSt == 'CF'){
			rsvSt = '환불완료';
		} else if(rsvSt == 'M'){
			rsvSt = '시간만료';
		} 

		html += '<tr>';
		html += '<td>' + no + '</td>';
		html += '<td>' + dt + '</td>';
		html += '<td>' + unit + '</td>';
		html += '<td>' + amnt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
		html += '<td>' + rateWeys.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
		html += '<td>' + weysBonus.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
		html += '<td>' + cms.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
		html += '<td>' + getAmnt.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</td>';
		html += '<td>' + rsvSt + '</td>';
		html += '</tr>';
	}
	
	$('#tab1_list').append(html);
}

function listTab3(list){
	var html = tab3_html;
	
	for(i=0 ; i<list.length ; i++){
		var tp = list[i].COUPON_TP;
		var nm = list[i].COUPON_NM;
		var st = list[i].USE_ST;
		var regDttm = list[i].REG_DTTM;
		var useDt = list[i].USE_DT;
		var endDt = list[i].END_DT;
		
		if(tp == 'B'){
			tp = '음료쿠폰';
		} else if(tp == 'DD' || tp == 'DR'){
			tp = '배송쿠폰';
		} else if(tp == 'P'){
			tp = '보너스쿠폰';
		}

		if(st == 'Y'){
			st = '사용가능';
		} else if(st == 'D'){
			st = '사용함';
		} else if(st == 'O'){
			st = '기간지남';
		}

		html += '<tr>';
		html += '<td>' + tp + '</td>';
		html += '<td>' + nm + '</td>';
		html += '<td>' + st + '</td>';
		html += '<td>' + regDttm + '</td>';
		html += '<td>' + useDt + '</td>';
		html += '<td>' + endDt + '</td>';
		html += '</tr>';
	}
	
	$('#tab3_list').append(html);
}

function listTab4(list){
	var html = tab4_html;
	
	for(i=0 ; i<list.length ; i++){
		var tp = '알림톡';
		var msg = list[i].MSG.replace(/\n/g, '<br/>');
		var templete = list[i].TEMPLETE;
		var regDttm = list[i].REG_DTTM;

		html += '<tr>';
		html += '<td>' + tp + '</td>';
		html += '<td style="text-align: left;font-size: 11px;">' + msg + '</td>';
		html += '<td>' + templete + '</td>';
		html += '<td>' + regDttm + '</td>';
		html += '</tr>';
	}
	
	$('#tab4_list').append(html);
}

function listTab5(list){
	var html = tab5_html;
	
	for(i=0 ; i<list.length ; i++){
		var regDttm = list[i].CONN_DTTM;

		html += '<tr>';
		html += '<td>' + regDttm + '</td>';
		html += '</tr>';
	}
	
	$('#tab5_list').append(html);
}

function listTab6(list){
	var html = tab6_html;
	
	for(i=0 ; i<list.length ; i++){
		var memo = list[i].MEMO.replace(/\n/g, '<br/>');
		var regDttm = list[i].REG_DTTM;
		var admin = list[i].ADMIN;

		html += '<tr>';
		html += '<td style="text-align:left;">' + memo + '</td>';
		html += '<td>' + admin + '</td>';
		html += '<td>' + regDttm + '</td>';
		html += '</tr>';
	}
	
	$('#tab6_list').append(html);
}

function listTab7(list){
	var html = tab7_html;
	
	for(i=0 ; i<list.length ; i++){
		var memo = list[i].SMS_INFO.replace(/\n/g, '<br/>');
		var regDttm = list[i].REG_DTTM;
		var admin = list[i].ADMIN_NAME;

		html += '<tr>';
		html += '<td style="text-align:left;">' + memo + '</td>';
		html += '<td>' + admin + '</td>';
		html += '<td>' + regDttm + '</td>';
		html += '</tr>';
	}
	
	$('#tab7_list').append(html);
}

</script>
</html>