package Model;

import Helper.DBConnection;

public class Etut {
	private String wdate,status;
	DBConnection conn = new DBConnection();

	
	

	public Etut(String wdate, String status) {
		super();
		this.wdate = wdate;
		this.status = status;
	}
	public Etut() {}

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

}
