<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="personal_project_jsp.dao.UserDao" %>
<%@ page import="personal_project_jsp.dao.impl.UserDaoImpl" %>
<%@ page import="personal_project_jsp.dto.User" %>
<%@ page session ="true" %>
<% response.setContentType("application/json"); %>

<%
	UserDao dao = UserDaoImpl.getInstance();
	User user = new User();
	user.setId(request.getParameter("id"));
	user.setPassword(request.getParameter("password"));
	user.setName(request.getParameter("name"));
	user.setEmail(request.getParameter("email"));

	int res = dao.insertUser(user);
	String comment = null;
	if(res == 1) {
		comment = "가입 완료!";
	}else{
		res = 0;
		comment = "가입 실패!";
	}
%>


<c:set var="res" value="<%= res %>"/>
<c:set var="comment" value="<%= comment %>"/>
<c:set var="id" value='<%= (request.getParameter("id")) %>'/>
<c:set var="name" value='<%= (request.getParameter("name")) %>'/>

[
	{"res":"${res}", "comment":"${comment}"},
<c:if test="${res eq 1}">
	{"name":"${name}"}
<% 
	session.setAttribute("user", request.getParameter("id"));
	session.setAttribute("name", (request.getParameter("name"))); 
%>
</c:if>
<c:if test="${res ne 1}">
	{"name":"null"}
</c:if>
]	