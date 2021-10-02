<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
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
	user.setPassword(request.getParameter("password"));
	
	int res = dao.loginChk(user);
	
	Enumeration en = request.getParameterNames();
	StringBuilder a = new StringBuilder();
	while(en.hasMoreElements()) {
		a.append((String)en.nextElement());
		a.append(" ");
		
	}
	
	Map map = request.getParameterMap();
	String test = map.toString();
%>


<c:set var="res" value="<%= res %>"/>
<c:set var="id" value='<%= test %>'/>
<c:set var="pass" value='<%= request.getMethod() %>'/>

[
	{"res":"${res}", "id":"${id}", "pass":"${pass}"},

<c:if test="${res eq 1}">
	{"userName":""}
	<% session.setAttribute("user", request.getParameter("id")); %>
</c:if>
<c:if test="${res ne 1}">
	{"msg":"아이디를 확인하시기 바랍니다."}
</c:if>
]	

	<% response.setContentType("application/json"); %>
