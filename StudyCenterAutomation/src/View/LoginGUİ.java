package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import Helper.*;
import Model.*;


public class LoginGUİ extends JFrame {

	private JPanel w_pane;
	private JPasswordField fld_adminPass;
	private JPasswordField fld_teacherPass;
	private JPasswordField fld_studentPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUİ frame = new LoginGUİ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUİ() {
		setTitle("Dershane Otomasyon");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 401);
		w_pane = new JPanel();
		w_pane.setBackground(Color.GRAY);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dershane Otomasyon Sistemine Hoşgeldiniz !");
		lblNewLabel.setBounds(106, 48, 287, 36);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(23, 164, 453, 188);
		w_pane.add(w_tabpane);
		
		JPanel w_studentLogin = new JPanel();
		w_studentLogin.setBackground(Color.LIGHT_GRAY);
		w_tabpane.addTab("Öğrenci Girişi", null, w_studentLogin, null);
		w_studentLogin.setLayout(null);
		
		JLabel lblTcNumaranzGiriniz = new JLabel("TC Numaranızı Giriniz:");
		lblTcNumaranzGiriniz.setVerticalAlignment(SwingConstants.TOP);
		lblTcNumaranzGiriniz.setBounds(26, 37, 150, 16);
		w_studentLogin.add(lblTcNumaranzGiriniz);
		
		JLabel lblifreniziGiriniz = new JLabel("Şifrenizi Giriniz:");
		lblifreniziGiriniz.setVerticalAlignment(SwingConstants.TOP);
		lblifreniziGiriniz.setBounds(26, 69, 150, 16);
		w_studentLogin.add(lblifreniziGiriniz);
		
		JTextPane fld_ogrenciTc = new JTextPane();
		fld_ogrenciTc.setBounds(188, 37, 155, 16);
		w_studentLogin.add(fld_ogrenciTc);
		

		JButton btn_studentLogin = new JButton("Giriş Yap");
		btn_studentLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_ogrenciTc.getText().length() == 0 || fld_studentPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_ogrenciTc.getText().equals(rs.getString("tc_no"))&&fld_studentPass.getText().equals(rs.getString("password"))&&rs.getString("type").equals("student")) {
								Student student = new Student();
								student.setUser_id(rs.getInt("user_id"));
								student.setName(rs.getString("name"));
								student.setTc_no(rs.getString("tc_no"));
								student.setPassword(rs.getString("password"));
								student.setType(rs.getString("type"));
								StudentGUİ sGUİ = new StudentGUİ(student);
								sGUİ.setVisible(true);
								dispose();
							
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btn_studentLogin.setBounds(146, 97, 140, 29);
		w_studentLogin.add(btn_studentLogin);
		
		fld_studentPass = new JPasswordField();
		fld_studentPass.setBounds(188, 64, 155, 21);
		w_studentLogin.add(fld_studentPass);
		
		JPanel w_teacherLogin = new JPanel();
		w_teacherLogin.setBackground(Color.LIGHT_GRAY);
		w_tabpane.addTab("Öğretmen Girişi", null, w_teacherLogin, null);
		w_teacherLogin.setLayout(null);
		
		JLabel lblTcNumaranzGiriniz_1 = new JLabel("TC Numaranızı Giriniz:");
		lblTcNumaranzGiriniz_1.setVerticalAlignment(SwingConstants.TOP);
		lblTcNumaranzGiriniz_1.setBounds(34, 23, 150, 16);
		w_teacherLogin.add(lblTcNumaranzGiriniz_1);
		
		JLabel lblifreniziGiriniz_1 = new JLabel("Şifrenizi Giriniz:");
		lblifreniziGiriniz_1.setVerticalAlignment(SwingConstants.TOP);
		lblifreniziGiriniz_1.setBounds(34, 55, 150, 16);
		w_teacherLogin.add(lblifreniziGiriniz_1);
		
		JTextPane fld_ogretmenTc = new JTextPane();
		fld_ogretmenTc.setBounds(196, 23, 155, 16);
		w_teacherLogin.add(fld_ogretmenTc);
		
		JButton btn_teacherLogin = new JButton("Giriş Yap");
		btn_teacherLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_ogretmenTc.getText().length() == 0 || fld_teacherPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_ogretmenTc.getText().equals(rs.getString("tc_no"))&&fld_teacherPass.getText().equals(rs.getString("password"))&&rs.getString("type").equals("teacher")) {
								Teacher teacher = new Teacher();
								teacher.setUser_id(rs.getInt("user_id"));
								teacher.setName(rs.getString("name"));
								teacher.setTc_no(rs.getString("tc_no"));
								teacher.setPassword(rs.getString("password"));
								teacher.setType(rs.getString("type"));
								TeacherGUI tGUI = new TeacherGUI(teacher);
								tGUI.setVisible(true);
								dispose();
							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_teacherLogin.setBounds(140, 93, 140, 29);
		w_teacherLogin.add(btn_teacherLogin);
		
		fld_teacherPass = new JPasswordField();
		fld_teacherPass.setBounds(196, 50, 155, 21);
		w_teacherLogin.add(fld_teacherPass);
		
		JPanel w_adminLogin = new JPanel();
		w_adminLogin.setBackground(Color.LIGHT_GRAY);
		w_tabpane.addTab("Yönetici Giriş", null, w_adminLogin, null);
		w_adminLogin.setLayout(null);
		
		JLabel lblTcNumaranzGiriniz_1_1 = new JLabel("TC Numaranızı Giriniz:");
		lblTcNumaranzGiriniz_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblTcNumaranzGiriniz_1_1.setBounds(43, 20, 150, 16);
		w_adminLogin.add(lblTcNumaranzGiriniz_1_1);
		
		JLabel lblifreniziGiriniz_1_1 = new JLabel("Şifrenizi Giriniz:");
		lblifreniziGiriniz_1_1.setVerticalAlignment(SwingConstants.TOP);
		lblifreniziGiriniz_1_1.setBounds(43, 52, 150, 16);
		w_adminLogin.add(lblifreniziGiriniz_1_1);
		
		JTextPane fld_adminTc = new JTextPane();
		fld_adminTc.setBounds(205, 20, 155, 16);
		w_adminLogin.add(fld_adminTc);
		
		JButton btn_adminLogin = new JButton("Giriş Yap");
		btn_adminLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_adminTc.getText().length() == 0 || fld_adminPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while(rs.next()) {
							if(fld_adminTc.getText().equals(rs.getString("tc_no"))&&fld_adminPass.getText().equals(rs.getString("password"))&&rs.getString("type").equals("admin")) {
								Admin admin = new Admin();
								admin.setUser_id(rs.getInt("user_id"));
								admin.setName(rs.getString("name"));
								admin.setTc_no(rs.getString("tc_no"));
								admin.setPassword(rs.getString("password"));
								admin.setType(rs.getString("type"));
								AdminGUI aGUI = new AdminGUI(admin);
								aGUI.setVisible(true);
								dispose();

							}
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_adminLogin.setBounds(149, 90, 140, 29);
		w_adminLogin.add(btn_adminLogin);
		
		fld_adminPass = new JPasswordField();
		fld_adminPass.setBounds(205, 52, 155, 16);
		w_adminLogin.add(fld_adminPass);
	}
}
