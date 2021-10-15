package personal_project_jsp.controller.board;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import personal_project_jsp.dao.BoardDao;
import personal_project_jsp.dao.UserDao;
import personal_project_jsp.dao.impl.BoardDaoImpl;
import personal_project_jsp.dao.impl.UserDaoImpl;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AuthChk")
public class AuthChkController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 세션 아이디와 게시글 번호 매치되는지 확인

        int res = 0;
        String comment = null;

        Board board = new Board();
        board.setNo(Long.parseLong(request.getParameter("no")));

        BoardDao boardDao = BoardDaoImpl.getInstance();
        board = boardDao.selectBoardByNo(board);

        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("user");

        UserDao dao = UserDaoImpl.getInstance();
        User user = new User();
        user.setId(id);

        user = dao.getUserInfo(user);
        int rootPermission = user.getRootPermission();

        System.out.println("게시물 작성자 아이디" + board.getId());
        System.out.println("로그인 세션 아이디" + id);
        System.out.println("관리자 권한" +rootPermission);
        System.out.println("로그인정보 확인" + user.getId().equals(id));

        if(id == null){
            res = 2;
            comment = "로그인 정보 없음";
        }else if(board.getId().equals(id) || rootPermission == 1){

            res = 1;
            comment = "수정 가능";
        }else if(!board.getId().equals(id)){
            res = 0;
            comment = "작성자만 수정 또는 삭제 가능";
        }else{
            res = 0;
            comment = "오류로 인하여 수정이 불가합니다";
        }


        // 응답할 json 만들기
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("res", res);
        jsonObject.addProperty("comment", comment);


        // 회신
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));

    }


    }
