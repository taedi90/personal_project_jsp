<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session ="true" %>


<c:set var="sessionId" value="${data.id}"/>
<c:set var="margin" value="${data.margin}"/>

<div>
    <p class="postCommentTitle">댓글</p>
</div>

<div class="commentBox">
    <c:forEach var="i" items='${data.dbResult}'>
        <c:if test="${i.delete ne true}">
            <div class="comment depth" style="margin-left: ${ (i.depth - 1) * margin }%">
                <div class="commentInfo" data-comment-no="${i.cno}" data-comment-depth="${i.depth}">
                    #${i.cno}&nbsp;&nbsp;
                    ${i.user.name}(${i.user.id})&nbsp;&nbsp;
                    [<fmt:formatDate value="${i.wriDate}" pattern="yyyy-MM-dd a KK:mm:ss"/>]
                    <c:if test="${not empty i.modDate}">
                        &nbsp;&nbsp;<span style="color: rgb(214, 97, 51)">[수정됨]</span>
                    </c:if>
                </div>
                <div class="commentContents">
                    <c:if test="${i.depth > 1}">
                        <span style="background-color: var(--bg-color-4)">#${i.cno}에 대한 답글</span>&nbsp;
                    </c:if>
                    <span id="cc${i.cno}">${i.comment}</span>
                </div>
                <div class="addCommentsComment">
                    <!-- 로그인 정보에 따라 다르게 표현 -->
                    <a href="#" onclick=" openNestedComment('${data.postNo}','${i.cno}')">댓글달기</a>&nbsp;
                    <c:if test="${sessionId eq i.user.id}">
                        <a href="#" onclick="modifyCommentFunc('${data.postNo}','${i.cno}')">수정하기</a>&nbsp;
                        <a href="#" onclick="deleteCommentFunc('${data.postNo}', '${i.cno}')">삭제하기</a>
                    </c:if>
                </div>
            </div>
        </c:if>
        <c:if test="${i.delete}">
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
    <div id="nestedComment-${data.postNo}" class="nestedComment hidden">
        <span id="nestedNum-${data.postNo}"></span> <button onclick="cancelNestedComment('${data.postNo}', 'nestedComment-${data.postNo}')">취소</button>
    </div>

    <textarea name="newComment" id="ct${data.postNo}" cols="30" rows="7" <c:if test="${empty sessionId}">placeholder="로그인을 하시면 댓글 작성이 가능합니다."</c:if>></textarea>
    <!-- Comment(long postNo, long pCno, String id, String comment) -->
    <c:if test="${not empty sessionId}">
        <button id="commentBtnNo-${data.postNo}" type="button" class="btnAddComment" data-post-no="${data.postNo}" data-parent-no="" data-modify-no="" data-comment-text="ct${data.postNo}" onclick="insertCommentFunc(this)">댓글 작성</button>
    </c:if>
    <c:if test="${empty sessionId}">
        <button id="commentBtnNo-${data.postNo}" type="button" class="btnAddComment" data-post-no="${data.postNo}" data-parent-no="" data-modify-no="" data-comment-text="ct${data.postNo}" onclick="openLoginModal()">로그인 하기</button>
    </c:if>

</div>
