package Model;

public class Admin extends User{

	public Admin(int user_id, String name, String tc_no, String password, String type) {
		super(user_id, name, tc_no, password, type);
	}
	
	public Admin() {}
}
