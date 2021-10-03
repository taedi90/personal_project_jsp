<%@ page import="personal_project_jsp.dao.UserDao" %>
<%@ page import="personal_project_jsp.dao.impl.UserDaoImpl" %>
<%@ page import="personal_project_jsp.dto.User" %>
<%@page import="personal_project_jsp.dto.Board"%>
<%@page import="personal_project_jsp.dao.impl.BoardDaoImpl"%>
<%@page import="personal_project_jsp.dao.BoardDao"%>
<%@ page session ="true" %>
<% response.setContentType("application/json"); %>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	// 세션 아이디와 게시글 번호 매치되는지 확인
	
	int res = 0;
	String comment = null;
	
	Board board = new Board();
	board.setNo(Long.parseLong(request.getParameter("no")));
	
	BoardDao boardDao = BoardDaoImpl.getInstance();
	board = boardDao.selectBoardByNo(board);
	
	String id = (String)session.getAttribute("user");
	
	UserDao dao = UserDaoImpl.getInstance();
	User user = new User();
	user.setId(id);
	
	user = dao.getUserInfo(user);
	int rootPermission = user.getRootPermission();
	
	System.out.println("게시물 작성자 아이디" + board.getId());
	System.out.println("로그인 세션 아이디" + id);
	System.out.println("관리자 권한" +rootPermission);
	System.out.println("로그인정보 확인" + user.getId().equals(id));

	if(id == null){
		res = 2;
		comment = "로그인 정보 없음";
	}else if(board.getId().equals(id) || rootPermission == 1){

		res = 1;
		comment = "수정 가능";
	}else if(!board.getId().equals(id)){
		res = 0;
		comment = "작성자만 수정 또는 삭제 가능";
	}else{
		res = 0;
		comment = "오류로 인하여 수정이 불가합니다";
	}
%>

<c:set var="res" value="<%= res %>"/>
<c:set var="comment" value='<%= comment %>'/>

[
	{"res":"${res}", "comment":"${comment}"}
]	