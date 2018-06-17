package mph.beans.sessions;

import java.sql.Date;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UsernameAlreadyExistsException;

/**
 * This interface provides methods for the users to register to the MPH system.
 */
interface UserRegisteringFunctionalities {
	
	/**
	 * Register a new user with the given arguments
	 * @param theUsername the chosen username
	 * @param thePasswordHash the hash of the chosen password
	 * @param theFirstName the user first name
	 * @param theLastName the user last name
	 * @param theBirthday the user birthday
	 * @param theEmail the user email
	 * @param theTelephoneNumber the user telephone number
	 * @throws UsernameAlreadyExistsException if the username given as parameter already exists in the database
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 */
	public void registerNewUser(String theUsername, PasswordHashDTO thePasswordHash, String theFirstName,
			String theLastName, Date theBirthday, String theEmail,
			String theTelephoneNumber) throws UsernameAlreadyExistsException, InvalidArgumentException;
	
}