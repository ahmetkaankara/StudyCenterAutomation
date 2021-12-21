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

import Model.Admin;
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

public class AdminGUI extends JFrame {
	
	static Admin admin = new Admin();
	private JPanel w_pane;
	private JTextField fld_tName;
	private JTextField fld_tTcno;
	private JTextField fld_tPass;
	private JTextField fld_teacherID;
	private JTable table_teacher;
	private DefaultTableModel teacherModel = null;
	private Object[] teacherData = null;

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
		w_scrollTeacher.setBounds(6, 6, 692, 379);
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
	
}
