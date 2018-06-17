package mph.gui.student;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UsernameAlreadyExistsException;
import mph.connection.StudentManager;
import mph.gui.LoginScreen;

/**
 * This GUI class lets a student register an account into the MPH system.<br/>
 * It extends {@link JFrame}.
 */
public class RegistrationScreen extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textFieldUsername;
	private JPasswordField passwordFieldPassword;
	private JPasswordField passwordFieldConfirmPassword;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldEmail;
	private JTextField textFieldTelephoneNumber;
	private JFormattedTextField formattedTextFieldBirthday;
	private JButton btnRegister;
	private JLabel lblConfirmPassword;

	/**
	 * Create the frame.
	 */
	public RegistrationScreen() {
		setIconImage(null);
		setResizable(false);
		setTitle("MPH - Registration");
		setBounds(100, 100, 331, 350);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelRegistration = new JPanel();
		panelRegistration.setBorder(new TitledBorder(null, "Registration Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelRegistration);
		panelRegistration.setLayout(new BorderLayout(0, 0));
		
		JPanel panelFields = new JPanel();
		panelRegistration.add(panelFields, BorderLayout.WEST);
		panelFields.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblUsername = new JLabel("Username");
		panelFields.add(lblUsername);
		
		textFieldUsername = new JTextField();
		panelFields.add(textFieldUsername);
		textFieldUsername.setColumns(10);
		lblUsername.setLabelFor(textFieldUsername);
		
		JLabel lblPassword = new JLabel("Password");
		panelFields.add(lblPassword);
		
		passwordFieldPassword = new JPasswordField();
		lblPassword.setLabelFor(passwordFieldPassword);
		panelFields.add(passwordFieldPassword);
		
		lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setLabelFor(lblConfirmPassword);
		panelFields.add(lblConfirmPassword);
		
		passwordFieldConfirmPassword = new JPasswordField();
		panelFields.add(passwordFieldConfirmPassword);
		
		/*
		 * Notify the user if passwords match
		 */
		DocumentListener passwordChangeListener = new DocumentListener(){
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
				     if (new String(passwordFieldPassword.getPassword()).equals(new String(passwordFieldConfirmPassword.getPassword()))) {
				    	 passwordFieldConfirmPassword.setBackground(Color.WHITE);
				    	 btnRegister.setEnabled(true);
				     }
				     else {
				    	 passwordFieldConfirmPassword.setBackground(Color.RED);
				    	 btnRegister.setEnabled(false);
				     }
				  }
				};
				
		passwordFieldPassword.getDocument().addDocumentListener(passwordChangeListener);
		passwordFieldConfirmPassword.getDocument().addDocumentListener(passwordChangeListener);

		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setLabelFor(lblFirstName);
		panelFields.add(lblFirstName);
		
		textFieldFirstName = new JTextField();
		panelFields.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setLabelFor(lblLastName);
		panelFields.add(lblLastName);
		
		textFieldLastName = new JTextField();
		panelFields.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		panelFields.add(lblEmail);
		
		textFieldEmail = new JTextField();
		lblEmail.setLabelFor(textFieldEmail);
		panelFields.add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		/*
		 * Notify the user if email is not valid
		 */
		DocumentListener eMailChangeListener = new DocumentListener(){
			  public void changedUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void removeUpdate(DocumentEvent e) {
				    warn();
				  }
				  public void insertUpdate(DocumentEvent e) {
				    warn();
				  }

				  public void warn() {
					  boolean match = Pattern.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]+", textFieldEmail.getText());
			
				     if (match) {
				    	 textFieldEmail.setBackground(Color.WHITE);
				    	 btnRegister.setEnabled(true);
				     }
				     else {
				    	 textFieldEmail.setBackground(Color.RED);
				    	 btnRegister.setEnabled(false);
				     }
				  }
				};
		textFieldEmail.getDocument().addDocumentListener(eMailChangeListener);		
		
		JLabel lblBirthday = new JLabel("Birthday (DD/MM/YYYY)");
		panelFields.add(lblBirthday);
		
		/* Date Formatting */
		formattedTextFieldBirthday = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		/* * */
		
		lblBirthday.setLabelFor(formattedTextFieldBirthday);
		panelFields.add(formattedTextFieldBirthday);
		
		JLabel lblTelephoneNumber = new JLabel("Telephone Number");
		panelFields.add(lblTelephoneNumber);
		
		textFieldTelephoneNumber = new JTextField();
		panelFields.add(textFieldTelephoneNumber);
		textFieldTelephoneNumber.setColumns(10);
		
		JPanel panelStars = new JPanel();
		panelRegistration.add(panelStars, BorderLayout.EAST);
		panelStars.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblUsernameStar = new JLabel("*");
		panelStars.add(lblUsernameStar);
		
		JLabel labelPasswordStar = new JLabel("*");
		panelStars.add(labelPasswordStar);
		
		JLabel lblConfirmPasswordStar = new JLabel("*");
		panelStars.add(lblConfirmPasswordStar);
		
		JLabel lblLastNameBlank = new JLabel(" ");
		panelStars.add(lblLastNameBlank);
		
		JLabel lblFirstNameBlank = new JLabel(" ");
		panelStars.add(lblFirstNameBlank);
		
		JLabel lblEmailStar = new JLabel("*");
		panelStars.add(lblEmailStar);
		
		JLabel lblBirthdayBlank = new JLabel(" ");
		panelStars.add(lblBirthdayBlank);
		
		JLabel lblTelephoneNumberBlank = new JLabel(" ");
		panelStars.add(lblTelephoneNumberBlank);
		
		JPanel panelButtons = new JPanel();
		panelRegistration.add(panelButtons, BorderLayout.SOUTH);
		panelButtons.setLayout(new BorderLayout(0, 0));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginScreen().setVisible(true);
			}
		});
		panelButtons.add(btnBack, BorderLayout.WEST);
		
		btnRegister = new JButton("Register");
		getRootPane().setDefaultButton(btnRegister);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					StudentManager.getInstance().register(textFieldUsername.getText(), new String(passwordFieldPassword.getPassword()), textFieldFirstName.getText(), textFieldLastName.getText(), (java.util.Date)formattedTextFieldBirthday.getValue(), textFieldEmail.getText(), textFieldTelephoneNumber.getText());
					JOptionPane.showMessageDialog(getContentPane(), "Registration Successful");
					dispose();
					new LoginScreen().setVisible(true);
				} catch (UsernameAlreadyExistsException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (InvalidArgumentException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} catch (NamingException e) {
					JOptionPane.showMessageDialog(getContentPane(), e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		panelButtons.add(btnRegister, BorderLayout.EAST);
		btnRegister.setHorizontalTextPosition(SwingConstants.LEADING);
		
		JLabel lblMandatory = new JLabel("Fields marked with * are mandatory");
		panelButtons.add(lblMandatory, BorderLayout.NORTH);

	}

}
