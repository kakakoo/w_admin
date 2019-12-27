<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<input type="hidden" id="motherPage" value="${pageContext.request.contextPath}${motherPage }">
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0;">
			<a href="${pageContext.request.contextPath}/api/main" class="site_title"><i class="fa fa-home"></i>
				<span>WEYS</span></a>
		</div>

		<div class="clearfix"></div>

		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<ul class="nav side-menu">
					<c:if test="${empty adminTp or adminTp == 'S'}">
						<li><a href="${pageContext.request.contextPath}/api/user"><i class="fa fa-user"></i> 사용자 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/store"><i class="fa fa-inbox"></i> 지점 / 매니저 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/coupon"><i class="fa fa-envelope"></i> 쿠폰 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/event"><i class="fa fa-magic"></i> 이벤트 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/cont"><i class="fa fa-book"></i> 컨텐츠 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/rsv/list"><i class="fa fa-truck"></i> 예약 / 배송 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/rsv/vbank"><i class="fa fa-icon-check"></i> 무통장입금 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/rsv/grp"><i class="fa fa-print"></i> 지점 예약 관리 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/rsv/cancel"><i class="fa fa-ban"></i> 예약/배송 취소 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/notice"><i class="fa fa-bullhorn"></i> 공지 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/unit"><i class="fa fa-money"></i> 통화 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/banner"><i class="fa fa-paste"></i> 배너 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/analysis"><i class="fa fa-bar-chart"></i> 앱 통계 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/version"><i class="fa fa-wrench"></i> 버전 / 로그</a></li>
<%-- 						<li><a href="${pageContext.request.contextPath}/api/cop"><i class="fa fa-group"></i> 제휴사</a></li> --%>
						<li><a href="${pageContext.request.contextPath}/api/push"><i class="fa fa-forward"></i> 푸시</a></li>
						<li><a href="${pageContext.request.contextPath}/api/user/delete"><i class="fa fa-remove"></i> 탈퇴정보</a></li>
						<li><a href="${pageContext.request.contextPath}/api/gold"><i class="fa fa-refresh"></i> 시제현황</a></li>
						<li><a href="${pageContext.request.contextPath}/api/staff"><i class="fa fa-group"></i> 당담자관리</a></li>
<%-- 						<li><a href="${pageContext.request.contextPath}/api/money"><i class="fa fa-refresh"></i> 환율관리</a></li> --%>
					</c:if>
					<c:if test="${adminTp == 'M'}">
						<li><a href="${pageContext.request.contextPath}/api/rsv/list/pt"><i class="fa fa-truck"></i> 예약 / 배송 </a></li>
						<li><a href="${pageContext.request.contextPath}/api/rsv/vbank"><i class="fa fa-icon-check"></i> 무통장입금 </a></li>
<%-- 						<li><a href="${pageContext.request.contextPath}/api/rsv/cancel"><i class="fa fa-ban"></i> 예약/배송 취소 </a></li> --%>
						<li><a href="${pageContext.request.contextPath}/api/gold"><i class="fa fa-refresh"></i> 시제관리</a></li>
					</c:if>
					
					
					
<%-- 					<li><a href="${pageContext.request.contextPath}/api/main"><i class="fa fa-home"></i> 메인 </a> --%>
<!-- 						<ul class="nav child_menu"></ul></li> -->
<%-- 					<li><a href="${pageContext.request.contextPath}/api/rsv/mng"><i class="fa fa-sitemap"></i> 예약 담당자 </a></li> --%>
<%-- 					<li><a href="${pageContext.request.contextPath}/api/pay"><i class="fa fa-user-md"></i> 결제 내역 </a></li> --%>
<%-- 					<li><a href="${pageContext.request.contextPath}/api/member"><i class="fa fa-credit-card"></i> 결제 관리 </a></li> --%>
<%-- 					<li><a href="${pageContext.request.contextPath}/api/cash"><i class="fa fa-tags"></i> 현금영수증 </a></li> --%>
<%-- 					<li><a href="${pageContext.request.contextPath}/api/trade"><i class="fa fa-table"></i> 직거래 </a></li> --%>
<%-- 					<li><a href="${pageContext.request.contextPath}/api/rsv/unit"><i class="fa fa-money"></i> 예약 통화 </a></li> --%>
<%-- 					<li><a href="${pageContext.request.contextPath}/api/chart/trade"><i class="fa fa-pie-chart"></i> 환율 통계(직거래) </a></li> --%>
<%-- 					<li><a href="${pageContext.request.contextPath}/api/chart/exc"><i class="fa fa-pie-chart"></i> 환율 통계(환전소) </a></li> --%>
<%-- 					<li><a href="${pageContext.request.contextPath}/api/tut"><i class="fa fa-forward"></i> 튜토리얼</a></li> --%>
				</ul>
			</div>

		</div>
		<!-- /sidebar menu -->

		<!-- /menu footer buttons -->
<!-- 		<div class="sidebar-footer hidden-small"> -->
<!-- 			<a data-toggle="tooltip" data-placement="top" title="Settings"> <span -->
<!-- 				class="glyphicon glyphicon-cog" aria-hidden="true"></span> -->
<!-- 			</a> <a data-toggle="tooltip" data-placement="top" title="FullScreen"> -->
<!-- 				<span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span> -->
<!-- 			</a> <a data-toggle="tooltip" data-placement="top" title="Lock"> <span -->
<!-- 				class="glyphicon glyphicon-eye-close" aria-hidden="true"></span> -->
<!-- 			</a> <a data-toggle="tooltip" data-placement="top" title="Logout" -->
<!-- 				href="login.html"> <span class="glyphicon glyphicon-off" -->
<!-- 				aria-hidden="true"></span> -->
<!-- 			</a> -->
<!-- 		</div> -->
		<!-- /menu footer buttons -->
	</div>
</div>