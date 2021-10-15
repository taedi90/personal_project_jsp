function postAjax(url = '', data = {}) {
    let res;
    $.ajax({
        url : url, //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
        method : 'post',
        async: false,
        data : data,			
        success : function(d){ //DB접근 후 가져온 데이터
            // console.log("Ajax 결과 -> ");
            // console.log(d);
            res = d;
        },		
        error: function(e) { 
            console.error(e); 
            res = 0;
        } 
    });
    return res;
}

function ajax(url = '',
              data = {},
              method = 'post',
              type = 'x-www-form-urlencoded') {

    let contentType = undefined;
    if(type == "json"){
        contentType = "application/json; charset=utf-8";
        data = JSON.stringify(data);
    }else{
        contentType = "application/x-www-form-urlencoded";
    }

    let res = undefined;
    $.ajax({
        url : url,
        method : method,
        async: false,
        data : data,
        contentType: contentType,
        success : function(result){ // ajax 통신 후 가져온 데이터
            //console.log("Ajax 결과 -> ");
            //console.log(result);
            res = result;
        },
        error: function(e) {
            console.error(e);
        }
    });
    return res;
}