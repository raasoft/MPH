package mph.gui.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.CourseDTO;
import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ProfessorDTO;
import mph.beans.dto.ProjectDTO;
import mph.beans.dto.StudentDTO;
import mph.beans.dto.TeamDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.FileTooLargeException;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.TeamNameAlreadyExists;
import mph.beans.exceptions.UploadErrorException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.connection.StudentManager;
import mph.gui.ComparableDeliverable;
import mph.gui.LoginScreen;
import util.CalendarUtility;

/**
 * This GUI class contains
 * <li>a tab with the information about the project
 * <li>a tab showing the teams enrolled in the project
 * <li>a tab which lets the student manage his/her project team if he/she has joined one
 * <li>a tab with the information about the requested project deliverables
 * <br/><br/>
 * It extends {@link JFrame}.
 */
public class ProjectInfoScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblProjectTitle;
	private JTextArea textAreaProjectDescription;
	private JList listTeams;
	private StudentIdDTO studentId = null;
	private ProjectIdDTO projectId = null;
	private ProjectDTO project = null;
	private ArrayList<TeamIdDTO> enrolledTeamsId = null;
	private ArrayList<TeamDTO> enrolledTeams = null;
	private TeamIdDTO projectTeamId = null;
	private TeamIdDTO selectedTeamId = null;
	private TeamDTO selectedTeam = null;
	private ArrayList<DeliverableIdDTO> deliverablesId = null;
	private ArrayList<DeliverableDTO> deliverables = null;
	private ArrayList<ArtifactDTO> deliveredArtifacts = null;
	private boolean isListDeliverablesAdjusting;
	private JCheckBox chckbxAcceptRequests;
	private JPanel panelTeamButtons;
	private JList listRequests;
	private JButton btnCreateTeam = null;
	private JButton btnLeaveTeam = null;
	private JLabel lblProjectStartDate;
	private JLabel lblProjectEndDate;
	private JList listTeamStudents;
	private JButton btnJoinTeam;
	private JLabel lblProfessor;
	private JLabel lblCourse;
	private JList listDeliverables;
	private JLabel lblDeliverableName;
	private JLabel lblDeliverableDeadline;
	private JTextArea textAreaDeliverableDescription;
	private JButton btnUpload;
	private JLabel lblArtifactNameBlank;
	private JLabel lblArtifactDateBlank;
	private JLabel lblArtifactScoreBlank;
	private JLabel lblFinalScore;

	/**
	 * Create the frame.
	 * @param theProjectId the id of the project to be displayed
	 * @param theStudentId the id of the student owning the client
	 */
	public ProjectInfoScreen(final ProjectIdDTO theProjectId, final StudentIdDTO theStudentId) {
		
		if (theProjectId == null || theStudentId == null) {
			JOptionPane.showMessageDialog(getContentPane(), "ERROR", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
			new LoginScreen().setVisible(true);
		} else {
			projectId = theProjectId;
			studentId = theStudentId;
			/* Get Project Team */
			try {
				projectTeamId = StudentManager.getInstance().getProjectTeam(studentId, projectId);
			} catch (InvalidArgumentException e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (UserNotLoggedInException e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		setTitle("MPH - Project Information");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 330);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelProjectInfo = new JPanel();
		tabbedPane.addTab("Project Information", null, panelProjectInfo, null);
		panelProjectInfo.setLayout(new BorderLayout(0, 0));
		
		JPanel panelProjectLabels = new JPanel();
		panelProjectInfo.add(panelProjectLabels, BorderLayout.NORTH);
		panelProjectLabels.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelProjectTitle = new JPanel();
		panelProjectLabels.add(panelProjectTitle);
		panelProjectTitle.setLayout(new GridLayout(0, 3, 0, 0));
		
		lblProjectTitle = new JLabel("Project Title");
		panelProjectTitle.add(lblProjectTitle);
		lblProjectTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		lblProjectEndDate = new JLabel("End Date: ");
		panelProjectTitle.add(lblProjectEndDate);
		lblProjectEndDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		lblProjectStartDate = new JLabel("Start Date: ");
		panelProjectTitle.add(lblProjectStartDate);
		
		JPanel panelProjectFields = new JPanel();
		panelProjectLabels.add(panelProjectFields);
		panelProjectFields.setLayout(new BorderLayout(0, 0));
		
		lblProfessor = new JLabel("Professor: ");
		panelProjectFields.add(lblProfessor, BorderLayout.WEST);
		
		lblCourse = new JLabel("Course: ");
		panelProjectFields.add(lblCourse, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelProjectInfo.add(scrollPane);
		
		textAreaProjectDescription = new JTextArea();
		textAreaProjectDescription.setEditable(false);
		textAreaProjectDescription.setWrapStyleWord(true);
		textAreaProjectDescription.setLineWrap(true);
		scrollPane.setViewportView(textAreaProjectDescription);
		
		JPanel panelTeams = new JPanel();
		tabbedPane.addTab("Project Teams", null, panelTeams, null);
		panelTeams.setLayout(new GridLayout(0, 2, 0, 0));
		
		JScrollPane scrollPaneTeams = new JScrollPane();
		panelTeams.add(scrollPaneTeams);
		
		listTeams = new JList();
		scrollPaneTeams.setViewportView(listTeams);
		listTeams.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelTeamInfo = new JPanel();
		panelTeamInfo.setBorder(new TitledBorder(null, "Project Team Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTeams.add(panelTeamInfo);
		panelTeamInfo.setLayout(new BorderLayout(0, 5));
		
		JLabel lblTeamStudents = new JLabel("Students composing team:");
		lblTeamStudents.setHorizontalAlignment(SwingConstants.CENTER);
		panelTeamInfo.add(lblTeamStudents, BorderLayout.NORTH);
		
		JScrollPane scrollPaneStudents = new JScrollPane();
		panelTeamInfo.add(scrollPaneStudents, BorderLayout.CENTER);
		
		listTeamStudents = new JList();
		listTeamStudents.setEnabled(false);
		listTeamStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTeamStudents.setVisibleRowCount(4);
		scrollPaneStudents.setViewportView(listTeamStudents);
		
		JPanel panel = new JPanel();
		panelTeamInfo.add(panel, BorderLayout.SOUTH);
		
		btnJoinTeam = new JButton("Join");
		btnJoinTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listTeams.getValueIsAdjusting() == false && listTeams.getSelectedIndex() > -1) {
				
					String message = "Do you want to send a membership request to team " + selectedTeamId.getTeamName() + "?";
					
					int action = JOptionPane.showConfirmDialog(getContentPane(), message, "Confirm", JOptionPane.YES_NO_OPTION);
					
					if (action == JOptionPane.OK_OPTION) {
						try {
							StudentManager.getInstance().joinTeam(selectedTeamId);
							fillOutgoingRequests();
							JOptionPane.showMessageDialog(getContentPane(), "A membership request has been sent to team \"" + selectedTeamId.getTeamName() +" \"", "Information", JOptionPane.INFORMATION_MESSAGE);
						} catch (UserNotLoggedInException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (ForbiddenOperationException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidArgumentException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else if (action == JOptionPane.NO_OPTION || action == JOptionPane.CLOSED_OPTION) {
						/*
						 * Do nothing
						 */
					}
				}
			}
		});
		panel.add(btnJoinTeam);
		
		JPanel panelManagement = new JPanel();
		tabbedPane.addTab("Team Management", null, panelManagement, null);
		panelManagement.setLayout(new BorderLayout(0, 0));
		
		JPanel panelRequests = new JPanel();
		panelRequests.setBorder(new TitledBorder(null, "Membership Requests", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelManagement.add(panelRequests, BorderLayout.WEST);
		panelRequests.setLayout(new BoxLayout(panelRequests, BoxLayout.X_AXIS));
		
		listRequests = new JList();
		listRequests.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listRequests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPaneRequests = new JScrollPane();
		scrollPaneRequests.setViewportView(listRequests);
		panelRequests.add(scrollPaneRequests);
		
		panelTeamButtons = new JPanel();
		panelManagement.add(panelTeamButtons, BorderLayout.CENTER);
		panelTeamButtons.setLayout(new GridLayout(0, 1, 0, 0));
		
		chckbxAcceptRequests = new JCheckBox("Accept Requests");
		chckbxAcceptRequests.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTeamButtons.add(chckbxAcceptRequests);
		
		lblFinalScore = new JLabel("Final Score: ");
		lblFinalScore.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblFinalScore.setEnabled(false);
		lblFinalScore.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelTeamButtons.add(lblFinalScore);
		
		JPanel panelDeliverables = new JPanel();
		tabbedPane.addTab("Deliverables", null, panelDeliverables, null);
		panelDeliverables.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneDeliverables = new JScrollPane();
		panelDeliverables.add(scrollPaneDeliverables, BorderLayout.CENTER);
		
		listDeliverables = new JList();
		listDeliverables.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				if (listDeliverables.getValueIsAdjusting() == false) {
					int selectedDeliverableIndex = listDeliverables.getSelectedIndex();
					
					if (selectedDeliverableIndex == -1) {
						lblDeliverableName.setText("Name: ");
						lblDeliverableDeadline.setText("Deadline: ");
						textAreaDeliverableDescription.setText(null);
						
						lblArtifactNameBlank.setText(null);
						lblArtifactDateBlank.setText(null);
						lblArtifactScoreBlank.setText(null);
						
					} else {
						DeliverableDTO selectedDeliverable = deliverables.get(selectedDeliverableIndex);
						lblDeliverableName.setText("Name: " + selectedDeliverable.getId().getDeliverableName());
						lblDeliverableDeadline.setText("Deadline: " + selectedDeliverable.getDeadline());
						textAreaDeliverableDescription.setText(selectedDeliverable.getDescription());
						
						ArtifactDTO selectedDeliverableArtifact = deliveredArtifacts.get(selectedDeliverableIndex);
						if (selectedDeliverableArtifact != null) {
							String fileName = selectedDeliverableArtifact.getFileName();
							int fileNameLength = fileName.length();
							lblArtifactNameBlank.setText(fileName.substring(0, 5) + "..." + fileName.substring(fileNameLength - 5, fileNameLength));
							lblArtifactDateBlank.setText(selectedDeliverableArtifact.getSubmissionDate().toString());
							lblArtifactScoreBlank.setText(selectedDeliverableArtifact.getFinalScore().toString());
						} else {
							lblArtifactNameBlank.setText(null);
							lblArtifactDateBlank.setText(null);
							lblArtifactScoreBlank.setText(null);
						}
					}
				}
			}
		});
		scrollPaneDeliverables.setViewportView(listDeliverables);
		
		JPanel panelDeliverableInfo = new JPanel();
		FlowLayout fl_panelDeliverableInfo = (FlowLayout) panelDeliverableInfo.getLayout();
		fl_panelDeliverableInfo.setVgap(8);
		panelDeliverables.add(panelDeliverableInfo, BorderLayout.EAST);
		
		JPanel panelArtifact = new JPanel();
		panelDeliverableInfo.add(panelArtifact);
		panelArtifact.setBorder(new TitledBorder(null, "Artifact", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelArtifact.setLayout(new GridLayout(12, 1, 0, -1));
		
		JLabel lblArtifactName = new JLabel("Name: ");
		panelArtifact.add(lblArtifactName);
		
		lblArtifactNameBlank = new JLabel(" ");
		lblArtifactNameBlank.setForeground(Color.BLACK);
		panelArtifact.add(lblArtifactNameBlank);
		
		JLabel lblArtifactDate = new JLabel("Submission Date: ");
		panelArtifact.add(lblArtifactDate);
		
		lblArtifactDateBlank = new JLabel(" ");
		lblArtifactDateBlank.setForeground(Color.BLACK);
		panelArtifact.add(lblArtifactDateBlank);
		
		JLabel lblScore = new JLabel("Score: ");
		panelArtifact.add(lblScore);
		
		lblArtifactScoreBlank = new JLabel(" ");
		lblArtifactScoreBlank.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblArtifactScoreBlank.setForeground(Color.RED);
		panelArtifact.add(lblArtifactScoreBlank);
		
		JPanel panelDeliverable = new JPanel();
		panelDeliverableInfo.add(panelDeliverable);
		panelDeliverable.setBorder(new TitledBorder(null, "Deliverable Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDeliverable.setLayout(new BoxLayout(panelDeliverable, BoxLayout.Y_AXIS));
		
		JPanel panelDeliverableFields = new JPanel();
		panelDeliverable.add(panelDeliverableFields);
		panelDeliverableFields.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblDeliverableName = new JLabel("Name: ");
		panelDeliverableFields.add(lblDeliverableName);
		
		lblDeliverableDeadline = new JLabel("Deadline: ");
		panelDeliverableFields.add(lblDeliverableDeadline);
		
		JPanel panelDeliverableBox = new JPanel();
		panelDeliverable.add(panelDeliverableBox);
		panelDeliverableBox.setLayout(new BoxLayout(panelDeliverableBox, BoxLayout.Y_AXIS));
		
		JScrollPane scrollPaneDeliverableDescription = new JScrollPane();
		panelDeliverableBox.add(scrollPaneDeliverableDescription);
		scrollPaneDeliverableDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		textAreaDeliverableDescription = new JTextArea();
		textAreaDeliverableDescription.setEditable(false);
		textAreaDeliverableDescription.setWrapStyleWord(true);
		textAreaDeliverableDescription.setLineWrap(true);
		scrollPaneDeliverableDescription.setViewportView(textAreaDeliverableDescription);
		textAreaDeliverableDescription.setRows(6);
		textAreaDeliverableDescription.setColumns(15);
		
		btnUpload = new JButton("Upload Artifact");
		btnUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isListDeliverablesAdjusting == false && listDeliverables.getSelectedIndex() > -1) {
					
					int selectedDeliverableIndex = listDeliverables.getSelectedIndex();
					final DeliverableIdDTO selectedDeliverableId = deliverablesId.get(selectedDeliverableIndex);
					
					final JFileChooser fc = new JFileChooser();
					
					int returnVal = fc.showOpenDialog(getContentPane());

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
		                
						try {
																	
							getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							StudentManager.getInstance().uploadArtifact(projectTeamId, selectedDeliverableId, file);
							getRootPane().setCursor(Cursor.getDefaultCursor());
							JOptionPane.showMessageDialog(getContentPane(), "File \" " + file.getName() + " \" uploaded successfully for deliverable " + selectedDeliverableId.getDeliverableName(), "Message", JOptionPane.INFORMATION_MESSAGE);
							fillDeliverablesTab();
							lblFinalScore.setText("Final score: " + StudentManager.getInstance().getTeamInfo(projectTeamId).getTeamScore());
						} catch (FileNotFoundException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (IllegalArgumentException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (ForbiddenOperationException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (UploadErrorException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (InvalidArgumentException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (UserNotLoggedInException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} catch (FileTooLargeException e) {
							JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						} finally {
							getRootPane().setCursor(Cursor.getDefaultCursor());
						}
					} 
				}
						
			}
		});
		
		btnUpload.setEnabled(true);
		btnUpload.setPreferredSize(new Dimension(150, 35));
		btnUpload.setSize(new Dimension(150, 28));
		btnUpload.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelDeliverableBox.add(btnUpload);
		
		JPanel panelBackButton = new JPanel();
		contentPane.add(panelBackButton, BorderLayout.SOUTH);
		panelBackButton.setLayout(new BorderLayout(0, 0));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* Open the student main screen */
				dispose();
				new StudentMainScreen(theStudentId).setVisible(true);
			}
		});
		panelBackButton.add(btnBack, BorderLayout.EAST);
	
		/* Get data from the server */
		try {
			
			
			/* Get project info */
			
			project = StudentManager.getInstance().getProjectInfo(theProjectId);
			if (project != null) {
				setTitle(getTitle() + " - " + project.getId().getProjectTitle());
				/* Fill project info tab */
				fillProjectInfoTab();
				
				/* Fill project teams tab*/
				fillProjectTeamsTab();
				
				/* Fill project deliverables tab */
				fillDeliverablesTab();
			}
			
			/* Add selection listener to the list of teams */
			listTeams.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent ev) {
					if (ev.getValueIsAdjusting() == false && listTeams.getSelectedIndex() > -1) {
						int selectedProjectIndex = listTeams.getSelectedIndex();
						selectedTeamId  = enrolledTeamsId.get(selectedProjectIndex);
						selectedTeam  = enrolledTeams.get(selectedProjectIndex);
						
						fillProjectTeamsInfoPanel();
					}
				}
			});
			
			/* If the student does not belong to any project team, disable the team management commands */
			if (projectTeamId == null) {
				disableTeamManagementTab();
			} else {
				enableTeamManagementTab();
			}
			

		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Fill the project info tab
	 */
	private void fillProjectInfoTab() {
		try {
			if (project != null) {
				lblProjectTitle.setText(project.getId().getProjectTitle());
				textAreaProjectDescription.setText(project.getDescription());
				lblProjectStartDate.setText(lblProjectStartDate.getText() + project.getStartDate().toString());
				lblProjectEndDate.setText(lblProjectEndDate.getText() + project.getEndDate().toString());
				
				CourseIdDTO projectCourseId = project.getId().getCourseId();
				CourseDTO projectCourse;
				
				projectCourse = StudentManager.getInstance().getCourseInfo(projectCourseId);
				
				ProfessorIdDTO projectProfessorId = projectCourse.getProfessorId();
				ProfessorDTO projectProfessor = StudentManager.getInstance().getUserInfo(projectProfessorId);
				
				lblProfessor.setText(lblProfessor.getText() + projectProfessor.getFirstName() + " " + projectProfessor.getLastName());
				lblCourse.setText(lblCourse.getText() + " " + projectCourse.getName());
			
			} else {
				JOptionPane.showMessageDialog(getContentPane(), "Error getting project data", "Error", JOptionPane.ERROR_MESSAGE);
				dispose();
			}
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Fill the project teams tab
	 */
	private void fillProjectTeamsTab() {
		
		try {
		
			DefaultListModel listModel = new DefaultListModel();
		
		
			enrolledTeamsId = new ArrayList<TeamIdDTO>(StudentManager.getInstance().getTeamSet(projectId));
		
			enrolledTeams = new ArrayList<TeamDTO>();
		
			for (int i = 0; i < enrolledTeamsId.size(); i++) {
				enrolledTeams.add(i, StudentManager.getInstance().getTeamInfo(enrolledTeamsId.get(i)));
			
				listModel.add(i, enrolledTeamsId.get(i).getTeamName());
			}
		
			listTeams.setModel(listModel);
		
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	/**
	 * Fill the project team panel with the information about the selected team
	 */
	private void fillProjectTeamsInfoPanel() {
		if (selectedTeam != null) {
			ArrayList<StudentIdDTO> studentsComposingSelectedTeam = new ArrayList<StudentIdDTO>(selectedTeam.getStudentSet());
			
			if (studentsComposingSelectedTeam.contains(studentId)) {
				btnJoinTeam.setEnabled(false);
			}
			
			DefaultListModel listModel = new DefaultListModel();
			
			for (StudentIdDTO studentId: studentsComposingSelectedTeam) {
				StudentDTO studentComposingTeam;
				try {
					studentComposingTeam = StudentManager.getInstance().getUserInfo(studentId);
					listModel.addElement(studentComposingTeam.getId().getUsername() + " | " + studentComposingTeam.getFirstName() + " " + studentComposingTeam.getLastName());
					listTeamStudents.setModel(listModel);
				} catch (InvalidArgumentException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UserNotLoggedInException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	/**
	 * Enable the commands available in the Team Management Tab
	 * Add the "Leave Team" button
	 */
	private void enableTeamManagementTab() {
		
		try {
			TeamDTO projectTeam = StudentManager.getInstance().getTeamInfo(projectTeamId);
			chckbxAcceptRequests.setEnabled(true);
			chckbxAcceptRequests.setSelected(!projectTeam.isClosed());
			
			/* Set the final score label */
			lblFinalScore.setText("Final Score: " + projectTeam.getTeamScore());
			java.util.Date todayDate = new java.util.Date();
		    java.sql.Date today = new java.sql.Date(todayDate.getTime());
			if (CalendarUtility.before(today, project.getEndDate())) {
				lblFinalScore.setEnabled(true);
			}
			
			btnUpload.setEnabled(true);
			fillTeamRequestsPanel();
			
			chckbxAcceptRequests.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent ev) {
					try {
						
						if (chckbxAcceptRequests.isEnabled() && chckbxAcceptRequests.isSelected()) {
							StudentManager.getInstance().setTeamEnrollmentRequests(projectTeamId, false);
						} else if (chckbxAcceptRequests.isEnabled() && !chckbxAcceptRequests.isSelected()) {
							StudentManager.getInstance().setTeamEnrollmentRequests(projectTeamId, true);
						}
						
					} catch (InvalidArgumentException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (ForbiddenOperationException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (UserNotLoggedInException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			if (btnCreateTeam != null) {
				panelTeamButtons.remove(btnCreateTeam);
				btnCreateTeam = null;
			}
			
			btnLeaveTeam = new JButton("Leave Team");
			btnLeaveTeam.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						int  answer = JOptionPane.showConfirmDialog(getContentPane(), "Are you sure to leave team " + projectTeamId.getTeamName() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (answer == JOptionPane.YES_OPTION) {
							StudentManager.getInstance().leaveTeam(projectTeamId);
							projectTeamId = null;
							disableTeamManagementTab();
							fillProjectTeamsTab();
							fillProjectTeamsInfoPanel();
						}
					}  catch (ForbiddenOperationException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidArgumentException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (UserNotLoggedInException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			panelTeamButtons.add(btnLeaveTeam);
		
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Fill the project deliverables tab
	 */
	private void fillDeliverablesTab() {
		
		try {
			deliverablesId = new ArrayList<DeliverableIdDTO>(StudentManager.getInstance().getDeliverableSet(projectId));
			
			ComparableDeliverable[] toBeSortedDeliverables = new ComparableDeliverable[deliverablesId.size()];
			for (int i = 0; i < deliverablesId.size(); i++) {
				ComparableDeliverable unsortedDeliverable = new ComparableDeliverable(StudentManager.getInstance().getDeliverableInfo(deliverablesId.get(i)));
				toBeSortedDeliverables[i] = unsortedDeliverable;
			}
			Arrays.sort(toBeSortedDeliverables);

			deliverablesId = new ArrayList<DeliverableIdDTO>();
			deliverables = new ArrayList<DeliverableDTO>();
			
			deliveredArtifacts = new ArrayList<ArtifactDTO>();
			
			DefaultListModel listModel = new DefaultListModel();
			
			for (int i = 0; i < toBeSortedDeliverables.length; i++) {
				deliverablesId.add(i, toBeSortedDeliverables[i].getDTO().getId());
				deliverables.add(i, toBeSortedDeliverables[i].getDTO());
				listModel.add(i, deliverablesId.get(i).getDeliverableName());
				
				if (projectTeamId != null) {
					ArtifactIdDTO anArtifactId = StudentManager.getInstance().getArtifact(deliverablesId.get(i), projectTeamId);
					
					if (anArtifactId != null) { 
						deliveredArtifacts.add(i, StudentManager.getInstance().getArtifactInfo(anArtifactId));
					} else {
						deliveredArtifacts.add(i, null);
					}
				} else {
					deliveredArtifacts.add(i, null);
				}
			}
			
			listDeliverables.setModel(listModel);
			
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	/**
	 * Fill the team requests panel with the incoming requests
	 */
	private void fillTeamRequestsPanel() {
		
		Set<JoinRequestIdDTO> aJoinRequestSet = null;
		
		try {
			aJoinRequestSet = StudentManager.getInstance().getPendingJoinRequests(projectTeamId);
			
			final ArrayList<JoinRequestIdDTO> requestsId = new ArrayList<JoinRequestIdDTO>(aJoinRequestSet);
			
			/*
			 * Fill a listmodel with the right content
			 */
			DefaultListModel listModel = new DefaultListModel();
			
			for (int i = 0; i < requestsId.size(); i++){
					listModel.add(i, requestsId.get(i).getStudentId().getUsername());
			}
			
			listRequests.setModel(listModel);
			
			/* Remove the current selection listeners from the list of requests */
			for (ListSelectionListener aListener: listRequests.getListSelectionListeners()) {
				listRequests.removeListSelectionListener(aListener);
			}
			
			/* Add selection listener to the team requests list */
			listRequests.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					try {
						if (listRequests.getSelectedIndex() > -1) {
							int selectedRequestIndex = listRequests.getSelectedIndex();
							JoinRequestIdDTO selectedRequestId  = requestsId.get(selectedRequestIndex);
							StudentDTO pendingStudent;
							
							pendingStudent = StudentManager.getInstance().getUserInfo(selectedRequestId.getStudentId());
							
							Object[] options = {"Accept", "Decline", "Decide Later"};
							String message = pendingStudent.toString();
							
							int action = JOptionPane.showOptionDialog(getContentPane(), message , "New Membership Request", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);
							if (action == JOptionPane.YES_OPTION) {
								StudentManager.getInstance().acceptPendingJoinRequest(selectedRequestId);
								fillTeamRequestsPanel();
								fillProjectTeamsTab();
								fillProjectTeamsInfoPanel();
							} else if (action == JOptionPane.NO_OPTION) {
								StudentManager.getInstance().declinePendingJoinRequest(selectedRequestId);
								fillTeamRequestsPanel();
							} else if (action == JOptionPane.CANCEL_OPTION || action == JOptionPane.CLOSED_OPTION) {
								/*
								 * Do nothing
								 */
							}
						}
					} catch (InvalidArgumentException ex) {
						JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (UserNotLoggedInException ex) {
						JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (ForbiddenOperationException ex) {
						JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} finally {
						listRequests.clearSelection();
					}
				}		
			});
			
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Disable the commands available in the Team Management Tab
	 * Add the "Create Team" button
	 */
	private void disableTeamManagementTab() {
		chckbxAcceptRequests.setEnabled(false);
		btnUpload.setEnabled(false);
		fillOutgoingRequests();
		
		/* Reset the final score label */
		lblFinalScore.setText("Final Score: ");
		lblFinalScore.setEnabled(false);
				
		if (btnLeaveTeam!= null) {
			panelTeamButtons.remove(btnLeaveTeam);
			btnLeaveTeam = null;
		}
		
		btnCreateTeam = new JButton("Create Team");
		btnCreateTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String theTeamName = JOptionPane.showInputDialog(getContentPane(), "Which name do you want for the Project Team?", "New Team Creation", JOptionPane.QUESTION_MESSAGE);
					if (projectId != null) {
						projectTeamId = StudentManager.getInstance().createTeam(projectId, theTeamName);
						enableTeamManagementTab();
						fillProjectTeamsTab();
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Error getting project data", "Error", JOptionPane.ERROR_MESSAGE);
						dispose();
					}
				} catch (InvalidArgumentException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UserNotLoggedInException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (TeamNameAlreadyExists e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ForbiddenOperationException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelTeamButtons.add(btnCreateTeam);
		
	}
	
	/**
	 * Fill the list in the team management tab with the outgoing membership requests
	 */
	private void fillOutgoingRequests() {
		
		Set<JoinRequestIdDTO> aJoinRequestSet = null;
		
		try {
			aJoinRequestSet = StudentManager.getInstance().getPendingJoinRequestSet(projectId);
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		final ArrayList<JoinRequestIdDTO> requestsId = new ArrayList<JoinRequestIdDTO>(aJoinRequestSet);
		
		/*
		 * Fill a listmodel with the right content
		 */
		DefaultListModel listModel = new DefaultListModel();
		
		for (int i = 0; i < requestsId.size(); i++){
				listModel.add(i, requestsId.get(i).getTeamId().getTeamName());
		}
		
		listRequests.setModel(listModel);
		
		/* Remove the current selection listeners from the list of requests */
		for (ListSelectionListener aListener: listRequests.getListSelectionListeners()) {
			listRequests.removeListSelectionListener(aListener);
		}
		
		/* Add selection listener to the team requests list */
		listRequests.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				try {
					if (listRequests.getSelectedIndex() > -1) {
						int selectedRequestIndex = listRequests.getSelectedIndex();
						JoinRequestIdDTO selectedRequestId  = requestsId.get(selectedRequestIndex);
						
						int action = JOptionPane.showConfirmDialog(getContentPane(), "Do you want to remove your membership request for team " + selectedRequestId.getTeamId().getTeamName() + " ?", "Confirm", JOptionPane.YES_NO_OPTION);
						if (action == JOptionPane.YES_OPTION) {
							StudentManager.getInstance().removePendingJoinRequest(selectedRequestId);
							fillOutgoingRequests();
						} else if (action == JOptionPane.NO_OPTION || action == JOptionPane.CLOSED_OPTION) {
							/*
							 * Do nothing
							 */
						}
					}
				} catch (InvalidArgumentException ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UserNotLoggedInException ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ForbiddenOperationException ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					listRequests.clearSelection();
				}
			}
		});
	}
	
	
}
