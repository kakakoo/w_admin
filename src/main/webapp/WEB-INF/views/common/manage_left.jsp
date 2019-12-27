<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<input type="hidden" id="motherPage" value="${pageContext.request.contextPath}${motherPage }">
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0;">
			<a href="${pageContext.request.contextPath}/api/manage/main" class="site_title"><i class="fa fa-home"></i>
				<span>WEYS</span></a>
		</div>

		<div class="clearfix"></div>

		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<ul class="nav side-menu">
					<li><a href="${pageContext.request.contextPath}/api/manage/active"><i class="fa fa-credit-card"></i> 거래 리스트</a></li>
					<li><a href="${pageContext.request.contextPath}/api/manage/buy"><i class="fa fa-money"></i> 외화 구매 </a></li>
					<li><a href="${pageContext.request.contextPath}/api/manage/sell"><i class="fa fa-money"></i> 외화 판매 </a></li>
					<li><a href="${pageContext.request.contextPath}/api/manage/money"><i class="fa fa-wrench"></i> 지점 관리 </a></li>
					<li><a href="${pageContext.request.contextPath}/api/manage/setting"><i class="fa fa-wrench"></i> 금고 관리 </a></li>
					<li><a href="${pageContext.request.contextPath}/api/manage/log"><i class="fa fa-inbox"></i> 관리 로그 </a></li>
				</ul>
			</div>
		</div>
		<!-- /sidebar menu -->

<!-- /menu footer buttons -->
		<div class="sidebar-footer hidden-small" style="color:white;padding:10px;">
			문의<br>
			mobile@i4u.kr<br>
			070-8767-7893
		</div>
		<!-- /menu footer buttons -->
	</div>
</div>