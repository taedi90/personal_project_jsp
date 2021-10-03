
function cancelPostFunc() {
    let msg = "작성을 종료할까요?";
    openModal(msg, 1, confirmCancelPost);
    // 예를 눌렀을 때 할 액션 맵핑해주기
}

function confirmCancelPost() {
    let res = postAjax('views/board.jsp');
    document.getElementById("main").innerHTML = res;
    // $('#main', window.parent.document).html(data); 
    // window.parent.document.getElementById("main").innerHTML = data; //부모요소를 조작하려면 window.parent.document 를 활용
}

function savePostFunc() {
    oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []); //textarea의 id를 적어줍니다. 
    let category = document.getElementById("selectCategory").value;
    let title = document.getElementById("title").value;
    let content = document.getElementById("smartEditor").value;
    if (category == "") { 
        //alert("카테고리를 선택해주세요."); 
        openModal("카테고리를 선택해주세요.", 0);
        document.getElementById("category").focus();
        return; 
    } 
    if (title== null || title == "") { 
        //alert("제목을 입력해주세요."); 
        openModal("제목을 입력해주세요.", 0);
        document.getElementById("title").focus();
        return; 
    } 
    if(content == "" || content == null || content == '&nbsp;' || content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
        //alert("본문을 작성해주세요."); 
        openModal("본문을 작성해주세요.", 0);
        oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱 
        return; 
    }

    openModal("게시글을 등록하시겠습니까?", 1, confirmSavePost);

    // var result = confirm("발행 하시겠습니까?"); 
    // if(result){ 
    //     alert("등록 완료!"); 
    //     //$("#writePost").submit(); 
    // }else{ 
    //     return; 
    // } 

}

function confirmSavePost() {
    openModal("발행완료 (사실 아직임)", 0);
}


let oEditors = []; 

function editorInit(){
    oEditors = []; 
    nhn.husky.EZCreator.createInIFrame({ 
        oAppRef: oEditors, elPlaceHolder: "smartEditor", //저는 textarea의 id와 똑같이 적어줬습니다. 
        
        sSkinURI : "se2/SmartEditor2Skin.html", //경로를 꼭 맞춰주세요! 
        fCreator : "createSEditor2", htParams : { // 툴바 사용 여부 (true:사용/ false:사용하지 않음) 
            bUseToolbar : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음) 
            bUseVerticalResizer : false, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음) 
            bUseModeChanger : false 
        } 
    }); 
}




// $(function() { 
//     $("#savePost").click(function() { 
//         oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []); //textarea의 id를 적어줍니다. 
//         let category = document.getElementById("selectCategory").value;
//         let title = document.getElementById("title").value;
//         let content = document.getElementById("smartEditor").value;
//         if (category == "") { 
//             alert("카테고리를 선택해주세요."); 
//             document.getElementById("category").focus();
//             return; 
//         } 
//         if (title== null || title == "") { 
//             alert("제목을 입력해주세요."); 
//             document.getElementById("title").focus();
//             return; 
//         } 
//         if(content == "" || content == null || content == '&nbsp;' || content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
//             alert("본문을 작성해주세요."); 
//             oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱 
//             return; 
//         }
//         var result = confirm("발행 하시겠습니까?"); 
//         if(result){ 
//             alert("등록 완료!"); 
//             $("#writePost").submit(); 
//         }else{ 
//             return; 
//         } 
//     }); 
// })
