package mph.beans.dto;

import java.sql.Date;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.dto.ids.UserIdDTO;

/**
 * This abstract class will be extended by the DTO's containing the information about a user.<br/>
 * It implements the {@link EntityDTO} interface.
 */
abstract public class UserDTO implements EntityDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -857741075803431485L;
	
	protected UserIdDTO id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String telephoneNumber;
	protected Date birthday;
	
	/**
	 * Create the DTO with the given arguments
	 * @param theFirstName the first name of the user
	 * @param theLastName the last name of the user
	 * @param theEmail the email of the user
	 * @param theTelephoneNumber the telephone number of the user
	 * @param theBirthday the user birthday
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 */
	public UserDTO(String theFirstName, String theLastName, String theEmail, String theTelephoneNumber, Date theBirthday) throws InvalidArgumentException {
		setFirstName(theFirstName);
		setLastName(theLastName);
		setEmail(theEmail);
		setTelephoneNumber(theTelephoneNumber);
		setBirthday(theBirthday);
	}

	@Override
	abstract public UserIdDTO getId();


	/**
	 * @return the user first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the user last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the user email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the user telephone number
	 */
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @return the user birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * Set the user first name
	 * @param firstName the new name to set
	 */
	protected void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Set the user last name
	 * @param lastName the new name to set
	 */
	protected void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Set the user email
	 * @param email the new email to set
	 */
	protected void setEmail(String email) {
		this.email = email;
	}

	/**
	 * set the user telephone number
	 * @param telephoneNumber the new number to set
	 */
	protected void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	/**
	 * Set the user birthday
	 * @param birthday the new date to set
	 */
	protected void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	public String toString() {
		return	"ID: " + getId() + 
				"\nFirst Name: " + getFirstName() + 
				"\nLast Name: " + getLastName() +
				"\nEmail: " + getEmail() +
				"\nTelephone Number: " + getTelephoneNumber() +
				"\nBirthday: " + getBirthday();
	}
	

	
}
