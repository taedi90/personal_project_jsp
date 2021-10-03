<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="personal_project_jsp.dao.impl.CategoryDaoImpl" %>
<%@ page import="personal_project_jsp.dao.CategoryDao" %>
<%@ page import="personal_project_jsp.dto.Category" %>

<%	
	CategoryDao dao = CategoryDaoImpl.getInstance();	
	ArrayList<Category> arr = dao.selectCategoryByAll();
%>

<form id="writePost">

	<label for=""></label>

	<div class="title">
		<input id="title" name="title" type="text" placeholder="제목을 입력해주세요.">
	</div>
	<div class="writeRow">
		<!-- <label for="selectCatrgory">카테고리</label> -->
		<select id="selectCategory" name="category">
		
			<c:set var="arr" value="<%= arr %>"/>
			<c:forEach var="i" items="${arr}">
				<option value="${i.getCategory()}">${i.getCategory()}</option>
			</c:forEach>
	
		</select>
	</div>

	<!-- SmartEditor2 --> 
	<div class="jsx-2303464893 editor"> 
		<div class="fr-box fr-basic fr-top" role="application"> 
			<div class="fr-wrapper show-placeholder" dir="auto" style="overflow: scroll;"> 
				<textarea name="notice_content" id="smartEditor" style="width: 100%; height: 412px;"></textarea> 
			</div> 
		</div> 
	</div>

	<div class="writePostBottom writeRow">
		<button type="button" id="cancelPost" onclick="cancelPostFunc()">뒤로가기</button>
		<button type="button" id="savePost" onclick="savePostFunc()">등록하기</button>	
	</div>


</form>
