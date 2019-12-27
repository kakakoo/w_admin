<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${param.endPageNo > 1 }">
<div class="paging">
<%--     <a href="javascript:goPage(${param.firstPageNo})" class="first">처음 페이지</a> --%>
    <a href="javascript:goPage(${param.prevPageNo}, 0)" class="prev">이전 페이지</a>
    <span>
        <c:forEach var="i" begin="${param.startPageNo}" end="${param.endPageNo}" step="1">
            <c:choose>
                <c:when test="${i eq param.pageNo}"><a href="javascript:goPage(${i}, 0)" class="on">${i}</a></c:when>
                <c:otherwise><a href="javascript:goPage(${i}, 0)">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
    </span>
    <a href="javascript:goPage(${param.nextPageNo}, 0)" class="next">다음 페이지</a>
<%--     <a href="javascript:goPage(${param.finalPageNo})" class="last">마지막 페이지</a> --%>
</div>
</c:if>