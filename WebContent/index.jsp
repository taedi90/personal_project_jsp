

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!doctype html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>자유 게시판</title>
	<link href="resources/css/main.css" rel="stylesheet">
	<link href="resources/css/board.css" rel="stylesheet">
	<link href="resources/css/sidebar.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	
</head>
<body>
<body>

    <div class="modal hidden">
        <div class="modal_overlay"></div>
        <div class="modal_content">
            <button class="closeModal">❌</button>
            <h2>로그인</h2>
            <label for="id">아이디</label>
            <input type="text"> <br>
            <label for="id">패스워드</label>
            <input type="password"> <br>
            <button>로그인</button>
            또는
            <a href="#">회원가입</a>
            <div id="registerForm" class="hidden">
                <label for="id">이름</label>
                <input type="password"> <br>      
                <label for="id">이메일</label>                          
                <input type="password"> <br>            
                <button>가입하기</button>                    
            </div>
            
        </div>
    </div>


    <div id="wrap">
        <div id="header">
            <button id="btnSidebar">☰</button>
            <h3>자유게시판</h3>
            
            
        </div>
        <div id="container">
            <div id="sidebar" class="hidden">
				<jsp:include page="views/sidebar.jsp" flush="true"></jsp:include>
            </div>
            <div id="main">
            	<jsp:include page="views/board.jsp?num=10&order=desc" flush="true"></jsp:include>
            </div>
        </div>
        <div id="footer">Copyright 2021. Author all rights reserved.</div>
    </div>




    <script>
        const btnSidebar = document.getElementById("btnSidebar");
        const sidebar = document.getElementById("sidebar");

        const toggleSidebar = () => {
            if (sidebar.className === "hidden") {
                sidebar.classList.remove("hidden");
                sidebar.classList.add("appear");
                setTimeout(function(){ sidebar.classList.remove('appear')},300);
            }else {
                sidebar.classList.add("disappear");
                setTimeout(function(){ sidebar.classList.add('hidden')},150);
                setTimeout(function(){ sidebar.classList.remove('disappear')},200);
            }
        }

        btnSidebar.addEventListener("click", toggleSidebar);


        const openButton = document.getElementById("open");
        const modal = document.querySelector(".modal");
        const overlay = modal.querySelector(".modal_overlay");
        const closeBtn = modal.querySelector(".closeModal");
        
        const openModal = () => {
            modal.classList.remove("hidden");
            document.body.style.overflow = "hidden";
        }

        openButton.addEventListener("click", openModal);
        
        const closeModal = () => {
            modal.classList.add("hidden");
            document.body.style.overflow = "auto";
        }
        closeBtn.addEventListener("click", closeModal);
        
    </script>
</body>
</html>