package personal_project_jsp.service.board.impl;

import personal_project_jsp.dao.BoardDao;
import personal_project_jsp.dao.impl.BoardDaoImpl;
import personal_project_jsp.dto.Category;
import personal_project_jsp.dto.User;
import personal_project_jsp.service.board.BoardService;

import java.util.Map;

public class BoardServiceImpl implements BoardService {
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


}
