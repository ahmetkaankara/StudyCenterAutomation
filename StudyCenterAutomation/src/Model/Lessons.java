package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Lessons {
	private int lessons_id;
	private String name;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;

	public Lessons() {
	}

	public int getLessons_id() {
		return lessons_id;
	}

	public void setLessons_id(int lessons_id) {
		this.lessons_id = lessons_id;

	}

	public ArrayList<Lessons> getList() throws SQLException {
		ArrayList<Lessons> list = new ArrayList<>();
		Lessons obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM lessons");
			while (rs.next()) {
				obj = new Lessons();
				obj.setLessons_id(rs.getInt("lessons_id"));
				obj.setName(rs.getString("name"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;

	}
	public boolean addLesson(String name) throws SQLException {
		String query = "INSERT INTO lessons " + "(name) VALUES"+ "(?)";
		
		boolean key = false;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
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
	public boolean deleteLesson(int id) throws SQLException {
		String query = "DELETE FROM user WHERE user_id = ?";
		
		boolean key = false;
		Connection con = conn.connDb();

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
	public boolean updateLesson(int id,String name) throws SQLException {
		String query = "UPDATE user SET name = ?,tc_no=?,password=? WHERE user_id = ?";
		
		boolean key = false;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, name);
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Lessons(int lessons_id, String name) {
		super();
		this.lessons_id = lessons_id;
		this.name = name;
	}

}
