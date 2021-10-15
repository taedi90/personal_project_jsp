package personal_project_jsp.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import personal_project_jsp.dao.UserDao;
import personal_project_jsp.dao.impl.UserDaoImpl;
import personal_project_jsp.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/idChk")
public class IdChkController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/wiews/errorPages/noAccess.html");


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 회원가입 시 실시간 아이디 중복체크

        UserDao dao = UserDaoImpl.getInstance();
        User user = new User();
        user.setId(request.getParameter("id"));

        int res = dao.idChk(user);

        // 응답할 json 만들기
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("res", res);


        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));
    }
}
