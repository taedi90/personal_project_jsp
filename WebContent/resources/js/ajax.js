// ajax 통신용
'use strict';

function postAjax(url = '', data = {}) {
    let xhr = new XMLHttpRequest();
    xhr.open('POST', url, true);
    xhr.setRequestHeader('Content-Type', 'application/json');

    xhr.send(JSON.stringify(data));

    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 201) {
            console.log(xhr.response);
            console.log("통신 성공");
        } else {
            console.log("통신 실패");
        }
    }    

}