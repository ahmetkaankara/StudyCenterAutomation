package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Etut;
import Helper.DBConnection;

public class Teacher extends User {

	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Teacher(int user_id, String name, String tc_no, String password, String type) {
		super(user_id, name, tc_no, password, type);
	}

	public Teacher() {
	}

	public ArrayList<User> getStudentList() throws SQLException {
		ArrayList<User> list = new ArrayList<>();
		User obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE type = 'student'");
			while (rs.next()) {
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

	public ArrayList<Etut> getEtutList(int teacher_id) throws SQLException {
		ArrayList<Etut> list = new ArrayList<>();
		Etut obj;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status='a'  AND teacher_id = " + teacher_id);
			while (rs.next()) {
				obj = new Etut(); 
				obj.setId(rs.getInt("id"));
				obj.setTeacher_id(rs.getInt("teacher_id"));
				obj.setTeacher_name(rs.getString("teacher_name"));
				obj.setStatus(rs.getString("status"));
				obj.setWdate(rs.getString("wdate"));
				list.add(obj);
		
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	public boolean addWhour(int teacher_id, String teacher_name,String wdate) {

		int key = 0;
		int count =0;
		String query = "INSERT INTO whour" + "(teacher_id,teacher_name,wdate) VALUES" + "(?,?,?)";
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM whour WHERE status='a' AND teacher_id ="+teacher_id+ " AND wdate ='"+wdate+"'");
			
			
			while (rs.next()) {
				count++;
				break;
			}
			if(count == 0) {
				preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, teacher_id);
				preparedStatement.setString(2, teacher_name);
				
				preparedStatement.setString(3, wdate);
				preparedStatement.executeUpdate();
			}
			key = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(key==1)
			return true;
		else 
			return false;
	}
	
	public boolean deleteWhour(int id)throws SQLException {
		String query = "DELETE FROM whour WHERE id = ?";
		boolean key = false;
		
		try {
			st= con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			key=true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if (key) {
			return true;
		}else {
			return false;
		}
	}
}
	