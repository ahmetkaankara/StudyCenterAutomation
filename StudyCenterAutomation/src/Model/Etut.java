package Model;

import Helper.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class Etut {
	private int id,teacher_id;
	private String wdate,status,teacher_name;
	DBConnection conn = new DBConnection();

	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	public Etut(int id, int teacher_id,String teacher_name, String wdate, String status) {
		this.id = id;
		this.teacher_id = teacher_id;
		this.teacher_name = teacher_name;
		this.wdate = wdate;
		this.status = status;
	}
	public Etut() {}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTeacher_name() {
		return teacher_name;
	}
	public void setTeacher_name(String teacher_name) {
		this.teacher_name = teacher_name;
	}
	public ArrayList<Etut> getEtutList(int teacher_id) throws SQLException {
		ArrayList<Etut> list = new ArrayList<>();
		Etut obj;
		try {
			Connection con = conn.connDb();
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
	

	

}
