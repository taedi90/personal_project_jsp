<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    
    <section id="ex9">
        <style>
            #ex9 .uploadBox{
                width:500px;
                height: 300px;
                border:1px solid gray;
                box-shadow: 2px 3px 9px hsl(0, 0%, 47%);
                padding:10px;
            }
        </style>
    <div class="uploadBox">
    <form action="../controller/board/uploadThumb.jsp" method="post" enctype="multipart/form-data">
        <button type="submit" class="btnUpload">업로드</button>
        <input type="file" class="btnFile dDone" name="thumb" id="">
    </form>
    </div>
</section>



</body>
</html>