'use strict';

// 모달창
const modalBody = document.getElementById("modalBody");
const modalButton = document.getElementById("modalButton");
const modal = document.querySelector(".modal");
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

// 창 열기
function openModal(message, type) {


    modalBody.textContent = message;
    
    // 1이면 예 아니오, 나머지는 확인
    if(type === 1){
        modalButtons.forEach(i => i.classList.add("hidden"));
        type1.forEach(i => i.classList.remove("hidden"));
    }else{
        modalButtons.forEach(i => i.classList.add("hidden"));
        type0.forEach(i => i.classList.remove("hidden"));
    }


    modal.classList.remove("hidden");
    document.body.style.overflow = "hidden";

}