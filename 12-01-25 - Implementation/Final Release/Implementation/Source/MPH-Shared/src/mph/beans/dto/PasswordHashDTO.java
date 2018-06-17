package mph.beans.dto;

import mph.beans.exceptions.InvalidArgumentException;
import util.HashUtility;
import util.StringUtility;

/**
 * This class is used to store the MD5 hash of a password.<br/>
 * It implements the {@link DataTransferObject} interface.
 */
public class PasswordHashDTO implements DataTransferObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2034469705163246044L;
	
	public static final int PASSWORD_MAX_LENGTH = 30;
	
	private String theHash;
	
	/**
	 * Create the DTO with the given argument
	 * @param thePassword the password to be hashed
	 * @throws InvalidArgumentException if the password given as parameter is too long
	 */
	public PasswordHashDTO(String thePassword) throws InvalidArgumentException {
		
		StringUtility.isNullOrEmpty(thePassword, "Password");
		
		if (thePassword.length() > PASSWORD_MAX_LENGTH)
			throw new InvalidArgumentException("The password length is too high. Only password of maximum " + PASSWORD_MAX_LENGTH + " characters are allowed");
		
		theHash = HashUtility.MD5(thePassword);
		
	}
	
		public String toString() {
		return theHash;
	}

}
