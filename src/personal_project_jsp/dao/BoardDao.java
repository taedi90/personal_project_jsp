package personal_project_jsp.dao;

import java.util.Map;

import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Category;

public interface BoardDao {
	// 총 페이지, idx번째 페이지 배열 이렇게 보여주면 어떨까
	Map<String, Object> selectBoardByAll(int idx, int num, String order);
	
	Board selectBoardByNo(Board board);
	
	Map<String, Object> selectBoardByCategory(Category category, int idx, int num, String order);
	
	Map<String, Object> selectBoardByTitle(Category category, int idx, int num, String order);
	
	Map<String, Object> selectBoardById(Category category, int idx, int num, String order);
	
	int insertBoard(Board board);
	int deleteBoard(Board board);
	int updateBoard(Board board);
	
	
}
