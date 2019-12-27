<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<input type="hidden" id="motherPage" value="${pageContext.request.contextPath}${motherPage }">
<div class="col-md-3 left_col">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0;">
			<a href="${pageContext.request.contextPath}/api/pt/rsv" class="site_title"><i class="fa fa-home"></i>
				<span>WEYS</span></a>
		</div>

		<div class="clearfix"></div>

		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<ul class="nav side-menu">
					<li><a href="${pageContext.request.contextPath}/api/rsv/list/pt"><i class="fa fa-truck"></i> 예약 / 배송 </a></li>
					<li><a href="${pageContext.request.contextPath}/api/rsv/cancel"><i class="fa fa-ban"></i> 예약/배송 취소 </a></li>
				</ul>
			</div>

		</div>
	</div>
</div>