package mph.gui.professor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
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

import util.CalendarUtility;

import mph.beans.dto.CourseDTO;
import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ProfessorDTO;
import mph.beans.dto.ProjectDTO;
import mph.beans.dto.StudentDTO;
import mph.beans.dto.TeamDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.connection.ProfessorManager;
import mph.gui.ComparableDeliverable;
import mph.gui.LoginScreen;
import java.awt.Font;

/**
 * This GUI class contains
 * <li>a tab with the information about the project
 * <li>a tab showing the teams and students enrolled in the project
 * <li>a tab with the requested project deliverables
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
	private ProjectIdDTO projectId = null;
	private ProjectDTO project = null;
	private ArrayList<TeamIdDTO> enrolledTeamsId = null;
	private ArrayList<TeamDTO> enrolledTeams = null;
	private TeamIdDTO selectedTeamId = null;
	private TeamDTO selectedTeam = null;
	private ArrayList<StudentIdDTO> studentsComposingSelectedTeam = null;
	private ArrayList<DeliverableIdDTO> deliverablesId = null;
	private ArrayList<DeliverableDTO> deliverables = null;
	private boolean isListDeliverablesAdjusting;
	private JLabel lblProjectStartDate;
	private JLabel lblProjectEndDate;
	private JList listTeamStudents;
	private JButton btnGetTeamArtifacts;
	private JLabel lblProfessor;
	private JLabel lblCourse;
	private JList listDeliverables;
	private JLabel lblDeliverableName;
	private JLabel lblDeliverableDeadline;
	private JTextArea textAreaDeliverableDescription;
	private JButton btnGetDeliverableArtifacts;
	private JLabel lblFinalScore;

	/**
	 * Create the frame.
	 * @param theProjectId the id of the project to be displayed
	 * @param theProfessorId the id of the professor logged in
	 */
	public ProjectInfoScreen(final ProjectIdDTO theProjectId, final ProfessorIdDTO theProfessorId) {
		if (theProjectId == null || theProfessorId == null) {
			JOptionPane.showMessageDialog(getContentPane(), "ERROR", "Error", JOptionPane.ERROR_MESSAGE);
			dispose();
			new LoginScreen().setVisible(true);
		} else {
			projectId = theProjectId;
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
		
		lblProjectStartDate = new JLabel("Start Date: ");
		panelProjectTitle.add(lblProjectStartDate);
		
		lblProjectEndDate = new JLabel("End Date: ");
		panelProjectTitle.add(lblProjectEndDate);
		lblProjectEndDate.setAlignmentX(Component.CENTER_ALIGNMENT);
		
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
		
		listTeamStudents = new JList();
		listTeamStudents.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				if (ev.getValueIsAdjusting() == false && listTeamStudents.getSelectedIndex() > -1) {
					
					int selectedStudentIndex = listTeamStudents.getSelectedIndex();
					StudentIdDTO selectedStudentId = studentsComposingSelectedTeam.get(selectedStudentIndex);
					listTeamStudents.clearSelection();
					try {
						StudentDTO selectedTeamStudent = ProfessorManager.getInstance().getUserInfo(selectedStudentId);
						JOptionPane.showMessageDialog(getContentPane(), selectedTeamStudent, "Message", JOptionPane.PLAIN_MESSAGE);
					} catch (InvalidArgumentException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (UserNotLoggedInException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		
		JLabel lblTeamStudents = new JLabel("Students composing team:");
		panelTeamInfo.add(lblTeamStudents, BorderLayout.NORTH);
		lblTeamStudents.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTeamStudents.setHorizontalAlignment(SwingConstants.CENTER);
		
		JScrollPane scrollPaneStudents = new JScrollPane();
		scrollPaneStudents.setViewportView(listTeamStudents);
		panelTeamInfo.add(scrollPaneStudents, BorderLayout.CENTER);
		listTeamStudents.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		listTeamStudents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listTeamStudents.setVisibleRowCount(4);
		
		JPanel panelTeamArtifactsButton = new JPanel();
		panelTeamInfo.add(panelTeamArtifactsButton, BorderLayout.SOUTH);
		
		btnGetTeamArtifacts = new JButton("Get Artifacts");
		btnGetTeamArtifacts.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnGetTeamArtifacts.setToolTipText("Get Artifacts By Team");
		btnGetTeamArtifacts.setEnabled(false);
		btnGetTeamArtifacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listTeams.getValueIsAdjusting() == false && listTeams.getSelectedIndex() > -1) {
					dispose();
					/* Open the artifacts screen */
					new ArtifactsScreen(theProfessorId, theProjectId, selectedTeamId).setVisible(true);
				}
			}
		});
		panelTeamArtifactsButton.setLayout(new BoxLayout(panelTeamArtifactsButton, BoxLayout.Y_AXIS));
		panelTeamArtifactsButton.add(btnGetTeamArtifacts);
		
		lblFinalScore = new JLabel("Final Score: ");
		lblFinalScore.setFont(new Font("SansSerif", Font.BOLD, 12));
		panelTeamArtifactsButton.add(lblFinalScore);
		lblFinalScore.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblFinalScore.setEnabled(false);
		
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
						btnGetDeliverableArtifacts.setEnabled(false);
					} else {
						DeliverableDTO selectedDeliverable = deliverables.get(selectedDeliverableIndex);
						lblDeliverableName.setText("Name: " + selectedDeliverable.getId().getDeliverableName());
						lblDeliverableDeadline.setText("Deadline: " + selectedDeliverable.getDeadline());
						textAreaDeliverableDescription.setText(selectedDeliverable.getDescription());
						btnGetDeliverableArtifacts.setEnabled(true);
					}
				}
			}
		});
		scrollPaneDeliverables.setViewportView(listDeliverables);
		
		JPanel panelDeliverable = new JPanel();
		panelDeliverable.setBorder(new TitledBorder(null, "Deliverable Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDeliverables.add(panelDeliverable, BorderLayout.EAST);
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
		
		btnGetDeliverableArtifacts = new JButton("Get Artifacts");
		btnGetDeliverableArtifacts.setToolTipText("Get Artifacts By Team");
		btnGetDeliverableArtifacts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isListDeliverablesAdjusting == false && listDeliverables.getSelectedIndex() > -1) {
					
					DeliverableIdDTO selectedDeliverableId = deliverablesId.get(listDeliverables.getSelectedIndex());
					dispose();
					/* Open the artifacts screen */
					new ArtifactsScreen(theProfessorId, theProjectId, selectedDeliverableId).setVisible(true);
				}
			}
		});
		
		btnGetDeliverableArtifacts.setEnabled(false);
		btnGetDeliverableArtifacts.setPreferredSize(new Dimension(150, 35));
		btnGetDeliverableArtifacts.setSize(new Dimension(150, 28));
		btnGetDeliverableArtifacts.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelDeliverableBox.add(btnGetDeliverableArtifacts);
		
		JPanel panelBackButton = new JPanel();
		contentPane.add(panelBackButton, BorderLayout.SOUTH);
		panelBackButton.setLayout(new BorderLayout(0, 0));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/* Open the professor main screen */
				dispose();
				new ProfessorMainScreen(theProfessorId).setVisible(true);
			}
		});
		panelBackButton.add(btnBack, BorderLayout.EAST);
	
		/* Get data from the server */
		try {
			
			
			/* Get project info */
			
			project = ProfessorManager.getInstance().getProjectInfo(theProjectId);
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
						btnGetTeamArtifacts.setEnabled(true);
					} else {
						btnGetTeamArtifacts.setEnabled(false);
					}
				}
			});
			
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
				
				projectCourse = ProfessorManager.getInstance().getCourseInfo(projectCourseId);
				
				ProfessorIdDTO projectProfessorId = projectCourse.getProfessorId();
				ProfessorDTO projectProfessor = ProfessorManager.getInstance().getUserInfo(projectProfessorId);
				
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
		
		
			enrolledTeamsId = new ArrayList<TeamIdDTO>(ProfessorManager.getInstance().getTeamSet(projectId));
		
			enrolledTeams = new ArrayList<TeamDTO>();
		
			for (int i = 0; i < enrolledTeamsId.size(); i++) {
				enrolledTeams.add(i, ProfessorManager.getInstance().getTeamInfo(enrolledTeamsId.get(i)));
			
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
		
		/* Set the final score label */
		lblFinalScore.setText("Final Score: " + selectedTeam.getTeamScore());
		java.util.Date utilDate = new java.util.Date();
	    java.sql.Date today = new java.sql.Date(utilDate.getTime());
		if (CalendarUtility.before(today, project.getEndDate())) {
			lblFinalScore.setEnabled(true);
		} 
		
		studentsComposingSelectedTeam = new ArrayList<StudentIdDTO>(selectedTeam.getStudentSet());
		
		DefaultListModel listModel = new DefaultListModel();
		
		for (int i = 0; i < studentsComposingSelectedTeam.size(); i++) {
			StudentDTO studentComposingTeam = null;
			try {
				studentComposingTeam = ProfessorManager.getInstance().getUserInfo(studentsComposingSelectedTeam.get(i));
				listModel.add(i, studentComposingTeam.getId().getUsername() + " | " + studentComposingTeam.getFirstName() + " " + studentComposingTeam.getLastName());
				listTeamStudents.setModel(listModel);
			} catch (InvalidArgumentException e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			} catch (UserNotLoggedInException e) {
				JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/**
	 * Fill the project deliverables tab
	 */
	private void fillDeliverablesTab() {
		
		try {
			deliverablesId = new ArrayList<DeliverableIdDTO>(ProfessorManager.getInstance().getDeliverableSet(projectId));
			
			ComparableDeliverable[] toBeSortedDeliverables = new ComparableDeliverable[deliverablesId.size()];
			for (int i = 0; i < deliverablesId.size(); i++) {
				ComparableDeliverable unsortedDeliverable = new ComparableDeliverable(ProfessorManager.getInstance().getDeliverableInfo(deliverablesId.get(i)));
				toBeSortedDeliverables[i] = unsortedDeliverable;
			}
			Arrays.sort(toBeSortedDeliverables);
			
			deliverablesId = new ArrayList<DeliverableIdDTO>();
			deliverables = new ArrayList<DeliverableDTO>();
			
			DefaultListModel listModel = new DefaultListModel();
			
			for (int i = 0; i < toBeSortedDeliverables.length; i++) {
				deliverablesId.add(i, toBeSortedDeliverables[i].getDTO().getId());
				deliverables.add(i, toBeSortedDeliverables[i].getDTO());
				listModel.add(i, deliverablesId.get(i).getDeliverableName());
			}
			
			listDeliverables.setModel(listModel);
			
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}	
	}
	
}
