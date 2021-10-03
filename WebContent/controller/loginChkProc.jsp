<%@ page language="java" contentType="application/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session ="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setContentType("application/json"); %>

<% 
	int res = 0;

	if((String)session.getAttribute("user") != null){
		res = 1;
	}
	System.out.println((String)session.getAttribute("user"));
%>

<c:set var="res" value="<%= res %>"/>

[
	{"res": "${res}"}
]