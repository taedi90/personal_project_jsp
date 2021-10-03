'use strict';

const btnOpenLogin = document.getElementById("btnOpenLogin"); // 로그인창 열기 버튼
const btnOpenLogout = document.getElementById("btnOpenLogout"); // 로그아웃 버튼
const loginModal = document.querySelector(".loginModal"); // 폼 내의 버튼
const btnCloseLogin = loginModal.querySelector(".btnCloseLogin"); // 폼 내의 종료 버튼


// 로그인창 열기 닫기
const openLoginModal = () => {
    loginModal.classList.remove("hidden");
    document.body.style.overflow = "hidden"; // 스크롤 방지
    registerOff(); // 초기 화면은 로그인 창
}

btnOpenLogin.addEventListener("click", openLoginModal);

const closeLoginModal = () => {
    loginModal.classList.add("hidden");
    document.body.style.overflow = "auto";
    // 값들 비울지 고민좀 해보고
    document.loginForm.reset();
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

const idChkFunc = () => {
    idChkRes.classList.add("hidden");
    // 0. 로그인 상황이면 띄우지 않기
    if(register === 0){
        console.log("로그인 중에는 아이디 체크 안함");
        return;
    }

    let id = idInput.value;

    // 1. 필드가 비었을 경우에는 안내하지 않기
    if(nullChk(id)){
        return;
    }

    // 2. 유효성 체크(숫자,영어,특문 일부 외에 모두 제외)
    // false일때도 수행되는 이유를 모르겠음..
    if(idRegExChk()){
        idChkRes.classList.remove("hidden");
        idChkRes.textContent = "영문자 또는 숫자 4~20자만 사용 가능";
        idChkRes.classList.add("idChkWarn");
        return;
    }

    // 3. 중복체크
    let data = postAjax('controller/idChkProc.jsp', {id: id});

    if(data[0].res === '0') {
        idChkRes.classList.remove("hidden");
        idChkRes.textContent = "사용 가능한 아이디입니다!";
        idChkRes.classList.remove("idChkWarn");
    }else {
        idChkRes.classList.remove("hidden");
        idChkRes.textContent = "아이디 중복!";
        idChkRes.classList.add("idChkWarn");
    }

}

function nullChk(input) {
    if(input === '') {
        return true;
    }
    return false;
}

const idRegEx = /^[a-z]+[a-z0-9]{3,19}$/;

function idRegExChk() {
    let id = idInput.value;

    if(!idRegEx.test(id)){
        return true; // 오류
    }
    return false; // 통과!
}


id.addEventListener("change", idChkFunc);



// 로그인
function loginProc() {
    let id = document.loginForm.id.value;
    let password = document.loginForm.password.value;

    let data = postAjax('controller/loginProc.jsp', {id: id, password: password})

    if (data === 0){
        return;
    }else if(data[0].res === '1') {
        alert(data[0].comment);
        document.getElementById("loginStat").classList.remove("hidden");
        document.getElementById("loginStat").textContent = data[1].name + "님 안녕하세요!"
        btnOpenLogin.classList.add("hidden");
        btnOpenLogout.classList.remove("hidden");
        closeLoginModal();
    }else {
        alert(data[0].comment);
        registerOn();


    }
}

// 로그아웃

function logoutProc() {
    openModal("정말 로그아웃 하시겠습니까?", 1, logoutConfirm);
}

function logoutConfirm(){
    let data = postAjax("controller/logoutProc.jsp");
    if (data === 0){
        return;
    }else if (data[0].res == 1) {
        openModal("로그아웃 완료!");
        document.getElementById("loginStat").classList.add("hidden");
        document.getElementById("loginStat").classList.textContent = '';
        btnOpenLogin.classList.remove("hidden");
        btnOpenLogout.classList.add("hidden");
    }
}

btnOpenLogout.addEventListener("click", logoutProc);



// 회원가입
function registerProc(){
    let id = document.loginForm.id.value;
    let password = document.loginForm.password.value;
    let name = document.loginForm.name.value;
    let email = document.loginForm.email.value;

    // 값 확인(나중에..)
    if(nullChk(id) || nullChk(password) || nullChk(name) || nullChk(email)){
        return;
    }

    if(idRegExChk()){
        return;
    }   


    // 전송
	let param = {
		id: id,
        password: password,
        name: name,
        email: email
	};
    let data = postAjax("controller/registerProc.jsp", param);
    if (data === 0){
        return;
    }else if (data[0].res == 1) {
        alert(data[0].comment);
        document.getElementById("loginStat").classList.remove("hidden");
        document.getElementById("loginStat").textContent = data[1].name + "님 안녕하세요!"
        btnOpenLogin.classList.add("hidden");
        btnOpenLogout.classList.remove("hidden");
        closeLoginModal();
    }else{
        alert(data[0].comment);
    }

}




