package personal_project_jsp.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import personal_project_jsp.dao.BoardDao;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Category;
import personal_project_jsp.dto.User;
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
		Category category = new Category(rs.getString("category"));
		String content = rs.getString("content");
		Date wriDate = rs.getDate("wri_date");
		Date modDate = rs.getDate("mod_date");
		String thumb = rs.getString("thumb");
		Date deleteDate = rs.getDate("delete_date");

		return new Board(no, id, title, category, content, wriDate, modDate, thumb);
	}
	
	private Map<String, Object> getMap(ResultSet rs1, ArrayList<Board> boardArr,int idx, int num) throws SQLException {
		// 조회값에 대한 데이터를 반환할 map 생성
		Map<String, Object> map = new HashMap<>();
		
		map.put("maxPost", rs1.getInt(1)); // 전체글 수
		map.put("nowPost", (idx-1)*num + boardArr.size()); // 현재글 (누적 idx)
		map.put("maxPageIdx", Math.ceil((double) rs1.getInt(1) / num )); // 총 페이지 인덱스
		map.put("nowPageIdx", idx); // 현재 페이지 인덱스
		map.put("list", boardArr); // 현재 페이지 글 리스트
		
		return map;
	}
	
	@Override
	public Map<String, Object> selectBoardByAll(int idx, int num, String order) {
		String sql1 = "select count(*) FROM board where delete_date is NULL";
		String sql2 = "select R1.* FROM(SELECT * FROM board where delete_date is NULL order by no " + order + " ) R1 LIMIT ?, ?";
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
		String sql = "select * FROM board where no = ?";
		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			
			pstmt.setLong(1, board.getNo());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					board = getBoard(rs);
				}

				return board;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return board;
	}

	@Override
	public Map<String, Object> selectBoardByCategory(Category category, int idx, int num, String order) {
		String sql1 = "select count(*) FROM board where category = ? and delete_date is NULL";
		String sql2 = "select R1.* FROM(SELECT * FROM board where category = ? and delete_date is NULL order by no " + order + " ) R1 LIMIT ?, ?";
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
	public Map<String, Object> selectBoardByKeyword(String keyword, int idx, int num, String order) {
		String sql1 = "select count(*) FROM board where (content like '%" + keyword + "%' or title like '%" + keyword + "%') and delete_date is NULL ";
		String sql2 = "select R1.* FROM(SELECT * FROM board where (content like '%" + keyword + "%' or title like '%" + keyword + "%') and delete_date is NULL order by no " + order + " ) R1 LIMIT ?, ?";
		ArrayList<Board> boardArr = null;
		Map<String, Object> map = null;
		
		System.out.println("디비에서 키워드" + keyword);
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				PreparedStatement pstmt2 = con.prepareStatement(sql2);
				ResultSet rs1 = pstmt1.executeQuery();){

			if(rs1.next() && rs1.getInt(1) <= 0) {
				return null;
			}
			
			pstmt2.setInt(1, (idx-1)*num);
			pstmt2.setInt(2, num);
			
			System.out.println(pstmt2.toString());
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
	public Map<String, Object> selectBoardById(User user, int idx, int num, String order) {
		String sql1 = "select count(*) FROM board where id = ?";
		String sql2 = "select R1.* FROM(SELECT * FROM board where id = ?  and delete_date is NULL order by no " + order + " ) R1 LIMIT ?, ?";
		ArrayList<Board> boardArr = null;
		Map<String, Object> map = null;
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt1 = con.prepareStatement(sql1);
				PreparedStatement pstmt2 = con.prepareStatement(sql2);){
			
			pstmt1.setString(1, user.getId());
			ResultSet rs1 = pstmt1.executeQuery();
			if(rs1.next() && rs1.getInt(1) <= 0) {
				return null;
			}
			
			pstmt2.setString(1, user.getId());
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
	public int insertBoard(Board board) {
		String sql = "insert into board(id, title, category, content, thumb) values ( ?, ?, ?, ?, ?)";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			System.out.println(board);
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getCategory().getCategory());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getThumb().equals("")? null : board.getThumb());
			
			return  pstmt.executeUpdate();


		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int deleteBoard(Board board) {
		String sql = "delete from board where no = ?";
		
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
				
				pstmt.setLong(1, board.getNo());
				
				return pstmt.executeUpdate();
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return 0;
					
		}

	@Override
	public int deleteBoardContext(Board board) {
		String sql = "update board set title = '', content = '', thumb = '', delete_date = ? where no = ?";
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
		){

			pstmt.setTimestamp(1, new Timestamp(new Date().getTime()));
			pstmt.setLong(2, board.getNo());

			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public int updateBoard(Board board) {
		String sql = "update board set title = ?, category = ?, content = ?, thumb = ? where no = ?";
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			){
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getCategory().getCategory());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getThumb().equals("")? null : board.getThumb());
			pstmt.setLong(5, board.getNo());
			
			
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return 0;
				
	}


}
