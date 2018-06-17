package mph.beans.sessions;

import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.TeamNameAlreadyExists;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provides methods to create a new project team into the MPH system.
 */
interface TeamCreationFunctionalities {

	/**
	 * Create a new project team
	 * @param theProjectId the id of the project referred by the team
	 * @param theTeamName the name of the team to create
	 * @return the id of the newly created team
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws TeamNameAlreadyExists if the name given as parameter is already in use
	 * @throws ForbiddenOperationException if the user is not allowed to execute the operation
	 */
	public TeamIdDTO createTeam(ProjectIdDTO theProjectId, String theTeamName) throws InvalidArgumentException, UserNotLoggedInException, TeamNameAlreadyExists, ForbiddenOperationException;

}
