<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../common/common.jsp"></jsp:include>
<html>
<head>
	<!-- start: Meta -->
	<meta charset="utf-8">
	<title>WEYS 통계</title>
	
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
				* 사용자 OS 통계(앱다운 / 회원가입)
			</div>
			<div class="refresh">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:12%"/>
					<col style="width:16%"/>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:16%"/>
					<col style="width:16%"/>
				</colgroup>
				<tr>
					<td></td>
					<c:forEach var="items" items="${resultList }">
						<td>${items.dt }</td>
					</c:forEach>
				</tr>
				<tr>
					<td>안드로이드</td>
					<c:forEach var="items" items="${resultList }">
						<td><fmt:formatNumber value="${items.andUsr }" groupingUsed="true"/></td>
					</c:forEach>
				</tr>
				<tr>
					<td>아이폰</td>
					<c:forEach var="items" items="${resultList }">
						<td><fmt:formatNumber value="${items.iosUsr }" groupingUsed="true"/></td>
					</c:forEach>
				</tr>
				<tr>
					<td>웹</td>
					<c:forEach var="items" items="${resultList }">
						<td><fmt:formatNumber value="${items.webUsr }" groupingUsed="true"/></td>
					</c:forEach>
				</tr>
				<tr>
					<td>총</td>
					<c:forEach var="items" items="${resultList }">
						<td><fmt:formatNumber value="${items.totalUsr }" groupingUsed="true"/></td>
					</c:forEach>
				</tr>
			</table>
			
			<canvas id="osChart" width="400" height="150" style="margin-top:20px;margin:auto;"></canvas>
			
			<div class="title">
				* 사용자 접속 통계
			</div>
			<div class="refresh">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:12%"/>
					<col style="width:16%"/>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:16%"/>
					<col style="width:16%"/>
				</colgroup>
				<tr>
					<td></td>
					<c:forEach var="items" items="${resultList }">
						<td>${items.dt }</td>
					</c:forEach>
				</tr>
				<tr>
					<td>접속</td>
					<c:forEach var="items" items="${resultList }">
						<td><fmt:formatNumber value="${items.logCnt }" groupingUsed="true"/></td>
					</c:forEach>
				</tr>
			</table>
			
			<canvas id="usrLoginChart" width="400" height="150" style="margin-top:20px;margin:auto;"></canvas>
			
			<div class="title">
				* 예약 수수료 통계
			</div>
			<div class="refresh">
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:20%"/>
					<col style="width:20%"/>
					<col style="width:30%"/>
				</colgroup>
				<tr>
					<td>월</td>
					<td>건수</td>
					<td>배송비(총/건당)</td>
					<td>수수료(총/건당)</td>
					<td>입금금액(총/건당)</td>
				</tr>
				<c:forEach var="items" items="${rsvList }">
					<tr>
						<td>${items.dttm }</td>
						<td><fmt:formatNumber value="${items.CNT }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.CMS }" groupingUsed="true"/> / <fmt:formatNumber value="${items.CMS/items.CNT }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.GET_AMNT - items.WON_BILL - items.CMS }" groupingUsed="true"/> / <fmt:formatNumber value="${(items.GET_AMNT - items.WON_BILL - items.CMS)/items.CNT }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.GET_AMNT }" groupingUsed="true"/> / <fmt:formatNumber value="${items.GET_AMNT/items.CNT }" groupingUsed="true"/></td>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.js"></script>
<script type="text/javascript">


$(function () {
	////////////////////////////////////////////////////
	// usrLoginChart start
	
	var ctx = document.getElementById("usrLoginChart");
	var usrLabel = ${loginChart.label};
	var usrTotal = ${loginChart.total};
	
	var lineData = {
			  labels: usrLabel,
			  datasets: [{
				    label: "Total",
				    data: usrTotal,
				    borderColor: "#000000",
			        fill: false
				  }]
			};

	var myUsrChart = new Chart(ctx, {
	    type: 'line',
	    data: lineData,
	    options: {
	    	title : {
	    		display : true,
	    		text : '월별 접속 통계'
	    	},
	    	events: false,
	        tooltips: {
	            enabled: false
	        },
	        hover: {
	            animationDuration: 0
	        },
	        animation: {
	            duration: 1,
	            onComplete: function () {
	                var chartInstance = this.chart,
	                    ctx = chartInstance.ctx;
	                ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
	                ctx.textAlign = 'center';
	                ctx.textBaseline = 'bottom';

	                this.data.datasets.forEach(function (dataset, i) {
	                    var meta = chartInstance.controller.getDatasetMeta(i);
	                    meta.data.forEach(function (bar, index) {
	                        var data = dataset.data[index];          
	                        ctx.fillStyle = '#000';
	                        ctx.fillText(data, bar._model.x, bar._model.y - 5);
	                    });
	                });
	            }
	        }
	    }
	});
	
	// usrLoginChart end
	////////////////////////////////////////////////////
	// osChart start

	var ctx = document.getElementById("osChart");
	var usrLabel = ${usrChart.label};
	var usrTotal = ${usrChart.total};
	var andUsr = ${usrChart.andUsr};
	var iosUsr = ${usrChart.iosUsr};
	var webUsr = ${usrChart.webUsr};
	
	var lineData = {
			  labels: usrLabel,
			  datasets: [{
				    label: "Total",
				    data: usrTotal,
				    borderColor: "#000000",
			        fill: false
				  },{
				    label: "AOS",
				    data: andUsr,
				    borderColor: "#86E57F",
			        fill: false
				  },
				  {
					    label: "IOS",
					    data: iosUsr,
					    borderColor: "#D95040",
				        fill: false
					  },
					  {
						    label: "WEB",
						    data: webUsr,
						    borderColor: "#4D9EFF",
					        fill: false
						  }]
			};

	var myUsrChart = new Chart(ctx, {
	    type: 'line',
	    data: lineData,
	    options: {
	    	title : {
	    		display : true,
	    		text : 'OS별 회원가입 통계'
	    	},
	    	events: false,
	        tooltips: {
	            enabled: false
	        },
	        hover: {
	            animationDuration: 0
	        },
	        animation: {
	            duration: 1,
	            onComplete: function () {
	                var chartInstance = this.chart,
	                    ctx = chartInstance.ctx;
	                ctx.font = Chart.helpers.fontString(Chart.defaults.global.defaultFontSize, Chart.defaults.global.defaultFontStyle, Chart.defaults.global.defaultFontFamily);
	                ctx.textAlign = 'center';
	                ctx.textBaseline = 'bottom';

	                this.data.datasets.forEach(function (dataset, i) {
	                    var meta = chartInstance.controller.getDatasetMeta(i);
	                    meta.data.forEach(function (bar, index) {
	                        var data = dataset.data[index];          
	                        ctx.fillStyle = '#000';
	                        ctx.fillText(data, bar._model.x, bar._model.y - 5);
	                    });
	                });
	            }
	        }
	    }
	});
	
	// osChart end
	////////////////////////////////////////////////////
	
});


</script>
</html>