package personal_project_jsp.service.board.impl;

import personal_project_jsp.dao.BoardDao;
import personal_project_jsp.dao.impl.BoardDaoImpl;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Category;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.board.BoardService;

import java.util.HashMap;
import java.util.Map;

public class BoardServiceImpl implements BoardService {
    private static final BoardServiceImpl instance = new BoardServiceImpl();
    private BoardServiceImpl(){}
    public static BoardServiceImpl getInstance(){
        return instance;
    }




    @Override
    public Map<String, Object> showPosts(Map<String, Object> data) {

        BoardDao dao = BoardDaoImpl.getInstance();

        switch ((String) data.get("action")) {
            case "myPost":
                data.put("dbResult", myPost(data, dao));
                break;
            case "newCategory":
                data.put("dbResult", newCategory(data, dao));
                break;
            case "searchPost":
                data.put("dbResult", searchPost(data, dao));
                break;
            default:
                data.put("dbResult", normal(data, dao));
                break;
        }

        return data;
    }

    private Map<String, Object> normal(Map<String, Object> data, BoardDao dao) {

        return dao.selectBoardByAll(
                Integer.parseInt((String) data.get("idx")),
                Integer.parseInt((String) data.get("num")),
                (String) data.get("order"));

    }

    private Map<String, Object> myPost(Map<String, Object> data, BoardDao dao) {

        return dao.selectBoardById(
                new User((String) data.get("id")),
                Integer.parseInt((String) data.get("idx")),
                Integer.parseInt((String) data.get("num")),
                (String) data.get("order"));

    }

    private Map<String, Object> newCategory(Map<String, Object> data, BoardDao dao) {

        return dao.selectBoardByCategory(
                new Category((String) data.get("category")),
                Integer.parseInt((String) data.get("idx")),
                Integer.parseInt((String) data.get("num")),
                (String) data.get("order"));

    }

    private Map<String, Object> searchPost(Map<String, Object> data, BoardDao dao) {

        return dao.selectBoardByKeyword(
                (String) data.get("keyword"),
                Integer.parseInt((String) data.get("idx")),
                Integer.parseInt((String) data.get("num")),
                (String) data.get("order"));
    }


    @Override
    public Map<String, Object> writePost(Map<String, Object> data) {

        Map<String, Object> res = new HashMap<>();


        // 값이 모두 다 들어왔는지 확인 (로그인 체크 포함)
        if((String)data.get("id") == null){
            res.put("res","0");
            res.put("comment", "로그인 필요");
            return res;
        }else if((String)data.get("title") == null
                || (String)data.get("content") == null
                || (String)data.get("category") == null ) {
            res.put("res","0");
            res.put("comment", "필수 입력 누락");
            return res;
        }

        // 사용하면 안되는 태그 정규식 처리


        // 카테고리가 기준에 부합하는지 여부 확인



        Board board = new Board();
        board.setCategory(new Category((String) data.get("category")));
        board.setId((String) data.get("id"));
        board.setTitle((String) data.get("title"));
        board.setContent((String) data.get("content"));
        board.setThumb((String) data.get("thumb"));

        //System.out.println("포스트 작성 요청 받은 값 \n" + board);

        // call dao
        BoardDao dao = BoardDaoImpl.getInstance();
        int dbResult = dao.insertBoard(board);


        // 정상처리되면 원래화면으로 넘어가고 에러는 기존화면(js에서 처리)
        if(dbResult == 1){
            res.put("res","1");
            res.put("comment", "등록 완료!");
        }else{
            res.put("res","0");
            res.put("comment", "등록 실패!");
        }

        return res;
    }

    @Override
    public Map<String, Object> modifyPost(Map<String, Object> data) {

        Map<String, Object> res = new HashMap<>();


        // 값이 모두 다 들어왔는지 확인 (로그인 체크 포함)
        if((String)data.get("id") == null){
            res.put("res","0");
            res.put("comment", "로그인 필요");
            return res;
        }else if((String)data.get("title") == null
                || (String)data.get("content") == null
                || (String)data.get("category") == null ) {
            res.put("res","0");
            res.put("comment", "필수 입력 누락");
            return res;
        }

        // 사용하면 안되는 태그 정규식 처리


        // 카테고리가 기준에 부합하는지 여부 확인

        // 아이디 체크


        Board board = new Board();
        board.setNo((Long.parseLong((String) data.get("postNo"))));
        board.setCategory(new Category((String) data.get("category")));
        board.setId((String) data.get("id"));
        board.setTitle((String) data.get("title"));
        board.setContent((String) data.get("content"));
        board.setThumb((String) data.get("thumb"));

        //System.out.println("포스트 작성 요청 받은 값 \n" + board);

        // call dao
        BoardDao dao = BoardDaoImpl.getInstance();
        int dbResult = dao.updateBoard(board);


        // 정상처리되면 원래화면으로 넘어가고 에러는 기존화면(js에서 처리)
        if(dbResult == 1){
            res.put("res","1");
            res.put("comment", "등록 완료!");
        }else{
            res.put("res","0");
            res.put("comment", "등록 실패!");
        }

        return res;
    }

    @Override
    public Map<String, Object> deletePost(Map<String, Object> data) {
        return null;
    }

}
