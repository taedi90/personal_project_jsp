

<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="personal_project_jsp.dao.BoardDao" %>
<%@ page import="personal_project_jsp.dao.impl.BoardDaoImpl" %>
<%@ page import="personal_project_jsp.dto.Board" %>
	
<%


	int idx = request.getParameter("idx") == null ? 1 : Integer.parseInt(request.getParameter("idx"));
	int num = request.getParameter("num") == null ? 10 : Integer.parseInt(request.getParameter("num"));
	String order = request.getParameter("order") == null ? "desc" : request.getParameter("order");
	
	BoardDao dao = BoardDaoImpl.getInstance();
	
	Map<String, Object> map = dao.selectBoardByAll(idx, num, order);
%>

<%-- <!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판</title>
<!--     <link href="../resources/css/main.css" rel="stylesheet">
    <link href="../resources/css/board.css" rel="stylesheet"> -->
</head>

<body> --%>

	<c:set var="map" value="<%= map %>"/>

    <div>
        <div id="boardTop">
            <div id="status">
                <span id="nowPost">${map.get("nowPost")}</span> / ${map.get("maxPost")}
            </div>

            <div id="option">
                <div>
                	<c:set var="num" value="<%= num %>"/>
                    <select id="num" name="num">
                        <option value="10" <c:if test="${num == 10}">selected</c:if>>10개씩 보기</option>
                        <option value="20" <c:if test="${num == 20}">selected</c:if>>20개씩 보기</option>
                        <option value="30" <c:if test="${num == 30}">selected</c:if>>30개씩 보기</option>
                    </select>
                </div>
                <div>
                	<c:set var="order" value="<%= order %>"/>
                    <select id="order" name="order">
                        <option value="desc"  <c:if test="${order == 'desc'}">selected</c:if>>최근작성순</option>
                        <option value="asc" <c:if test="${order == 'asc'}">selected</c:if>>작성일자순</option>
                    </select>
                </div>
            </div>
        </div>


        <div id="boardMain">
        
        <!-- 게시물 출력 -->
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
            <div id="post${status.index}_con" class="postBody hidden">
                <div class="postContainer">
                	<hr>
                	<p class="modDate">최종 수정일 : ${i.getModDate()}</p>

                	<p class="postContentTitle">본문</p>
                	<p class="postContent">${i.getContent()}</p>
                	<button>수정</button>
                	<button>삭제</button>
                	<p></p>
                </div>
            </div>
        </c:forEach>
           
        </div>
        <div id="boardBottom">

        
        <c:set var="startIdx" value='${ map.get("nowPageIdx") - (map.get("nowPageIdx")%10 == 0 ? 10 : map.get("nowPageIdx")%10) + 1}' />
        <c:if test='${startIdx > 1}'>
       		<div id="prev" value="${startIdx - 1}">이전</div>&nbsp;&nbsp;
       	</c:if>
        <c:forEach var="i" begin='${startIdx}' end='${startIdx + 9 > map.get("maxPageIdx")? map.get("maxPageIdx"):startIdx + 9}'>

        	
        	<c:choose>
        		<c:when test='${map.get("nowPageIdx") == i}'>
					<div class="nowPage">${i}</div>
				</c:when>
				<c:otherwise>
					<div class="pageSwap">${i}</div>
				</c:otherwise>
			</c:choose>
			<c:if test="${i ne startIdx + 9}">&nbsp;|&nbsp;</c:if>
        	
        	
        </c:forEach>
        <c:if test='${startIdx + 9 < map.get("maxPageIdx")}'>
       		&nbsp;&nbsp;<div id="next" value="${startIdx + 10}">다음</div>
       	</c:if>

        </div>
    </div>
    
    
    
    <script>
    
    
    $(function(){
        $("#num").change(function(){
        	console.log("num 버튼 토글" +  $("#nowPost").text() + parseInt($("#num").val()));
            $.ajax({
                url : 'views/board.jsp', //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
                mathod : 'post',
                data : {
                    idx : Math.ceil($("#nowPost").text() / $("#num").val()),
                    num : $("#num").val(),
                    order : $("#order").val()
                },			
                success : function(data){ //DB접근 후 가져온 데이터
                    $('#main').html(data);
                }		
            })
        })
    })	
    
    $(function(){
        $("#order").change(function(){
        	console.log("order 버튼 토글");
            $.ajax({
                url : 'views/board.jsp', //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
                mathod : 'post',
                data : {
                    idx : 1,
                    num : $("#num").val(),
                    order : $("#order").val()
                },			
                success : function(data){ //DB접근 후 가져온 데이터
                    $('#main').html(data);
                }		
            })
        })
    })	
    
    $(function(){
        $(".pageSwap").click(function(){
        	console.log("페이지 인덱스 클릭" + $(this).text());
        	
            $.ajax({
                url : 'views/board.jsp', //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
                mathod : 'post',
                data : {
                    idx : $(this).text(),
                    num : $("#num").val(),
                    order : $("#order").val()
                },			
                success : function(data){ //DB접근 후 가져온 데이터
                    $('#main').html(data);
                    $('#main').animate({scrollTop:0}, 200);
                }		
            })
        })
    })	
    
    $(function(){
        $("#next").click(function(){
        	console.log("페이지 인덱스 클릭" + $(this).text());
        	
            $.ajax({
                url : 'views/board.jsp', //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
                mathod : 'post',
                data : {
                    idx : $(this).attr("value"),
                    num : $("#num").val(),
                    order : $("#order").val()
                },			
                success : function(data){ //DB접근 후 가져온 데이터
                    $('#main').html(data);
                    $('#main').animate({scrollTop:0}, 200);
                }		
            })
        })
    })	
    
    $(function(){
        $("#prev").click(function(){
        	console.log("페이지 인덱스 클릭" + $(this).text());
        	
            $.ajax({
                url : 'views/board.jsp', //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
                mathod : 'post',
                data : {
                    idx : $(this).attr("value"),
                    num : $("#num").val(),
                    order : $("#order").val()
                },			
                success : function(data){ //DB접근 후 가져온 데이터
                    $('#main').html(data);
                    $('#main').animate({scrollTop:0}, 200);
                }		
            })
        })
    })	
    
    $(function(){
        $(".postCard").click(function(){
        	console.log("게시물 클릭");
        	const item = "#" + $(this).attr('id') + "_con";
        	if($(item).hasClass("hidden")){
            	$(".postBody").addClass("hidden");
                $(item).removeClass("hidden");
        	}else{
        		$(".postBody").addClass("hidden");
        	}
            
        })
    })	
    
    
    </script>
    
    

<%-- </body>

</html> --%>