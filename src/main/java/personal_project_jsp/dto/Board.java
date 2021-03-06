package personal_project_jsp.dto;

import java.util.Date;

public class Board {
	private long no;
	private String id;
	private String title;
	private Category category;
	private String content;
	private Date wriDate;
	private Date modDate;
	private String thumb;
	private Date deleteDate;

	public Board() {
	}

	public Board(String id, String title, Category category, String content) {
		this.id = id;
		this.title = title;
		this.category = category;
		this.content = content;
	}

	public Board(long no, String id, String title, Category category, String content, Date wriDate, Date modDate,
			String thumb) {
		this.no = no;
		this.id = id;
		this.title = title;
		this.category = category;
		this.content = content;
		this.wriDate = wriDate;
		this.modDate = modDate;
		this.thumb = thumb;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriDate() {
		return wriDate;
	}

	public void setWriDate(Date wriDate) {
		this.wriDate = wriDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	@Override
	public String toString() {
		return "Board{" +
				"no=" + no +
				", id='" + id + '\'' +
				", title='" + title + '\'' +
				", category=" + category +
				", content='" + content + '\'' +
				", wriDate=" + wriDate +
				", modDate=" + modDate +
				", thumb='" + thumb + '\'' +
				", deleteDate=" + deleteDate +
				'}';
	}
}
