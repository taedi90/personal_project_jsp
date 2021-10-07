package personal_project_jsp.dto;

import java.util.Date;

public class User {
	private String id;
	private String name;
	private String password;
	private String salt;
	private String originPass;
	private String email;
	private Date regDate;
	private Date withdrawDate;
	private int rootPermission;
	
	public User() {
	}
	
	
	
	public User(String id) {
		super();
		this.id = id;
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

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", origin_pass='" + originPass + '\'' +
				", email='" + email + '\'' +
				", regDate=" + regDate +
				", withdraw_date=" + withdrawDate +
				", rootPermission=" + rootPermission +
				'}';
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setOriginPass(String originPass) {
		this.originPass = originPass;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public void setWithdrawDate(Date withdrawDate) {
		this.withdrawDate = withdrawDate;
	}

	public void setRootPermission(int rootPermission) {
		this.rootPermission = rootPermission;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	public String getOriginPass() {
		return originPass;
	}

	public String getEmail() {
		return email;
	}

	public Date getRegDate() {
		return regDate;
	}

	public Date getWithdrawDate() {
		return withdrawDate;
	}

	public int getRootPermission() {
		return rootPermission;
	}
}
