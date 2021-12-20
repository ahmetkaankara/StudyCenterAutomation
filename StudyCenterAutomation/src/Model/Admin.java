package Model;

import java.sql.*;
import java.util.ArrayList;
import Helper.*;

public class Admin extends User{
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;

	public Admin(int user_id, String name, String tc_no, String password, String type) {
		super(user_id, name, tc_no, password, type);
	}
	
	public Admin() {}
	
	public ArrayList<User> getTeacherList() throws SQLException{
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'teacher'");
			while(rs.next()) {
				obj = new User();
				obj.setUser_id(rs.getInt("user_id"));
				obj.setName(rs.getString("name"));
				obj.setTc_no(rs.getString("tc_no"));
				obj.setPassword(rs.getString("password"));
				obj.setType(rs.getString("type"));
				list.add(obj);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			//st.close();
			//rs.close();
			//con.close();
		}
		return list;
		

	}
}
