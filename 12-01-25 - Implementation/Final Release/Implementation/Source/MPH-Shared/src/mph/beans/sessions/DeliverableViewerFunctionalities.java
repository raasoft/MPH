package mph.beans.sessions;

import java.util.Set;

import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provides methods to get information about project deliverables.
 */
interface DeliverableViewerFunctionalities {

	/**
	 * @param theProjectId the project id
	 * @return the set of deliverables composing the project given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<DeliverableIdDTO> getDeliverableSet(ProjectIdDTO theProjectId) throws InvalidArgumentException, UserNotLoggedInException;
	
	/**
	 * @param theDeliverableId the id of the deliverable
	 * @return the DTO containing the information about the deliverable given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public DeliverableDTO getDeliverableInfo(DeliverableIdDTO theDeliverableId) throws UserNotLoggedInException, InvalidArgumentException;
	
}
