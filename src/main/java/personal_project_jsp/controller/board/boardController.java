package personal_project_jsp.controller.board;

import personal_project_jsp.service.board.BoardService;
import personal_project_jsp.service.board.impl.BoardServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board")
public class boardController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/wiews/errorPages/noAccess.html");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("action", request.getParameter("action")); // numChange, orderChange, myPost, newCategory, searchPost, pageSwap
        data.put("idx", request.getParameter("idx") == null ? "1" : request.getParameter("idx")); // 페이지 기본 값 1
        data.put("num", request.getParameter("num") == null ? "10" : request.getParameter("num")); // 화면에 표시할 갯수 기본 값 1
        data.put("order", request.getParameter("order") == null ? "desc" : request.getParameter("order")); // 정렬순서 기본값 내림차순
        data.put("category", request.getParameter("category") == null ? "전체" : request.getParameter("category")); // 카테고리 기본값 전체
        data.put("myPost", request.getParameter("myPost") == null ? "0" : request.getParameter("myPost")); // 내글확인 여부
        data.put("keyword", request.getParameter("keyword") == null ? "" : request.getParameter("keyword")); // 검색키워드

        HttpSession session = request.getSession();
        data.put("id", session.getAttribute("user")); // 로그인 아이디

        BoardService bs = new BoardServiceImpl();
        // bs.showPosts(data);

    }

}
