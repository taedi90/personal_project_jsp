<%@page import="java.util.LinkedList"%>
<%@page import="personal_project_jsp.dto.Board"%>
<%@page import="personal_project_jsp.dao.impl.CommentDaoImpl"%>
<%@page import="personal_project_jsp.dao.CommentDao"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session ="true" %>
<% response.setContentType("application/json"); %>

<% 

	long postNo = Long.parseLong(request.getParameter("postNo"));
	
	Board board = new Board(); 
	board.setNo(postNo);

	CommentDao dao = CommentDaoImpl.getInstance();
	
	LinkedList list = dao.selectCommentByPostNo(board);
	
	int res = 0;
	
	if(list != null){
		res = 1;
	}else{
		res = 0;
	}


%>