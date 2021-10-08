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
	
	
	int maxDepth = 0;
	double margin = 3.0; // 대댓글 간격
	if(list != null){
		for (Comment c : list){
			if(c.getDepth() >  maxDepth){
				maxDepth = c.getDepth();
			}
		}
		margin = 40.0 / maxDepth;

		if (margin > 3){
			margin = 3;
		}
		System.out.println("최대 깊이" + maxDepth + "마진 사이즈" + margin);
	}

%>

<c:set var="list" value="<%= list %>"/>
<c:set var="sessionId" value="<%= sessionId %>"/>
<c:set var="margin" value="<%= margin %>"/>

<div>
    <p class="postCommentTitle">댓글</p>
</div>

<div class="commentBox">    
    <c:forEach var="i" items='${list}'>
        <c:if test="${i.isDelete() ne true}">
            <div class="comment depth" style="margin-left: ${ (i.getDepth() - 1) * margin }%">
                <div class="commentInfo" data-comment-no="${i.getCno()}" data-comment-depth="${i.getDepth()}">
                    #${i.getCno()}&nbsp;&nbsp;
                    ${i.getUser().getName()}(${i.getUser().getId()})&nbsp;&nbsp;
                    [<fmt:formatDate value="${i.getWriDate()}" pattern="yyyy-MM-dd a KK:mm:ss"/>]
                    <c:if test="${not empty i.getModDate()}">
                        &nbsp;&nbsp;<span style="color: rgb(214, 97, 51)">[수정됨]</span>
                    </c:if>
                </div>
                <div class="commentContents">   
                    <c:if test="${i.getDepth() > 1}">
                        <span style="background-color: var(--bg-color-4)">#${i.getpCno()}에 대한 답글</span>&nbsp;
                    </c:if>
                    <span id="cc${i.getCno()}">${i.getComment()}</span>
                </div>
                <div class="addCommentsComment">
                    <!-- 로그인 정보에 따라 다르게 표현 -->
                    <a href="#" onclick=" openNestedComment('<%= postNo %>','${i.getCno()}')">댓글달기</a>&nbsp;
                    <c:if test="${sessionId eq i.getUser().getId()}">
                        <a href="#" onclick="modifyCommentFunc('<%= postNo %>','${i.getCno()}')">수정하기</a>&nbsp;
                        <a href="#" onclick="deleteCommentFunc('<%= postNo %>', '${i.getCno()}')">삭제하기</a>
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${i.isDelete() eq true}">
            <div class="comment depth" style="margin-left: ${ (i.getDepth() - 1) * margin }%">
                <div class="commentContents" style="color: var(--txt-color-3); padding: 2rem;">   
                    삭제 된 게시물입니다.
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>

<!-- 댓글 더보기 -->
<!-- <div class="commentPlus"> -->
    <!-- 더보기 로딩용 이미지 -->
    <!-- <img src="resources/imgs/loading.gif" alt="" style="width: 2rem; height: 2rem">	 -->
    <!-- <button class="btnCommentPlus hidden">더보기</button> -->
<!-- </div> -->

<div class="writeComment">
    <div id="nestedComment-<%= postNo %>" class="nestedComment hidden">
        <span id="nestedNum-<%= postNo %>"></span> <button onclick="cancelNestedComment('<%= postNo %>', 'nestedComment-<%= postNo %>')">취소</button>
    </div>
    
    <textarea name="newComment" id="ct<%= postNo %>" cols="30" rows="7" <c:if test="${empty sessionId}">placeholder="로그인을 하시면 댓글 작성이 가능합니다."</c:if>></textarea>
    <!-- Comment(long postNo, long pCno, String id, String comment) -->
    <c:if test="<%= sessionId != null %>">
        <button id="commentBtnNo-<%= postNo %>" type="button" class="btnAddComment" data-post-no="<%= postNo %>" data-parent-no="" data-modify-no="" data-comment-text="ct<%= postNo %>" onclick="insertCommentFunc(this)">댓글 작성</button>
    </c:if>
    <c:if test="<%= sessionId == null %>">
        <button id="commentBtnNo-<%= postNo %>" type="button" class="btnAddComment" data-post-no="<%= postNo %>" data-parent-no="" data-modify-no="" data-comment-text="ct<%= postNo %>" onclick="openLoginModal()">로그인 하기</button>
    </c:if>

</div>
