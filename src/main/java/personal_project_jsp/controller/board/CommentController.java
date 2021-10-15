package personal_project_jsp.controller.board;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import personal_project_jsp.dao.CommentDao;
import personal_project_jsp.dao.impl.CommentDaoImpl;
import personal_project_jsp.dto.Comment;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.board.CommentService;
import personal_project_jsp.service.board.impl.CommentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/comment")
public class CommentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("postNo", request.getParameter("postNo"));

        HttpSession session = request.getSession();
        data.put("id", session.getAttribute("user")); // 로그인 아이디

        CommentService commentService = CommentServiceImpl.getInstance();

        request.setAttribute("data", commentService.showComments(data));

        RequestDispatcher rd = request.getRequestDispatcher("/views/comment.jsp");
        rd.forward(request, response);


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        Map data = new HashMap();

        StringBuffer json = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }

        }catch(Exception e) {
            System.out.println("Error reading JSON string: " + e.toString());
        }
        data = (Map) gson.fromJson(json.toString(), data.getClass());

        HttpSession session = request.getSession();
        data.put("id", session.getAttribute("user")); // 로그인 아이디

        CommentService commentService = CommentServiceImpl.getInstance();

        Map res = commentService.writeComment(data);


        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        jsonObject.addProperty("res", (Integer) res.get("res"));

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        Map data = new HashMap();

        StringBuffer json = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }

        }catch(Exception e) {
            System.out.println("Error reading JSON string: " + e.toString());
        }
        data = (Map) gson.fromJson(json.toString(), data.getClass());

        HttpSession session = request.getSession();
        data.put("id", session.getAttribute("user")); // 로그인 아이디

        CommentService commentService = CommentServiceImpl.getInstance();

        Map res = commentService.modifyComment(data);

        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        jsonObject.addProperty("res", (Integer) res.get("res"));

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));


    }
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Gson gson = new Gson();
        Map data = new HashMap();

        StringBuffer json = new StringBuffer();
        String line = null;

        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null) {
                json.append(line);
            }

        }catch(Exception e) {
            System.out.println("Error reading JSON string: " + e.toString());
        }
        data = (Map) gson.fromJson(json.toString(), data.getClass());

        HttpSession session = request.getSession();
        data.put("id", session.getAttribute("user")); // 로그인 아이디

        CommentService commentService = CommentServiceImpl.getInstance();

        Map res = commentService.deleteComment(data);

        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        jsonObject.addProperty("res", (Integer) res.get("res"));

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }


}
