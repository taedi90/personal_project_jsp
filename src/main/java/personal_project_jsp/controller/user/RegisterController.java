package personal_project_jsp.controller.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.user.RegisterService;
import personal_project_jsp.service.user.impl.RegisterServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

// 모든 servlet 상위 클래스는 HttpServlet 이어야함
@WebServlet("/register")
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = 2L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/wiews/errorPages/noAccess.html");


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request 받아오기
        User user = new User();
        user.setId(request.getParameter("id"));
        user.setOriginPass(request.getParameter("password"));
        user.setName(request.getParameter("name"));
        user.setEmail(request.getParameter("email"));

        RegisterService register = new RegisterServiceImpl();
        Map<String, String> map = register.insertUser(user);


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


        // 회원가입 완료시 비밀번호 이력테이블은 트리거로 처리
//        delimiter $$
//        create trigger tr_password_change
//        after update on user
//        for each row
//        begin
//        declare id_temp varchar(50);
//        declare password_temp varchar(300);
//        declare salt_temp varchar(50);
//
//        set id_temp = new.id;
//        set password_temp = new.password;
//        set salt_temp = new.salt;
//
//        if old.password <> new.password then begin
//        insert into pass_history(id, password, salt) value (id_temp, password_temp, salt_temp);
//        end; end if;
//
//
//        end $$
//        delimiter ;
//
//        delimiter $$
//        create trigger tr_password_init
//        after insert on user
//        for each row
//        begin
//        declare id_temp varchar(50);
//        declare password_temp varchar(300);
//        declare salt_temp varchar(50);
//
//        set id_temp = new.id;
//        set password_temp = new.password;
//        set salt_temp = new.salt;
//
//        insert into pass_history(id, password, salt) value (id_temp, password_temp, salt_temp);
//
//
//        end $$
//        delimiter ;

    }

}