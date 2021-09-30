

<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="personal_project_jsp.dao.BoardDao" %>
<%@ page import="personal_project_jsp.dao.impl.BoardDaoImpl" %>
<%@ page import="personal_project_jsp.dto.Board" %>
	
<%
	BoardDao dao = BoardDaoImpl.getInstance();
	
	Map<String, Object> map = dao.selectBoardByAll(1, 3, "desc");
%>

<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>

	<c:set var="map" value="<%= map %>"/>
	now : ${map.get("nowPost")} / total : ${map.get("maxPost")}
<table>

	<c:forEach var="i" items='${map.get("list")}'>
	<tr>
		<td>${i.getNo()}</td>
		<td>${i.getCategory()}</td>
		<td>${i.getTitle()}</td>
		<td>${i.getWriDate()} / </td>
		<td><fmt:formatDate value="${i.getWriDate()}" pattern="yyyy월 MM월 dd일 hh시"/></td>
	</tr>
		
	</c:forEach>
	

	
</table>
	<c:forEach var="i" begin='1' end='${map.get("maxPageIdx")}' >
		<c:choose>
			<c:when test='${map.get("nowPageIdx") == i}'>
				<b>${i}</b>
			</c:when>
			<c:otherwise>
				${i}
			</c:otherwise>
		</c:choose>
	</c:forEach>
</body>
</html>