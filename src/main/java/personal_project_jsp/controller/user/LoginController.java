package personal_project_jsp.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.user.LoginService;
import personal_project_jsp.service.user.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/wiews/errorPages/noAccess.html");

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request 받아오기
        User user = new User();
        user.setId(request.getParameter("id"));
        user.setOriginPass(request.getParameter("password"));

        LoginService loginService = new LoginServiceImpl();
        Map<String, String> map = loginService.login(user);


        // 응답할 json 만들기
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        for (String key : map.keySet()) {
            jsonObject.addProperty(key, map.get(key));
        }


        // 세션에 로그인 정보 넣어주기
        if(map.get("res").equals("1")){
            HttpSession session = request.getSession();
            session.setAttribute("user", user.getId());
            session.setAttribute("name", map.get("name"));
        }


        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }

}
