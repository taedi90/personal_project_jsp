package personal_project_jsp.service.board.impl;

import personal_project_jsp.dao.CommentDao;
import personal_project_jsp.dao.impl.CommentDaoImpl;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Comment;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.board.CommentService;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CommentServiceImpl implements CommentService {
    private static final CommentServiceImpl instance = new CommentServiceImpl();
    private CommentServiceImpl(){}
    public static CommentServiceImpl getInstance(){return instance;}


    @Override
    public Map<String, Object> showComments(Map<String, Object> data) {

        System.out.println((String)data.get("postNo"));

        Board board = new Board();
        board.setNo(Long.parseLong((String)data.get("postNo")));

        CommentDao dao = CommentDaoImpl.getInstance();

        LinkedList<Comment> list = dao.selectCommentByPostNo(board);

        //map.put("sessionId", (String)data.get("id"));


        int maxDepth = 0;
        double margin = 3.0; // 대댓글 간격
        if(list != null){
            for (Comment c : list){
                if(c.getDepth() >  maxDepth){
                    maxDepth = c.getDepth();
                }
            }
            margin = 40.0 / maxDepth;

            if (margin > 3){
                margin = 3;
            }
            System.out.println("최대 깊이" + maxDepth + "마진 사이즈" + margin);
        }

        data.put("dbResult", list);
        data.put("margin", margin);

        System.out.println(data.get("dbResult"));

        return data;


    }

    @Override
    public Map<String, Object> writeComment(Map<String, Object> data) {

        Long postNo = Long.parseLong((String)data.get("postNo"));
        String id = (String)data.get("id");
        Long pCno = ((String)data.get("parentNo")).equals("")? 0: Long.parseLong((String)data.get("parentNo"));
        String comment = (String)data.get("comment");

        CommentDao dao = CommentDaoImpl.getInstance();

        // pCno가 있으면 depth 구하기
        int depth = 1;

        if(pCno != 0) {
            Comment parent = new Comment();
            parent.setCno(pCno);
            parent = dao.selectCommentByCommentNo(parent);
            depth = parent.getDepth() + 1;
        }


        // 코멘트 생성
        Comment newCom = new Comment(postNo, pCno, new User(id), comment, depth);

        int res = dao.insertComment(newCom);


        // 게시물 갱신


        Map<String, Object> map = new HashMap<>();

        map.put("res", res);

        return map;
    }

    @Override
    public Map<String, Object> modifyComment(Map<String, Object> data) {
        String id = (String)data.get("id");
        Long cno = Long.parseLong((String)data.get("cno"));
        String comment = (String)data.get("comment");

        CommentDao dao = CommentDaoImpl.getInstance();

        // 코멘트 생성
        Comment comm = new Comment();
        comm.setCno(cno);
        comm.setComment(comment);
        comm.setUser(new User(id));

        int res = dao.updateComment(comm);


        // 게시물 갱신


        Map<String, Object> map = new HashMap<>();

        map.put("res", res);

        return map;
    }

    @Override
    public Map<String, Object> deleteComment(Map<String, Object> data) {
        CommentDao dao = CommentDaoImpl.getInstance();

        // 코멘트 생성
        Comment comm = new Comment();
        comm.setCno(Long.parseLong((String)data.get("cno")));
        comm.setUser(new User((String) data.get("id")));


        int res = dao.deleteComment(comm);
        System.out.println("댓글 삭제-" + res );

        Map<String, Object> map = new HashMap<>();

        map.put("res", res);

        return map;
    }
}
