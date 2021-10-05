<%@page import="java.util.LinkedList"%>
<%@page import="personal_project_jsp.dto.Board"%>
<%@page import="personal_project_jsp.dao.impl.CommentDaoImpl"%>
<%@page import="personal_project_jsp.dao.CommentDao"%>
<%@page import="personal_project_jsp.dto.Comment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session ="true" %>

<% 

	long postNo = Long.parseLong(request.getParameter("postNo"));
	
	Board board = new Board(); 
	board.setNo(postNo);

	CommentDao dao = CommentDaoImpl.getInstance();
	
	LinkedList<Comment> list = dao.selectCommentByPostNo(board);
	
	String sessionId = (String)session.getAttribute("user");
%>

<c:set var="list" value="<%= list %>"/>
<c:set var="sessionId" value="<%= sessionId %>"/>

<div>
    <p class="postCommentTitle">댓글</p>
</div>

<div class="commentBox">    
    <c:forEach var="i" items='${list}'>
    <div class="comment depth" style="margin-left: ${ (i.getDepth() - 1) * 3 }rem">
        <div class="commentInfo" data-comment-no="${i.getCno()}" data-comment-depth="${i.getDepth()}">
            #${i.getCno()}&nbsp;&nbsp;
            ${i.getId()}&nbsp;&nbsp;
            [<fmt:formatDate value="${i.getWriDate()}" pattern="yyyy-MM-dd a KK시mm분"/>]
        </div>
        <div class="commentContents">   
            <c:if test="${i.getDepth() > 1}">
                <span style="background-color: var(--bg-color-4)">#${i.getpCno()}에 대한 답글</span>&nbsp;
            </c:if>
            ${i.getComment()}
        </div>
        <div class="addCommentsComment">
            <!-- 로그인 정보에 따라 다르게 표현 -->
            <a href="">댓글달기</a>
            <c:if test="${sessionId eq i.getId()}">
                &nbsp;&nbsp;<a href="">수정하기</a>&nbsp;&nbsp;<a href="">삭제하기</a>
            </c:if>
        </div>
    </div>
    </c:forEach>
</div>

<!-- 댓글 더보기 -->
<!-- <div class="commentPlus"> -->
    <!-- 더보기 로딩용 이미지 -->
    <!-- <img src="resources/imgs/loading.gif" alt="" style="width: 2rem; height: 2rem">	 -->
    <!-- <button class="btnCommentPlus hidden">더보기</button> -->
<!-- </div> -->

<div class="writeComment">
    <textarea name="newComment" id="newCommentText" cols="30" rows="7" <c:if test="${empty sessionId}">placeholder="로그인을 하시면 댓글 작성이 가능합니다."</c:if>></textarea>
    <button class="btnAddComment">댓글 작성</button>
</div>
