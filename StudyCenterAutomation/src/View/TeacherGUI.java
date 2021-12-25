package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Teacher;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;

import Helper.Helper;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.TableModel;

public class TeacherGUI extends JFrame {

	static Teacher teacher = new Teacher();
	
	private JPanel contentPane;
	
	private JTable w_studentTable;
	private JTable table_student;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	
	private JTable table_etut;
	private DefaultTableModel etutModel;
	private Object[] etutData = null;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherGUI frame = new TeacherGUI(teacher);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public TeacherGUI(Teacher teacher) throws SQLException{
		studentModel = new DefaultTableModel();
		Object[] colStudentName = new Object[4];
		colStudentName[0] = "ID";
		colStudentName[1] = "Ad Soyad";
		colStudentName[2] = "TC NO";
		colStudentName[3] = "Notlari";
		
		

		studentModel.setColumnIdentifiers(colStudentName);
		studentData = new Object[3];
		for(int i = 0; i < teacher.getStudentList().size(); i++) {
			studentData[0] = teacher.getStudentList().get(i).getUser_id();
			studentData[1] = teacher.getStudentList().get(i).getName();
			studentData[2] = teacher.getStudentList().get(i).getTc_no();

			studentModel.addRow(studentData);
		}
		
		etutModel = new DefaultTableModel();
		Object[] colEtutDate = new Object[1];
		colEtutDate[0] = "Tarih";
		etutModel.setColumnIdentifiers(colEtutDate);
		etutData = new Object[1];
		for(int i = 0; i<teacher.getEtutList().size();i++) {
			etutData[0] = teacher.getEtutList().get(i).getWdate();
			etutModel.addRow(etutData);
					
		}
		
		
		setTitle("Teacher");
		setResizable(false);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayin " + teacher.getName()+" Ögretmen");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 10, 300, 40);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Çıkış Yap");
		btnNewButton.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnNewButton.setBounds(750, 25, 120, 30);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 60, 870, 450);
		contentPane.add(tabbedPane);
		
		JPanel w_studentpane = new JPanel();
		w_studentpane.setBackground(Color.WHITE);
		tabbedPane.addTab("Ögrenciler", null, w_studentpane, null);
		w_studentpane.setLayout(null);
		
		JScrollPane w_scrollStudent = new JScrollPane();
		w_scrollStudent.setBounds(0, 0, 700, 410);
		w_studentpane.add(w_scrollStudent);
		
		table_student = new JTable(studentModel);
		table_student.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		table_student.setBackground(Color.WHITE);
		w_scrollStudent.setViewportView(table_student);
		
		JPanel w_etutpane = new JPanel();
		w_etutpane.setLayout(null);
		w_etutpane.setBackground(Color.WHITE);
		tabbedPane.addTab("Etüt", null, w_etutpane, null);
		
		JDateChooser select_date = new JDateChooser();
		select_date.getCalendarButton().setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		select_date.setBounds(10, 10, 130, 30);
		w_etutpane.add(select_date);
		
		JComboBox select_time = new JComboBox();
		select_time.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		select_time.setModel(new DefaultComboBoxModel(new String[] {"10:00", "13:00", "15:00", "17:00", "19:00"}));
		select_time.setBounds(161, 10, 80, 30);
		w_etutpane.add(select_time);
		
		JButton btn_etutekle = new JButton("Ekle");
		btn_etutekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String date = "";
				try {
					date = sdf.format(select_date.getDate());	
				} catch (Exception e2) {
					
				}
				if(date.length() == 0) {
					Helper.showMsg("Lütfen Geçerli Bir Tarih Giriniz !");
				}else {
					String time = " " + select_time.getSelectedItem().toString() + ":00";
					String selectDate = date + time ;	
					try {				
						boolean control = teacher.addWhour(selectDate);
						if(control) {
							Helper.showMsg("success");
							updateEtutModel();
						}else {
							Helper.showMsg("error");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}					
			}
		});
		btn_etutekle.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btn_etutekle.setBounds(412, 10, 120, 30);
		w_etutpane.add(btn_etutekle);
		
		JScrollPane w_scrollPane = new JScrollPane();
		w_scrollPane.setBounds(20, 50, 835, 363);
		w_etutpane.add(w_scrollPane);
		
		table_etut = new JTable(etutModel);
		table_etut.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		w_scrollPane.setViewportView(table_etut);
		
	}
	public void updateEtutModel() throws SQLException{
		DefaultTableModel clearModel = (DefaultTableModel) table_etut.getModel();
		clearModel.setRowCount(0);
		for(int i=0; i< teacher.getEtutList().size();i++) {
			etutData[0] = teacher.getEtutList().get(i).getWdate();
			etutModel.addRow(etutData);	
			}}
		
}
