package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Helper.Item;
import Model.Appointment;
import Model.Etut;
import Model.Lessons;
import Model.Student;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class StudentGUİ extends JFrame {
	static Student student = new Student();
	private JPanel w_pane;
	private JTable table_appoint;
	private JTextField w_teacherlist;
	private JTable table_teacher;
	private DefaultTableModel teacherModel;
	private Object[] teacherData =null;
	private JTextField txtAlnabilecekSaatler;
	private JTable table_whour;
	private Etut etut = new Etut();
	private Lessons lessons = new Lessons();
	private DefaultTableModel etutModel;
	private Object[] etutData =null;
	private int selectTeacherID = 0;
	private String selectTeacherName = null;
	private DefaultTableModel appointModel;
	private Object[] appointData =null;
	private Appointment appoint = new Appointment();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentGUİ frame = new StudentGUİ(student);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public StudentGUİ(Student student) throws SQLException {
		teacherModel = new DefaultTableModel();
		Object[] colTeacher = new Object[2];
		colTeacher[0] = "ID";
		colTeacher[1] = "Ad Soyad";
		teacherModel.setColumnIdentifiers(colTeacher);
		teacherData = new Object[2];
		
		
		etutModel = new DefaultTableModel();
		Object[] coletut = new Object[2];
		coletut[0] = "ID";
		coletut[1] = "Tarih";
		etutModel.setColumnIdentifiers(coletut);
		etutData = new Object[2];
		
		
		appointModel = new DefaultTableModel();
		Object[] colAppoint = new Object[3];
		colAppoint[0] = "ID";
		colAppoint[1] = "Öğretmen";
		colAppoint[2] = "Tarih";
		appointModel.setColumnIdentifiers(colAppoint);
		appointData = new Object[3];
		for(int i = 0;i<appoint.getStudentList(student.getUser_id()).size();i++) {
			appointData[0] = appoint.getStudentList(student.getUser_id()).get(i).getId();
			appointData[1] = appoint.getStudentList(student.getUser_id()).get(i).getTeacherName();
			appointData[2] = appoint.getStudentList(student.getUser_id()).get(i).getAppDate();
			appointModel.addRow(appointData);

		}
	
	
		
		
		setTitle("ÖĞRENCİ GİRİŞİ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		w_pane = new JPanel();
		w_pane.setBackground(Color.WHITE);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOŞGELDİNİZ "+student.getName());
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		lblNewLabel.setBounds(303, 13, 280, 44);
		w_pane.add(lblNewLabel);
		
		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(10, 67, 876, 436);
		w_pane.add(w_tabpane);
		
		JPanel w_etutviewpane = new JPanel();
		w_etutviewpane.setBackground(Color.WHITE);
		w_tabpane.addTab("Etütlerim", null, w_etutviewpane, null);
		w_etutviewpane.setLayout(null);
		
		JScrollPane w_scrollAppoint = new JScrollPane();
		w_scrollAppoint.setBounds(10, 10, 851, 389);
		w_etutviewpane.add(w_scrollAppoint);
		
		table_appoint = new JTable(appointModel);
		w_scrollAppoint.setViewportView(table_appoint);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabpane.addTab("Etüt Alma Sistemi", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane w_scrollteacher = new JScrollPane();
		w_scrollteacher.setBounds(10, 50, 280, 333);
		panel.add(w_scrollteacher);
		
		table_teacher = new JTable(teacherModel);
		w_scrollteacher.setViewportView(table_teacher);
		
		w_teacherlist = new JTextField();
		w_teacherlist.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_teacherlist.setText("Öğretmen Listesi");
		w_teacherlist.setBounds(10, 10, 135, 28);
		panel.add(w_teacherlist);
		w_teacherlist.setColumns(10);
		
		JTextPane w_lessonlist = new JTextPane();
		w_lessonlist.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		w_lessonlist.setText("Ders Listesi");
		w_lessonlist.setBounds(340, 10, 94, 28);
		panel.add(w_lessonlist);
		
		JComboBox select_lesson = new JComboBox();
		select_lesson.setBounds(340, 50, 150, 35);
		select_lesson.addItem("---Ders Seçiniz---");
		for(int i =0;i<lessons.getList().size();i++) {
			select_lesson.addItem(new Item(lessons.getList().get(i).getLessons_id(), lessons.getList().get(i).getName()));
		}
		
		panel.add(select_lesson);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(528, 50, 280, 333);
		panel.add(w_scrollWhour);
		
		table_whour = new JTable(etutModel);
		w_scrollWhour.setViewportView(table_whour);
		
		txtAlnabilecekSaatler = new JTextField();
		txtAlnabilecekSaatler.setText("Alınabilecek Saatler");
		txtAlnabilecekSaatler.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		txtAlnabilecekSaatler.setColumns(10);
		txtAlnabilecekSaatler.setBounds(528, 10, 150, 28);
		panel.add(txtAlnabilecekSaatler);
		
		JTextPane w_lessonlist_1 = new JTextPane();
		w_lessonlist_1.setText("Öğretmen Listesi");
		w_lessonlist_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		w_lessonlist_1.setBounds(340, 129, 122, 28);
		panel.add(w_lessonlist_1);
		
		JButton btn_selectteacher = new JButton("SEÇ");
		btn_selectteacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_teacher.getSelectedRow();
				if(row >=0) {
					String value = table_teacher.getModel().getValueAt(row, 0).toString();
					int id = Integer.parseInt(value);
					
					DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
					clearModel.setRowCount(0);
					try {
						for(int i = 0; i<etut.getEtutList(id).size();i++) {
							
							etutData[0] = etut.getEtutList(id).get(i).getId();
							etutData[1] = etut.getEtutList(id).get(i).getWdate();
							etutModel.addRow(etutData);
						}
				


						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_whour.setModel(etutModel);
					selectTeacherID = id;
					selectTeacherName = table_teacher.getModel().getValueAt(row, 1).toString();
				}else {
					Helper.showMsg("Lütfen Öğretmen Seçiniz!");
				}
			}
		});
		btn_selectteacher.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_selectteacher.setBounds(349, 167, 141, 35);
		panel.add(btn_selectteacher);
		
		JTextPane w_lessonlist_1_1 = new JTextPane();
		w_lessonlist_1_1.setText("Etüt Al");
		w_lessonlist_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		w_lessonlist_1_1.setBounds(340, 250, 122, 28);
		panel.add(w_lessonlist_1_1);
		
		JButton btn_addAppoint = new JButton("SEÇ");
		btn_addAppoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selRow = table_whour.getSelectedRow();
				if(selRow>=0) {
					String date = table_whour.getModel().getValueAt(selRow, 1).toString();
					try {
					
						boolean control = student.addAppointment(selectTeacherID, student.getUser_id(), selectTeacherName, student.getName(), date);
						if(control) {
							Helper.showMsg("success");
							student.updateWhourStatus(selectTeacherID, date);
							updateWhourModel(selectTeacherID);
							updateAppointModel(student.getUser_id());
							
						
						}else {
							Helper.showMsg("error");
							
						}
					
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen Geçerli Bir Tarih Giriniz");
				}
			}
		});
		btn_addAppoint.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_addAppoint.setBounds(349, 288, 141, 35);
		panel.add(btn_addAppoint);
		select_lesson.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(select_lesson.getSelectedIndex() != 0) {
						JComboBox c = (JComboBox) e.getSource();
						Item item = (Item) c.getSelectedItem();

						DefaultTableModel clearModel = (DefaultTableModel) table_teacher.getModel();
						clearModel.setRowCount(0);
						try {
							for(int i = 0;i<lessons.getTeacherList(item.getKey()).size();i++) {
								teacherData[0]=lessons.getTeacherList(item.getKey()).get(i).getUser_id();
								teacherData[1]=lessons.getTeacherList(item.getKey()).get(i).getName();
								teacherModel.addRow(teacherData);
							}
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}else {

						DefaultTableModel clearModel = (DefaultTableModel) table_teacher.getModel();
						clearModel.setRowCount(0);
					}
			}
				});
		
		
		JButton w_exit = new JButton("Çıkış Yap");
		w_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		
		w_exit.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 22));
		w_exit.setBounds(743, 10, 133, 51);
		w_pane.add(w_exit);
	}
	
	public void updateWhourModel(int teacher_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_whour.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i<etut.getEtutList(teacher_id).size();i++) {
			
			etutData[0] = etut.getEtutList(teacher_id).get(i).getId();
			etutData[1] = etut.getEtutList(teacher_id).get(i).getWdate();
			etutModel.addRow(etutData);
		}
		
	}
	public void updateAppointModel(int student_id) throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_appoint.getModel();
		clearModel.setRowCount(0);
		for(int i = 0;i<appoint.getStudentList(student_id).size();i++) {
			appointData[0] = appoint.getStudentList(student_id).get(i).getId();
			appointData[1] = appoint.getStudentList(student_id).get(i).getTeacherName();
			appointData[2] = appoint.getStudentList(student_id).get(i).getAppDate();
			appointModel.addRow(appointData);

		}
		
	}
}
