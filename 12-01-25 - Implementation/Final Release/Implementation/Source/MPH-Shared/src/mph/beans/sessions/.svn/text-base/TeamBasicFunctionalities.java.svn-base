package mph.beans.sessions;

import java.util.Set;

import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provides basic methods for the user to interact with a project team.
 */
interface TeamBasicFunctionalities {

	/**
	 * Join the team given as parameter
	 * @param theTeamId the id of the team to join
	 * @throws ForbiddenOperationException if the user is not allowed to execute the operation
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public void joinTeam(TeamIdDTO theTeamId) throws ForbiddenOperationException, InvalidArgumentException, UserNotLoggedInException;
	
	/**
	 * Leave the team given as parameter
	 * @param theTeamId the id of the team to join
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws ForbiddenOperationException if the user is not allowed to execute the operation
	 */
	public void leaveTeam(TeamIdDTO theTeamId) throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException;
	
	/**
	 * Remove the membership request given as parameter
	 * @param theJoinRequestId the id of the request to remove
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws ForbiddenOperationException if the user is not allowed to execute the operation
	 */
	public void removePendingJoinRequest(JoinRequestIdDTO theJoinRequestId) throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException;
	
	/**
	 * @param theProjectIdDTO the id of the project
	 * @return the set of membership requests for all teams belonging to the project given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<JoinRequestIdDTO> getPendingJoinRequestSet(ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException, UserNotLoggedInException;
		
}
