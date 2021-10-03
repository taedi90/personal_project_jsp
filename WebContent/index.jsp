<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
    <link href="resources/css/writePost.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous" defer></script>
</head>

<body>
    <!-- 로그인 & 모달창 -->
    <jsp:include page="views/login.jsp" flush="true"></jsp:include>

    <div id="wrap">
        <div id="header">
            <button id="btnSidebar">☰</button>
            <h3><a href="">자유게시판</a></h3>
            <c:if test='${user ne null}'>
                <div id="loginStat" class="afterLogin">${name}님 안녕하세요!</div>
            </c:if>
            <c:if test='${user eq null}'>
                <div id="loginStat" class="afterLogin hidden"></div>
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
        <div id="footer">Copyright 2021. Author all rights reserved.</div>
    </div>

    <script src="resources/js/main.js" defer></script>
    <script src="resources/js/board.js" defer></script>
    <script src="resources/js/login.js" defer></script>
    <script src="resources/js/modal.js" defer></script>
    <!-- <script src="resources/js/fetch.js" defer></script> -->
    <!-- <script src="resources/js/ajax.js" defer></script> -->
    <script src="resources/js/jAjax.js" defer></script>
    <!-- SmartEditor2 라이브러리 -->
    <script type="text/javascript" src="se2/js/HuskyEZCreator.js" charset="utf-8" defer></script>
    <script type="text/javascript" src = "resources/js/writePost.js" defer></script>
</body>

</html>