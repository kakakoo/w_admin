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
				* 환전소 통계
			</div>
			<div class="refresh" style="font-size:10px;padding-top:30px;font-weight:0;">
				* 구매/판매 는 고객 입장의 상황
			</div>
			
			<table class="list_table">
				<colgroup>
					<col style="width:10%"/>
					<col style="width:10%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:10%"/>
					<col style="width:15%"/>
					<col style="width:15%"/>
					<col style="width:10%"/>
				</colgroup>
				<tr>
					<td>국가</td>
					<td>구매한 횟수</td>
					<td>구매한 외화</td>
					<td>구매한 한화(₩)</td>
					<td>판매한 횟수</td>
					<td>판매한 외화</td>
					<td>판매한 한화(₩)</td>
					<td>총 거래</td>
				</tr>
				<c:set var="totSum" value = "0"/>
				<c:set var="buySum" value = "0"/>
				<c:set var="buyKor" value = "0"/>
				<c:set var="sellSum" value = "0"/>
				<c:set var="sellKor" value = "0"/>
				<c:forEach var="items" items="${excList }">
					<tr>
						<td>${items.unit }</td>
						<td><fmt:formatNumber value="${items.buyCnt }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.buySum }" groupingUsed="true"/> ${items.unitDp }</td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.buyKor }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.sellCnt }" groupingUsed="true"/></td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.sellSum }" groupingUsed="true"/> ${items.unitDp }</td>
						<td style="text-align:right;"><fmt:formatNumber value="${items.sellKor }" groupingUsed="true"/></td>
						<td><fmt:formatNumber value="${items.totalCnt }" groupingUsed="true"/></td>
					</tr>
					<c:set var="totSum" value = "${totSum + items.totalCnt }"/>
					<c:set var="buySum" value = "${buySum + items.buyCnt }"/>
					<c:set var="buyKor" value = "${buyKor + items.buyKor }"/>
					<c:set var="sellSum" value = "${sellSum + items.sellCnt }"/>
					<c:set var="sellKor" value = "${sellKor + items.sellKor }"/>
				</c:forEach>
				<tr>
					<td>TOTAL</td>
					<td><fmt:formatNumber value="${buySum }" groupingUsed="true"/></td>
					<td style="text-align:right;">-</td>
					<td style="text-align:right;"><fmt:formatNumber value="${buyKor }" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${sellSum }" groupingUsed="true"/></td>
					<td style="text-align:right;">-</td>
					<td style="text-align:right;"><fmt:formatNumber value="${sellKor }" groupingUsed="true"/></td>
					<td><fmt:formatNumber value="${totSum }" groupingUsed="true"/></td>
				</tr>
			</table>
			
			<canvas id="excChart" width="400" height="150" style="margin-top:20px;margin:auto;"></canvas>
			
			<table style="width:100%;margin-top:20px;">
				<colgroup>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
				</colgroup>
				<tr style="text-align:center;">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td style="color:#FF0000;font-weight:900;">USD</td>
					<td style="color:#C72FB3;font-weight:900;">JPY</td>
					<td style="color:#FFE400;font-weight:900;">EUR</td>
					<td style="color:#1DDB16;font-weight:900;">CNY</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			<canvas id="excMonthlyData" width="400" height="150" style="margin-top:20px;margin:auto;"></canvas>
			
			<table style="width:100%;margin-top:20px;">
				<colgroup>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
					<col style="width:8.3%"/>
				</colgroup>
				<tr style="text-align:center;">
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td style="color:#FF0000;font-weight:900;">USD</td>
					<td style="color:#C72FB3;font-weight:900;">JPY</td>
					<td style="color:#FFE400;font-weight:900;">EUR</td>
					<td style="color:#1DDB16;font-weight:900;">CNY</td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			
			<canvas id="excWeeklyData" width="400" height="150" style="margin-top:20px;margin:auto;"></canvas>
			
			<canvas id="weeklySumChart" width="400" height="150" style="margin-top:20px;margin:auto;"></canvas>
			
			<canvas id="excBarChart" width="400" height="150" style="margin-top:20px;margin:auto;"></canvas>
		</div>
		
		<!-- 내용 끝 -->
		</div>
	</div>
</div>


</body>

<jsp:include page="../common/commonjs.jsp"/>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script> -->
<script type="text/javascript">


$(function () {

	var tradeChartCtx = document.getElementById("excChart").getContext("2d");
	var tcLabel = ${excChart.label};
	var totalCnt = ${excChart.cnt};
	
	var tcConfig = {
			type : 'pie',
			data : {
				datasets : [{
					data : totalCnt,
					backgroundColor : [
						"#FF0000",
						"#C72FB3",
						"#FFE400",
						"#1DDB16"
					],
					label : 'Dataset 1'
				}] ,
				labels : tcLabel
			},
			options : {
				title : {
					display : true,
					text : '화폐별 거래량 비율'
				},
			    animation: {
			        onComplete: function () {
			            var self = this,
			                chartInstance = this.chart,
			                ctx = chartInstance.ctx;

			            ctx.font = '18px Arial';
			            ctx.textAlign = "center";
			            ctx.fillStyle = "#ffffff";

			            Chart.helpers.each(self.data.datasets.forEach((dataset, datasetIndex) => {
			                var meta = self.getDatasetMeta(datasetIndex),
			                    total = 0, //total values to compute fraction
			                    labelxy = [],
			                    offset = Math.PI / 2, //start sector from top
			                    radius,
			                    centerx,
			                    centery, 
			                    lastend = 0; //prev arc's end line: starting with 0

			                for (var val of dataset.data) { total += val; } 

			                Chart.helpers.each(meta.data.forEach((element, index) => {
			                    radius = 0.9 * element._model.outerRadius - element._model.innerRadius;
			                    centerx = element._model.x;
			                    centery = element._model.y;
			                    var thispart = dataset.data[index],
			                        arcsector = Math.PI * (2 * thispart / total);
			                    if (element.hasValue() && dataset.data[index] > 0) {
			                      labelxy.push(lastend + arcsector / 2 + Math.PI + offset);
			                    }
			                    else {
			                      labelxy.push(-1);
			                    }
			                    lastend += arcsector;
			                }), self)

			                var lradius = radius * 3 / 4;
			                for (var idx in labelxy) {
			                  if (labelxy[idx] === -1) continue;
			                  var langle = labelxy[idx],
			                      dx = centerx + lradius * Math.cos(langle),
			                      dy = centery + lradius * Math.sin(langle),
			                      val = Math.round(dataset.data[idx] / total * 100);
			                  ctx.fillText(val + '%', dx, dy);
			                }

			            }), self);
			          }
			      },
			      tooltips: {
			    	  custom : function(tooltip){
			    		  tooltip.displayColors = false;  
			    	  },
			        callbacks: {
			          label: function(tooltipItem, data) {
			          	var dataset = data.datasets[tooltipItem.datasetIndex];
			            var total = dataset.data.reduce(function(previousValue, currentValue, currentIndex, array) {
			              return previousValue + currentValue;
			            });
			            var currentValue = dataset.data[tooltipItem.index];
			            var precentage = Math.floor(((currentValue/total) * 100)+0.5);         
			            return data.labels[tooltipItem.index] + " : " + currentValue + "건 (" + precentage + "%)";
			          }
			        }
			      }
			}
	};
	
	var myTradeChart = new Chart(tradeChartCtx, tcConfig);
	
	////////////////////////////////////////////////////
	// excMonthlyData start

	var ctx = document.getElementById("excMonthlyData").getContext("2d");
	var label = ${excMonthlyData.label};
	var usd = ${excMonthlyData.usd};
	var jpy = ${excMonthlyData.jpy};
	var eur = ${excMonthlyData.eur};
	var cny = ${excMonthlyData.cny};
	
	var lineData = {
			  labels: label,
			  datasets: [{
				    label: "USD",
				    data: usd,
				    borderColor: "#FF0000",
			        fill: false
				  },
				  {
					    label: "JPY",
					    data: jpy,
					    borderColor: "#C72FB3",
				        fill: false
					  },
				  {
					    label: "EUR",
					    data: eur,
					    borderColor: "#FFE400",
				        fill: false
					  },
				  {
					    label: "CNY",
					    data: cny,
					    borderColor: "#1DDB16",
				        fill: false
					  }]
			};

	var myUsrChart = new Chart(ctx, {
	    type: 'line',
	    data: lineData,
	    options: {
	    	title : {
	    		display : true,
	    		text : '월별 거래 현황'
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

	// excMonthlyData start
	////////////////////////////////////////////////////
	// excWeeklyData start

	var ctx = document.getElementById("excWeeklyData").getContext("2d");
	var label = ${excWeeklyData.label};
	var usd = ${excWeeklyData.usd};
	var jpy = ${excWeeklyData.jpy};
	var eur = ${excWeeklyData.eur};
	var cny = ${excWeeklyData.cny};
	
	var lineData = {
			  labels: label,
			  datasets: [{
					    label: "USD",
					    data: usd,
					    borderColor: "#FF0000",
				        fill: false
					  },
				  {
					    label: "JPY",
					    data: jpy,
					    borderColor: "#C72FB3",
				        fill: false
					  },
				  {
					    label: "EUR",
					    data: eur,
					    borderColor: "#FFE400",
				        fill: false
					  },
				  {
					    label: "CNY",
					    data: cny,
					    borderColor: "#1DDB16",
				        fill: false
					  }]
			};

	var myUsrChart = new Chart(ctx, {
	    type: 'line',
	    data: lineData,
	    options: {
	    	title : {
	    		display : true,
	    		text : '최근 4주간 거래 현황'
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

	// excWeeklyData end
	//////////////////////////////////////////////////////
	// excWeekly sum start
	
	var label = ${excWeeklyData.label};
	var usdSum = ${excWeeklyData.usdSum};
	var jpySum = ${excWeeklyData.jpySum};
	var eurSum = ${excWeeklyData.eurSum};
	var cnySum = ${excWeeklyData.cnySum};
	
	var barChartData = {
			labels : label,
			datasets : [{
			    label: "USD",
			    data: usdSum,
			    backgroundColor: "#FF0000",
				yAxisID : "y-axis-1",
			  },
		  {
			    label: "JPY",
			    data: jpySum,
			    backgroundColor: "#C72FB3",
				yAxisID : "y-axis-1",
			  },
		  {
			    label: "EUR",
			    data: eurSum,
			    backgroundColor: "#FFE400",
				yAxisID : "y-axis-1",
			  },
		  {
			    label: "CNY",
			    data: cnySum,
			    backgroundColor: "#1DDB16",
				yAxisID : "y-axis-1",
			  }]
	};
	
	var barCtx = document.getElementById("weeklySumChart").getContext("2d");
	var myBarChart = new Chart(barCtx, {
		type : 'bar',
		data : barChartData,
		options : {
			responsive : true,
			title : {
				display : true,
				text : "최근 4주간 환전금액 현황"
			},
			tooltips : {
				mode : 'index',
				intersect : true,
				callbacks : {
					label : function(tooltipItem, data){
						return data.datasets[tooltipItem.datasetIndex].label + " : " 
								+ Number(tooltipItem.yLabel).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
					}
				}
			},
			scales : {
				yAxes : [{
					type : "linear",
					display : true,
					position : "left",
					id : "y-axis-1",
					ticks : {
						callback : function(label, index, labels){
							return Number(label).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
						}
					}
				}]
				
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
	                        ctx.fillText(Number(data).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","), bar._model.x, bar._model.y - 5);
	                    });
	                });
	            }
	        }
		}
	});
	// excWeekly sum end
	//////////////////////////////////////////////////////
	// groupBarChart start

	var barLabel = ${excBarChart.label};
	var buyKorMem = ${excBarChart.buyKorMem};
	var buyKorNoMem = ${excBarChart.buyKorNoMem};
	var sellKorMem = ${excBarChart.sellKorMem};
	var sellKorNoMem = ${excBarChart.sellKorNoMem};
	
	var barChartData = {
			labels : barLabel,
			datasets : [{
				label : '맴버십 구매',
				backgroundColor : [
					"#FF0000",
					"#C72FB3",
					"#FFE400",
					"#1DDB16"
				],
				stack : 'Stack 0',
				yAxisID : "y-axis-1",
				borderColor : 'white',
				borderWidth : 2,
				data : buyKorMem
			},{
				label : '비맴버십 구매',
				backgroundColor : [
					"rgba(255, 0, 0, 0.3)",
					"rgba(199, 47, 179, 0.3)",
					"rgba(255, 228, 0, 0.3)",
					"rgba(29, 219, 22, 0.3)"
				],
				stack : 'Stack 0',
				yAxisID : "y-axis-1",
				borderColor : 'white',
				borderWidth : 2,
				data : buyKorNoMem
			}, {
				label : '맴버십 판매',
				backgroundColor : [
					"#FF0000",
					"#C72FB3",
					"#FFE400",
					"#1DDB16"
				],
				stack : 'Stack 1',
				yAxisID : "y-axis-1",
				borderColor : 'white',
				borderWidth : 2,
				data : sellKorMem
			}, {
				label : '비맴버십 판매',
				backgroundColor : [
					"rgba(255, 0, 0, 0.3)",
					"rgba(199, 47, 179, 0.3)",
					"rgba(255, 228, 0, 0.3)",
					"rgba(29, 219, 22, 0.3)"
				],
				stack : 'Stack 1',
				borderColor : 'white',
				borderWidth : 2,
				yAxisID : "y-axis-1",
				data : sellKorNoMem
			}]
	};
	
	var barCtx = document.getElementById("excBarChart").getContext("2d");
	var myBarChart = new Chart(barCtx, {
		type : 'bar',
		data : barChartData,
		options : {
			responsive : true,
			title : {
				display : true,
				text : "통화별 구매/판매 현황"
			},
			tooltips : {
				mode : 'index',
				intersect : true,
				callbacks : {
					label : function(tooltipItem, data){
						return data.datasets[tooltipItem.datasetIndex].label + " : " 
								+ Number(tooltipItem.yLabel).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
					}
				}
			},
			scales : {
				yAxes : [{
					type : "linear",
					display : true,
					position : "left",
					id : "y-axis-1",
					stacked : true,
					ticks : {
						callback : function(label, index, labels){
							return Number(label).toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
						}
					}
				}],
				xAxes : [{
					stacked : true
				}]
				
			}
		}
	});
});


</script>
</html>