function postAjax(url = '', data = {}) {
    let res;
    $.ajax({
        url : url, //데이터베이스에 접근해 현재페이지로 결과를 뿌려줄 페이지
        method : 'post',
        async: false,
        data : data,			
        success : function(d){ //DB접근 후 가져온 데이터
            console.log(d);
            res = d;
        },		
        error: function(e) { 
            console.error(e); 
            res = 0;
        } 
    });
    return res;
}