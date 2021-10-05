package personal_project_jsp.dao;

import java.util.ArrayList;
import java.util.LinkedList;

import personal_project_jsp.dto.Board;
import personal_project_jsp.dto.Comment;

public interface CommentDao {
	
	// 코멘트 검색
	LinkedList<Comment> selectCommentByPostNo(Board board);
	
	// 코멘트 입력
	int insertComment(Comment comment);
	
	// 코멘트 수정
	int updateComment(Comment comment);
	
	// 코멘트 삭제 (삭제는 내용만 지우기)
	int deleteComment(Comment comment);

}
