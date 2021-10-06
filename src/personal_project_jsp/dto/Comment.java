package personal_project_jsp.dto;

import java.sql.Timestamp;

public class Comment {
	private long cno;	//댓글번호
	private long postNo; //게시물번호 -> board 객체로? 설마?
	private long pCno; //상위댓글 -> Long 자료형은 null이 전달된다?
	//private String id; //작성자 -> user 객체로 받아야 하는건가?
	//private String name; //작성자명(join)
	private User user; //작성자 정보
	private String comment; //댓글 내용
	private Timestamp wriDate; //작성일
	private Timestamp modDate; //변경일
	private boolean delete; //삭제여부
	private int depth; //계층

	
	


	public Comment() {
	}


	public Comment(long postNo, long pCno, User user, String comment, int depth) {
		super();
		this.postNo = postNo;
		this.pCno = pCno;
		this.user = user;
		this.comment = comment;
		this.depth = depth;
	}


	public long getCno() {
		return cno;
	}


	public void setCno(long cno) {
		this.cno = cno;
	}


	public long getPostNo() {
		return postNo;
	}


	public void setPostNo(long postNo) {
		this.postNo = postNo;
	}


	public long getpCno() {
		return pCno;
	}


	public void setpCno(long pCno) {
		this.pCno = pCno;
	}

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	public Timestamp getWriDate() {
		return wriDate;
	}


	public void setWriDate(Timestamp wriDate) {
		this.wriDate = wriDate;
	}


	public Timestamp getModDate() {
		return modDate;
	}


	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}


	public boolean isDelete() {
		return delete;
	}


	public void setDelete(boolean delete) {
		this.delete = delete;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
	}


	@Override
	public String toString() {
		return "Comment [cno=" + cno + ", postNo=" + postNo + ", pCno=" + pCno + ", user=" + user + ", comment="
				+ comment + ", wriDate=" + wriDate + ", modDate=" + modDate + ", delete=" + delete + ", depth=" + depth
				+ "]";
	}




	
	
	

}
