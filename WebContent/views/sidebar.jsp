<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="personal_project_jsp.dao.impl.CategoryDaoImpl" %>
<%@ page import="personal_project_jsp.dao.CategoryDao" %>
<%@ page import="personal_project_jsp.dto.Category" %>


<%	
	CategoryDao dao = CategoryDaoImpl.getInstance();	
	ArrayList<Category> arr = dao.selectCategoryByAll();
%>

<div id="sidebarWrap">

	<input type="text" class="searchInput" placeholder="검색" onkeyup="if(window.event.keyCode==13){searchPostFunc(this.value)}">

	<br>

	<div class="afterLogin <c:if test='${user eq null}'>hidden</c:if>">
		내 글 보기
	</div>

	<p>&nbsp;</p>

	카테고리
	<br>
	<b>. . .</b>

	<ul> 
		<li class="category" onclick="categoryChange('전체')">전체</li>
		<c:set var="arr" value="<%= arr %>"/>
		<c:forEach var="i" items="${arr}">
			<li class="category" onclick="categoryChange('${i.getCategory()}')">${i.getCategory()}</li>
		</c:forEach>
	</ul>

	<!-- <br>
	<ul> 관리자(미구현)
	<hr>
		<li>게시물 관리</li>
		<li>회원 관리</li>
	<hr>
	</ul> -->

	<p>&nbsp;</p>
	<p>&nbsp;</p>
	

	<c:if test='${user ne null}'>
		<button id="btnOpenLogin" class="beforeLogin hidden">로그인</button>
		<button id="btnOpenLogout" class="afterLogin">로그아웃</button>
	</c:if>

	<c:if test='${user eq null}'>
		<button id="btnOpenLogin" class="beforeLogin">로그인</button>
		<button id="btnOpenLogout" class="afterLogin hidden">로그아웃</button>
	</c:if>
	
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	
	<p><img src="resources/imgs/slide.svg" style="width: 5rem"></p> 
</div>
