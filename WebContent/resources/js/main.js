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


// 게시글 작성
function writePost(){
    if(loginChk()){
        openModal("로그인 정보가 없습니다. 지금 로그인 하시겠습니까?", 1, openLoginModal);
        return;
    }

    let res = postAjax('views/writePost.jsp', {option: "new"});
    main.innerHTML=res;
    editorInit();
}

function loginChk(){
    let data = postAjax('controller/loginChkProc.jsp');
    if (data == 0){
        return true; // 로그인 오류가 true
    }else if (data[0].res == 1){
        return false;
    }else{
        return true;
    }

}



// 게시글 수정
let postNo = undefined;
function modifyPostFunc(no){
    postNo = no;

    if(loginChk()){
        openModal("로그인 정보가 없습니다. 지금 로그인 하시겠습니까?", 1, openLoginModal);
        return;
    }

    // 게시글 수정 & 삭제 권한 확인
    if(authPostChk()){
        return;
    }


    openModal("수정하시겠습니까?", 1, modifyPostConfirm);
}

function modifyPostConfirm() {
    // 게시글 수정
    let param =  {
        no: postNo,
        option: "modify"
    };

    let data = postAjax("views/writePost.jsp", param);
    main.innerHTML = data;
    editorInit();
}



// 게시글 삭제
function deletePostFunc(no){
    postNo = no;

    // 로그인 여부 확인
    if(loginChk()){
        openModal("로그인 정보가 없습니다. 지금 로그인 하시겠습니까?", 1, openLoginModal);
        return;
    }

    // 게시글 삭제 권한 확인
    if(authPostChk()){
        return;
    }

    
    openModal("삭제하시겠습니까?", 1, deletePostConfirm);
}

// 게시물 삭제 "예" 선택시
function deletePostConfirm() {
    let param =  {
        no: postNo
    };

    let data = postAjax("controller/deletePostProc.jsp", param);
    if (data[0].res == 1){
        openModal(data[0].comment);
        data = postAjax("views/board.jsp");
        main.innerHTML=data;
    }else{
        openModal(data[0].comment);
    }
}


function authPostChk(){
    let data = postAjax("controller/authPostProc.jsp", {no: postNo});
    if (data == 0){
        openModal("서버 통신 오류");
        return true;
    }else if (data[0].res == 1){
        // 게시글 수정 권한 있을 경우
        return false;
    }else if (data[0].res == 2){
        openModal("로그인 정보가 없습니다. 지금 로그인 하시겠습니까?", 1, openLoginModal);
        return true
    }else{
        openModal(data[0].comment);
        return true;
    }
}
