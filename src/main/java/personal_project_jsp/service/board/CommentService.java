package personal_project_jsp.service.board;

import java.util.Map;

public interface CommentService {

    Map<String, Object> showComments(Map<String, Object> data);

    Map<String, Object> writeComment(Map<String, Object> data);
    Map<String, Object> modifyComment(Map<String, Object> data);
    Map<String, Object> deleteComment(Map<String, Object> data);
}
