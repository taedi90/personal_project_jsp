package personal_project_jsp.dto;

public class Board {
	private long no;
	private String id;
	private String title;
	private String category;
	private String content;
	private String wriDate;
	private String modDate;
	private String thumb;

	public Board() {
	}

	public Board(String id, String title, String category, String content) {
		super();
		this.id = id;
		this.title = title;
		this.category = category;
		this.content = content;
	}

	public Board(long no, String id, String title, String category, String content, String wriDate, String modDate,
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriDate() {
		return wriDate;
	}

	public void setWriDate(String wriDate) {
		this.wriDate = wriDate;
	}

	public String getModDate() {
		return modDate;
	}

	public void setModDate(String modDate) {
		this.modDate = modDate;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", id=" + id + ", title=" + title + ", category=" + category + ", content="
				+ content + ", wriDate=" + wriDate + ", modDate=" + modDate + ", thumb=" + thumb + "]";
	}
	
	
	
}
