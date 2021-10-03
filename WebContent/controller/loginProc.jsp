<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
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
	
	Map map = dao.loginChk(user);
	int res = (Integer)map.get("res");
	String name = (String)map.get("name");
	String comment = null;
	
	if (res == 1){
		comment = name + "님 로그인 되었습니다.";
	} else {
		res = 0;
		comment = "아이디 또는 비밀번호를 확인해주세요.";
	}
%>


<c:set var="res" value="<%= res %>"/>
<c:set var="comment" value='<%= comment %>'/>
<c:set var="id" value='<%= (request.getParameter("id")) %>'/>
<c:set var="name" value='<%= name %>'/>

[
	{"res":"${res}", "comment":"${comment}"},

<c:if test="${res eq 1}">
	{"name":"${name}"}
<% 
	session.setAttribute("user", request.getParameter("id"));
	session.setAttribute("name", name); 
%>
</c:if>
<c:if test="${res ne 1}">
	{"name":"null"}
</c:if>
]	


