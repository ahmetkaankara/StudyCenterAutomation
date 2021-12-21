package Model;

import java.sql.*;
import java.util.ArrayList;
import Helper.*;

public class Admin extends User{
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;


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

		return list;
		

	}
	
	public boolean addTeacher(String name,String tcno,String password) throws SQLException {
		String query = "INSERT INTO user " + "(name,tc_no,password,type) VALUES"+ "(?,?,?,?)";
		
		boolean key = false;
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, "teacher");
			preparedStatement.executeUpdate();
			key = true;
			
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		if(key == true) 
			return true;
		
		else 
			return false;
		

		
		
	}
	public boolean deleteTeacher(int id) throws SQLException {
		String query = "DELETE FROM user WHERE user_id = ?";
		
		boolean key = false;
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1,id);
	
			preparedStatement.executeUpdate();
			key = true;
			
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		if(key == true) 
			return true;
		
		else 
			return false;
	}
	
	public boolean updateTeacher(int id,String name,String tcno,String password) throws SQLException {
		String query = "UPDATE user SET name = ?,tc_no=?,password=? WHERE user_id = ?";
		
		boolean key = false;
		
		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, tcno);
			preparedStatement.setString(3, password);
			preparedStatement.setInt(4, id);
	
			preparedStatement.executeUpdate();
			key = true;
			
		}
		
		catch(Exception e){
			e.printStackTrace();
			
		}
		
		if(key == true) 
			return true;
		
		else 
			return false;
	}
}
