package mph.beans.sessions;

import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;


/**
 * This interface provides methods to evaluate the artifacts registered into the MPH system.<br/>
 * It extends {@link UserEditingFunctionalities}.
 */
interface ArtifactEvaluatorFunctionalities extends UserEditingFunctionalities {
	
	/**
	 * Set the score to the artifact given as parameter
	 * @param theArtifactId the id of the artifact to be evaluated
	 * @param theScore the desired score
	 * @throws ForbiddenOperationException if the user is not allowed to execute this operation
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public void setArtifactScore(ArtifactIdDTO theArtifactId, Double theScore) throws ForbiddenOperationException, InvalidArgumentException, UserNotLoggedInException; 
	
}