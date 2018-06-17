package mph.gui.professor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
import javax.swing.tree.DefaultMutableTreeNode;

import mph.beans.dto.ProfessorDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.connection.ProfessorManager;
import mph.gui.FolderNode;
import mph.gui.LoginScreen;
import mph.gui.TreeCourse;
import mph.gui.TreeEntity;
import mph.gui.TreeProject;
import java.awt.Cursor;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import java.awt.Color;

/**
 * This GUI class lets the professor update his/her personal information and contains the list of the published projects.<br/>
 * It also lets a professor create a new project to be published.<br/>
 * It extends {@link JFrame}.
 */
public class ProfessorMainScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Needed for storing the User information
	 */
	private ProfessorDTO professor;
	
	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JTextField textFieldPhoneNumber;
	private JFormattedTextField formattedTextFieldBirthday;
	
	/**
	 * Create the frame.
	 * @param theProfessorId the id of the professor logged in
	 */
	public ProfessorMainScreen(final ProfessorIdDTO theProfessorId) {
		
		setTitle("MPH - Main Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
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
				
				/* Update the locally stored professor object */
				try {
					professor = ProfessorManager.getInstance().getUserInfo(theProfessorId);
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
					ProfessorManager.getInstance().updateUserInfo(professor.getId(), textFieldFirstName.getText(), textFieldLastName.getText(), (java.util.Date)formattedTextFieldBirthday.getValue(), textFieldEmail.getText(), textFieldPhoneNumber.getText());
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
		panelProjects.setBorder(new TitledBorder(null, "Published Projects", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59, 59)));
		contentPane.add(panelProjects, BorderLayout.CENTER);
		panelProjects.setLayout(new BoxLayout(panelProjects, BoxLayout.X_AXIS));
		
		/* Populate the tree */
		try {
				
			/* Create the root node */
			FolderNode root = new FolderNode("MPH");
			
			/* Get course id's */
			ArrayList<CourseIdDTO> coursesId = new ArrayList<CourseIdDTO>(ProfessorManager.getInstance().getCourseSet(theProfessorId));
			
			/* Create course nodes */
			for (CourseIdDTO courseId: coursesId) {
				
				/* Store course data in a new node */
				TreeCourse treeCourse = new TreeCourse(ProfessorManager.getInstance().getCourseInfo(courseId));
				FolderNode courseNode = new FolderNode(treeCourse);
				
				/* Get project id's */
				ArrayList<ProjectIdDTO> projectsId = new ArrayList<ProjectIdDTO>(ProfessorManager.getInstance().getProjectSet(courseId));
				
				/* Create project nodes */
				for (ProjectIdDTO projectId: projectsId) {
					
					/* Store project data in a new node */
					TreeProject treeProject = new TreeProject(ProfessorManager.getInstance().getProjectInfo(projectId));
					DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(treeProject, false);
					
					courseNode.add(projectNode);
				}
				
				root.add(courseNode);
			}
			
			/* Create the JTree */
			final JTree treeProjects = new JTree(root);
			treeProjects.setToggleClickCount(1);
			treeProjects.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent arg0) {
					
					/* Get the selected node */
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeProjects.getLastSelectedPathComponent();
					TreeEntity selectedObject = (TreeEntity) selectedNode.getUserObject();
					
					/* If selected node is a project open the project info screen */
					if (selectedObject.isProject()) {
						TreeProject selectedProject = (TreeProject) selectedObject;
						ProjectIdDTO selectedProjectId = selectedProject.getDTO().getId();
						dispose();
						new ProjectInfoScreen(selectedProjectId, theProfessorId).setVisible(true);
					}
					
				}
			});
			treeProjects.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			treeProjects.setRootVisible(false);
			
			/* Add JScrollPane to the JTree */
			JScrollPane scrollPane = new JScrollPane(treeProjects);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			panelProjects.add(scrollPane);
			
			JPanel panelButtons = new JPanel();
			contentPane.add(panelButtons, BorderLayout.SOUTH);
			
			JButton btnCreateProject = new JButton("Create Project");
			getRootPane().setDefaultButton(btnCreateProject);
			btnCreateProject.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
					new NewProjectScreen(theProfessorId).setVisible(true);
				}
			});
			panelButtons.setLayout(new BorderLayout(0, 0));
			panelButtons.add(btnCreateProject, BorderLayout.CENTER);
			
			JButton btnNewButton = new JButton("Log Out");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						int action = JOptionPane.showConfirmDialog(getContentPane(), "Do you really want to log out?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (action == JOptionPane.YES_OPTION) {
							ProfessorManager.getInstance().logout();
							/* Open the Login Screen */
							dispose();
							new LoginScreen().setVisible(true);
						}
					} catch (UserNotLoggedInException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			panelButtons.add(btnNewButton, BorderLayout.EAST);
			
			/* Get Student Info */
			professor = ProfessorManager.getInstance().getUserInfo(theProfessorId);
			
			/* Populate user fields */
			populateUserFields();
			
			/* Add professor username in the window title */
			if (professor != null) {
				this.setTitle(getTitle()+" - "+professor.getId().getUsername());
			}
		
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Populate the user fields
	 */
	private void populateUserFields() {
		if (professor != null) {
			textFieldFirstName.setText(professor.getFirstName());
			textFieldLastName.setText(professor.getLastName());
			formattedTextFieldBirthday.setValue(professor.getBirthday());
			textFieldEmail.setText(professor.getEmail());
			textFieldPhoneNumber.setText(professor.getTelephoneNumber());
		} else {
			JOptionPane.showMessageDialog(getContentPane(), "Error getting professor data", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
		}
	}

}
