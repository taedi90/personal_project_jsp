<%@page import="personal_project_jsp.dto.User"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="personal_project_jsp.dao.BoardDao" %>
<%@ page import="personal_project_jsp.dao.impl.BoardDaoImpl" %>
<%@ page import="personal_project_jsp.dto.Board" %>
<%@ page import="personal_project_jsp.dto.Category" %>
<%@ page session ="true" %>
	
<%


	int idx = request.getParameter("idx") == null ? 1 : Integer.parseInt(request.getParameter("idx"));
	int num = request.getParameter("num") == null ? 10 : Integer.parseInt(request.getParameter("num"));
	String order = request.getParameter("order") == null ? "desc" : request.getParameter("order");
	String category = request.getParameter("category") == null ? "전체" : request.getParameter("category");
	int myPost = request.getParameter("myPost") == null ? 0 : Integer.parseInt(request.getParameter("myPost")); // 내글확인 
	System.out.println(myPost);
	
	// 키워드 검색인지 확인
	String keyword = request.getParameter("keyword");
	int keywordLength = 0;


	BoardDao dao = BoardDaoImpl.getInstance();
	Map<String, Object> map = null;
	
	System.out.println(keyword);

	if(keyword != null){
		keyword = keyword.trim();
		keywordLength = keyword.length();
	}
	
	if(myPost == 1){ // 내 글 보기
		User user = new User();
		user.setId((String)session.getAttribute("user"));
		map = dao.selectBoardById(user, idx, num, order);
	}
	else if(keywordLength > 0){ // 키워드로 보기
		//System.out.println("키워드로 검색");
		map = dao.selectBoardByKeyword(keyword, idx, num, order);
	}
	
	else if(category.equals("전체")){ // 전체 리스트 보기
		//System.out.println("전체 리스트 검색");
		map = dao.selectBoardByAll(idx, num, order);
	}else{ // 카테고리 검색
		System.out.println("카테고리 검색");
		Category ca = new Category(category);
		map = dao.selectBoardByCategory(ca, idx, num, order);
	}

%>

	<c:set var="map" value="<%= map %>"/>
	<c:set var="category" value="<%= category %>"/>

	<div id="categoryWrap">
		<c:if test="<%= keywordLength <= 0 && myPost == 1 %>">
			<div id="myPost" class="hidden">1</div>
			<span id="category">내 게시물 검색</span>
		</c:if>
		<c:if test="<%= keywordLength <= 0 && myPost != 1 %>">
			<span id="category">${category}</span>
		</c:if>
		<c:if test="<%= keywordLength > 0 %>">
			<div id="keyword" class="hidden"><%= keyword %></div>
			<span id="category">`<%= keyword %>` 검색 결과</span>
		</c:if>
        <div class="writePostButton">
            <button type="button" onclick="writePost()">글쓰기</button>
        </div>

	</div>
	
<c:if test='${map.get("maxPost") > 0}'>
    <div id="boardWrap">
        <div id="boardTop">
            <div id="status">
                <span id="nowPost">${map.get("nowPost")}</span> / ${map.get("maxPost")}
            </div>

            <div id="option">
                <div>
                	<c:set var="num" value="<%= num %>"/>
                    <select id="num" name="num" onchange="if(this.value) numChange()">
                        <option value="10" <c:if test="${num == 10}">selected</c:if>>10개씩 보기</option>
                        <option value="20" <c:if test="${num == 20}">selected</c:if>>20개씩 보기</option>
                        <option value="30" <c:if test="${num == 30}">selected</c:if>>30개씩 보기</option>
                    </select>
                </div>
                <div>
                	<c:set var="order" value="<%= order %>"/>
                    <select id="order" name="order" onchange="if(this.value) orderChange()">
                        <option value="desc"  <c:if test="${order == 'desc'}">selected</c:if>>최근작성순</option>
                        <option value="asc" <c:if test="${order == 'asc'}">selected</c:if>>작성일자순</option>
                    </select>
                </div>
            </div>
        </div>


        <div id="boardMain">
        
        <!-- 게시물 출력  status.index to -->
		<c:forEach var="i" items='${map.get("list")}' varStatus="status">
			<div id="post${i.getNo()}" class="postCard" data-no="${i.getNo()}" data-id="${i.getId()}" data-idx="${status.index}" onclick="postClick(this.dataset.no)">
				<div class="postDesc">
                    <p class="postCategory">${i.getCategory().getCategory()}</p>
                    <p class="postTitle">${i.getTitle()}</p>
                    <p class="postAuthor">${i.getId()}</p>
                    <p class="postDate"><fmt:formatDate value="${i.getWriDate()}" pattern="yyyy월 MM월 dd일"/></p>
                </div>
                <div class="postThumb">
                	<c:if test="${i.getThumb() != null}">
                		<img src="${i.getThumb()}"/>
                    </c:if>
                </div>
            </div>
            <div id="post${i.getNo()}_con" class="postBody hidden">
                <div class="postContainer">
                	<hr>
                	<p class="postModDate">최종 수정일 : ${i.getModDate()}</p>

                	<p class="postContentTitle">본문</p>
                	<p class="postContent">${i.getContent()}</p>
                	<button data-no="${i.getNo()}" onclick="modifyPostFunc(this.dataset.no)">수정</button>
                	<button data-no="${i.getNo()}" onclick="deletePostFunc(this.dataset.no)">삭제</button>
                	<p></p>
                </div>
            </div>
            <div id="post${i.getNo()}_comment" class="postComment">
				<!-- 댓글들이 추가될 자리 -->
				<!-- <img src="resources/imgs/loading.gif" alt="" style="width: 2rem; height: 2rem">			 -->
            </div>
            
        </c:forEach>
           
        </div>
        
        
        
        
        
        
        <div id="boardBottom">

        
        <c:set var="startIdx" value='${ map.get("nowPageIdx") - (map.get("nowPageIdx")%10 == 0 ? 10 : map.get("nowPageIdx")%10) + 1}' />
        <c:if test='${startIdx > 1}'>
       		<div id="prev" onclick="prev(${startIdx - 1})">이전</div>&nbsp;&nbsp;
       	</c:if>
        <c:forEach var="i" begin='${startIdx}' end='${startIdx + 9 > map.get("maxPageIdx")? map.get("maxPageIdx"):startIdx + 9}'>

        	
        	<c:choose>
        		<c:when test='${map.get("nowPageIdx") == i}'>
					<div class="nowPage">${i}</div>
				</c:when>
				<c:otherwise>
					<div class="pageSwap" onclick="pageSwap(${i})">${i}</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${i ne startIdx + 9}">&nbsp;|&nbsp;</c:if>
        	
        	
        </c:forEach>
        <c:if test='${startIdx + 9 < map.get("maxPageIdx")}'>
       		&nbsp;&nbsp;<div id="next" onclick="next(${startIdx + 10})">다음</div>
       	</c:if>

        </div>
    </div>
</c:if>
    
<c:if test='${map.get("maxPost") == null}'>
<hr />
	<div id="noContents">게시물이 없습니다.</div>
<hr />
</c:if>

    
