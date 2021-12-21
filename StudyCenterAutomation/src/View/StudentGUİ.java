package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Model.Student;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

public class StudentGUİ extends JFrame {
	static Student student = new Student();
	private JPanel w_pane;
	private JTable table;
	private JTextField w_teacherlist;
	private JTable table_teacher;
	private DefaultTableModel teacherModel;
	private Object[] teacherData =null;
	private JTextField textField;
	private JTable table_whour;
	

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
	 */
	public StudentGUİ(Student student) {
		teacherModel = new DefaultTableModel();
		Object[] colTeacher = new Object[2];
		colTeacher[0] = "ID";
		colTeacher[1] = "Ad Soyad";
		
		teacherModel.setColumnIdentifiers(colTeacher);
		teacherData = new Object[2];
		
	
		
		
		setTitle("ÖĞRETMEN GİRİŞİ");
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
		
		JPanel w_deneme = new JPanel();
		w_deneme.setBackground(Color.WHITE);
		w_tabpane.addTab("Deneme Sonuçları", null, w_deneme, null);
		w_deneme.setLayout(null);
		
		JPanel w_etutviewpane = new JPanel();
		w_etutviewpane.setBackground(Color.WHITE);
		w_tabpane.addTab("Etütlerim", null, w_etutviewpane, null);
		w_etutviewpane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 851, 389);
		w_etutviewpane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		w_tabpane.addTab("Etüt Alma Sistemi", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 50, 280, 333);
		panel.add(scrollPane_1);
		
		table_teacher = new JTable();
		scrollPane_1.setViewportView(table_teacher);
		
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
		panel.add(select_lesson);
		
		JScrollPane w_scrollWhour = new JScrollPane();
		w_scrollWhour.setBounds(528, 50, 280, 333);
		panel.add(w_scrollWhour);
		
		table_whour = new JTable();
		w_scrollWhour.setViewportView(table_whour);
		
		textField = new JTextField();
		textField.setText("Öğretmen Listesi");
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(528, 10, 135, 28);
		panel.add(textField);
		
		JTextPane w_lessonlist_1 = new JTextPane();
		w_lessonlist_1.setText("Öğretmen Listesi");
		w_lessonlist_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		w_lessonlist_1.setBounds(340, 129, 122, 28);
		panel.add(w_lessonlist_1);
		
		JButton btn_selectteacher = new JButton("SEÇ");
		btn_selectteacher.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		btn_selectteacher.setBounds(349, 167, 141, 35);
		panel.add(btn_selectteacher);
		select_lesson.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if(select_lesson.getSelectedIndex() != 0) {
						JComboBox c = (JComboBox) e.getSource();
						
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
}
