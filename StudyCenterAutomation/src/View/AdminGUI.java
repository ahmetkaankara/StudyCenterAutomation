package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Model.*;
import Model.Teacher;
import Model.Student;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Helper.*;
import javax.swing.JComboBox;

public class AdminGUI extends JFrame {
	
	static Admin admin = new Admin();
	Lessons lesson = new Lessons();
	private JPanel w_pane;
	private JTextField fld_tName;
	private JTextField fld_tTcno;
	private JTextField fld_tPass;
	private JTextField fld_teacherID;
	private JTable table_teacher;
	private DefaultTableModel teacherModel = null;
	private Object[] teacherData = null;
	private JTable table_student;
	private JTextField fld_sName;
	private JTextField fld_sTcno;
	private JTextField fld_sPass;
	private JTextField fld_studentID;
	private DefaultTableModel studentModel = null;
	private Object[] studentData = null;
	private JTable table_lessons;
	private JTextField fld_lesson;
	private DefaultTableModel lessonModel = null;
	private Object[] lessonData = null;
	private JTable table_TeacherLesson;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI(admin);
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
	public AdminGUI(Admin admin) throws SQLException {
		
		teacherModel = new DefaultTableModel();
		Object[] colTeacherName = new Object[4];
		colTeacherName[0] = "ID";
		colTeacherName[1] = "Ad Soyad";
		colTeacherName[2] = "TC NO";
		colTeacherName[3] = "Şifre";
		
		teacherModel.setColumnIdentifiers(colTeacherName);
		teacherData = new Object[4];
		
		for(int i = 0; i < admin.getTeacherList().size(); i++) {
			teacherData[0] = admin.getTeacherList().get(i).getUser_id();
			teacherData[1] = admin.getTeacherList().get(i).getName();
			teacherData[2] = admin.getTeacherList().get(i).getTc_no();
			teacherData[3] = admin.getTeacherList().get(i).getPassword();
			
			teacherModel.addRow(teacherData);
		}
		
		
		studentModel = new DefaultTableModel();
		Object[] colStudentName = new Object[4];
		colStudentName[0] = "ID";
		colStudentName[1] = "Ad Soyad";
		colStudentName[2] = "TC NO";
		colStudentName[3] = "Şifre";
		
		studentModel.setColumnIdentifiers(colStudentName);
		studentData = new Object[4];
		
		for(int i = 0; i < admin.getStudentList().size(); i++) {
			studentData[0] = admin.getStudentList().get(i).getUser_id();
			studentData[1] = admin.getStudentList().get(i).getName();
			studentData[2] = admin.getStudentList().get(i).getTc_no();
			studentData[3] = admin.getStudentList().get(i).getPassword();
			
			studentModel.addRow(studentData);
		}
		//LessonsModel
				lessonModel = new DefaultTableModel();
				Object[] colLessons = new Object[2];
				colLessons[0] = "ID";
				colLessons[1]= "Ders adı";
				lessonModel.setColumnIdentifiers(colLessons);
				lessonData = new Object[2];
				for(int i=0; i< lesson.getList().size();i++) {
					lessonData[0] = lesson.getList().get(i).getLessons_id();
					lessonData[1]= lesson.getList().get(i).getName();
					lessonModel.addRow(lessonData);		
					}
				
				DefaultTableModel teacherLModel = new DefaultTableModel();
				Object[] colTeacher = new Object[2];
				colTeacher[0] = "ID";
				colTeacher[1] = "Ad Soyad";
				teacherLModel.setColumnIdentifiers(colTeacher);
				Object[] teacherData = new Object[2];
				
						

		
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 550);
		w_pane = new JPanel();
		w_pane.setBackground(Color.LIGHT_GRAY);
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(w_pane);
		w_pane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hoşgeldiniz Sayın: "+admin.getName());
		lblNewLabel.setBounds(6, 21, 308, 20);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		w_pane.add(lblNewLabel);
		
		JButton btn_logout = new JButton("Çıkış Yap");
		btn_logout.setBounds(777, 19, 117, 29);
		w_pane.add(btn_logout);
		
		JTabbedPane w_tab = new JTabbedPane(JTabbedPane.TOP);
		w_tab.setBounds(6, 79, 888, 437);
		w_pane.add(w_tab);
		
		JPanel w_teacher = new JPanel();
		w_tab.addTab("Öğretmen Yönetimi", null, w_teacher, null);
		w_teacher.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Ad Soyad");
		lblNewLabel_1.setBounds(707, 40, 115, 16);
		w_teacher.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("TC Numarası");
		lblNewLabel_1_1.setBounds(707, 104, 115, 16);
		w_teacher.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Şifre");
		lblNewLabel_1_2.setBounds(707, 168, 115, 16);
		w_teacher.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Kullanıcı ID");
		lblNewLabel_1_3.setBounds(707, 278, 115, 16);
		w_teacher.add(lblNewLabel_1_3);
		
		fld_tName = new JTextField();
		fld_tName.setBounds(707, 66, 130, 26);
		w_teacher.add(fld_tName);
		fld_tName.setColumns(10);
		
		fld_tTcno = new JTextField();
		fld_tTcno.setColumns(10);
		fld_tTcno.setBounds(707, 130, 130, 26);
		w_teacher.add(fld_tTcno);
		
		fld_tPass = new JTextField();
		fld_tPass.setColumns(10);
		fld_tPass.setBounds(707, 196, 130, 26);
		w_teacher.add(fld_tPass);
		
		JButton btn_tCreate = new JButton("Kayıt Et");
		btn_tCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_tName.getText().length()==0 || fld_tTcno.getText().length()==0||fld_tPass.getText().length() == 0 ) {
					Helper.showMsg("fill");
					
				}
				else {
					try {
						boolean control = admin.addTeacher(fld_tName.getText(), fld_tTcno.getText(), fld_tPass.getText());
						if(control) {
							Helper.showMsg("success");
							fld_tName.setText(null);
							fld_tTcno.setText(null);
							fld_tPass.setText(null);
							updateTeacherModel();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_tCreate.setBounds(707, 231, 117, 29);
		w_teacher.add(btn_tCreate);
		
		fld_teacherID = new JTextField();
		fld_teacherID.setBounds(707, 311, 130, 26);
		w_teacher.add(fld_teacherID);
		fld_teacherID.setColumns(10);
		
		JButton btn_tDelete = new JButton("Sil");
		btn_tDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_teacherID.getText().length()==0) {
					Helper.showMsg("Lütfen Bir Öğretmen Seçiniz");
				}
				else {
					
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_teacherID.getText());
						boolean control;
						try {
							control = admin.deleteTeacher(selectID);
							if(control) {
								Helper.showMsg("success");
								fld_teacherID.setText(null);
								updateTeacherModel();
								
								
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}


				}
				
			}
		});
		btn_tDelete.setBounds(707, 356, 117, 29);
		w_teacher.add(btn_tDelete);
		
		JScrollPane w_scrollTeacher = new JScrollPane();
		w_scrollTeacher.setBounds(10, 10, 692, 379);
		w_teacher.add(w_scrollTeacher);
		
		table_teacher = new JTable(teacherModel);
		w_scrollTeacher.setViewportView(table_teacher);
		table_teacher.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
				fld_teacherID.setText(table_teacher.getValueAt(table_teacher.getSelectedRow(), 0).toString());
				}
				catch(Exception ex) {
					
				}
			}
			
		});
		table_teacher.getModel().addTableModelListener(new TableModelListener() {

			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()== TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_teacher.getValueAt(table_teacher.getSelectedRow(), 0).toString());
					String selectName = table_teacher.getValueAt(table_teacher.getSelectedRow(), 1).toString();
					String selectTcno = table_teacher.getValueAt(table_teacher.getSelectedRow(), 2).toString();
					String selectPass = table_teacher.getValueAt(table_teacher.getSelectedRow(), 3).toString();
					
					try {
						boolean control = admin.updateTeacher(selectID, selectName, selectTcno, selectPass);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				
			}
			
		});
		
		JPanel w_student = new JPanel();
		w_tab.addTab("Öğrenci Yönetimi", null, w_student, null);
		w_student.setLayout(null);
		
		JScrollPane w_scrollStudent = new JScrollPane();
		w_scrollStudent.setBounds(6, 6, 705, 379);
		w_student.add(w_scrollStudent);
		
		table_student = new JTable(studentModel);
		w_scrollStudent.setViewportView(table_student);
		table_student.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
				fld_studentID.setText(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());
				}
				catch(Exception ex) {
					
				}
			}
			
		});
		table_student.getModel().addTableModelListener(new TableModelListener() {

			
			@Override
			public void tableChanged(TableModelEvent e) {
				if(e.getType()== TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table_student.getValueAt(table_student.getSelectedRow(), 0).toString());
					String selectName = table_student.getValueAt(table_student.getSelectedRow(), 1).toString();
					String selectTcno = table_student.getValueAt(table_student.getSelectedRow(), 2).toString();
					String selectPass = table_student.getValueAt(table_student.getSelectedRow(), 3).toString();
					
					try {
						boolean control = admin.updateStudent(selectID, selectName, selectTcno, selectPass);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
			
		});
		
		JLabel lblNewLabel_2 = new JLabel("Ad Soyad");
		lblNewLabel_2.setBounds(723, 22, 61, 16);
		w_student.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("TC Numarası");
		lblNewLabel_2_1.setBounds(723, 84, 105, 16);
		w_student.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Şifre");
		lblNewLabel_2_2.setBounds(723, 139, 61, 16);
		w_student.add(lblNewLabel_2_2);
		
		JButton btn_sCreate = new JButton("Kayıt Et");
		btn_sCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_sName.getText().length()==0||fld_sTcno.getText().length()==0||fld_sPass.getText().length() == 0) {
					Helper.showMsg("fill");
					
				}
				else {
					try {
						boolean control = admin.addStudent(fld_sName.getText(), fld_sTcno.getText(), fld_sPass.getText());
						if(control) {
							Helper.showMsg("success");
							fld_tName.setText(null);
							fld_tTcno.setText(null);
							fld_tPass.setText(null);								
							updateStudentModel();
						}
					} catch (SQLException e1) {
							e1.printStackTrace();
					}
				}
					
			}
		});
		btn_sCreate.setBounds(723, 219, 117, 29);
		w_student.add(btn_sCreate);
		
		JButton btn_sDelete = new JButton("Sil");
		btn_sDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_studentID.getText().length()==0) {
					Helper.showMsg("Lütfen Bir Öğrenci Seçiniz");
				}
				else {
					
					if(Helper.confirm("sure")) {
						int selectID = Integer.parseInt(fld_studentID.getText());
						boolean control;
						try {
							control = admin.deleteStudent(selectID);
							if(control) {
								Helper.showMsg("success");
								fld_studentID.setText(null);
								updateStudentModel();
								
								
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}


				}
			}
		});
		btn_sDelete.setBounds(723, 332, 117, 29);
		w_student.add(btn_sDelete);
		
		fld_sName = new JTextField();
		fld_sName.setBounds(723, 46, 130, 26);
		w_student.add(fld_sName);
		fld_sName.setColumns(10);
		
		fld_sTcno = new JTextField();
		fld_sTcno.setColumns(10);
		fld_sTcno.setBounds(723, 101, 130, 26);
		w_student.add(fld_sTcno);
		
		fld_sPass = new JTextField();
		fld_sPass.setColumns(10);
		fld_sPass.setBounds(723, 159, 130, 26);
		w_student.add(fld_sPass);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Kullanıcı ID");
		lblNewLabel_2_2_1.setBounds(723, 273, 72, 16);
		w_student.add(lblNewLabel_2_2_1);
		
		fld_studentID = new JTextField();
		fld_studentID.setColumns(10);
		fld_studentID.setBounds(723, 294, 130, 26);
		w_student.add(fld_studentID);
		
		JPanel w_lessons = new JPanel();
		w_lessons.setBackground(Color.WHITE);
		w_tab.addTab("Öğretmen Ders Ekleme", null, w_lessons, null);
		w_lessons.setLayout(null);
		
		JScrollPane w_scrollLessons = new JScrollPane();
		w_scrollLessons.setBounds(10, 11, 350, 387);
		w_lessons.add(w_scrollLessons);
		table_lessons = new JTable(lessonModel);
		w_scrollLessons.setViewportView(table_lessons);
		
		JLabel lblNewLabel_1_4 = new JLabel("Dersler");
		lblNewLabel_1_4.setBounds(382, 11, 115, 16);
		w_lessons.add(lblNewLabel_1_4);
		
		fld_lesson = new JTextField();
		fld_lesson.setBounds(382, 37, 130, 26);
		fld_lesson.setColumns(10);
		w_lessons.add(fld_lesson);
		
		JButton btn_addLessons = new JButton("Kayıt Et");
		btn_addLessons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(fld_lesson.getText().length()==0) {
					Helper.showMsg("fill");
				}
				else {
					try {
						boolean control= lesson.addLesson(fld_lesson.getText());
						if(control) {
							Helper.showMsg("success");
							fld_lesson.setText(null);
							updateLessonModel();
						}
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btn_addLessons.setBounds(382, 74, 117, 29);
		w_lessons.add(btn_addLessons);
		

		JScrollPane w_scrollTeacherLesson = new JScrollPane();
		w_scrollTeacherLesson.setBounds(522, 11, 351, 387);
		w_lessons.add(w_scrollTeacherLesson);
		
		table_TeacherLesson = new JTable(teacherLModel);
		w_scrollTeacherLesson.setViewportView(table_TeacherLesson);
		
		JComboBox select_teacher = new JComboBox();
		select_teacher.setBounds(380, 303, 117, 29);
		for(int i=0 ; i < admin.getTeacherList().size(); i++) {
			select_teacher.addItem(new Item(admin.getTeacherList().get(i).getUser_id(),admin.getTeacherList().get(i).getName()));
		}
		select_teacher.addActionListener(e -> {
			JComboBox c = (JComboBox) e.getSource();
			Item item = (Item) c.getSelectedItem();
			System.out.println(item.getKey() + " " + item.getValue());
		});
		
		w_lessons.add(select_teacher);
		
		JButton btn_addTeacher = new JButton("Ekle");
		btn_addTeacher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int selRow = table_lessons.getSelectedRow();
				if (selRow >= 0) {
					String selLesson = table_lessons.getModel().getValueAt(selRow, 0).toString();
					int selLessonID = Integer.parseInt(selLesson);
					Item teacherItem = (Item) select_teacher.getSelectedItem();
					try {
						boolean control = admin.addLessonTo(teacherItem.getKey(), selLessonID);
						if(control) {
							Helper.showMsg("başarılı");
						}
						else {
							Helper.showMsg("error");
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					Helper.showMsg("Lütfen bir ders seçin.");
				}
				
			}
			
		});
		btn_addTeacher.setBounds(380, 347, 117, 29);
		w_lessons.add(btn_addTeacher);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Dersler");
		lblNewLabel_1_4_1.setBounds(382, 165, 115, 16);
		w_lessons.add(lblNewLabel_1_4_1);
		
		JButton btn_selectLesson = new JButton("Seç");
		btn_selectLesson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selRow =  table_lessons.getSelectedRow();
				if (selRow>=0) {
					String selLesson = table_lessons.getModel().getValueAt(selRow, 0).toString();
					int selLessonID = Integer.parseInt(selLesson);
					DefaultTableModel clearModel = (DefaultTableModel) table_TeacherLesson.getModel();
					clearModel.setRowCount(0);
					
					try {
						for (int i=0; i < admin.getLessonsTeacherList(selLessonID).size();i++) {
							teacherData[0] = admin.getLessonsTeacherList(selLessonID).get(i).getUser_id();
							teacherData[1] = admin.getLessonsTeacherList(selLessonID).get(i).getName();
							teacherLModel.addRow(teacherData);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_TeacherLesson.setModel(teacherLModel);
				}else {
					Helper.showMsg("Bir ders seçin.");
				}
				
			}
		});
		btn_selectLesson.setBounds(380, 189, 117, 29);
		w_lessons.add(btn_selectLesson);
		
		JPanel w_studentExam = new JPanel();
		w_tab.addTab("Öğrenci Deneme Sınavı Yönetimi", null, w_studentExam, null);
	}
	
	public void updateTeacherModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_teacher.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i < admin.getTeacherList().size(); i++) {
			teacherData[0] = admin.getTeacherList().get(i).getUser_id();
			teacherData[1] = admin.getTeacherList().get(i).getName();
			teacherData[2] = admin.getTeacherList().get(i).getTc_no();
			teacherData[3] = admin.getTeacherList().get(i).getPassword();
			
			teacherModel.addRow(teacherData);
		}
		
	}
	public void updateStudentModel() throws SQLException {
		DefaultTableModel clearModel = (DefaultTableModel) table_student.getModel();
		clearModel.setRowCount(0);
		for(int i = 0; i < admin.getStudentList().size(); i++) {
			studentData[0] = admin.getStudentList().get(i).getUser_id();
			studentData[1] = admin.getStudentList().get(i).getName();
			studentData[2] = admin.getStudentList().get(i).getTc_no();
			studentData[3] = admin.getStudentList().get(i).getPassword();
			
			studentModel.addRow(studentData);
		}
		
	}
	
	public void updateLessonModel() throws SQLException{
	DefaultTableModel clearModel = (DefaultTableModel) table_lessons.getModel();
	clearModel.setRowCount(0);
	for(int i=0; i< lesson.getList().size();i++) {
		lessonData[0] = lesson.getList().get(i).getLessons_id();
		lessonData[1]= lesson.getList().get(i).getName();
		lessonModel.addRow(lessonData);		
		}}
}
