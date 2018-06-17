package com.raaxxo.mph.sessions.local;

import java.sql.Date;
import java.util.Set;

import javax.ejb.Local;

import com.raaxxo.mph.entities.User;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.UserDTO;
import mph.beans.dto.ids.UserIdDTO;
import mph.beans.exceptions.AlreadyLoggedInException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.PasswordMismatchException;
import mph.beans.exceptions.UserDoesNotExistException;
import mph.beans.exceptions.UsernameAlreadyExistsException;
import util.HashUtility;



/**
 * 
 * Local interface that manages the {@link User} entities.
 * 
 */
@Local
public interface UserManagerLocal {
	
	public void registerNewUser(UserFactoryLocal theUserFactory, String theUsername, PasswordHashDTO thePasswordHash, String theFirstName,
			String theLastName, Date theBirthday, String theEmail,
			String theTelephoneNumber) throws UsernameAlreadyExistsException, InvalidArgumentException;
	
	/**
	 * The method login let a user to login into the program.<br>
	 * The user name is a non-null and not-empty string used to identify the
	 * player and log the player in.
	 * 
	 * The password is a PasswordHashDTO object, that contains the MD5 hash created with the method MD5 of the class {@link HashUtility}.
	 * The real password is NEVER passed to this function for security purposes.
	 * 
	 * @param theUsername
	 *            is the username of the user
	 * @param thePasswordHash
	 *            is the MD5 hash of the user's password
	 * @throws IllegalArgumentException
	 *             is thrown if theUsername is empty or null
	 * 
	 */
	public void login(String theUsername, PasswordHashDTO thePasswordHash,Class<? extends User> theUserType)
			throws InvalidArgumentException, AlreadyLoggedInException, UserDoesNotExistException, PasswordMismatchException;
	
	public <T extends User> T getEntity(Class<T> theUserType, String theUserId) throws InvalidArgumentException;
	public <T extends User> T getEntity(Class<T> theUserType, UserIdDTO theUserIdDTO) throws InvalidArgumentException;
	
	public <T extends User> boolean userExists(Class<T> theUserType, String theUsername) throws InvalidArgumentException;
	public <T extends User> void checkUserExists(Class<T> theUserType, String theUsername) throws InvalidArgumentException;

	
	public <T extends UserDTO> T getUserInfo(UserIdDTO theUserIdDTO) throws InvalidArgumentException;
	public void updateUserInfo(UserDTO theUpdatedUserInfoDTO) throws InvalidArgumentException;
	
	public <T extends User, W extends UserIdDTO> Set<W> getUserIdDTOSet(Class<T> theUserType) throws InvalidArgumentException;

}
