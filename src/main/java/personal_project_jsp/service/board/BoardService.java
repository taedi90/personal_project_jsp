package personal_project_jsp.service.board;

import java.util.Map;

public interface BoardService {
    Map<String, Object> showPosts(Map<String, Object> data);

    Map<String, Object> writePost(Map<String, Object> data);
    Map<String, Object> modifyPost(Map<String, Object> data);
    Map<String, Object> deletePost(Map<String, Object> data);

}
