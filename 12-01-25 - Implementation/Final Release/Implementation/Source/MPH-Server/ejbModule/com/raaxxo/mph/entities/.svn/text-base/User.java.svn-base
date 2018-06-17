package com.raaxxo.mph.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.UserDTO;
import mph.beans.exceptions.InvalidArgumentException;
import util.StringUtility;

/**
 * Entity Bean implementation for the Database Entity User
 */	
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract public class User implements TransferrableEntity {
	
	private static final long serialVersionUID = 8753749929988259443L;
	public static final int USERNAME_MAX_LENGTH = 30;
	public static final int FIRSTNAME_MAX_LENGTH = 30;
	public static final int LASTNAME_MAX_LENGTH = 30;
	public static final int EMAIL_MAX_LENGTH = 70;
	public static final int TELNUMB_MAX_LENGTH = 16;


	
	@Id	@Column(name="Username", length = USERNAME_MAX_LENGTH)protected String username; 
	@Column(name="Password", length = 32) protected String password;
	@Column(name="FirstName", length = FIRSTNAME_MAX_LENGTH) protected String firstName;
	@Column(name="LastName", length = LASTNAME_MAX_LENGTH) protected String lastName;
	@Column(name="Email", length = EMAIL_MAX_LENGTH) protected String email;
	@Column(name="Birthday") protected Date birthday;
	@Column(name="TelephoneNumber", length = TELNUMB_MAX_LENGTH) protected String telephoneNumber;

	public User(String theUsername, PasswordHashDTO thePasswordHash, String theFirstName, 
			String theLastName, Date theBirthday, String theEmail, String theTelephoneNumber) throws InvalidArgumentException {
		setUsername(theUsername);
		setPassword(thePasswordHash.toString());
		setFirstName(theFirstName);
		setLastName(theLastName);
		setBirthday(theBirthday);
		setEmail(theEmail);
		setTelephoneNumber(theTelephoneNumber);

	}

	protected User() {
		
	}

	
	public String getUsername() {
		return username;
	}
	private void setUsername(String theUsername) throws InvalidArgumentException {
		
		StringUtility.isNullOrEmpty(theUsername, "Username");
		StringUtility.checkSize(theUsername, "Username", USERNAME_MAX_LENGTH);
	
		if (theUsername.contains(" ") == true) {
			throw new InvalidArgumentException("The username should not contain spaces.");
		}
		
		this.username = theUsername;
	}


	
	public String getPassword() {
		return password;
	}
	public void setPassword(String thePassword) throws InvalidArgumentException {
		
		StringUtility.isNullOrEmpty(thePassword, "Password");
		
		this.password = thePassword;
	}


	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String theFirstName) throws InvalidArgumentException {
		
		StringUtility.checkSize(theFirstName, "First Name", FIRSTNAME_MAX_LENGTH);
		
		this.firstName = theFirstName;
	}


	public String getLastName() {
		return lastName;
	}
	public void setLastName(String theLastName) throws InvalidArgumentException {

		StringUtility.checkSize(theLastName, "Last Name", LASTNAME_MAX_LENGTH);
		
		this.lastName = theLastName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String theEmail) throws InvalidArgumentException {
		
		StringUtility.isNullOrEmpty(theEmail, "Email");
		
		StringUtility.checkSize(theEmail, "Email", EMAIL_MAX_LENGTH);
		
		this.email = theEmail;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date theBirthday) {
		this.birthday = theBirthday;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String theTelephoneNumber) throws InvalidArgumentException {
		
		StringUtility.checkSize(theTelephoneNumber, "Telephone Number", TELNUMB_MAX_LENGTH);
		
		this.telephoneNumber = theTelephoneNumber;
	}
	
	String getTag() {
		return "User";
	}
	
	@Override
	public String toString() {
		
		return 	getTag() + " name: " + getFirstName() + " " + getLastName() + 
				" registrated with the username " + getUsername() + " born on " + getBirthday() + ". Other info are phone (" + 
				getTelephoneNumber() + ") and email (" + getEmail() + ")";
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.raaxxo.mph.entities.TransferrableEntity#getDTO()
	 */
	@Override
	public UserDTO getDTO() {
		return null;
	}


	@Override
	public boolean equals(Object object) {
		if (object instanceof User) {
			User pk = (User)object;

			if (getUsername() != null)
				if (getUsername().equals( pk.getUsername() ) == false)
					return false;

			return true;   
			
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {

		int hash = getUsername().hashCode();

		if (getPassword() != null)
			hash += getPassword().hashCode();

		if (getLastName() != null)
			hash += getLastName().hashCode();

		if (getEmail() != null)
			hash += getEmail().hashCode();

		if (getBirthday() != null)
			hash += getBirthday().hashCode();

		if (getTelephoneNumber() != null)
			hash += getTelephoneNumber().hashCode();

		return hash;
	}


}