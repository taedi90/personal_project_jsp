
'use strict';


// 로그인

// 파라미터 가져오기
// 전송
// 들어온 값 파싱



// let a = postData('controller/login2.jsp', {loginId: 'test', password: '4567'})
//   .then(data => {return data})
//   .catch(error => console.error('error'));




// post 방식 fetch
function postData(url = '', data = {}) {
    console.log(data);
    return fetch(url, {
        method: 'POST', 
        mode: 'cors', 
        cache: 'no-cache', 
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json; charset=utf-8',
        },
        redirect: 'follow', 
        referrer: 'no-referrer', 
        body: JSON.stringify(data), 
    })
    .then(response => response.json()); 
}









// 가입
// 로그아웃
// 게시글 수정
// 게시글 삭제





const btn = document.getElementById("ajax_btn");


