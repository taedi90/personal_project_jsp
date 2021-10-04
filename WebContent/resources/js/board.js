'use strict';
let lastCategory = '전체';
let lastNum = 10;

// 게시물 수 변경
function numChange(){
    let data = {
        idx : Math.ceil(($("#nowPost").text() - lastNum + 1) / $("#num").val()),
        num : $("#num").val(),
        order : $("#order").val(),
        category : lastCategory,
        keyword : $("#keyword").text()

    }
    let res = postAjax("views/board.jsp", data);

    $('#main').html(res);
    $('#main').animate({scrollTop:0}, 200);
    lastNum = $("#num").val();
}

// 정렬 변경
function orderChange(){
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
}

// 카테고리 변경
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
    let postBodys = document.getElementsByClassName("postBody")

    if(item.classList.contains("hidden")){
        for(let i = 0; i < postBodys.length; i++){
            postBodys.item(i).classList.add("hidden");
        }
        item.classList.remove("hidden");
    }else{
        for(let i = 0; i < postBodys.length; i++){
            postBodys.item(i).classList.add("hidden");
        }
    }

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