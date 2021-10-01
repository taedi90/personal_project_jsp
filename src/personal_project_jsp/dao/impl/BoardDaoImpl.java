package personal_project_jsp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
		Date wriDate = rs.getDate("wri_date");
		Date modDate = rs.getDate("mod_date");
		String thumb = rs.getString("thumb");

		return new Board(no, id, title, category, content, wriDate, modDate, thumb);
	}
	
	private Map<String, Object> getMap(ResultSet rs1, ArrayList<Board> boardArr,int idx, int num) throws SQLException {
		// 조회값에 대한 데이터를 반환할 map 생성
		Map<String, Object> map = new HashMap<>();
		
		map.put("maxPost", rs1.getInt(1)); // 전체글 수
		map.put("nowPost", (idx-1)*num + boardArr.size()); // 현재글 (누적 idx)
		map.put("maxPageIdx", Math.ceil(rs1.getInt(1) / num )); // 총 페이지 인덱스
		map.put("nowPageIdx", idx); // 현재 페이지 인덱스
		map.put("list", boardArr); // 현재 페이지 글 리스트
		
		return map;
	}
	
	@Override
	public Map<String, Object> selectBoardByAll(int idx, int num, String order) {
		String sql1 = "select count(*) FROM board";
		String sql2 = "select R1.* FROM(SELECT * FROM board order by no " + order + " ) R1 LIMIT ?, ?";
		ArrayList<Board> boardArr = null;
		Map<String, Object> map = null;
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				ResultSet rs1 = pstmt1.executeQuery();){
			if(rs1.next() && rs1.getInt(1) <= 0) {
				return null;
			}
			pstmt2.setInt(1, (idx-1)*num);
			pstmt2.setInt(2, num);
			try(ResultSet rs2 = pstmt2.executeQuery();){
				if(rs2.next()) {
					boardArr = new ArrayList<>();
					do {
						boardArr.add(getBoard(rs2));
					}while(rs2.next());
				}
				map = getMap(rs1, boardArr, idx, num);
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
	public Map<String, Object> selectBoardByCategory(Category category, int idx, int num, String order) {
		String sql1 = "select count(*) FROM board where category = ?";
		String sql2 = "select R1.* FROM(SELECT * FROM board where category = ? order by no " + order + " ) R1 LIMIT ?, ?";
		ArrayList<Board> boardArr = null;
		Map<String, Object> map = null;
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				PreparedStatement pstmt2 = con.prepareStatement(sql2);){
			
			pstmt1.setString(1, category.getCategory());
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next() && rs1.getInt(1) <= 0) {
				return null;
			}
			
			pstmt2.setString(1, category.getCategory());
			pstmt2.setInt(2, (idx-1)*num);
			pstmt2.setInt(3, num);
			try(ResultSet rs2 = pstmt2.executeQuery();){
				if(rs2.next()) {
					boardArr = new ArrayList<>();
					do {
						boardArr.add(getBoard(rs2));
					}while(rs2.next());
				}
				map = getMap(rs1, boardArr, idx, num);
				return map;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public Map<String, Object> selectBoardByTitle(Category category, int idx, int num, String order) {
		
		return null;
	}

	@Override
	public Map<String, Object> selectBoardById(Category category, int idx, int num, String order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(Board board) {
		String sql = "insert into board(id, title, category, content) values ( ?, ?, ?, ? )";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){

			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getCategory());
			pstmt.setString(4, board.getContent());
			
			return  pstmt.executeUpdate();


		} catch(SQLException e) {
			e.printStackTrace();
		}
		
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
