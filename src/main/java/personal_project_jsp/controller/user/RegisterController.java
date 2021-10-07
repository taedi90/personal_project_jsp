package personal_project_jsp.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.user.Register;
import personal_project_jsp.service.user.impl.RegisterImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

// 모든 servlet 상위 클래스는 HttpServlet 이어야함
@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        EncryptUtil enc = EncryptUtil.getInstance();
//
//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
//        out.print(enc.getHashing("12345","kitty"));
//        out.close();

        // get 방식 거부
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("올바르지 않은 접근입니다.");
        out.close();


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request 받아오기
        User user = new User();
        user.setId(request.getParameter("id"));
        user.setOriginPass(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));

        Register register = new RegisterImpl();
        Map<String, String> map = register.insertUser(user);


        // 응답할 json 만들기
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        for (String key : map.keySet()) {
            jsonObject.addProperty(key, map.get(key));
        }

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }

}