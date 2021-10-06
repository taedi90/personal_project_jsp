package personal_project_jsp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;

import personal_project_jsp.dao.CommentDao;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Comment;
import personal_project_jsp.dto.User;
import personal_project_jsp.util.JdbcUtil;

public class CommentDaoImpl implements CommentDao {
	
	public static final CommentDaoImpl instance = new CommentDaoImpl();
	public static CommentDaoImpl getInstance() {return instance;}
	private CommentDaoImpl() {}
	
	
	
	private Comment getComment(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		User user = new User();
		
		comment.setCno(rs.getLong("cno"));
		comment.setPostNo(rs.getLong("post_no"));
		comment.setpCno(rs.getLong("p_cno"));
		
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		comment.setUser(user);
		
		comment.setComment(rs.getString("comment"));
		comment.setWriDate(rs.getTimestamp("wri_date"));
		comment.setModDate(rs.getTimestamp("mod_date"));
		comment.setDelete(rs.getBoolean("delete"));
		comment.setDepth(rs.getInt("depth"));
		
		return comment;
	}
	
	

	@Override
	public LinkedList<Comment> selectCommentByPostNo(Board board) {
		
		// 게시물 번호에 해당하는 댓글(계층 오름차순, 번호 내림차순 정렬)
		// String sql = "select * from comment where post_no = ? order by depth, cno desc";
		String sql = "select `cno`, `post_no`, `p_cno`, `c`.`id` id, `comment`, `wri_date`, `mod_date`, `delete`, `depth`, `name` from `comment` c left join `user` u on c.id = u.id where post_no = ? order by depth, cno desc";
		
		// 댓글들을 저장할 배열(LL)
		LinkedList<Comment> list = null;

		Comment comment = null;

		
		try(Connection con = JdbcUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){
			
			
			pstmt.setLong(1, board.getNo());
			try(ResultSet rs = pstmt.executeQuery();){
				if(rs.next()) {
					list = new LinkedList<Comment>(); 
					long parent = 0;
					do {

						comment = getComment(rs);
						
						// 댓글들 순서대로 정렬하기
						if(comment.getDepth() == 1) { 
							list.add(0, comment);  // 최상위 댓글들은 리스트 앞에 추가 (내림차순 -> 오름차순)
						}else {
							parent = comment.getpCno(); //
							int i = 0;
							
							for(Comment c : list) {
								if(parent == c.getCno()) {
									list.add(i+1, comment); // 하위 댓글들은 상위 댓글 뒤에 끼워넣기 (내림차순 -> 오름차순)
									break;
								}
								i++;
							}
							
						}
						
					}while(rs.next());
					
				}

				return list;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
	
		return null;
	}

	@Override
	public int insertComment(Comment comment) {

		String sql = "insert into comment(`post_no`, `p_cno`, `id`, `comment`, `depth`) values (?, ?, ?, ?, ?)";

		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setLong(1, comment.getPostNo());
						
			if(comment.getpCno() == 0) {
				pstmt.setNull(2, Types.BIGINT);
			}else {
				pstmt.setLong(2, comment.getpCno());
			}

//			pstmt.setLong(2, comment.getpCno() == 0 ? null : comment.getpCno());
			pstmt.setString(3, comment.getUser().getId());
			pstmt.setString(4, comment.getComment());
			pstmt.setInt(5, comment.getDepth());
			
			return pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int updateComment(Comment comment) {
		String sql = "update comment set comment = ? where cno = ? and id = ?";

		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setString(1, comment.getComment());
			pstmt.setLong(2, comment.getCno());
			pstmt.setString(3, comment.getUser().getId());
						
			return pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int deleteComment(Comment comment) { // 실제로는 내용만 지움
		
		String sql = "update comment set `delete` = 1 where `cno` = ? and `id` = ?";

		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			
			pstmt.setLong(1, comment.getCno());
			pstmt.setString(2, comment.getUser().getId());
						
			return pstmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public Comment selectCommentByCommentNo(Comment comment) {

		String sql = "select * from comment where cno = ?";

		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {

			pstmt.setLong(1, comment.getCno());
			try (ResultSet rs = pstmt.executeQuery();) {
				if (rs.next()) {
					comment = getComment(rs);

				}

				return comment;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
