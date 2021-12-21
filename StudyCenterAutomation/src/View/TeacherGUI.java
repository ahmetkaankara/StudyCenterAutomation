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

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TeacherGUI extends JFrame {

	static Teacher teacher = new Teacher();
	private JPanel contentPane;
	private JTable w_studentTable;
	private JTable table_student;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	
	
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
		btnNewButton.setBounds(754, 23, 120, 30);
		contentPane.add(btnNewButton);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 60, 866, 450);
		contentPane.add(tabbedPane);
		
		JPanel w_studentpane = new JPanel();
		w_studentpane.setBackground(Color.WHITE);
		tabbedPane.addTab("Ögrenciler", null, w_studentpane, null);
		w_studentpane.setLayout(null);
		
		JScrollPane w_scrollStudent = new JScrollPane();
		w_scrollStudent.setBounds(0, 0, 695, 413);
		w_studentpane.add(w_scrollStudent);
		
		table_student = new JTable(studentModel);
		table_student.setBackground(Color.WHITE);
		w_scrollStudent.setViewportView(table_student);
		
		JPanel w_etutpane = new JPanel();
		w_etutpane.setBackground(Color.WHITE);
		tabbedPane.addTab("Etütler", null, w_etutpane, null);
		w_etutpane.setLayout(null);
		
		JScrollPane w_scrollEtut = new JScrollPane();
		w_scrollEtut.setBounds(10, 10, 736, 423);
		w_etutpane.add(w_scrollEtut);
		
		
		
		JButton w_etutEkle = new JButton("Etüt Ekle");
		w_etutEkle.setBounds(756, 10, 95, 39);
		w_etutpane.add(w_etutEkle);
	}

}
