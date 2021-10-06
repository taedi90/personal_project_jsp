'use strict';
let lastCategory = '전체';
let lastNum = 10;

// 게시물 수 변경
function numChange(){
	
	let nowPageIdx = Math.ceil(($("#nowPost").text() - lastNum + 1) / $("#num").val());
	
    let data = {
        idx : nowPageIdx < 1 ? 1 : nowPageIdx,
        num : $("#num").val(),
        order : $("#order").val(),
        myPost : document.getElementById("myPost") == null ? 0 : document.getElementById("myPost").textContent,
        category : lastCategory,
        keyword : $("#keyword").text()

    }
    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
    lastNum = $("#num").val();
}

// 정렬 변경(오름차순, 내림차순)
function orderChange(){
    let data = {
        idx : 1,
        num : $("#num").val(),
        order : $("#order").val(),
        myPost : document.getElementById("myPost") == null ? 0 : document.getElementById("myPost").textContent,
        category : lastCategory,
        //keyword : $("#keyword").text()
    }
    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
}

// 내 게시물 검색
function myPostSearch(category){

    let data = {
        idx : 1,
        num : $("#num").val(),
        order : $("#order").val(),
        myPost : 1
        //keyword : $("#keyword").text()
    }
    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
    sidebar.classList.add("hidden");
}

// 카테고리 검색
function categoryChange(category){

    lastCategory = category;

    let data = {
        idx : 1,
        num : $("#num").val(),
        order : $("#order").val(),
        category : lastCategory,
        //keyword : $("#keyword").text()
    }
    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
    sidebar.classList.add("hidden");
}

// 다른 페이지글 
function pageSwap(idx){
    let data = {
        idx : idx,
        num : $("#num").val(),
        order : $("#order").val(),
        myPost : document.getElementById("myPost") == null ? 0 : document.getElementById("myPost").textContent,
        category : lastCategory,
        //keyword : $("#keyword").text()
    }
    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
}

// 다음 10개 페이지
function next(idx){
    let data = {
        idx : idx,
        num : $("#num").val(),
        order : $("#order").val(),
        myPost : document.getElementById("myPost") == null ? 0 : document.getElementById("myPost").textContent,
        category : lastCategory,
        keyword : $("#keyword").text()
    }

    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
}

// 이전 10개 페이지
function prev(idx){
    let data = {
        idx : idx,
        num : $("#num").val(),
        order : $("#order").val(),
        myPost : document.getElementById("myPost") == null ? 0 : document.getElementById("myPost").textContent,
        category : lastCategory,
        keyword : $("#keyword").text()
    }
    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
}

// 게시물 클릭
function postClick(idx){
    console.log("게시물 클릭");
    let item = document.getElementById("post" + idx + "_con"); 
    let comm = document.getElementById("post" + idx + "_comment"); 
    let postBodys = document.getElementsByClassName("postBody"); //본문 리스트
    let postComms = document.getElementsByClassName("postComment"); //댓글 리스트


    // 전체 다 닫고 선택 된 것만 열거나 닫기
    if(item.classList.contains("hidden")){
        for(let i = 0; i < postBodys.length; i++){
            postBodys.item(i).classList.add("hidden");
        }
        for(let i = 0; i < postComms.length; i++){
            postComms.item(i).classList.add("hidden");
        }
        item.classList.remove("hidden");
        comm.classList.remove("hidden");
    }else{
        for(let i = 0; i < postBodys.length; i++){
            postBodys.item(i).classList.add("hidden");
        }
        for(let i = 0; i < postComms.length; i++){
            postComms.item(i).classList.add("hidden");
        }
    }

    // 댓글 불러오기
    selectCommentFunc(idx);
    

}

// 검색 (간단하게만..)
function searchPostFunc(keyword) {
    let data = {
        idx : 1,
        num : $("#num").val(),
        order : $("#order").val(),
        category : "전체",
        keyword : keyword
    }
    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
    sidebar.classList.add("hidden");
}

// 댓글 불러오기
function selectCommentFunc(idx){

    let param = {
        postNo: idx 
    };
    let res = postAjax("views/comment.jsp", param);

    let comTag = "#post" + idx + "_comment";

    $(comTag).html(res);
}

// 대댓글 쓰기 (글에서 벗어날 때 비워줄지 고민)
function openNestedComment(postNo, idx){
    cancelNestedComment(postNo, idx);
    document.getElementById('nestedComment-' + postNo).classList.remove("hidden");
    document.getElementById('nestedNum-' + postNo).innerText = '#' + idx + ' 글에 답글쓰기';
    document.getElementById('commentBtnNo-' + postNo).dataset.parentNo = idx;
    location.href='#nestedComment-' + postNo;
    main.scrollBy(0, -(window.innerHeight/4));

}

// 대댓글 & 내댓글수정 취소
function cancelNestedComment(postNo, idx){

    document.getElementById('nestedComment-' + postNo).classList.add("hidden"); // 전체 박스
    document.getElementById('nestedNum-' + postNo).innerText = ''; // 안내글
    document.getElementById('commentBtnNo-' + postNo).dataset.parentNo = ''; // 대댓글 상위 번호
    document.getElementById('commentBtnNo-' + postNo).dataset.modifyNo = ''; // 수정할 댓글번호
    document.getElementById('ct' + postNo).value = ''; // 댓글 작성창

}


// 댓글 수정
function modifyCommentFunc(postNo, idx){
    cancelNestedComment(postNo, idx);
    document.getElementById('nestedComment-' + postNo).classList.remove("hidden");
    document.getElementById('nestedNum-' + postNo).innerText = '#' + idx + ' 글 수정';
    document.getElementById('commentBtnNo-' + postNo).dataset.modifyNo = idx;
    document.getElementById('ct' + postNo).value = document.getElementById('cc' + idx).textContent; // 댓글 작성창
    location.href='#nestedComment-' + postNo;
    main.scrollBy(0, -(window.innerHeight/4));

}

// 댓글 삭제
let deleteIdx = undefined;
let deletePostNo = undefined;
function deleteCommentFunc(postNo, idx){

    deleteIdx = idx;
    deletePostNo = postNo;
    openModal("정말 삭제하시겠습니까?", 1, deleteCommentConfirm);

}

function deleteCommentConfirm(){

    let param = {
        cno: deleteIdx
    }

    let res =  postAjax('controller/board/deleteCommentProc.jsp' , param);

    if(res[0].res == 1){
        // 댓글 리로드
        selectCommentFunc(deletePostNo);
        openModal("삭제 완료!");

    }else{
        openModal("삭제에 실패했습니다.");
    }

    deleteIdx = undefined;
    deletePostNo = undefined;

}





// 댓글 작성 버튼 클릭
function insertCommentFunc(elem) {

    // 로그인 확인(나중에)

    // 수정인지 확인하기
    if(elem.dataset.modifyNo != ''){
        console.log("댓글 수정 업로드");
        updateCommentFunc(elem);
    }else{
        console.log("댓글 신규 업로드");
        newComment(elem);
    }

    //작성글로 이동 // 하이라이트 효과

}

// 댓글 신규 업로드
function newComment(elem){
    // 데이터 받아오기
    let param = {
        postNo: elem.dataset.postNo,
        parentNo: elem.dataset.parentNo,
        comment: document.getElementById(elem.dataset.commentText).value
    }


    let res =  postAjax('controller/board/writeCommentProc.jsp' , param);

    if(res[0].res == 1){
        // 댓글 리로드
        selectCommentFunc(elem.dataset.postNo);
        openModal("작성 성공!");
    }else{
        openModal("댓글 작성에 실패했습니다.");
    }
}

// 댓글 수정 업로드
function updateCommentFunc(elem){
    // 데이터 받아오기
    let param = {
        cno: elem.dataset.modifyNo,
        comment: document.getElementById(elem.dataset.commentText).value,
    }


    let res =  postAjax('controller/board/updateCommentProc.jsp' , param);

    if(res[0].res == 1){
        // 댓글 리로드
        selectCommentFunc(elem.dataset.postNo);
        openModal("수정 성공!");

    }else{
        openModal("댓글 작성에 실패했습니다.");
    }
}