package mph.gui.student;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import mph.beans.dto.ProjectDTO;
import mph.beans.dto.StudentDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.connection.StudentManager;
import mph.gui.LoginScreen;
import mph.gui.TreeCourse;
import mph.gui.TreeEntity;
import mph.gui.TreeProject;
import java.awt.FlowLayout;
import java.awt.Dimension;

/**
 * This GUI class lets the student update his/her personal information and contains the list of the enrolled projects.<br/>
 * It also lets a student view the available projects and see information about other students.<br/>
 * It extends {@link JFrame}.
 */
public class StudentMainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Needed for storing the User information
	 */
	private StudentDTO student;
	
	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JTextField textFieldPhoneNumber;
	private JFormattedTextField formattedTextFieldBirthday;
	private JScrollPane scrollPane;
	
	/**
	 * Create the frame.
	 */
	public StudentMainScreen(final StudentIdDTO theStudentId) {
		
		setTitle("MPH - Main Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 320);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelProfileInfo = new JPanel();
		panelProfileInfo.setBorder(new TitledBorder(null, "Profile Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelProfileInfo, BorderLayout.WEST);
		panelProfileInfo.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblFirstName = new JLabel("First Name");
		panelProfileInfo.add(lblFirstName);
		
		textFieldFirstName = new JTextField();
		panelProfileInfo.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		panelProfileInfo.add(lblLastName);
		
		textFieldLastName = new JTextField();
		panelProfileInfo.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		panelProfileInfo.add(lblEmail);
		
		textFieldEmail = new JTextField();
		panelProfileInfo.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		panelProfileInfo.add(lblPhoneNumber);
		
		textFieldPhoneNumber = new JTextField();
		panelProfileInfo.add(textFieldPhoneNumber);
		textFieldPhoneNumber.setColumns(10);
		
		JLabel lblBirthday = new JLabel("Birthday");
		panelProfileInfo.add(lblBirthday);
		
		/* Date Formatting */
		formattedTextFieldBirthday = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		/* * */
		
		panelProfileInfo.add(formattedTextFieldBirthday);
		
		JButton btnRevertInfo = new JButton("Revert");
		btnRevertInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				/* Update the locally stored student object */
				try {
					student = StudentManager.getInstance().getUserInfo(theStudentId);
				} catch (InvalidArgumentException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UserNotLoggedInException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				/* Populate user fields */
				populateUserFields();
				
			}
		});
		panelProfileInfo.add(btnRevertInfo);
		
		JButton btnSaveInfo = new JButton("Save");
		btnSaveInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					StudentManager.getInstance().updateUserInfo(student.getId(), textFieldFirstName.getText(), textFieldLastName.getText(), (java.util.Date)formattedTextFieldBirthday.getValue(), textFieldEmail.getText(), textFieldPhoneNumber.getText());
				} catch (InvalidArgumentException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					populateUserFields();
				} catch (UserNotLoggedInException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					populateUserFields();
				}
			}
		});
		panelProfileInfo.add(btnSaveInfo);
		
		JPanel panelProjects = new JPanel();
		panelProjects.setBorder(new TitledBorder(null, "Enrolled Projects", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelProjects, BorderLayout.CENTER);
		panelProjects.setLayout(new BoxLayout(panelProjects, BoxLayout.X_AXIS));
		
		JPanel panelButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setHgap(20);
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		
		JButton btnSearchStudents = new JButton("Search Students");
		btnSearchStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new StudentInfoScreen(theStudentId).setVisible(true);
			}
		});
		panelButtons.add(btnSearchStudents);
		
		JButton btnSelectProject = new JButton("View Projects");
		btnSelectProject.setPreferredSize(new Dimension(125, 28));
		btnSelectProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ProjectSelectionScreen(theStudentId).setVisible(true);
			}
		});
		panelButtons.add(btnSelectProject);
		
		JButton btnNewButton = new JButton("Log Out");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					int action = JOptionPane.showConfirmDialog(getContentPane(), "Do you really want to log out?", "Confirm", JOptionPane.YES_NO_OPTION);
					if (action == JOptionPane.YES_OPTION) {
						StudentManager.getInstance().logout();
						/* Open the Login Screen */
						dispose();
						new LoginScreen().setVisible(true);
					}
				} catch (UserNotLoggedInException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		panelButtons.add(btnNewButton);
		
		/* Get Student Info */
		try {
			student = StudentManager.getInstance().getUserInfo(theStudentId);
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		/* Populate user fields */
		populateUserFields();
		
		/* Add student username in the window title */
		if (student != null) {
			this.setTitle(getTitle()+" - "+student.getId().getUsername());
		}
			
		/* Populate the tree */
		try {
				
			/* Create the root node */
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("MPH");
			
			/* Get enrolled project id's */
			ArrayList<ProjectIdDTO> enrolledProjectsId;
			enrolledProjectsId = new ArrayList<ProjectIdDTO>(StudentManager.getInstance().getEnrolledProjectSet(student.getId()));
			
			/* Get course id's */
			Set<CourseIdDTO> coursesIdSet = new HashSet<CourseIdDTO>();
			for (ProjectIdDTO projectId: enrolledProjectsId) {
				coursesIdSet.add(projectId.getCourseId());
			}
			ArrayList<CourseIdDTO> coursesId = new ArrayList<CourseIdDTO>(coursesIdSet);
			
			/* Create course nodes */
			for (CourseIdDTO courseId: coursesId) {
			
				/* Store course data in a new node */
				TreeCourse treeCourse = new TreeCourse(StudentManager.getInstance().getCourseInfo(courseId));
				DefaultMutableTreeNode courseNode = new DefaultMutableTreeNode(treeCourse);
				
				/* Create project nodes */
				for (ProjectIdDTO projectId: enrolledProjectsId) {
					if (projectId.getCourseId() == courseId) {
						/* Store course data in a new node */
						TreeStudentProject treeProject = new TreeStudentProject(theStudentId, StudentManager.getInstance().getProjectInfo(projectId));
						DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(treeProject, false);
					
						courseNode.add(projectNode);
					}
				}
				
				root.add(courseNode);
			}
			
			/* Create the JTree */
			final JTree treeProjects = new JTree(root);
			treeProjects.setToggleClickCount(1);
			
			for (int i = 0; i < treeProjects.getRowCount(); i++) {
				treeProjects.expandRow(i);
			}
			
			treeProjects.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent arg0) {
					
					/* Get the selected node */
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeProjects.getLastSelectedPathComponent();
					TreeEntity selectedObject = (TreeEntity) selectedNode.getUserObject();
					
					/* If selected node is a project open the project info screen */
					if (selectedObject.isProject()) {
						TreeStudentProject selectedProject = (TreeStudentProject) selectedObject;
						ProjectIdDTO selectedProjectId = selectedProject.getDTO().getId();
						
						dispose();
						new ProjectInfoScreen(selectedProjectId, theStudentId).setVisible(true);
					}
					
				}
			});
			treeProjects.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			treeProjects.setRootVisible(false);
			
			/* Add JScrollPane to the JTree */
			scrollPane = new JScrollPane(treeProjects);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panelProjects.add(scrollPane);
			

		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		getRootPane().setDefaultButton(btnSelectProject);
		
	}
	
	/**
	 * Populate the user fields
	 */
	private void populateUserFields() {
		if (student != null) {
			textFieldFirstName.setText(student.getFirstName());
			textFieldLastName.setText(student.getLastName());
			formattedTextFieldBirthday.setValue(student.getBirthday());
			textFieldEmail.setText(student.getEmail());
			textFieldPhoneNumber.setText(student.getTelephoneNumber());
		} else {
			JOptionPane.showMessageDialog(getContentPane(), "Error getting student data", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
	}

	/**
	 * Private class used to display the number of incoming membership requests next to the project title.<br/>
	 * It extends {@link TreeProject}
	 */
	private class TreeStudentProject extends TreeProject {
		
		private int requestsNumber = 0;
		
		/**
		 * See {@link TreeProject#TreeProject(ProjectDTO)}
		 * @param theStudentId the student logged in
		 * @param theDTO the object to store
		 */
		private TreeStudentProject(StudentIdDTO theStudentId, ProjectDTO theDTO) {
			super(theDTO);
			
			try {
				TeamIdDTO teamId = StudentManager.getInstance().getProjectTeam(theStudentId, theDTO.getId());
				ArrayList<JoinRequestIdDTO> joinRequests = new ArrayList<JoinRequestIdDTO>(StudentManager.getInstance().getPendingJoinRequests(teamId));
				requestsNumber = joinRequests.size();
			} catch (InvalidArgumentException e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (UserNotLoggedInException e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public String toString() {
			if (requestsNumber > 0) {
				return "<html><b>"+ super.toString() + " (" + requestsNumber + " Requests!)</b></html> ";
			} else {
				return super.toString();
			}
		}

	}
	
}
