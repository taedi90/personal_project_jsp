package personal_project_jsp.dao;

import java.util.Map;

import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Category;

public interface BoardDao {
	// 전체 페이지
	Map<String, Object> selectBoardByAll(int idx, int num, String order);
	
	// 검색용
	Map<String, Object> selectBoardByCategory(Category category, int idx, int num, String order);
	Map<String, Object> selectBoardByTitle(Category category, int idx, int num, String order);
	Map<String, Object> selectBoardById(Category category, int idx, int num, String order);
	

	Board selectBoardByNo(Board board);
	
	
	int insertBoard(Board board);
	int deleteBoard(Board board);
	int updateBoard(Board board);
	
	
}
