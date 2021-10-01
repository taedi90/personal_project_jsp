<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="personal_project_jsp.dao.impl.CategoryDaoImpl" %>
<%@ page import="personal_project_jsp.dao.CategoryDao" %>
<%@ page import="personal_project_jsp.dto.Category" %>


<%	
	CategoryDao dao = CategoryDaoImpl.getInstance();	
	ArrayList<Category> arr = dao.selectCategoryByAll();
%>

검색(미구현)<br>

<ul> 카테고리<hr>
	<li class="category">전체</li>
	<c:set var="arr" value="<%= arr %>"/>
	<c:forEach var="i" items="${arr}">
		<li class="category">${i.getCategory()}</li>
	</c:forEach>
	<hr>
</ul>
	
<br>
<ul> 관리자(미구현)
<hr>
	<li>게시물 관리</li>
	<li>회원 관리</li>
<hr>
</ul>


<button id="open">로그인(미구현)</button>


<script>

$(function(){
    $(".category").click(function(){
    	console.log("카테고리 클릭" + $(this).text());
    	let cate = $(this).text();    	
    	if($(this).text() === "전체"){
    		cate = undefined;
    	}
    	
    	
        $.ajax({
            url : 'views/board.jsp', //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
            mathod : 'post',
            data : {
                category : cate
            },			
            success : function(data){ //DB접근 후 가져온 데이터
                $('#main').html(data);
                $('#main').animate({scrollTop:0}, 200);
            }		
        })
    })
})	

</script>