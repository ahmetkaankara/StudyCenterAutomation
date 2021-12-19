package Model;

public class User {
	private int user_id;
	private String name,tc_no,password,type;
	
	
	
	public User(int user_id, String name, String tc_no, String password, String type) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.tc_no = tc_no;
		this.password = password;
		this.type = type;
	}
	
	public User() {}
	
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTc_no() {
		return tc_no;
	}
	public void setTc_no(String tc_no) {
		this.tc_no = tc_no;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
