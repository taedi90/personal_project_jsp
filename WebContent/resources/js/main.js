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
    let res = postAjax('views/writePost.jsp', {option: "new"});
    main.innerHTML=res;
    //$('#main').html(res);

    // writer 초기화
    editorInit();
}

// 게시글 수정
let postNo = undefined;
function modifyPostFunc(no){
    postNo = no;
    openModal("수정하시겠습니까?", 1, modifyPostConfirm);
}

function modifyPostConfirm() {
    let data = postAjax("controller/authPostProc.jsp", {no: postNo});
    if (data == 0){
        openModal("오류");
        return
    }else if (data[0].res == 1){
        console.log(data[0].res + "메세지" + data[0].comment);
        //수정하러가기
        let param =  {
            no: postNo,
            option: "modify"
        };

        data = postAjax("views/writePost.jsp", param);
        main.innerHTML = data;
        editorInit();
    }else if (data[0].res == 2){
        openModal("로그인 정보가 없습니다. 지금 로그인 하시겠습니까?", 1, openLoginModal);
    }else{
        openModal(data[0].comment);
    }
}

// 게시글 삭제
function deletePostFunc(no){
    postNo = no;
    openModal("삭제하시겠습니까?", 1, deletePostConfirm);
}

function deletePostConfirm() {
    let data = postAjax("controller/authPostProc.jsp", {no: postNo});
    if (data == 0){
        openModal("오류");
        return
    }else if (data[0].res == 1){
        console.log(data[0].res + "메세지" + data[0].comment);
        //삭제하기
        let param =  {
            no: postNo
        };

        data = postAjax("controller/deletePostProc.jsp", param);
        if (data[0].res == 1){
            openModal(data[0].comment);
            data = postAjax("views/board.jsp");
            main.innerHTML=data;
        }else{
            openModal(data[0].comment);
        }



    }else if (data[0].res == 2){
        openModal("로그인 정보가 없습니다. 지금 로그인 하시겠습니까?", 1, openLoginModal);
    }else{
        openModal(data[0].comment);
    }
}
