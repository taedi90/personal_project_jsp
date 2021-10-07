package personal_project_jsp.controller.user;

import personal_project_jsp.util.EncryptUtil;
import personal_project_jsp.util.RandUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 모든 servlet 상위 클래스는 HttpServlet 이어야함
@WebServlet("/register")
public class RegisterController extends HttpServlet  {
    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EncryptUtil enc = EncryptUtil.getInstance();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(enc.getHashing("tesasdf34jie234t","appfsdsdf12f32f3le"));
        out.close();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request 받아오기
        //request.
        // 정상적인 값인지 확인하는 작엄
        // 비밀번호 + salt(random) -> 단방향 해시함수


        //

    }

}