<%@page import="personal_project_jsp.dto.Comment"%>
<%@page import="personal_project_jsp.dto.User"%>
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
	String id = (String)session.getAttribute("user");
	long pCno = request.getParameter("parentNo").equals("")? 0: Long.parseLong(request.getParameter("parentNo"));
	String comment = request.getParameter("comment");

	CommentDao dao = CommentDaoImpl.getInstance();
	
	// pCno가 있으면 depth 구하기
	int depth = 1;

	if(pCno != 0) {
		Comment parent = new Comment();
		parent.setCno(pCno);
		parent = dao.selectCommentByCommentNo(parent);
		depth = parent.getDepth() + 1;
	}
	

	// 코멘트 생성
	Comment newCom = new Comment(postNo, pCno, new User(id), comment, depth); 

	int res = dao.insertComment(newCom);

%>

[
{"res" : "<%= res %>"}
]