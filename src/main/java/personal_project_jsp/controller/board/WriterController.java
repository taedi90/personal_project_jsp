package personal_project_jsp.controller.board;

import personal_project_jsp.dao.BoardDao;
import personal_project_jsp.dao.CategoryDao;
import personal_project_jsp.dao.impl.BoardDaoImpl;
import personal_project_jsp.dao.impl.CategoryDaoImpl;
import personal_project_jsp.dto.Board;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/writer")
public class WriterController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // writer 페이지 로드(신규)
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 로그인 확인

        // 페이지 로드

        CategoryDao categoryDao = CategoryDaoImpl.getInstance();
        request.setAttribute("categoryArr", categoryDao.selectCategoryByAll());


        RequestDispatcher rd = request.getRequestDispatcher("/views/writePost.jsp");
        rd.forward(request, response);

    }

    // 수정
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 파라미터에서 게시물 번호 받아오기
        Map<String, Object> data = new HashMap<>();
        data.put("postNo", request.getParameter("no"));

        HttpSession session = request.getSession();
        data.put("id", session.getAttribute("user"));

        CategoryDao categoryDao = CategoryDaoImpl.getInstance();
        request.setAttribute("categoryArr", categoryDao.selectCategoryByAll());

        // 게시물 조회 (service)
        Board board = new Board();
        board.setNo(Long.parseLong((String)data.get("postNo")));

        BoardDao boardDao = BoardDaoImpl.getInstance();
        board = boardDao.selectBoardByNo(board);

        // 권한확인
        if(board.getId().equals((String)data.get("id"))){
            request.setAttribute("res", "1");
            request.setAttribute("postNo", (String)data.get("postNo"));
            request.setAttribute("board", board);
        }

        // 뿌리기
        RequestDispatcher rd = request.getRequestDispatcher("/views/writePost.jsp");
        rd.forward(request, response);

    }

}
