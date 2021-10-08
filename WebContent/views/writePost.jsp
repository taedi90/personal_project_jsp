<%@page import="personal_project_jsp.dto.Board"%>
<%@page import="personal_project_jsp.dao.impl.BoardDaoImpl"%>
<%@page import="personal_project_jsp.dao.BoardDao"%>
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
	// 카테고리 목록 가져오기
	CategoryDao dao = CategoryDaoImpl.getInstance();	
	ArrayList<Category> arr = dao.selectCategoryByAll();
	
	Board board = null;
	int res = 0;

	// 수정&삭제 구별
	if(request.getParameter("option").equals("modify")){
		res = 1;
		session.setAttribute("writeMod", "modify");
		session.setAttribute("postNo", request.getParameter("no"));
		
		// 수정 페이지 진입은 다른 파일로 처리하자
		board = new Board();
		board.setNo(Long.parseLong(request.getParameter("no")));
		
		BoardDao boardDao = BoardDaoImpl.getInstance();
		board = boardDao.selectBoardByNo(board);
		
	}else{
		res = 0;
		session.setAttribute("writeMod", "new");
		session.setAttribute("postNo", "");
	}

%>

<c:set var="res" value="<%= res %>"/>
<c:set var="arr" value="<%= arr %>"/>
<c:if test="${res eq 1}">
	<c:set var="title" value="<%= board.getTitle() %>"/>
	<c:set var="category" value="<%= board.getCategory().getCategory() %>"/>
	<c:set var="content" value="<%= board.getContent() %>"/>			
	<c:set var="thumb" value="<%= board.getThumb() %>"/>			

</c:if>


<form id="writePost" name="writePost">

	<div id="wpThumbHolder"  style='<c:if test="${thumb ne null}">background: white url("${thumb}") no-repeat right top/contain;</c:if>'>
                		
		<div class="writePostCategory">
			<!-- <label for="selectCatrgory">카테고리</label> -->
			<select id="selectCategory" name="category">
			
			<c:if test="${res eq 1}">
				<c:forEach var="i" items="${arr}">
					<c:if test="${i.getCategory() eq category}">
						<option value="${i.getCategory()}" selected>${i.getCategory()}</option>
					</c:if>
					<c:if test="${i.getCategory() ne category}">
						<option value="${i.getCategory()}">${i.getCategory()}</option>				
					</c:if>
				</c:forEach>
			
			</c:if>
			<c:if test="${res ne 1}">
				<c:forEach var="i" items="${arr}">
					<option value="${i.getCategory()}">${i.getCategory()}</option>
				</c:forEach>
			</c:if>
	
		
			</select>
		</div>
		<div class="writePostTitle">
			<input id="title" name="title" type="text" placeholder="제목을 입력해주세요." <c:if test="${res eq 1}">value="${title}"</c:if>>
		</div>
	</div>



	<!-- SmartEditor2 --> 
	<div class="jsx-2303464893 editor"> 
		<div class="fr-box fr-basic fr-top" role="application"> 
			<div class="fr-wrapper show-placeholder" dir="auto" style="overflow: visible;"> 
				<textarea name="content" id="smartEditor" style="width: 100%; height: 412px;">
					<c:if test="${res eq 1}">${content}</c:if>
				</textarea> 
			</div> 
		</div> 
	</div>
	
	<div>
		<button type="button" onclick="openUploadModal()">썸네일 업로드</button> 
		<button id="btnDeleteThumb" <c:if test="${thumb eq null}">class="hidden"</c:if> type="button" onclick="deleteThumb()">썸네일 삭제</button>
		<input id="thumbSrc" name="thumb" type="text" hidden>
	</div>

	

	<div class="writePostBottom">
		<button type="button" id="cancelPost" onclick="cancelPostFunc()">뒤로가기</button>
		<button type="button" id="savePost" onclick="savePostFunc()">등록하기</button>	
	</div>


</form>
