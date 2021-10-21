package personal_project_jsp.dao;

import java.util.Map;

import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Category;
import personal_project_jsp.dto.User;

public interface BoardDao {
	// 전체 페이지
	Map<String, Object> selectBoardByAll(int idx, int num, String order);
	
	// 검색용
	Map<String, Object> selectBoardByCategory(Category category, int idx, int num, String order);
	Map<String, Object> selectBoardByKeyword(String keyword, int idx, int num, String order);
	Map<String, Object> selectBoardById(User user, int idx, int num, String order);
	

	Board selectBoardByNo(Board board);
	
	
	int insertBoard(Board board);
	int deleteBoard(Board board);

	int deleteBoardContext(Board board);

	int updateBoard(Board board);
	
	
}
