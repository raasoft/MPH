package mph.beans.sessions;

import java.util.Set;

import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provides methods to manage a project team.
 */
interface TeamManagementFunctionalities {

	/**
	 * @param theTeamId the id of the team
	 * @return the set of incoming membership requests for the project team given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<JoinRequestIdDTO> getPendingJoinRequests(TeamIdDTO theTeamId) throws InvalidArgumentException, UserNotLoggedInException;
	
	/**
	 * Set the possibility of a project team to accept new incoming membership requests
	 * @param theTeamId the id of the team
	 * @param areRequestsClosed true means the project team given as parameter will not accept incoming requests
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws ForbiddenOperationException if the user is not allowed to execute the operation
	 */
	public void setTeamEnrollmentRequests(TeamIdDTO theTeamId, boolean areRequestsClosed) throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException;
	
	/**
	 * Accept the join request given as parameter
	 * @param theJoinRequestId the id of the request
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws ForbiddenOperationException if the user is not allowed to execute the operation
	 */
	public void acceptPendingJoinRequest(JoinRequestIdDTO theJoinRequestId) throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException;
	
	/**
	 * Decline the join request given as parameter
	 * @param theJoinRequestId the id of the request
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws ForbiddenOperationException if the user is not allowed to execute the operation
	 */
	public void declinePendingJoinRequest(JoinRequestIdDTO theJoinRequestId) throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException;

				
}
