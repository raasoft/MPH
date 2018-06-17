package mph.beans.sessions;

import mph.beans.dto.UserDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;


/**
 * This interface provides methods to update the data of a user.
 */
interface UserEditingFunctionalities {
	
	/**
	 * Update the user information with the data contained in the DTO given as parameter
	 * @param theUpdatedUserDTO the DTO containing the updated user data
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	void updateUserInfo(UserDTO theUpdatedUserDTO) throws InvalidArgumentException, UserNotLoggedInException;
	
}