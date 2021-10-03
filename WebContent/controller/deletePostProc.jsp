<%@page import="personal_project_jsp.dto.Board"%>
<%@page import="personal_project_jsp.dao.impl.BoardDaoImpl"%>
<%@page import="personal_project_jsp.dao.BoardDao"%>
<%@ page session ="true" %>
<% response.setContentType("application/json"); %>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
	int res = 0;
	String comment = null;

	Board board = new Board();
	board.setNo(Long.parseLong(request.getParameter("no")));
	
	BoardDao boardDao = BoardDaoImpl.getInstance();
	res = boardDao.deleteBoard(board);
	
	if (res == 1){
		comment = "삭제 완료!";
	} else{
		res = 0;
		comment = "삭제 실패!";
	}

%>

<c:set var="res" value="<%= res %>"/>
<c:set var="comment" value='<%= comment %>'/>

[
	{"res":"${res}", "comment":"${comment}"}
]	