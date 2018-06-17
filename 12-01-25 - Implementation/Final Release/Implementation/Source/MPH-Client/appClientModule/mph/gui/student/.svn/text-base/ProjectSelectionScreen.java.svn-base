package mph.gui.student;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import mph.beans.dto.CourseDTO;
import mph.beans.dto.ProfessorDTO;
import mph.beans.dto.ProjectDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.connection.StudentManager;
import mph.gui.FolderNode;
import mph.gui.TreeCourse;
import mph.gui.TreeEntity;
import mph.gui.TreeProfessor;
import mph.gui.TreeProject;

/**
 * This GUI class lets the student select a project from a tree.<br/>
 * The tree is composed by:
 * <li>Professor nodes
 * <li>Course nodes contained in Professor nodes
 * <li>Project nodes contained in Course nodes
 * <br/><br/>
 * It extends {@link JFrame}.<br/>
 * See {@link JTree}.
 */
public class ProjectSelectionScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCourseTitle;
	private JLabel lblCourseId;
	private JLabel lblCourseDescription;
	private JLabel lblUsername;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblBirthday;
	private JLabel lblEmail;
	private JLabel lblPhoneNumber;

	/**
	 * Create the frame.
	 * @param theStudentId the id of the student logged in
	 */
	public ProjectSelectionScreen(final StudentIdDTO theStudentId) {
		setTitle("MPH - Project Selection");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new BorderLayout(0, 0));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new StudentMainScreen(theStudentId).setVisible(true);
			}
		});
		panelButtons.add(btnBack, BorderLayout.EAST);
		
		JPanel panelInfo = new JPanel();
		contentPane.add(panelInfo, BorderLayout.EAST);
		panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));
		
		JPanel panelCourseInfo = new JPanel();
		panelCourseInfo.setBorder(new TitledBorder(null, "Course Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfo.add(panelCourseInfo);
		panelCourseInfo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelCourseTitle = new JPanel();
		panelCourseInfo.add(panelCourseTitle);
		panelCourseTitle.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblCourseTitle = new JLabel("Course Title");
		panelCourseTitle.add(lblCourseTitle);
		
		lblCourseId = new JLabel("Course ID");
		panelCourseTitle.add(lblCourseId);
		
		lblCourseDescription = new JLabel("Course Description");
		panelCourseInfo.add(lblCourseDescription);
		
		JPanel panelProfessorInfo = new JPanel();
		panelProfessorInfo.setBorder(new TitledBorder(null, "Professor Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInfo.add(panelProfessorInfo);
		panelProfessorInfo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelUsername = new JPanel();
		panelProfessorInfo.add(panelUsername);
		panelUsername.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblUsername = new JLabel("Username");
		panelUsername.add(lblUsername);
		
		JPanel panelName = new JPanel();
		panelProfessorInfo.add(panelName);
		panelName.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblFirstName = new JLabel("First Name");
		panelName.add(lblFirstName);
		
		lblLastName = new JLabel("Last Name");
		panelName.add(lblLastName);
		
		JPanel panelPersonal = new JPanel();
		panelProfessorInfo.add(panelPersonal);
		panelPersonal.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblBirthday = new JLabel("Birthday");
		panelPersonal.add(lblBirthday);
		
		lblEmail = new JLabel("Email");
		panelPersonal.add(lblEmail);
		
		lblPhoneNumber = new JLabel("Telephone Number");
		panelPersonal.add(lblPhoneNumber);
		
		/* Get data from server */
		try {
			
			
			/* Populate the tree */		
			
			/* Create the root node */
			FolderNode root = new FolderNode("MPH");
			
			/* Get professors id's */
			ArrayList<ProfessorIdDTO> professorsId = new ArrayList<ProfessorIdDTO>(StudentManager.getInstance().getProfessorSet());
			ProfessorDTO[] professors = new ProfessorDTO[professorsId.size()];
			
			/* Create professor nodes */
			for (int i = 0; i < professors.length; i++) {
				
				/* Store professor data in a new node */
				professors[i] = StudentManager.getInstance().getUserInfo(professorsId.get(i));
				TreeProfessor treeProfessor = new TreeProfessor(professors[i]);
				FolderNode professorNode = new FolderNode(treeProfessor);
				
				/* Get course id's */
				ArrayList<CourseIdDTO> professorCoursesId = new ArrayList<CourseIdDTO>(StudentManager.getInstance().getCourseSet(professorsId.get(i)));
				CourseDTO[] professorCourses = new CourseDTO[professorCoursesId.size()];
				
				/* Create course nodes */
				for (int j = 0; j < professorCourses.length; j++) {
					
					/* Store course data in a new node */
					professorCourses[j] = StudentManager.getInstance().getCourseInfo(professorCoursesId.get(j));
					TreeCourse treeCourse = new TreeCourse(professorCourses[j]);
					FolderNode courseNode = new FolderNode(treeCourse);
					
					/* Get project id's */
					ArrayList<ProjectIdDTO> courseProjectsId = new ArrayList<ProjectIdDTO>(StudentManager.getInstance().getProjectSet(professorCoursesId.get(j)));
					ProjectDTO[] courseProjects = new ProjectDTO[courseProjectsId.size()];
					
					/* Create project nodes */
					for (int k = 0; k < courseProjects.length; k++) {
						
						/* Store project data in a new node */
						courseProjects[k] = StudentManager.getInstance().getProjectInfo(courseProjectsId.get(k));
						TreeProject treeProject = new TreeProject(courseProjects[k]);
						DefaultMutableTreeNode projectNode = new DefaultMutableTreeNode(treeProject, false);
						
						courseNode.add(projectNode);
					}
					
					professorNode.add(courseNode);
				}
				
				root.add(professorNode);
			}
			
			/* Create the JTree using the nodes previously defined */
			final JTree treeProjects = new JTree(root);
			treeProjects.addTreeSelectionListener(new TreeSelectionListener() {
				public void valueChanged(TreeSelectionEvent arg0) {
					
					/* Get the selected node */
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeProjects.getLastSelectedPathComponent();
					TreeEntity selectedObject = (TreeEntity) selectedNode.getUserObject();
					
					/* If the selected node is a professor fill the professor info panel */
					if (selectedObject.isProfessor()) {
						TreeProfessor selectedProfessor = (TreeProfessor) selectedObject;
						fillProfessorInfoPanel(selectedProfessor.getDTO());
						fillCourseInfoPanel(null);
					} 
					
					/* If the selected node is a course fill the course info panel and the professor info panel */
					else if (selectedObject.isCourse()) { 
						TreeCourse selectedCourse = (TreeCourse) selectedObject;
						fillCourseInfoPanel(selectedCourse.getDTO());
						
						FolderNode selectedCourseProfessorNode = (FolderNode) selectedNode.getParent();
						TreeProfessor selectedCourseProfessor = (TreeProfessor) selectedCourseProfessorNode.getUserObject();
						fillProfessorInfoPanel(selectedCourseProfessor.getDTO());
					}
					
					/* If selected node is a project open the project info screen */
					else if (selectedObject.isProject()) {
						TreeProject selectedProject = (TreeProject) selectedObject;
						ProjectIdDTO selectedProjectId = selectedProject.getDTO().getId();
						dispose();
						ProjectInfoScreen aProjInfoScreen = new ProjectInfoScreen(selectedProjectId, theStudentId);
						aProjInfoScreen.setVisible(true);
					} 
					
					
			
				}
			});
			treeProjects.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			treeProjects.setToggleClickCount(1);
			treeProjects.setRootVisible(false);
			treeProjects.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
			/* Add JScrollPane to the JTree */
			JScrollPane scrollPane = new JScrollPane(treeProjects);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			contentPane.add(scrollPane, BorderLayout.CENTER);
			
			
			
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	/**
	 * Fill the professor info panel
	 * @param theSelectedProfessor the object containing the professor information
	 */
	private void fillProfessorInfoPanel(ProfessorDTO theSelectedProfessor) {
		lblUsername.setText(theSelectedProfessor.getId().getUsername());
		lblFirstName.setText(theSelectedProfessor.getFirstName());
		lblBirthday.setText("Birthday: " + theSelectedProfessor.getBirthday().toString());
		lblEmail.setText("Email: " + theSelectedProfessor.getEmail());
		lblPhoneNumber.setText("Telephone Number: " + theSelectedProfessor.getTelephoneNumber());
	}
	
	/**
	 * Fill the course info panel with the info given as parameter
	 * @param theSelectedCourse the object containing the course information.<br/>
	 * If it is null clear the panel
	 */
	private void fillCourseInfoPanel(CourseDTO theSelectedCourse) {
		if (theSelectedCourse != null) {
			lblCourseTitle.setText(theSelectedCourse.getName());
			lblCourseDescription.setText(theSelectedCourse.getDescription());
			lblCourseId.setText(" ID: " + theSelectedCourse.getId().getId());
		} else {
			lblCourseTitle.setText("Course Title");
			lblCourseDescription.setText("Course Description");
			lblCourseId.setText("Course Id");
		}
	}
	
}
