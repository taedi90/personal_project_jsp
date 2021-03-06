package personal_project_jsp.controller.board;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import personal_project_jsp.util.RandUtil;
import personal_project_jsp.util.ThumbUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Enumeration;

@WebServlet("/thumb")
public class ThumbController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int res = 0;
        String comment = null;


        // 랜덤한 이름의 저장경로 생성
        String absPath = null;

        String path = request.getSession().getServletContext().getRealPath("/"); // 배포폴더
        String parentPath = Paths.get(path).getParent().getFileName().toString(); // 배포 폴더가 위치한 최상위 폴더
        if(parentPath.equals("webapps")){
            // 서버일 경우
            absPath = Paths.get(path).getParent().getFileName().toAbsolutePath().toString()
                    + File.separator +"personal_project_file_storage" + File.separator + "upload";
        }else {
            // 작업환경일 경우(wtpwebapps, artifacts)
            absPath = path + "upload";
        }


        RandUtil ru = RandUtil.getInstance();
        String uploadPath = null;	// 썸네일이 저장 될 경로
        String imgPath = null;	// 썸네일 파일 웹 경로
        File folder = null;
        String rand = null;

        do{
            rand = ru.getSecureRand();
            uploadPath = absPath + File.separator + rand;
            folder = new File(uploadPath);

        }while(folder.exists());


        try{
            folder.mkdirs(); //폴더 생성합니다.
            System.out.println(uploadPath);
        }
        catch(Exception e){
            e.getStackTrace();
        }

        String fileName = null; // 업로드한 파일

        try {
            MultipartRequest multi = new MultipartRequest(
                    request,
                    uploadPath, // 파일을 저장 디렉토리
                    10 * 1024 * 1024, // 파일 최대 용량(bite) / 현재 10mb
                    "UTF-8", // 인코딩 방식
                    new DefaultFileRenamePolicy() // 중복 파일 처리(동일한 파일명이 업로드되면 뒤에 숫자 등을 붙여 중복 회피)
            );

            Enumeration files=multi.getFileNames();
            String file1 =(String)files.nextElement();


            fileName = multi.getFilesystemName(file1);
            //fileName = multi.getFilesystemName("thumb");
            System.out.println("파일명" + fileName);

        } catch (Exception e) {
            e.getStackTrace();
        }


        //파일 확장자 검사
        String chkExtension = fileName.toLowerCase();



        if(chkExtension.endsWith(".jpg") || chkExtension.endsWith(".jpeg")
                || chkExtension.endsWith(".png") || chkExtension.endsWith(".gif")){

            // 파일 용량 트림
            ThumbUtil tu = ThumbUtil.getInstance();
            res = tu.thumbTrim(uploadPath + File.separator + fileName, uploadPath + File.separator + "thumb.png");

            // 원본 파일 삭제
            File file = new File(uploadPath + File.separator + fileName);
            if( file.exists() ){
                file.delete();
            }

            imgPath = "upload/" + rand + "/thumb.png";
            comment = "성공";

        }else{
            comment = "파일 형식을 확인해주세요.";
            folder.delete();
            res = 2;
        }


        // res 1: 정상, 2: 형식에 맞지 않음, 0: 변환오류


        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        jsonObject.addProperty("res", res);
        jsonObject.addProperty("comment", comment);
        jsonObject.addProperty("imgPath", imgPath);

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }
}
