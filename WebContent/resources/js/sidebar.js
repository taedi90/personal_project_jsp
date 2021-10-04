'use strict';

// 사이드바 on off
const btnSidebar = document.getElementById("btnSidebar");
const sidebar = document.getElementById("sidebar");

const toggleSidebar = () => {
    if (sidebar.className === "hidden") {
        sidebar.classList.remove("hidden");
        sidebar.classList.add("appear");
        setTimeout(function () { sidebar.classList.remove('appear') }, 300);
    } else {
        sidebar.classList.add("disappear");
        setTimeout(function () { sidebar.classList.add('hidden') }, 150);
        setTimeout(function () { sidebar.classList.remove('disappear') }, 200);
    }
}

btnSidebar.addEventListener("click", toggleSidebar);


// 마우스 드래그 & 드롭에 따라 사이드바 옮기기
// 참고 https://heodolf.tistory.com/105, https://d2.naver.com/helloworld/80243, 
// https://stackoverflow.com/questions/41993176/determine-touch-position-on-tablets-with-javascript
// 모바일에서 preventDefault 사용하면 터치 씹힘

// 마우스 다운 이벤트 핸들러
function handleMouseDown(e) {
    //e.preventDefault();

    // 마우스 좌표 값
    let mouseX = undefined;

    if(e.type == 'touchstart' || e.type == 'touchmove' || e.type == 'touchend' || e.type == 'touchcancel'){
    	var evt = (typeof e.originalEvent === 'undefined') ? e : e.originalEvent;
    	var touch = evt.touches[0] || evt.changedTouches[0];
        mouseX = touch.pageX;
        console.log("모바일");
    } else if (e.type == 'mousedown' || e.type == 'mouseup' || e.type == 'mousemove' || e.type == 'mouseover'|| e.type=='mouseout' || e.type=='mouseenter' || e.type=='mouseleave') {
		//e.preventDefault();        
		mouseX = e.clientX;
        console.log("데스크");
    }

    // 사이드바의 X좌표
    // const sideBarPos = sidebar.getBoundingClientRect();
    // const sideBarX = sideBarPos.x;
    const sideBarX = 0;

    // 사이드바 안에 있는 마우스 커서의 X좌표
    const gapX = mouseX - sideBarX;

    sidebar.setAttribute("gap-x", gapX);

    sidebar.classList.add("hold");

}

// 마우스 이동 이벤트 핸들러
function handleMouseMove(e) {
    //e.preventDefault();

    if (sidebar.classList.contains("hold")) {
        // 움직이는 마우스 커서의 X좌표
        let mouseX = undefined;

        if(e.type == 'touchstart' || e.type == 'touchmove' || e.type == 'touchend' || e.type == 'touchcancel'){
    		var evt = (typeof e.originalEvent === 'undefined') ? e : e.originalEvent;
    		var touch = evt.touches[0] || evt.changedTouches[0];
            mouseX = touch.pageX;
        } else if (e.type == 'mousedown' || e.type == 'mouseup' || e.type == 'mousemove' || e.type == 'mouseover'|| e.type=='mouseout' || e.type=='mouseenter' || e.type=='mouseleave') {
			//e.preventDefault(); 
            mouseX = e.clientX;
        }


        // 마우스 갭
        const gapX = sidebar.getAttribute("gap-x");

        // 마우스 커서 위치에 따른 사이드바 이동 
        const sideBarX = mouseX - gapX;
        sidebar.setAttribute("sideBar-x", sideBarX);
        console.log("sideBarX: " + sideBarX + ", mouseX: " + mouseX + ", gapX: " + gapX);

        // 마우스 커서 위치에 따른 사이드바 이동 (최대치 이상은 움직이지 않도록)
        if (sideBarX < 0){
            sidebar.style.transform = "translateX(" + sideBarX + "px)";
        }else {
            sidebar.style.transform = "translateX(" + 0 + "px)";
        }

    }
}

// 마우스 업 이벤트 핸들러
function handleMouseUp(e) {
    //e.preventDefault();

    if (sidebar.classList.contains("hold")) {

        //60% 이상 줄었을 때 사이드바 닫기
        const thresholdPoint = (0.6 * sidebar.offsetWidth) * -1;

        if(sidebar.getAttribute("sideBar-x") < thresholdPoint){
            sidebar.classList.add("hidden");
        }else{
            sidebar.classList.remove("hidden");
        }

        // 움직임에 적용된 속성 및 class를 삭제
        sidebar.classList.remove("hold");
        sidebar.removeAttribute("gap-x");
        sidebar.removeAttribute("sideBar-x");
        sidebar.style.transform = "translateX(" + 0 + "px)";

    }
}

// 사이드바 마우스 클릭 이벤트 리스너
sidebar.addEventListener('mousedown', handleMouseDown);

// 마우스가 범위를 벗어날 수 있기 때문에 move, up은 문서 전체에 할당
document.addEventListener('mouseup', handleMouseUp);
document.addEventListener('mousemove', handleMouseMove);


sidebar.addEventListener('touchstart', handleMouseDown, {passive: false});
document.addEventListener('touchend', handleMouseUp, {passive: false});
document.addEventListener('touchmove', handleMouseMove, {passive: false});



// // 모바일용
// // 터치 다운 이벤트 핸들러
// function handleTouchMove(event) {
//     event.preventDefault();

//     // 터치 좌표 값
//     const touch = event.originalEvent.touches[0] || event.originalEvent.changedTouches[0];
//     const touchX = touch.pageX;

//     // 사이드바의 X좌표
//     // const sideBarPos = sidebar.getBoundingClientRect();
//     // const sideBarX = sideBarPos.x;
//     const sideBarX = 0;

//     // 사이드바 안에 있는 터치 커서의 X좌표
//     const gapX = touchX - sideBarX;

//     sidebar.setAttribute("gap-x", gapX);

//     sidebar.classList.add("hold");

// }

// // 터치 이동 이벤트 핸들러
// function handleTouchDown(event) {
//     event.preventDefault();

//     if (sidebar.classList.contains("hold")) {
//         // 움직이는 터치 커서의 X좌표
//         const touchX = event.clientX;

//         // 터치 갭
//         const gapX = sidebar.getAttribute("gap-x");

//         // 터치 커서 위치에 따른 사이드바 이동 
//         const sideBarX = touchX - gapX;
//         sidebar.setAttribute("sideBar-x", sideBarX);
//         console.log("sideBarX: " + sideBarX + ", touchX: " + touchX + ", gapX: " + gapX);

//         // 터치 커서 위치에 따른 사이드바 이동 (최대치 이상은 움직이지 않도록)
//         if (sideBarX < 0){
//             sidebar.style.transform = "translateX(" + sideBarX + "px)";
//         }else {
//             sidebar.style.transform = "translateX(" + 0 + "px)";
//         }

//     }
// }

// // 터치 업 이벤트 핸들러
// function handleTouchUp(event) {
//     event.preventDefault();

//     if (sidebar.classList.contains("hold")) {

//         //60% 이상 줄었을 때 사이드바 닫기
//         const thresholdPoint = (0.6 * sidebar.offsetWidth) * -1;

//         if(sidebar.getAttribute("sideBar-x") < thresholdPoint){
//             sidebar.classList.add("hidden");
//         }else{
//             sidebar.classList.remove("hidden");
//         }

//         // 움직임에 적용된 속성 및 class를 삭제
//         sidebar.classList.remove("hold");
//         sidebar.removeAttribute("gap-x");
//         sidebar.removeAttribute("sideBar-x");
//         sidebar.style.transform = "translateX(" + 0 + "px)";

//     }
// }


// sidebar.addEventListener('touchstart', handleTouchDown, {passive: false});
// document.addEventListener('touchend', handleTouchUp, {passive: false});
// document.addEventListener('touchmove', handleTouchMove, {passive: false});