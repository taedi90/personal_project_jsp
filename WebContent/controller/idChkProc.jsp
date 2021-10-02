<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="personal_project_jsp.dao.UserDao" %>
<%@ page import="personal_project_jsp.dao.impl.UserDaoImpl" %>
<%@ page import="personal_project_jsp.dto.User" %>
<%@ page session ="true" %>

<c:if test='<%= request.getMethod().equals("GET") %>'>
	<!-- GET방식 차단 -->
	<c:redirect url="/"></c:redirect>
</c:if>

<%
	UserDao dao = UserDaoImpl.getInstance();
	User user = new User();
	user.setId(request.getParameter("id"));
	
	int res = dao.idChk(user);
%>


<c:set var="res" value="<%= res %>"/>

[
	{"res":"${res}"}
]	
<% response.setContentType("application/json"); %>