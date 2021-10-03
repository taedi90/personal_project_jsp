<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="personal_project_jsp.dao.BoardDao" %>
<%@ page import="personal_project_jsp.dao.impl.BoardDaoImpl" %>
<%@ page import="personal_project_jsp.dto.Board" %>
<%@ page import="personal_project_jsp.dto.Category" %>
<%@ page session ="true" %>
<% response.setContentType("application/json"); %>

<%	

	// 결과(1:성공, 0:실패) 및 상세사유 반환시킬 변수
	int res = 0;
	String comment = null;

	
	
	String id = (String)session.getAttribute("user");
	String title = request.getParameter("title");
	Category category = new Category(request.getParameter("category"));
	String content = request.getParameter("content");

	
	
	// 값이 모두 다 들어왔는지 확인 (로그인 체크 포함)
	if(id == null || title == null || content == null){
		res = 0;
		comment = "(등록 실패) 기본 값 누락";
	}
	
	// 사용하면 안되는 태그 정규식 처리
	
	
	// 카테고리가 기준에 부합하는지 여부 확인
	// category.getCategory() == null 
	
	
	Board board = new Board();
	board.setCategory(category);
	board.setId(id);

	board.setTitle(title);
	
	
	
	
	
	BoardDao dao = BoardDaoImpl.getInstance();	
	
	
	// option은 modify수정 new신규
	if(request.getParameter("option").equals("modify")){
		// 본인 글이 맞는지 확인(게시물 변경)
	
		Long no = Long.parseLong(request.getParameter("no"));
		board.setNo(no);
		//update
		
		
	}else if(request.getParameter("option").equals("new")){
		
		res = dao.insertBoard(board);
	
	}else{
		res = 0;
		comment = "(등록 실패) 올바르지 않은 옵션 오류";
	}
	
	



	// 정상처리되면 원래화면으로 넘어가고 에러는 기존화면(js에서 처리)
	
	







%>

<c:set var="res" value="<%= res %>"/>
<c:set var="comment" value='<%= comment %>'/>

[
	{"res":"${res}", "comment":"${comment}"}
]	

	