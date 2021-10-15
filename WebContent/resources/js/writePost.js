
function cancelPostFunc() {
    let msg = "작성을 종료할까요?";
    openModal(msg, 1, confirmCancelPost);
}

function confirmCancelPost() {
    let data = ajax("board",'', 'get');
    document.getElementById("main").innerHTML = data;
    // $('#main', window.parent.document).html(data); 
    // window.parent.document.getElementById("main").innerHTML = data; //부모요소를 조작하려면 window.parent.document 를 활용
}

function savePostFunc() {
    oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []); //textarea의 id를 적어줍니다. 
    let category = document.getElementById("selectCategory").value;
    let title = document.getElementById("title").value;
    let content = document.getElementById("smartEditor").value;
    if (category == "") { 
        openModal("카테고리를 선택해주세요.", 0);
        document.getElementById("category").focus();
        return; 
    } 
    if (title== null || title == "") { 
        openModal("제목을 입력해주세요.", 0);
        document.getElementById("title").focus();
        return; 
    } 
    if(content == "" || content == null || content == '&nbsp;' || content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
        openModal("본문을 작성해주세요.", 0);
        oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱 
        return; 
    }

    openModal("게시글을 등록하시겠습니까?", 1, confirmSavePost);
}

function confirmSavePost() {
    
    let param = {
        postNo: document.writePost.postNo.value,
        category: document.writePost.category.value,
        title: document.writePost.title.value,
        content: document.writePost.content.value,
		thumb: document.writePost.thumbSrc.value
    }

    let data;
    if(document.writePost.postNo.value !== ''){
        data = ajax("board", param, 'put','json');
    }else{
        data = ajax("board", param, 'post','json');
    }



    if (data.res == 1){
        openModal(data.comment, 0);
        data = ajax("board",'', 'get');
        main.innerHTML=data;
    }else{
        openModal(data.comment, 0);
    }

}


let oEditors = []; 

function editorInit(){
    oEditors = []; 
    nhn.husky.EZCreator.createInIFrame({ 
        oAppRef: oEditors, elPlaceHolder: "smartEditor", //저는 textarea의 id와 똑같이 적어줬습니다. 
        
        sSkinURI : "se2/SmartEditor2Skin.html", //경로를 꼭 맞춰주세요! 
        fCreator : "createSEditor2", htParams : { 
            bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
            elAppContainer : false
            //bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
            //bUseModeChanger : true // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
        } 
    }); 
    // if (document.postTemp){
    //     oEditors.getById["smartEditor"].exec("PASTE_HTML", document.postTemp.value);
    //     document.postTemp.removeChild();
    //     //oEditors.getById["smartEditor"].exec("PASTE_HTML", document.getElementById("postTemp").innerText);
    // }

}
