<%@page import="personal_project_jsp.dto.Comment"%>
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

	String id = (String)session.getAttribute("user");
	long cno = Long.parseLong(request.getParameter("cno"));

	CommentDao dao = CommentDaoImpl.getInstance();

	// 코멘트 생성
	Comment comm = new Comment(); 
	comm.setCno(cno);
	comm.setId(id);


	int res = dao.deleteComment(comm);
	System.out.println("댓글 삭제-" + res );

%>

[
{"res" : "<%= res %>"}
]