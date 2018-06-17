package mph.beans.sessions;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.exceptions.AlreadyLoggedInException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.PasswordMismatchException;
import mph.beans.exceptions.UserDoesNotExistException;
import mph.beans.exceptions.UserNotLoggedInException;


/**
 * This interface provides methods which allow the MPH users to log in or log out from the system
 */
interface UserLoginFunctionalities {
	
	/**
	 * Log into the MPH system the user identified by the username given as parameter
	 * @param theUsername the username
	 * @param thePasswordHash the hash of the user password
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws UserDoesNotExistException if the username given as parameter is not registered into the system
	 * @throws PasswordMismatchException if the hash given as parameter does not match with the one saved into the database
	 */
	public void login(String theUsername, PasswordHashDTO thePasswordHash) throws InvalidArgumentException, AlreadyLoggedInException, UserDoesNotExistException, PasswordMismatchException;
	
	/**
	 * Log out a user from the system
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public void logout() throws UserNotLoggedInException;
	
}