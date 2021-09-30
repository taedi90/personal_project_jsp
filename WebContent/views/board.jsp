

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
	
	Map<String, Object> map = dao.selectBoardByAll(1, 5, "desc");
%>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
    <link href="../resources/css/main.css" rel="stylesheet">
    <link href="../resources/css/board.css" rel="stylesheet">
</head>

<body>

	<c:set var="map" value="<%= map %>"/>

    <div>
        <div id="boardTop">
            <div id="status">
                now : ${map.get("nowPost")} / total : ${map.get("maxPost")}
            </div>

            <div id="option">
                <div>
                    <select id="num" name="num">
                        <option value="10" selected>10개씩 보기</option>
                        <option value="20">20개씩 보기</option>
                        <option value="30">30개씩 보기</option>
                        <!-- 페이징 수 바뀌면 페이지 인덱스 계산 -->
                    </select>
                </div>
                <div>
                    <select id="order" name="order">
                        <option value="desc" selected>최근작성순</option>
                        <option value="asc">작성일자순</option>
                    </select>
                </div>
            </div>
        </div>


        <div id="boardMain">
        
        
		<c:forEach var="i" items='${map.get("list")}' varStatus="status">
			<div id="post${status.index}" class="postCard">
				<div class="postDesc">
                    <p class="category">${i.getCategory()}</p>
                    <p class="title">${i.getTitle()}</p>
                    <p class="author">${i.getId()}</p>
                    <p class="date"><fmt:formatDate value="${i.getWriDate()}" pattern="yyyy월 MM월 dd일"/></p>
                </div>
                <div class="postThumb">
                    ${i.getThumb()}
                </div>
            </div>
            <div id="content${status.index}" class="postBody">
                <div></div>
            </div>
        </c:forEach>
           
        </div>
        <div id="boardBottom">
        <c:forEach var="i" begin='1' end='${map.get("maxPageIdx")}' >
			<c:choose>
				<c:when test='${map.get("nowPageIdx") == i}'>
					<b>${i}</b>
				</c:when>
				<c:otherwise>
					<a href="#${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
        </div>
    </div>

</body>

</html>