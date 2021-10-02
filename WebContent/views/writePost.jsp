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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<!-- SmartEditor2 라이브러리 -->
<script type="text/javascript" src="../se2/js/HuskyEZCreator.js"
	charset="utf-8"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<link href="../resources/css/main.css" rel="stylesheet">
</head>
<body>

<form id="writePost">

	<label for=""></label>

	<div>
		<input id="title" name="title" type="text" placeholder="제목을 입력해주세요.">
	</div>
	<div>
		<label for="selectCatrgory">카테고리</label>
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

	<div class="writePostBottom">
		<button type="button" id="cancelPost">뒤로가기</button>
		<button type="button" id="savePost">등록하기</button>	
	</div>


</form>







</body>
</html>

<script type="text/javascript" src = "../resources/js/writePost.js"></script>
<script src="../resources/js/jAjax.js" defer></script>
