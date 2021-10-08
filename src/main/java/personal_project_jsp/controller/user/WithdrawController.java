package personal_project_jsp.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import personal_project_jsp.dao.UserDao;
import personal_project_jsp.dao.impl.UserDaoImpl;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.user.ChangePassService;
import personal_project_jsp.service.user.impl.ChangePassServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/withdraw")
public class WithdrawController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("올바르지 않은 접근입니다.");
        out.close();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 응답할 json 만들기
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();


        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("user");

        User user = new User(id);

        UserDao dao = UserDaoImpl.getInstance();

        int res = dao.withdrawUser(user);

        if(res == 1){
            jsonObject.addProperty("res", "1");
            jsonObject.addProperty("comment", "탈퇴 완료!");
        }else{
            jsonObject.addProperty("res", "0");
            jsonObject.addProperty("comment", "탈퇴 실패!");
        }

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }
}
