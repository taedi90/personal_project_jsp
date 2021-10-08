package personal_project_jsp.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

@WebServlet("/changePass")
public class ChangePassController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print("올바르지 않은 접근입니다.");
        out.close();


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> data = new HashMap<>();
        data.put("originPass", request.getParameter("originPass"));
        data.put("newPassword",request.getParameter("newPassword"));
        data.put("confirm",request.getParameter("confirm"));

        HttpSession session = request.getSession();
        data.put("id", (String) session.getAttribute("user"));


        // 응답할 json 만들기
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        ChangePassService cps = new ChangePassServiceImpl();
        Map<String, String> map = cps.changePass(data);

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
