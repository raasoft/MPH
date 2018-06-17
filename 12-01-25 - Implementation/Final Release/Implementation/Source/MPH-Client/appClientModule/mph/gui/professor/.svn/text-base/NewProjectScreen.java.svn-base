package mph.gui.professor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.exceptions.DateInconsistencyException;
import mph.beans.exceptions.DeliverableNameAlreadyExists;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.ProjectNameAlreadyExists;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.connection.ProfessorManager;
import javax.swing.SwingConstants;


/**
 * This GUI class lets a professor publish a new project with the desired data and add the deliverables for it.<br/>
 * It extends {@link JFrame}.
 */
public class NewProjectScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldProjectTitle;
	private DefaultListModel deliverablesListModel;
	private JList listDeliverables;
	private boolean isListDeliverablesAdjusting;
	private JFormattedTextField formattedTextFieldStartDate;
	private JFormattedTextField formattedTextFieldEndDate;
	private JTextArea textAreaProjectDescription;
	private JTextField textFieldDeliverableName;
	private JFormattedTextField formattedTextFieldDeadline;
	private JTextArea textAreaDeliverableDescription;
	private JFormattedTextField formattedTextFieldDelayPenalty;
	private JComboBox comboBoxCourse;
	
	private ArrayList<CourseIdDTO> coursesId;
	private Vector<String> courseNamesVector;
	private JButton btnRemoveDeliverable;
	private JButton btnNewDeliverable;

	/**
	 * Create the frame.
	 * @param theProfessorId the id of the professor logged in
	 */
	public NewProjectScreen(final ProfessorIdDTO theProfessorId) {
		setResizable(false);
		setTitle("MPH - New Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelProject = new JPanel();
		contentPane.add(panelProject, BorderLayout.CENTER);
		panelProject.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panelProjectInfo = new JPanel();
		panelProjectInfo.setBorder(new TitledBorder(null, "Project Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProject.add(panelProjectInfo);
		
		/* Get courses */
		try {
			coursesId = new ArrayList<CourseIdDTO>(ProfessorManager.getInstance().getCourseSet(theProfessorId));
		
			ArrayList<String> courseNames = new ArrayList<String>();
			
			/* Fill arraylist with the names of the courses held by the professor */
			for (CourseIdDTO courseId: coursesId) {
				courseNames.add(ProfessorManager.getInstance().getCourseInfo(courseId).getName());
			}
			
			/* Create a vector of names to be used with the JComboBox */
			courseNamesVector = new Vector<String>(courseNames); 
		
		} catch (InvalidArgumentException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (UserNotLoggedInException e) {
			JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		panelProjectInfo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panelCourse = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelCourse.getLayout();
		flowLayout_1.setVgap(0);
		panelProjectInfo.add(panelCourse);
		
		JLabel lblCourse = new JLabel("Select Course");
		panelCourse.add(lblCourse);
		
		comboBoxCourse = new JComboBox(courseNamesVector);
		lblCourse.setLabelFor(comboBoxCourse);
		panelCourse.add(comboBoxCourse);
		comboBoxCourse.setPreferredSize(new Dimension(170, 25));
		comboBoxCourse.setSelectedIndex(-1);
		
		JPanel panelTitle = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelTitle.getLayout();
		flowLayout_2.setVgap(0);
		panelProjectInfo.add(panelTitle);
		
		JLabel lblProjectTitle = new JLabel("Project Title");
		panelTitle.add(lblProjectTitle);
		
		textFieldProjectTitle = new JTextField();
		lblProjectTitle.setLabelFor(textFieldProjectTitle);
		panelTitle.add(textFieldProjectTitle);
		textFieldProjectTitle.setColumns(17);
		
		JPanel panelDate = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panelDate.getLayout();
		flowLayout_3.setHgap(0);
		flowLayout_3.setVgap(0);
		panelProjectInfo.add(panelDate);
		
		JLabel lblStartDate = new JLabel("Start Date");
		panelDate.add(lblStartDate);
		
		formattedTextFieldStartDate = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		lblStartDate.setLabelFor(formattedTextFieldStartDate);
		panelDate.add(formattedTextFieldStartDate);
		formattedTextFieldStartDate.setToolTipText("<html>Insert Start Date<br/>(DD/MM/YYYY)</html>");
		formattedTextFieldStartDate.setColumns(7);
		
		JLabel lblEndDate = new JLabel("End Date");
		panelDate.add(lblEndDate);
		
		formattedTextFieldEndDate = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		lblEndDate.setLabelFor(formattedTextFieldEndDate);
		panelDate.add(formattedTextFieldEndDate);
		formattedTextFieldEndDate.setToolTipText("<html>Insert End Date<br/>(DD/MM/YYYY)</html>");
		formattedTextFieldEndDate.setColumns(7);
		
		JScrollPane scrollPaneProjectDescription = new JScrollPane();
		scrollPaneProjectDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneProjectDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelProjectInfo.add(scrollPaneProjectDescription);
		
		textAreaProjectDescription = new JTextArea();
		textAreaProjectDescription.setWrapStyleWord(true);
		textAreaProjectDescription.setLineWrap(true);
		textAreaProjectDescription.setToolTipText("Insert Project Description");
		scrollPaneProjectDescription.setViewportView(textAreaProjectDescription);
		textAreaProjectDescription.setColumns(15);
		textAreaProjectDescription.setRows(9);
		
		JPanel panelDeliverables = new JPanel();
		panelDeliverables.setBorder(new TitledBorder(null, "Deliverables", TitledBorder.TRAILING, TitledBorder.TOP, null, null));
		panelProject.add(panelDeliverables);
		panelDeliverables.setLayout(new BoxLayout(panelDeliverables, BoxLayout.PAGE_AXIS));
		
		JPanel panelDeliverableFields = new JPanel();
		panelDeliverables.add(panelDeliverableFields);
		panelDeliverableFields.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDeliverableName = new JLabel("Deliverable");
		lblDeliverableName.setHorizontalAlignment(SwingConstants.CENTER);
		panelDeliverableFields.add(lblDeliverableName);
		
		textFieldDeliverableName = new JTextField();
		textFieldDeliverableName.setEditable(false);
		lblDeliverableName.setLabelFor(textFieldDeliverableName);
		panelDeliverableFields.add(textFieldDeliverableName);
		textFieldDeliverableName.setColumns(10);
		
		JLabel lblDeliverableDeadline = new JLabel("Deadline");
		lblDeliverableDeadline.setHorizontalAlignment(SwingConstants.CENTER);
		panelDeliverableFields.add(lblDeliverableDeadline);
		
		formattedTextFieldDeadline = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		formattedTextFieldDeadline.setEditable(false);
		lblDeliverableDeadline.setLabelFor(formattedTextFieldDeadline);
		panelDeliverableFields.add(formattedTextFieldDeadline);
		
		JLabel lblDelayPenalty = new JLabel("Delay Penalty");
		lblDelayPenalty.setHorizontalAlignment(SwingConstants.CENTER);
		panelDeliverableFields.add(lblDelayPenalty);
		
		/* Number formatting for the delay penalty field */
		NumberFormat numberFormat = NumberFormat.getNumberInstance();
		formattedTextFieldDelayPenalty = new JFormattedTextField(numberFormat);
		formattedTextFieldDelayPenalty.setEditable(false);
		lblDelayPenalty.setLabelFor(formattedTextFieldDelayPenalty);
		panelDeliverableFields.add(formattedTextFieldDelayPenalty);
		
		JScrollPane scrollPaneDeliverableDescription = new JScrollPane();
		scrollPaneDeliverableDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelDeliverables.add(scrollPaneDeliverableDescription);
		
		textAreaDeliverableDescription = new JTextArea();
		textAreaDeliverableDescription.setEditable(false);
		textAreaDeliverableDescription.setLineWrap(true);
		textAreaDeliverableDescription.setWrapStyleWord(true);
		textAreaDeliverableDescription.setColumns(10);
		scrollPaneDeliverableDescription.setViewportView(textAreaDeliverableDescription);
		textAreaDeliverableDescription.setRows(3);
		
		JPanel panelDeliverablesButtons = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelDeliverablesButtons.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(0);
		panelDeliverables.add(panelDeliverablesButtons);
		
		btnRemoveDeliverable = new JButton("Remove");
		btnRemoveDeliverable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (isListDeliverablesAdjusting == false && listDeliverables.getSelectedIndex() > -1) {
					int selectedDeliverableIndex = listDeliverables.getSelectedIndex();
					deliverablesListModel.removeElementAt(selectedDeliverableIndex);
					setDeliverablePanelEditable(false);
				}
			}
		});
		btnRemoveDeliverable.setPreferredSize(new Dimension(77, 20));
		panelDeliverablesButtons.add(btnRemoveDeliverable);
		
		btnNewDeliverable = new JButton("New Deliverable");
		btnNewDeliverable.setPreferredSize(new Dimension(120, 20));
		panelDeliverablesButtons.add(btnNewDeliverable);
		
		JScrollPane scrollPaneDeliverables = new JScrollPane();
		scrollPaneDeliverables.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDeliverables.add(scrollPaneDeliverables);
		
		/* Create a list model */
		deliverablesListModel = new DefaultListModel();
		
		listDeliverables = new JList(deliverablesListModel);
		listDeliverables.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDeliverables.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				isListDeliverablesAdjusting = ev.getValueIsAdjusting();
				int selectedDeliverableIndex = listDeliverables.getSelectedIndex();
				
				if (isListDeliverablesAdjusting == false && selectedDeliverableIndex > -1) {
					ListDeliverable selectedDeliverable = (ListDeliverable) listDeliverables.getSelectedValue();
					textFieldDeliverableName.setText(selectedDeliverable.getName());
					formattedTextFieldDeadline.setValue(selectedDeliverable.getDeadlineDate());
					formattedTextFieldDelayPenalty.setValue(selectedDeliverable.getDelayPenalty());
					textAreaDeliverableDescription.setText(selectedDeliverable.getDescription());
					btnRemoveDeliverable.setEnabled(true);
				}
				if (selectedDeliverableIndex == -1) {
					btnRemoveDeliverable.setEnabled(false);
				}
				
			}
		});
		listDeliverables.setVisibleRowCount(3);
		scrollPaneDeliverables.setViewportView(listDeliverables);
		
		JPanel panelButtons = new JPanel();
		contentPane.add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new BorderLayout(5, 0));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new ProfessorMainScreen(theProfessorId).setVisible(true);
			}
		});
		panelButtons.add(btnBack, BorderLayout.WEST);
		
		JButton btnPublish = new JButton("Publish");
		btnPublish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					Set<DeliverableDTO> deliverablesSet = new HashSet<DeliverableDTO>(); 
					/* Get deliverables data */
					for (int i = 0; i < deliverablesListModel.getSize(); i++) {
						ListDeliverable newDeliverable = (ListDeliverable) deliverablesListModel.getElementAt(i);
						String newDeliverableName = newDeliverable.getName();
						java.sql.Date newDeliverableDeadline = newDeliverable.getDeadlineDate();
						double newDeliverableDelayPenalty = newDeliverable.getDelayPenalty();
						String newDeliverableDescription = newDeliverable.getDescription();
						
						DeliverableIdDTO newDeliverableIdDTO = new DeliverableIdDTO(null, newDeliverableName);
						DeliverableDTO newDeliverableDTO = new DeliverableDTO(newDeliverableIdDTO, newDeliverableDeadline, newDeliverableDescription, newDeliverableDelayPenalty);
						
						deliverablesSet.add(newDeliverableDTO);
					}
					
					/* Get selected course */
					if (comboBoxCourse.getSelectedIndex() > -1) {
						CourseIdDTO selectedCourseId = coursesId.get(comboBoxCourse.getSelectedIndex());
						
						ProfessorManager.getInstance().publishNewProject(selectedCourseId, textFieldProjectTitle.getText(), (java.util.Date) formattedTextFieldStartDate.getValue(), (java.util.Date) formattedTextFieldEndDate.getValue(), textAreaProjectDescription.getText(), deliverablesSet);
						JOptionPane.showMessageDialog(getContentPane(), "The project has been published successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
						dispose();
						new ProfessorMainScreen(theProfessorId).setVisible(true);
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "No course selected", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				} catch (InvalidArgumentException ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UserNotLoggedInException ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ProjectNameAlreadyExists ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (DateInconsistencyException ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (DeliverableNameAlreadyExists ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (ForbiddenOperationException ex) {
					JOptionPane.showMessageDialog(getContentPane(), ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnPublish.setPreferredSize(new Dimension(100, 28));
		btnPublish.setMaximumSize(new Dimension(100, 28));
		btnPublish.setMinimumSize(new Dimension(100, 28));
		panelButtons.add(btnPublish, BorderLayout.EAST);
		getRootPane().setDefaultButton(btnPublish);
		/* Disable deliverable panel editing */
		setDeliverablePanelEditable(false);
	}
	
	/**
	 * Enable or disable the editing of the deliverables panel
	 * @param theValue true enables the editing of the panel
	 */
	private void setDeliverablePanelEditable(boolean theValue) {
		
		textFieldDeliverableName.setEditable(theValue);
		formattedTextFieldDeadline.setEditable(theValue);
		formattedTextFieldDelayPenalty.setEditable(theValue);
		textAreaDeliverableDescription.setEditable(theValue);
		
		listDeliverables.setEnabled(!theValue);
		
		textFieldDeliverableName.setText(null);
		formattedTextFieldDeadline.setText(null);
		formattedTextFieldDeadline.setValue(null);
		formattedTextFieldDelayPenalty.setValue(0.00);
		textAreaDeliverableDescription.setText(null);
		
		btnRemoveDeliverable.setEnabled(false);
		
		for (ActionListener aListener: btnNewDeliverable.getActionListeners()) {
			btnNewDeliverable.removeActionListener(aListener);
		}
		
		if (theValue == true) {
			/* Color deliverable editable fields */
			textFieldDeliverableName.setBackground(Color.YELLOW);
			formattedTextFieldDeadline.setBackground(Color.YELLOW);
			formattedTextFieldDelayPenalty.setBackground(Color.YELLOW);
			textAreaDeliverableDescription.setBackground(Color.YELLOW);
			
			textAreaDeliverableDescription.setToolTipText("Insert Deliverable Description");
			btnNewDeliverable.setText("Add Deliverable");
			btnNewDeliverable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!textFieldDeliverableName.getText().isEmpty() && formattedTextFieldDeadline.getValue() != null) {
						ListDeliverable newDeliverable = new ListDeliverable(textFieldDeliverableName.getText(), (java.util.Date) formattedTextFieldDeadline.getValue(), (Double) formattedTextFieldDelayPenalty.getValue(), textAreaDeliverableDescription.getText());
						deliverablesListModel.addElement(newDeliverable);
						setDeliverablePanelEditable(false);
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Deliverable name and deadline must not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
		} else if (theValue == false) {
			/* Restore deliverable editable fields */
			textFieldDeliverableName.setBackground(Color.WHITE);
			formattedTextFieldDeadline.setBackground(Color.WHITE);
			formattedTextFieldDelayPenalty.setBackground(Color.WHITE);
			textAreaDeliverableDescription.setBackground(Color.WHITE);
			
			textAreaDeliverableDescription.setToolTipText(null);
			btnNewDeliverable.setText("New Deliverable");
			btnNewDeliverable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setDeliverablePanelEditable(true);
				}
			});
		}
	}
	
	/**
	 * Private class used to locally store data of deliverables before sending them to the server
	 */
	private class ListDeliverable {
		private String deliverableName = null;
		private java.sql.Date deadlineDate = null;
		private double delayPenalty = 0.00;
		private String description = null;
		
		/**
		 * Create an object containing the data of the deliverable
		 * @param theDeliverableName
		 * @param theDeadlineDate
		 * @param theDelayPenalty
		 * @param theDescription
		 */
		private ListDeliverable(String theDeliverableName, java.util.Date theDeadlineDate, double theDelayPenalty, String theDescription) {
			deliverableName = theDeliverableName;
			
			if (theDeadlineDate != null) {
				deadlineDate = new java.sql.Date(theDeadlineDate.getTime());
			}
			
			delayPenalty = theDelayPenalty;
			description = theDescription;
		}

		/**
		 * @return the deliverableName
		 */
		private String getName() {
			return deliverableName;
		}

		/**
		 * @return the deadlineDate
		 */
		private java.sql.Date getDeadlineDate() {
			return deadlineDate;
		}

		/**
		 * @return the delayPenalty
		 */
		private double getDelayPenalty() {
			return delayPenalty;
		}
		
		/**
		 * @return the description
		 */
		private String getDescription() {
			return description;
		}
		
		public String toString() {
			return getName();
		}
	}

}
