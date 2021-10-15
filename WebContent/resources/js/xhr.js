// xhr 통신용
'use strict';

function xhr(url = '', data = {}) {
    let res;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', url, false);
    //xhr.setRequestHeader('Content-Type', 'application/json');
    //xhr.send(JSON.stringify(data));
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send(new URLSearchParams(Object.entries(data)).toString());


    xhr.onload = function () {
        if (xhr.status === 200 || xhr.status === 201) {
            console.log(xhr.response);
            console.log("통신 성공");
            res = xhr.response;
        } else {
            console.log("통신 실패");
            res = 0;
        }
    }

    return res;

}