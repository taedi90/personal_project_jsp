<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
    <link href="resources/css/modal.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>

<body>

    <div class="modal hidden">
        <div class="modal_overlay"></div>
        <div class="modal_content">
            <div class="modal_close">
                <span class="closeModal">❌</span>
            </div>
            <div id="loginForm" class="form">
            	<form action="views/login.jsp" method="get">
                    <h2>로그인</h2>
                    <div class="form_row">
                        <div class="form_col1">
                            <label for="loginId">아이디</label>
                        </div>
                        <div class="form_col2">
                            <input id="loginId" name="loginId" type="text">
                        </div>
                    </div>
                    <div class="form_row">
                        <div class="form_col1">
                            <label for="loginPass">패스워드</label>
                        </div>
                        <div class="form_col2">
                            <input id="loginPass" name="loginPass" type="password">
                        </div>
                    </div>
                    <button type="submit">로그인</button>
                    <div>또는&nbsp;<a href="javascript:registerOn();">회원가입</a></div>
                </form>
            </div>
            <div id="registerForm" class="form hidden">
                <form action="views/register.jsp" method="post">
                    <h2>회원가입</h2>
                    <div class="form_row">
                        <div class="form_col1">
                            <label for="id">아이디</label>
                        </div>
                        <div class="form_col2">
                            <input id="id" name="id" type="text">
                        </div>
                    </div>
                    <div id="idChkOk" class="form_row hidden">
                    	이미 사용중인 아이디입니다.
                    </div>
                   	<div id="idChkNo" class="form_row hidden">
                    	아이디를 확인해주세요.
                    </div>
                    <div class="form_row">
                        <div class="form_col1">
                            <label for="password">패스워드</label>
                        </div>
                        <div class="form_col2">
                            <input id="password" name="password" type="password">
                        </div>
                    </div>
                    <div class="form_row">
                        <div class="form_col1">
                            <label for="name">이름</label>
                        </div>
                        <div class="form_col2">
                            <input id="name" name="name" type="text">
                        </div>
                    </div>
                    <div class="form_row">
                        <div class="form_col1">
                            <label for="email">이메일</label>
                        </div>
                        <div class="form_col2">
                            <input id="email" name="email" type="email">
                        </div>
                    </div>
                    <button type="submit">회원가입</button>
                    <div><a href="javascript:registerOff();">로그인으로</a></div>
                </form>
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
                setTimeout(function () { sidebar.classList.remove('appear') }, 300);
            } else {
                sidebar.classList.add("disappear");
                setTimeout(function () { sidebar.classList.add('hidden') }, 150);
                setTimeout(function () { sidebar.classList.remove('disappear') }, 200);
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
        
        const loginForm = document.getElementById("loginForm");
        const registerForm = document.getElementById("registerForm");
        
        function registerOn(){
        	loginForm.classList.add("hidden");
        	registerForm.classList.remove("hidden");
        }
        
        function registerOff(){
        	loginForm.classList.remove("hidden");
        	registerForm.classList.add("hidden");
        }        
        
        const id = document.getElementById("id");
        const idChkOk = document.getElementById("idChkOk");
        const idChkNo = document.getElementById("idChkNo");
        const idChkFunc = () => {
        	$.ajax({
                url : 'views/idchk.jsp', //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
                method : 'post',
                data : {
                    id : $.trim($("#id").val()),
                },			
                success : function(data){ //DB접근 후 가져온 데이터
                	let res = $.trim(data);
                	console.log(res + $("#id").val());
                	if (res == 0) {
                		$("#idChkOk").removeClass("hidden");
                		$("#idChkNo").addClass("hidden");
                	}else {
                		$("#idChkOk").addClass("hidden");
                		$("#idChkNo").removeClass("hidden");
                	}
                }		
            })
        }
        
        
        id.addEventListener("change", idChkFunc);

    </script>
</body>

</html>