package personal_project_jsp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import personal_project_jsp.dao.BoardDao;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Category;
import personal_project_jsp.util.JdbcUtil;

public class BoardDaoImpl implements BoardDao {
	// Singleton
	private static final BoardDaoImpl instance = new BoardDaoImpl();
	public static BoardDaoImpl getInstance() {return instance;}
	private BoardDaoImpl() {}

	private Board getBoard(ResultSet rs) throws SQLException {
		long no = rs.getLong("no");
		String id = rs.getString("id");
		String title = rs.getString("title");
		String category = rs.getString("category");
		String content = rs.getString("content");
		String wriDate = rs.getString("wri_date");
		String modDate = rs.getString("mod_date");
		String thumb = rs.getString("thumb");

		return new Board(no, id, title, category, content, wriDate, modDate, thumb);
	}
	
	@Override
	public Map<String, Object> selectBoardByAll(int idx, int num) {
		String sql1 = "select count(*) FROM board";
		String sql2 = "select R1.* FROM(SELECT * FROM board order by no desc) R1 LIMIT ?, ?";
		ArrayList<Board> boardArr = null;
		Map<String, Object> map = null;
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				ResultSet rs1 = pstmt1.executeQuery();){
			if(rs1.next() && rs1.getInt(1) <= 0) {
				return null;
			}
			
			pstmt2.setInt(1, idx - 1);
			pstmt2.setInt(2, num);
			try(ResultSet rs2 = pstmt2.executeQuery();){
				if(rs2.next()) {
					boardArr = new ArrayList<>();
					do {
						boardArr.add(getBoard(rs2));
					}while(rs2.next());
				}
				map = new HashMap<>();
				map.put("total", rs1.getInt(1));
				map.put("now", boardArr.size());
				map.put("accum", (idx-1)*num + boardArr.size());
				map.put("list", boardArr);
				return map;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Board selectBoardByNo(Board board) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectBoardByCategory(Category category, int idx, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectBoardByTitle(Category category, int idx, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectBoardById(Category category, int idx, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}


}
