package mph.gui.professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.IncomingFileDTO;
import mph.beans.dto.TeamDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.DownloadErrorException;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.connection.ProfessorManager;
import util.CalendarUtility;

/**
 * This GUI class contains a list of artifact, it lets the professor evaluate and download each delivered file.<br/>
 * It extends {@link JFrame}.
 */
public class ArtifactsScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private final ProfessorIdDTO professorId;
	private final ProjectIdDTO projectId;
	private ArrayList<ArtifactIdDTO> artifactsId = null;
	private ArrayList<ArtifactDTO> artifacts = null;
	private ArtifactDTO selectedArtifact = null;
	private JList listArtifacts;
	private JLabel lblArtifactName;
	private JLabel lblDelay;
	private JLabel lblSubmissionDate;
	private JLabel lblTeam;
	private JLabel lblDeliverable;
	private JButton btnDownload;
	private JButton btnSetScore;
	private JSpinner spinnerScore;
	private JLabel lblFinalScoreBlank;
	
	/**
	 * Create the frame.
	 */
	private void createFrame() {
		
		setTitle("View Artifacts");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelArtifactsList = new JPanel();
		panelArtifactsList.setBorder(new TitledBorder(null, "Artifacts", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelArtifactsList, BorderLayout.WEST);
		panelArtifactsList.setLayout(new BoxLayout(panelArtifactsList, BoxLayout.X_AXIS));
		
		JScrollPane scrollPaneArtifacts = new JScrollPane();
		panelArtifactsList.add(scrollPaneArtifacts);
		
		listArtifacts = new JList();
		listArtifacts.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				if (ev.getValueIsAdjusting() == false && listArtifacts.getSelectedIndex() > -1) {
					try {
						int selectedArtifactIndex = listArtifacts.getSelectedIndex();
						selectedArtifact = artifacts.get(selectedArtifactIndex);
						DeliverableIdDTO selectedArtifactDeliverableId = selectedArtifact.getId().getDeliverableId();
						
						DeliverableDTO selectedArtifactDeliverable = ProfessorManager.getInstance().getDeliverableInfo(selectedArtifactDeliverableId);
						TeamDTO selectedArtifactTeam = ProfessorManager.getInstance().getTeamInfo(selectedArtifact.getId().getTeamId());
						
						java.sql.Date deliverableDeadline = selectedArtifactDeliverable.getDeadline();
						Double delayDays = new Double(CalendarUtility.daysBetween( deliverableDeadline, selectedArtifact.getSubmissionDate()));
						String fileName = selectedArtifact.getFileName();
						int fileNameLength = fileName.length();
						int lblWidth = lblArtifactName.getWidth();
						/* Fix text of label if file name is too long */
						if (fileNameLength * lblArtifactName.getFont().getSize() > lblWidth) {
							lblArtifactName.setText(fileName.substring(0, (lblWidth / 2) - 3) + 
									"..." + fileName.substring(lblWidth / 2));
						}
						lblArtifactName.setText(fileName);
						
						if (delayDays <= 0) {
							lblDelay.setText("Submitted late? No");
						} else {
							lblDelay.setText("Submitted late? Yes, of " + delayDays.toString() + " day(s)");
						}
						lblDeliverable.setText("Deliverable : " + selectedArtifactDeliverableId.getDeliverableName());
						lblFinalScoreBlank.setText("Final Score: " + selectedArtifact.getFinalScore().toString());
						lblSubmissionDate.setText("Submitted: " + selectedArtifact.getSubmissionDate().toString());
						lblTeam.setText("Team: " + selectedArtifactTeam.getId().getTeamName());
						
						btnDownload.setEnabled(true);
						btnSetScore.setEnabled(true);
						spinnerScore.setEnabled(true);
					
					} catch (UserNotLoggedInException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (InvalidArgumentException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		listArtifacts.setVisibleRowCount(11);
		scrollPaneArtifacts.setViewportView(listArtifacts);
		listArtifacts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelArtifactInfo = new JPanel();
		contentPane.add(panelArtifactInfo, BorderLayout.CENTER);
		panelArtifactInfo.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panelInfo = new JPanel();
		panelArtifactInfo.add(panelInfo);
		panelInfo.setLayout(new GridLayout(0, 1, 0, 0));
		
		lblArtifactName = new JLabel("Name: ");
		panelInfo.add(lblArtifactName);
		
		lblDelay = new JLabel("Delay: ");
		panelInfo.add(lblDelay);
		
		lblSubmissionDate = new JLabel("Submission Date: ");
		panelInfo.add(lblSubmissionDate);
		
		lblTeam = new JLabel("Team: ");
		panelInfo.add(lblTeam);
		
		lblDeliverable = new JLabel("Deliverable: ");
		panelInfo.add(lblDeliverable);
		
		JPanel panelButtons = new JPanel();
		panelArtifactInfo.add(panelButtons);
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.Y_AXIS));
		
		btnDownload = new JButton("Download");
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (listArtifacts.getValueIsAdjusting() == false && listArtifacts.getSelectedIndex() > -1) {
					try {
						JFileChooser fc = new JFileChooser();
						fc.setSelectedFile(new File(selectedArtifact.getFileName()));
						int returnVal = fc.showSaveDialog(getContentPane());
						
						if (returnVal == JFileChooser.APPROVE_OPTION) {
						
							File destination = fc.getSelectedFile(); 
							
							boolean saveFile = true;
							
							if (destination.exists()) {
								int answer = JOptionPane.showConfirmDialog(getContentPane(), "File already exists. Do you want to overwrite it?", "Confirm Replace", JOptionPane.YES_NO_OPTION);
								
								if (answer == JOptionPane.YES_OPTION) {
									saveFile = true;
								} else {
									saveFile = false;
								}
							}
							
							if (saveFile) {
								
								getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
								String destinationPath = destination.getAbsolutePath();
								IncomingFileDTO incomingFile = ProfessorManager.getInstance().downloadArtifact(selectedArtifact.getId());
								incomingFile.saveAs(destinationPath);
								getRootPane().setCursor(Cursor.getDefaultCursor());
								JOptionPane.showMessageDialog(getContentPane(), "File downloaded successfully" ,"Message", JOptionPane.INFORMATION_MESSAGE);
							}
						}
						
					} catch (InvalidArgumentException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (UserNotLoggedInException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (DownloadErrorException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} finally {
						getRootPane().setCursor(Cursor.getDefaultCursor());
					}
				}
			}
		});
		btnDownload.setEnabled(false);
		btnDownload.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnDownload.setMaximumSize(new Dimension(150, 28));
		btnDownload.setSize(new Dimension(150, 0));
		btnDownload.setPreferredSize(new Dimension(150, 28));
		panelButtons.add(btnDownload);
		
		btnSetScore = new JButton("Set Score");
		btnSetScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				if (listArtifacts.getValueIsAdjusting() == false && listArtifacts.getSelectedIndex() > -1) {
					
					try {
						int selectedArtifactIndex = listArtifacts.getSelectedIndex();
						Double setScore = (Double) spinnerScore.getValue();
						ProfessorManager.getInstance().setArtifactScore(selectedArtifact.getId(), setScore);
						
						/* Update the final score of selected artifact */
						Double finalScore = ProfessorManager.getInstance().getArtifactInfo(selectedArtifact.getId()).getFinalScore();
						lblFinalScoreBlank.setText(finalScore.toString());
						
						JOptionPane.showMessageDialog(getContentPane(), "Score set correctly", "Message", JOptionPane.INFORMATION_MESSAGE);
						fillArtifactsList();
						listArtifacts.setSelectedIndex(selectedArtifactIndex);
						
					} catch (InvalidArgumentException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (ForbiddenOperationException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					} catch (UserNotLoggedInException e) {
						JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnSetScore.setEnabled(false);
		panelButtons.add(btnSetScore);
		
		JPanel panelScore = new JPanel();
		panelButtons.add(panelScore);
		panelScore.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		panelScore.add(lblScore);
		
		spinnerScore = new JSpinner();
		spinnerScore.setEnabled(false);
		spinnerScore.setPreferredSize(new Dimension(60, 28));
		spinnerScore.setSize(new Dimension(100, 0));
		spinnerScore.setModel(new SpinnerNumberModel(1.0, 1.0, 10.0, 0.05));
		panelScore.add(spinnerScore);
		
		JLabel lblFinalScore = new JLabel("Final Score: ");
		lblFinalScore.setHorizontalAlignment(SwingConstants.CENTER);
		panelScore.add(lblFinalScore);
		
		lblFinalScoreBlank = new JLabel(" ");
		lblFinalScoreBlank.setHorizontalAlignment(SwingConstants.LEFT);
		lblFinalScoreBlank.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 12));
		lblFinalScoreBlank.setForeground(Color.RED);
		panelScore.add(lblFinalScoreBlank);
		
		JPanel panelBackButton = new JPanel();
		contentPane.add(panelBackButton, BorderLayout.SOUTH);
		panelBackButton.setLayout(new BorderLayout(0, 0));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				/* Open the project info screen */
				new ProjectInfoScreen(projectId, professorId).setVisible(true);
			}
		});
		panelBackButton.add(btnBack, BorderLayout.EAST);
	}

	/**
	 * Create the frame containing the artifacts uploaded for the given deliverable.<br/>
	 * It invokes {@link #createFrame()}.
	 * @param theProfessorId the id of the professor logged in
	 * @param theProjectId the project the artifacts belong to
	 * @param theDeliverableId the deliverable referred by the artifacts
	 */
	public ArtifactsScreen(ProfessorIdDTO theProfessorId, ProjectIdDTO theProjectId, DeliverableIdDTO theDeliverableId) {
		
		professorId = theProfessorId;
		projectId = theProjectId;
		
		try {
			artifactsId = new ArrayList<ArtifactIdDTO>(ProfessorManager.getInstance().getArtifactSetByDeliverable(theDeliverableId));
			createFrame();
			fillArtifactsList();
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Create the frame containing the artifacts delivered by the given team.<br/>
	 * It invokes {@link #createFrame()}.
	 * @param theProfessorId the id of the professor logged in
	 * @param theProjectId the project the artifacts belong to
	 * @param theTeamId the team which delivered the artifacts
	 */
	public ArtifactsScreen(ProfessorIdDTO theProfessorId, ProjectIdDTO theProjectId, TeamIdDTO theTeamId) {
		setResizable(false);
		
		professorId = theProfessorId;
		projectId = theProjectId;
		
		try {
			artifactsId = new ArrayList<ArtifactIdDTO>(ProfessorManager.getInstance().getArtifactSetByTeam(theTeamId));
			createFrame();
			fillArtifactsList();
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Fill the list of artifacts
	 */
	private void fillArtifactsList() {
		
		try {
			artifacts = new ArrayList<ArtifactDTO>();
			DefaultListModel listModel = new DefaultListModel();
			
			for (int i = 0; i < artifactsId.size(); i++) {
				artifacts.add(i, ProfessorManager.getInstance().getArtifactInfo(artifactsId.get(i)));
				
				listModel.add(i, artifacts.get(i).getFileName());
			}
			listArtifacts.setModel(listModel);
			
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
