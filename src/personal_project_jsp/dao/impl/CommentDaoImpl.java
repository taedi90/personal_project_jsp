package personal_project_jsp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import personal_project_jsp.dao.CommentDao;
import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Comment;
import personal_project_jsp.util.JdbcUtil;

public class CommentDaoImpl implements CommentDao {
	
	public static final CommentDaoImpl instance = new CommentDaoImpl();
	public static CommentDaoImpl getInstance() {return instance;}
	private CommentDaoImpl() {}
	
	
	
	private Comment getComment(ResultSet rs) throws SQLException {
		Comment comment = new Comment();
		
		comment.setCno(rs.getLong("cno"));
		comment.setPostNo(rs.getLong("post_no"));
		comment.setpCno(rs.getLong("p_cno"));
		comment.setId(rs.getString("id"));
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
		String sql = "select * from comment where post_no = ? order by depth, cno desc";
		
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
						if(comment.getDepth() == 1) { // 최상위 댓글들은 리스트 앞으로 추가
							list.add(0, comment);  // (내림차순 -> 오름차순)
						}else {// 하위 댓글들은 상위 댓글 뒤에 끼워넣기
							parent = comment.getpCno(); //
							int i = 0;
							
							for(Comment c : list) {
								if(parent == c.getCno()) {
									list.add(i+1, comment); // (내림차순 -> 오름차순)
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		return 0;
	}

}
