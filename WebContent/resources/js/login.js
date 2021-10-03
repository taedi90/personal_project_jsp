'use strict';

const btnOpenLogin = document.getElementById("btnOpenLogin"); // 로그인창 열기 버튼
const btnOpenLogout = document.getElementById("btnOpenLogout"); // 로그아웃 버튼
const loginModal = document.querySelector(".loginModal"); // 폼 내의 버튼
const btnCloseLogin = loginModal.querySelector(".btnCloseLogin"); // 폼 내의 종료 버튼

// 로그인창 열기
const openLoginModal = () => {
    loginModal.classList.remove("hidden");
    document.body.style.overflow = "hidden"; // 스크롤 방지
    registerOff(); // 초기 화면은 로그인 창
}
btnOpenLogin.addEventListener("click", openLoginModal);

// 로그인창 닫기
const closeLoginModal = () => {
    loginModal.classList.add("hidden");
    document.body.style.overflow = "auto";
    // 값들 비울지 고민좀 해보고
    document.loginForm.reset();
}
btnCloseLogin.addEventListener("click", closeLoginModal);



// 로그인 & 회원가입 폼 전환 관련
let register = 0; // 회원가입 폼 전환 여부(0: 로그인 1: 회원가입)
const registerOptions = document.getElementsByClassName("registerOption"); // 회원가입에서 보여줄 요소들
const loginOptions = document.getElementsByClassName("loginOption"); // 로그인에서 보여줄 요소들


// 회원가입 폼 전환
function registerOn(){
    // 회원가입 관련 요소 나타내기
    for(let i = 0; i < registerOptions.length; i++){
        registerOptions.item(i).classList.remove("hidden");
    }

    // 로그인 관련 요소 숨기기
    for(let i = 0; i < loginOptions.length; i++){
        loginOptions.item(i).classList.add("hidden");
    }

    // 타이틀 명 바꾸기
    document.getElementById("loginFormTitle").innerHTML = "회원가입";

    register = 1;

    idChkFunc(); // 아이디 체크
}


// 로그인 폼 전환
function registerOff(){
    // 회원가입 관련 요소 숨기기
    for(let i = 0; i < registerOptions.length; i++){
        registerOptions.item(i).classList.add("hidden");
    }

    // 로그인 관련 요소 나타내기
    for(let i = 0; i < loginOptions.length; i++){
        loginOptions.item(i).classList.remove("hidden");
    }

    //타이틀 명 바꾸기
    document.getElementById("loginFormTitle").innerHTML = "로그인";

    register = 0;
}

// 아이디 체크(유효성 체크, 중복체크)
const idInput = document.getElementById("id"); // 아이디 입력창
const idChkRes = document.getElementById("idChkRes"); // 아이디 입력관련 메세지 출력 요소

const idChkFunc = () => {
    idChkRes.classList.add("hidden");

    // 불필요한 문자 제거
    notAllowedCharChk();

    // 0. 로그인 상황이면 메세지 띄우지 않기
    if(register === 0){
        return;
    }

    // 입력값을 변수에 저장
    let id = idInput.value;

    // 1. 필드가 비었을 경우에는 안내하지 않기
    if(nullChk(id)){
        return;
    }

    // 2. 유효성 체크(숫자,영어,특문 일부 외에 모두 제외)
    // false일때도 수행되는 이유를 모르겠음..
    if(idRegExChk()){
        idChkRes.classList.remove("hidden"); // 메세지 출력
        idChkRes.classList.add("idChkWarn"); // 오류 메세지는 붉은 글자 처리
        idChkRes.textContent = "영문자 또는 숫자 4~20자만 사용 가능합니다.";

        return;
    }

    // 3. 중복체크
    if(idExistChk()){
        idChkRes.classList.remove("hidden");
        idChkRes.classList.add("idChkWarn");
        idChkRes.textContent = "아이디 중복!";
    }else{
        idChkRes.classList.remove("hidden");
        idChkRes.classList.remove("idChkWarn");
        idChkRes.textContent = "사용 가능한 아이디입니다!";

    }

}
idInput.addEventListener("keyup", idChkFunc);


// 공백 여부 체크
function nullChk(input) {
    if(input === '') {
        return true;
    }
    return false;
}

// 사용 불가능한 글자 제외
const notAllowedChar = /[^a-z0-9]/gi;
function notAllowedCharChk(){
    let id = idInput.value;
    idInput.value = id.replace(notAllowedChar,'');
}

// 아이디 문법 검사
const idRegEx = /^[a-z]+[a-z0-9]{3,19}$/;


function idRegExChk() {
    let id = idInput.value;

    if(!idRegEx.test(id)){
        return true; // 오류
    }
    return false; // 통과!
}

// 아이디 중복 체크
function idExistChk() {
    let data = postAjax('controller/idChkProc.jsp', {id: id});
    if(data[0].res === '0') {
        return false; // 사용 가능한 아이디
    }else{
        return true; // 사용 불가능한 아이디
    }
}





// 로그인
const afterLogins = document.getElementsByClassName("afterLogin"); // 로그인 이후 표출할 요소
const beforeLogins = document.getElementsByClassName("beforeLogin"); // 로그인 이전 표출할 요소

function loginProc() {
    let id = document.loginForm.id.value;
    let password = document.loginForm.password.value;

    let data = postAjax('controller/loginProc.jsp', {id: id, password: password})

    if (data === 0){
        return;
    }else if(data[0].res === '1') {
        // 로그인 성공 시 관련요소들 표출
        for(let i = 0; i < afterLogins.length; i++){
            afterLogins.item(i).classList.remove("hidden");
        }

        // 로그인 성공 시 이전 요소들 감추기
        for(let i = 0; i < beforeLogins.length; i++){
            beforeLogins.item(i).classList.add("hidden");
        }
        document.getElementById("loginStat").textContent = data[1].name + "님 안녕하세요!"

        closeLoginModal();
    }else {
        openModal(data[0].comment);

        // 가입하지 않은 아이디라면 회원가입 화면으로 유도
        if(idExistChk()){
            registerOn();
        }
    }
}

// 로그아웃
function logoutProc() {
    openModal("로그아웃 하시겠습니까?", 1, logoutConfirm);
}
btnOpenLogout.addEventListener("click", logoutProc);


function logoutConfirm(){
    let data = postAjax("controller/logoutProc.jsp");
    if (data === 0){
        return;
    }else if (data[0].res == 1) {
        openModal("로그아웃 완료!");
        // 로그아웃 시 로그인 이후 요소 감추기
        for(let i = 0; i < afterLogins.length; i++){
            afterLogins.item(i).classList.add("hidden");
        }

        // 로그아웃 시 로그인용 요소 표출
        for(let i = 0; i < beforeLogins.length; i++){
            beforeLogins.item(i).classList.remove("hidden");
        }
        document.getElementById("loginStat").classList.textContent = '';
    }
}



// 회원가입
function registerProc(){
    let id = document.loginForm.id.value;
    let password = document.loginForm.password.value;
    let name = document.loginForm.name.value;
    let email = document.loginForm.email.value;

    // 값 확인
    if(nullChk(id) || nullChk(password) || nullChk(name) || nullChk(email)){
        return;
    }

    // 아이디 문법 검사
    if(idRegExChk()){
        return;
    }   

    // 아이디 중복 검사
    if(idExistChk()){
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
        openModal(data[0].comment);
        document.getElementById("loginStat").classList.remove("hidden");
        document.getElementById("loginStat").textContent = data[1].name + "님 안녕하세요!"
        btnOpenLogin.classList.add("hidden");
        btnOpenLogout.classList.remove("hidden");
        closeLoginModal();
    }else{
        openModal(data[0].comment);
    }

}




