'use strict';

// 모달창
const modal = document.querySelector(".modal");
const modalBody = document.getElementById("modalBody");
const modalButton = document.getElementById("modalButton");
const overlay = modal.querySelector(".modalOverlay");
const closeModals = modal.querySelectorAll(".closeModal");
const modalButtons = modal.querySelectorAll(".button");
const type1 = modal.querySelectorAll(".type1");
const type0 = modal.querySelectorAll(".type0");


// 창 닫기
const closeModal = () => {
    modal.classList.add("hidden");
    document.body.style.overflow = "auto";
}

// 모든 closeModal 클래스에 창 닫기 함수 연결
closeModals.forEach(i => {
    i.addEventListener("click",closeModal);
})


function yesFunc(){
    closeModal();
    joinFunc();
}
let joinFunc = undefined;

// 창 열기
function openModal(message = '', type = 0, func = function(){}) {


    modalBody.textContent = message;
    
    // 1이면 예 아니오, 나머지는 확인
    if(type === 1){
        modalButtons.forEach(i => i.classList.add("hidden"));
        type1.forEach(i => i.classList.remove("hidden"));
        joinFunc = func;
    }else{
        modalButtons.forEach(i => i.classList.add("hidden"));
        type0.forEach(i => i.classList.remove("hidden"));
    }


    modal.classList.remove("hidden");
    document.body.style.overflow = "hidden";

}




let thumbModal = document.getElementById("thumbModal");
let thumbModalImg = document.getElementById("thumbModalImg");

function thumbZoom(src){

    thumbModal.classList.remove("hidden");
    thumbModalImg.setAttribute("src", src);
}

thumbModal.addEventListener("click",() => {
    thumbModal.classList.add("hidden");
});