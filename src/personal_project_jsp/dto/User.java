package personal_project_jsp.dto;

import java.util.Date;

public class User {
	private String id;
	private String name;
	private String password;
	private String email;
	private Date regDate;
	private int rootPermission;
	
	public User() {
	}
	
	public User(String id, String name, String password, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	
	public User(String id, String name, String password, String email, int rootPermission) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.email = email;
		this.rootPermission = rootPermission;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getRootPermission() {
		return rootPermission;
	}

	public void setRootPermission(int rootPermission) {
		this.rootPermission = rootPermission;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", reg_date="
				+ regDate + "]";
	}
	
	
	
}
