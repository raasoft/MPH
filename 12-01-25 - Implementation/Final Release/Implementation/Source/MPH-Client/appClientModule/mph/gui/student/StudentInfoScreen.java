package mph.gui.student;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mph.beans.dto.StudentDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.connection.StudentManager;

/**
 * This GUI class lets a student see information about the other students registered into the MPH system.<br/>
 * It extends {@link JFrame}.
 */
public class StudentInfoScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList listStudents;
	
	private ArrayList<StudentDTO> students = null;
	private JLabel lblStudentInfo;

	/**
	 * Create the frame.
	 * @param theStudentId the id of the student logged in
	 */
	public StudentInfoScreen(final StudentIdDTO theStudentId) {
		
		setTitle("MPH - Students");
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelStudents = new JPanel();
		panelStudents.setBorder(new TitledBorder(null, "Students List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelStudents);
		panelStudents.setLayout(new BoxLayout(panelStudents, BoxLayout.X_AXIS));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelStudents.add(scrollPane);
		
		listStudents = new JList();
		listStudents.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (listStudents.getValueIsAdjusting() == false && listStudents.getSelectedIndex() > -1) {
					int selectedStudentindex = listStudents.getSelectedIndex();
					StudentDTO selectedStudent = students.get(selectedStudentindex);
					
					String selectedStudentUsername = selectedStudent.getId().getUsername();
					String selectedStudentFirstName = selectedStudent.getFirstName();
					String selectedStudentLastName = selectedStudent.getLastName();
					String selectedStudentBirthday = null;
					if (selectedStudent.getBirthday() != null) {
						selectedStudentBirthday = selectedStudent.getBirthday().toString();
					}
					String selectedStudentEmail = selectedStudent.getEmail();
					String selectedStudentTelephoneNumber = selectedStudent.getTelephoneNumber();
					
					lblStudentInfo.setText("<html>" + selectedStudentUsername + "<br/><br/>" + 
													selectedStudentFirstName + " " + selectedStudentLastName + "<br/><br/>" + 
													"Birthday: " + selectedStudentBirthday + "<br/><br/>" +
													"Email: " + selectedStudentEmail + "<br/><br/>" + 
													"Telephone Number: " + selectedStudentTelephoneNumber + "</html>");
				}
			}
		});
		listStudents.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		listStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listStudents);
		
		JPanel panelRight = new JPanel();
		contentPane.add(panelRight);
		panelRight.setLayout(new BorderLayout(0, 0));
		
		JPanel panelStudentInfo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelStudentInfo.getLayout();
		flowLayout.setVgap(10);
		panelRight.add(panelStudentInfo);
		panelStudentInfo.setBorder(new TitledBorder(null, "Student Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		lblStudentInfo = new JLabel("INFO");
		lblStudentInfo.setText("<html>FirstName LastName<br/><br/>Birthday<br/><br/>E-Mail<br/><br/>TelephoneNumber</html>");
		panelStudentInfo.add(lblStudentInfo);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new StudentMainScreen(theStudentId).setVisible(true);
			}
		});
		panelRight.add(btnBack, BorderLayout.SOUTH);
		
		/* Get students */
		try {
			
			ArrayList<StudentIdDTO> studentsId = new ArrayList<StudentIdDTO>(StudentManager.getInstance().getStudentSet());
			students = new ArrayList<StudentDTO>();
			for (StudentIdDTO studentId:studentsId) {
				students.add((StudentDTO) StudentManager.getInstance().getUserInfo(studentId));
			}
			
			fillStudentList(students);
					
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Fill the students list
	 * @param theStudents the list of student objects which will be inserted into the list of students
	 */
	private void fillStudentList(ArrayList<StudentDTO> theStudents) {
		/*
		 * Fill a listmodel with the right content
		 */
		DefaultListModel listModel = new DefaultListModel();
		
		for (int i = 0; i < theStudents.size(); i++){
				listModel.add(i, theStudents.get(i).getId().getUsername());
		}
		
		listStudents.setModel(listModel);
	}

}
