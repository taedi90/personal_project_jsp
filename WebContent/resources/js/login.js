'use strict';

// 로그인창 열기 닫기
const btnOpenLogin = document.getElementById("btnOpenLogin");
const loginModal = document.querySelector(".loginModal");
const btnCloseLogin = loginModal.querySelector(".btnCloseLogin");

const openLoginModal = () => {
    loginModal.classList.remove("hidden");
    document.body.style.overflow = "hidden"; // 스크롤 방지
}

btnOpenLogin.addEventListener("click", openLoginModal);

const closeLoginModal = () => {
    loginModal.classList.add("hidden");
    document.body.style.overflow = "auto";
    // 값들 비울지 고민좀 해보고
}
btnCloseLogin.addEventListener("click", closeLoginModal);

// 로그인 & 회원가입 폼 전환
let register = 0;
const registerOptions = document.getElementsByClassName("registerOption");
const loginOptions = document.getElementsByClassName("loginOption");


function registerOn(){
    for(let i = 0; i < registerOptions.length; i++){
        registerOptions.item(i).classList.remove("hidden");
    }
    for(let i = 0; i < loginOptions.length; i++){
        loginOptions.item(i).classList.add("hidden");
    }
    document.getElementById("loginFormTitle").innerHTML = "회원가입";
    register = 1;
    idChkFunc(); // 아이디 체크
}

function registerOff(){
    for(let i = 0; i < registerOptions.length; i++){
        registerOptions.item(i).classList.add("hidden");
    }
    for(let i = 0; i < loginOptions.length; i++){
        loginOptions.item(i).classList.remove("hidden");
    }
    document.getElementById("loginFormTitle").innerHTML = "로그인";
    register = 0;
}

// 아이디 체크
const idInput = document.getElementById("id");
const idChkRes = document.getElementById("idChkRes");
const idRegEx = /^[a-z]+[a-z0-9]{5,19}$/g;


const idChkFunc = () => {
    idChkRes.classList.add("hidden");
    // 로그인 상황이면 띄우지 않기
    if(register === 0){
        console.log("로그인 중에는 아이디 체크 안함");
        return;
    }

    let id = idInput.value;

    // 내용이 공백일 경우에는 모두 지우기
    if(id === '') {
        console.log("아이디가 공백임");
        return;
    }

    // 아이디 유효성 검사
    // false일때도 수행되는 이유를 모르겠음..
    if(!idRegEx.test(id)){
        idChkRes.classList.remove("hidden");
        idChkRes.textContent = "영문자로 시작하고 영문자 또는 숫자 6~20자";
        idChkRes.classList.add("warnTxt");
        return;
    }

    // 아이디 중복여부 검사
    let data = postAjax('controller/idChkProc.jsp', {id: id});

    if(data[0].res === '0') {
        idChkRes.classList.remove("hidden");
        idChkRes.textContent = "사용 가능한 아이디입니다!";
    }else {
        idChkRes.classList.remove("hidden");
        idChkRes.textContent = "아이디 중복!";
    }


    // postAjax('controller/idChkProc.jsp', {id: id})
    //     .then(data => {
    //         if(data[0].res === '0') {
    //             idChkRes.classList.remove("hidden");
    //             idChkRes.textContent = "사용 가능한 아이디입니다!";
    //         }else {
    //             idChkRes.classList.remove("hidden");
    //             idChkRes.textContent = "아이디 중복!";
    //         }
    //     })
    //     .catch(error => {
    //         console.error(error);
    //     });

}

id.addEventListener("change", idChkFunc);



// 로그인
function loginProc() {
    let id = document.loginForm.id.value;
    let password = document.loginForm.password.value;

    let data = postAjax('controller/loginProc.jsp', {id: id, password: password})
    if(data[0].res === '1') {
        alert("로그인가능");
    }else {
        alert("로그인실패");
    }



    // postData('controller/loginProc.jsp', {id: id, password: password})
    //     .then(data => {
    //         console.log(data);
    //         if(data[0].res === '1') {
    //             alert("로그인가능");
    //         }else {
    //             alert("로그인실패");
    //         }
    //     })
    //     .catch(error => {
    //         alert("통신오류");
    //         console.error(error);
    //     });


    // document.loginForm.id.value
    // document.loginForm.password.value
    // document.loginForm.name.value
    // document.loginForm.email.value
}


// 입력 관리(가입시에만)
// 0. 필드가 비었을 경우에는 안내하지 않기
// 1. 사용할 수 없는 문자 오류 체크(숫자,영어,특문 일부 외에 모두 제외)
// 2. 중복체크(아이디만)




