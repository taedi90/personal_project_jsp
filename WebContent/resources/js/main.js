'use strict';

const main = document.getElementById("main");

// 게시판 로드
let boardData = ajax('board','','get');
main.innerHTML = boardData;


// 게시글 작성
function writePost(){
    if(loginChk()){
        openModal("로그인 정보가 없습니다. 지금 로그인 하시겠습니까?", 1, openLoginModal);
        return;
    }

    //let res = postAjax('views/writePost.jsp', {option: "new"});
    let data = ajax('writer', {},'get');
    main.innerHTML = data;
    editorInit();
}

function loginChk(){
    let data = postAjax('loginChk');
    if (data == 0){
        return true; // 로그인 오류가 true
    }else if (data.res == 1){
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
        no: postNo
    };

    let data = ajax('writer', param, 'post');
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

    let data = ajax("board", param,'delete','json');
    if (data.res == 1){
        openModal(data.comment);
        data = ajax('board','','get');
        main.innerHTML=data;
    }else{
        openModal(data.comment);
    }
}


function authPostChk(){
    let data = postAjax("AuthChk", {no: postNo});
    if (data == 0){
        openModal("서버 통신 오류");
        return true;
    }else if (data.res == 1){
        // 게시글 수정 권한 있을 경우
        return false;
    }else if (data.res == 2){
        openModal("로그인 정보가 없습니다. 지금 로그인 하시겠습니까?", 1, openLoginModal);
        return true
    }else{
        openModal(data.comment);
        return true;
    }
}
