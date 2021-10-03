'use strict';

// 사이드바 on off
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

const main = document.getElementById("main");

function writePost(){
    //main.innerHTML='<object type="text/html" data="views/writePost.jsp" ></object>';
    let res = postAjax('views/writePost.jsp');
    main.innerHTML=res;
    //$('#main').html(res);

    // writer 초기화
    editorInit();
}