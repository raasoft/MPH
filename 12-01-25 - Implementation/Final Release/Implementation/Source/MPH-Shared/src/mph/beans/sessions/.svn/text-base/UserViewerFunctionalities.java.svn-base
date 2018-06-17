package mph.beans.sessions;

import java.util.Set;

import mph.beans.dto.UserDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.UserIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provides method to get information about the users registered into the MPH system.
 */
interface UserViewerFunctionalities {
	
	/**
	 * @param theUserId the id of the user
	 * @return the DTO containing the information about the user given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public <T extends UserDTO> T getUserInfo(UserIdDTO theUserId) throws InvalidArgumentException, UserNotLoggedInException;
	
	/**
	 * @return the set of all professors registered into the MPH system
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<ProfessorIdDTO> getProfessorSet() throws UserNotLoggedInException;
	
	/**
	 * @return the set of all students registered into the MPH system
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<StudentIdDTO> getStudentSet() throws UserNotLoggedInException;
	
}