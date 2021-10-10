<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, maximum-scale=1">
    <title>자유 게시판</title>
    <link href="resources/css/main.css?ver=3" rel="stylesheet">
    <link href="resources/css/board.css?ver=2" rel="stylesheet">
    <link href="resources/css/sidebar.css?ver=1" rel="stylesheet">
    <link href="resources/css/modal.css?ver=3" rel="stylesheet">
    <link href="resources/css/writePost.css?ver=1" rel="stylesheet">
    <link href="resources/css/login.css?ver=1" rel="stylesheet">
    <link href="resources/css/uploadThumb.css?ver=1" rel="stylesheet">
    <link rel="shortcut icon" href="resources/imgs/favicon.svg">
    <script src="resources/js/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous" defer></script>
</head>

<body>
    <!-- 로그인 & 모달창 -->
    <jsp:include page="views/login.jsp" flush="true"></jsp:include>
    <jsp:include page="views/modal.jsp" flush="true"></jsp:include>
    <jsp:include page="views/uploadThumb.jsp" flush="true"></jsp:include>

    <div id="wrap">
        <div id="header">
            <button id="btnSidebar">☰</button>
            <h3><a href="">자유게시판</a></h3>
            <c:if test='${user ne null}'>
                <div id="loginStat" class="afterLogin">${name}님 안녕하세요!</div>
            </c:if>
            <c:if test='${user eq null}'>
                <!-- <div id="loginStat" class="afterLogin hidden"></div> -->
                <div id="loginStat" class="afterLogin">테스트 계정 id: test1 & pass: 1234</div>

            </c:if>

        </div>
        <div id="container">
            <div id="sidebar" class="hidden">
                <jsp:include page="views/sidebar.jsp" flush="true"></jsp:include>
            </div>
            <div id="main">
                <jsp:include page="views/board.jsp?num=10&order=desc" flush="true"></jsp:include>
            </div>
        </div>
        <div id="footer"><a style="text-decoration: none; color: white" href="https://github.com/taedi90/personal_project_jsp" target="_self">https://github.com/taedi90/personal_project_jsp</a></div>
		<!-- <div id="footer">Copyright 2021. Author all rights reserved.</div> -->
    </div>

    <script type="text/javascript" src="resources/js/main.js?ver=1" defer></script>
    <script type="text/javascript" src="resources/js/board.js?ver=2" defer></script>
    <script type="text/javascript" src="resources/js/login.js?ver=1" defer></script>
    <script type="text/javascript" src="resources/js/modal.js?ver=1" defer></script>
    <script type="text/javascript" src="resources/js/sidebar.js?ver=2" defer></script>
    <script type="text/javascript" src="resources/js/jAjax.js?ver=1" defer></script>
    <!-- SmartEditor2 라이브러리 -->
    <script type="text/javascript" src="se2/js/HuskyEZCreator.js" charset="utf-8" defer></script>
    <script type="text/javascript" src = "resources/js/writePost.js?ver=1" defer></script>
    <script type="text/javascript" src="resources/js/thumbUpload.js?ver=1" defer></script>
</body>

</html>