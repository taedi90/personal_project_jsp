

<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="personal_project_jsp.dao.BoardDao" %>
<%@ page import="personal_project_jsp.dao.impl.BoardDaoImpl" %>
<%@ page import="personal_project_jsp.dto.Board" %>
	
<%
	BoardDao dao = BoardDaoImpl.getInstance();
	
	Map<String, Object> map = dao.selectBoardByAll(1, 10);
%>

<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>

	<c:set var="map" value="<%= map %>"/>
	now : ${map.get("accum")} / total : ${map.get("total")}
<table>

	<c:forEach var="i" items='${map.get("list")}'>
	<tr>
		<td>${i.getNo()}</td>
		<td>${i.getCategory()}</td>
		<td>${i.getTitle()}</td>
		<td>${i.getWriDate()}</td>
	</tr>
		
	</c:forEach>
</table>
</body>
</html>