'use strict';

let uploadBox = document.querySelector('.uploadModalContent');  // 드래그 앤 드롭으로 변경시킬 배경 영역
let uploadStat = document.querySelector('.uploadStat'); // 업로드 하단 상태 메세지
let inputMethod = document.getElementById("hiddenInput"); // 인풋박스
let firstView = document.getElementById("firstView"); // 파일 찾기 화면
let secondView = document.getElementById("secondView"); // 업로드 화면
let uploadBtn = document.querySelector(".submitBtn"); // 업로드 버튼

let btnCloseUpload = document.querySelector('.btnCloseUpload'); // 창닫기
let uploadModalWindow = document.querySelector('.uploadModal'); // 모달창

let formData = new FormData(); // FromData객체는 HTML5이기 때문에,, 호환을 잘 확인하고 사용. IE의 경우 10버전 이상만 가능!
let data = undefined; // 파일 정보, //data.type //data.size // 통과해야하는걸로
// 'image/jpeg', 'image/gif', 'image/png'


// 모달창 열기(업로드 화면 hidden 설정 다시 해줘야함)
function openUploadModal(){
    uploadModalWindow.classList.remove("hidden");
    
    //firstView.classList.add("hidden");
    //secondView.classList.remove("hidden");

    document.body.style.overflow = "hidden";
    inputMethod.value = '';

}

// 모달창 닫기
function closeUploadModal(){
    uploadModalWindow.classList.add("hidden");
    firstView.classList.remove("hidden");
    secondView.classList.add("hidden");
    document.body.style.overflow = "auto";
    uploadStat.innerHTML = "파일을 여기에 끌어다 놓을 수 있습니다."

}
btnCloseUpload.addEventListener("click", closeUploadModal);





/* 박스 밖으로 Drag가 나갈 때 */
uploadBox.addEventListener('dragleave', function(e) {
    console.log('dragleave');

    this.style.backgroundColor = 'white';
});

/* 박스 안에 Drag를 하고 있을 때 */
uploadBox.addEventListener('dragover', function(e) {
    e.preventDefault();
    console.log('dragover');
    this.style.backgroundColor = '#ed7a7a';
});


/* 박스 안에서 Drag를 Drop했을 때 */
uploadBox.addEventListener('drop', function(e) {
    e.preventDefault();

    this.style.backgroundColor = 'white';

    data = e.dataTransfer.files[0];
    
    uploadFileChk();

});

// 파일 선택시
inputMethod.addEventListener("change", () =>{

    data = inputMethod.files[0];
    uploadFileChk();
    
});



// 파일 확인
function uploadFileChk(){

    // 확장자 확인
    if (data.type != 'image/jpeg' && data.type != 'image/gif' && data.type != 'image/png'){
        uploadStat.innerHTML = "지정 된 확장자(.jpg, .png, .gif)만 가능!";
        document.getElementById("secondView").classList.add("hidden");
        document.getElementById("firstView").classList.remove("hidden");
        return;
    }

    // 사이즈 확인
    if (data.size > 20 * 1024 * 1024){
        uploadStat.innerHTML = "10mb 이하의 파일을 선택해주세요";
        document.getElementById("secondView").classList.add("hidden");
        document.getElementById("firstView").classList.remove("hidden");
        return;
    }

    uploadStat.innerHTML = data.name + "<br>파일을 선택했습니다!";
    document.getElementById("secondView").classList.remove("hidden");
    document.getElementById("firstView").classList.add("hidden");
    formData.append("thumb", data);

}






// 업로드 버튼 클릭
function uploadBtnEvent(){
    
    $.ajax({
        type:"POST",
        url:"controller/board/uploadThumbProc.jsp",
        data:formData,
        processData:false,	//
        contentType:false,	// 이 두줄이 중요!!
        dataType:"text",
        success:function(result) {
            // 주소값 받아오고
            let parse = JSON.parse(result);

            console.log(parse[0].res);
            console.log(parse[0].comment);
            console.log(parse[1].imgPath);

            let res = parse[0].res;
            
            


            //성공 실패 창 띄우기
            if(res == 1){
                openModal("업로드 성공!");
                let imgPath = parse[1].imgPath;
                document.getElementById("wpThumbHolder").style.background = "white url('" + imgPath + "') no-repeat right top/contain";
                
                document.getElementById("thumbSrc").value = imgPath;
                document.getElementById("btnDeleteThumb").classList.remove("hidden");
            }else{
                openModal(parse[0].comment);
            }



            closeUploadModal();
        }
    });

}
uploadBtn.addEventListener("click", uploadBtnEvent);

function deleteThumb(){
    document.getElementById("wpThumbHolder").style.background = "none";
    document.getElementById("thumbSrc").value = '';
    document.getElementById("btnDeleteThumb").classList.add("hidden");
}