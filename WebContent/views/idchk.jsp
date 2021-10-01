<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="personal_project_jsp.dao.UserDao" %>
<%@ page import="personal_project_jsp.dao.impl.UserDaoImpl" %>
<%@ page import="personal_project_jsp.dto.User" %>
<%@ page session ="true" %>

<%
	UserDao dao = UserDaoImpl.getInstance();
	User user = new User();
	user.setId(request.getParameter("id"));
	
	int res = dao.idChk(user);
%>


<c:set var="res" value="<%= res %>"/>


<c:if test="${res eq 1}">
	1
</c:if>
<c:if test="${res ne 1}">
	0
</c:if>