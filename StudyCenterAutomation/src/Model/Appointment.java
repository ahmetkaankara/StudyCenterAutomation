package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Helper.DBConnection;

public class Appointment {
	private int id,teacherID,studentID;
	private String teacherName,studentName,appDate;

	DBConnection conn = new DBConnection();
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement preparedStatement = null;
	
	
	public Appointment(int id, int teacherID, int studentID, String teacherName, String studentName, String appDate) {
		super();
		this.id = id;
		this.teacherID = teacherID;
		this.studentID = studentID;
		this.teacherName = teacherName;
		this.studentName = studentName;
		this.appDate = appDate;
	}

	public Appointment() {
		
	}

	public ArrayList<Appointment> getStudentList(int student_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE student_id = "+ student_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setTeacherID(rs.getInt("teacher_id"));
				obj.setTeacherName(rs.getString("teacher_name"));
				obj.setStudentID(rs.getInt("student_id"));
				obj.setStudentName(rs.getString("student_name"));
				obj.setAppDate(rs.getString("app_date"));
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
	
	public ArrayList<Appointment> getTeacherList(int teacher_id) throws SQLException {
		ArrayList<Appointment> list = new ArrayList<>();
		Appointment obj;
		Connection con = conn.connDb();

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM appointment WHERE teacher_id = "+ teacher_id);
			while (rs.next()) {
				obj = new Appointment();
				obj.setId(rs.getInt("id"));
				obj.setTeacherID(rs.getInt("teacher_id"));
				obj.setTeacherName(rs.getString("teacher_name"));
				obj.setStudentID(rs.getInt("student_id"));
				obj.setStudentName(rs.getString("student_name"));
				obj.setAppDate(rs.getString("app_date"));
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
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	
}

