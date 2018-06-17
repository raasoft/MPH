package mph.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.NamingException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.exceptions.AlreadyLoggedInException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.PasswordMismatchException;
import mph.beans.exceptions.UserDoesNotExistException;
import mph.connection.ProfessorManager;
import mph.connection.StudentManager;
import mph.gui.professor.ProfessorMainScreen;
import mph.gui.student.RegistrationScreen;
import mph.gui.student.StudentMainScreen;
import java.awt.Font;
import javax.swing.SwingConstants;

/**
 * This GUI class lets a user log into the MPH system.<br/>
 * It extends {@link JFrame}.
 */
public class LoginScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnRegister;
	private JButton btnLogin;
	private JRadioButton rdbtnStudent;
	private JRadioButton rdbtnProfessor;
	private JLabel lblNewLabel;

	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setResizable(false);
		setTitle("MPH - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panelLogin = new JPanel();
		panelLogin.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelLogin, BorderLayout.SOUTH);
		panelLogin.setLayout(new GridLayout(4, 2, 0, 0));
		
		JLabel lblUsername = new JLabel("Username");
		panelLogin.add(lblUsername);
		
		textFieldUsername = new JTextField();
		panelLogin.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		panelLogin.add(lblPassword);
		
		passwordFieldPassword = new JPasswordField();
		panelLogin.add(passwordFieldPassword);
		
		rdbtnProfessor = new JRadioButton("Professor");
		rdbtnProfessor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnProfessor.isSelected()) {
					btnLogin.setEnabled(true);
					btnRegister.setEnabled(false);
					getRootPane().setDefaultButton(btnLogin);
				}
			}
		});
		buttonGroup.add(rdbtnProfessor);
		panelLogin.add(rdbtnProfessor);
		
		JPanel panelBtnLogin = new JPanel();
		panelLogin.add(panelBtnLogin);
		
		btnLogin = new JButton("Log In");
		btnLogin.setEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					
					if(rdbtnProfessor.isSelected()) {
						ProfessorManager.getInstance().login(textFieldUsername.getText(), new String(passwordFieldPassword.getPassword()));
						dispose();
						new ProfessorMainScreen(new ProfessorIdDTO(textFieldUsername.getText().toLowerCase())).setVisible(true);
					}
					
					if(rdbtnStudent.isSelected()) {
						StudentManager.getInstance().login(textFieldUsername.getText(), new String(passwordFieldPassword.getPassword()));
						dispose();
						new StudentMainScreen(new StudentIdDTO(textFieldUsername.getText().toLowerCase())).setVisible(true);
					}
					
				} catch (NamingException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(getContentPane(), "Connection Refused.\nCheck your internet connection and retry later.", "Error", JOptionPane.ERROR_MESSAGE);
				} catch (InvalidArgumentException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (AlreadyLoggedInException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (UserDoesNotExistException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (PasswordMismatchException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}	
			}
		});
		btnLogin.setBounds(new Rectangle(0, 0, 200, 0));
		panelBtnLogin.add(btnLogin);
		
		rdbtnStudent = new JRadioButton("Student");
		rdbtnStudent.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if (rdbtnStudent.isSelected()) {
					btnLogin.setEnabled(true);
					btnRegister.setEnabled(true);
					getRootPane().setDefaultButton(btnLogin);
				}
			}
		});
		buttonGroup.add(rdbtnStudent);
		panelLogin.add(rdbtnStudent);
		
		JPanel panelBtnRegister = new JPanel();
		panelLogin.add(panelBtnRegister);
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				new RegistrationScreen().setVisible(true);
			}
		});
		btnRegister.setEnabled(false);
		panelBtnRegister.add(btnRegister);
		
		lblNewLabel = new JLabel("Manage Project Homework");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

}
