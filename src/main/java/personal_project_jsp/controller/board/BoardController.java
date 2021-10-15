package personal_project_jsp.controller.board;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import personal_project_jsp.dao.BoardDao;
import personal_project_jsp.dao.impl.BoardDaoImpl;
import personal_project_jsp.dto.Board;
import personal_project_jsp.service.board.BoardService;
import personal_project_jsp.service.board.impl.BoardServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board")
public class BoardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 게시물 조회
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("action", request.getParameter("action") == null ? "normal" : request.getParameter("action")); // numChange, orderChange, myPost, newCategory, searchPost, pageSwap
        data.put("idx", request.getParameter("idx") == null ? "1" : request.getParameter("idx")); // 페이지 기본 값 1
        data.put("num", request.getParameter("num") == null ? "10" : request.getParameter("num")); // 화면에 표시할 갯수 기본 값 1
        data.put("order", request.getParameter("order") == null ? "desc" : request.getParameter("order")); // 정렬순서 기본값 내림차순
        data.put("category", request.getParameter("category") == null ? "전체" : request.getParameter("category")); // 카테고리 기본값 전체
        data.put("myPost", request.getParameter("myPost") == null ? "0" : request.getParameter("myPost")); // 내글확인 여부
        data.put("keyword", request.getParameter("keyword") == null ? "" : request.getParameter("keyword")); // 검색키워드

        HttpSession session = request.getSession();
        data.put("id", session.getAttribute("user")); // 로그인 아이디

        BoardService bs = BoardServiceImpl.getInstance();
        Map<String, Object> map = bs.showPosts(data);

        request.setAttribute("data", map);

        RequestDispatcher rd = request.getRequestDispatcher("/views/board.jsp");
        rd.forward(request, response);
    }

    // 게시물 신규
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request(json) to map
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
        data.put("id", session.getAttribute("user"));


        // call BoardService
        BoardService bs = BoardServiceImpl.getInstance();
        Map<String, Object> res =  bs.writePost(data);

        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        for (String key : res.keySet()) {
            jsonObject.addProperty(key, (String) res.get(key));
        }

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }

    // 게시물 수정
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // request(json) to map
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
        data.put("id", session.getAttribute("user"));


        // call BoardService
        BoardService bs = BoardServiceImpl.getInstance();
        Map<String, Object> res =  bs.modifyPost(data);

        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        for (String key : res.keySet()) {
            jsonObject.addProperty(key, (String) res.get(key));
        }

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }

    // 게시물 삭제
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


        Board board = new Board();
        board.setNo(Long.parseLong((String) data.get("no")));

        BoardDao boardDao = BoardDaoImpl.getInstance();
        int res = boardDao.deleteBoard(board);
        String comment = null;
        if (res == 1){
            comment = "삭제 완료!";
        } else{
            res = 0;
            comment = "삭제 실패!";
        }


        JsonObject jsonObject = new JsonObject();

        // 서비스 결과를 json 형식으로 담기
        jsonObject.addProperty("res", res);
        jsonObject.addProperty("comment", comment);

        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }

}
