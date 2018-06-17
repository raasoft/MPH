package mph.beans.sessions;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.OutcomingFileDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UploadErrorException;
import mph.beans.exceptions.UserNotLoggedInException;


/**
 * This interface provides methods to upload artifacts.<br/>
 * It extends {@link UserEditingFunctionalities}.
 */
interface ArtifactEditingFunctionalities extends UserEditingFunctionalities {

	/**
	 * Upload an artifact to the Server and register it into the system
	 * @param theTeamId the id of the team uploading the artifact
	 * @param deliverableId the id of the deliverable referred by the artifact
	 * @param theArtifact the object containing the file to upload
	 * @return the id of the newly registered artifact
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws ForbiddenOperationException if the user is not allowed to execute this operation
	 * @throws UploadErrorException if an error occurs during the upload of the file
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public ArtifactIdDTO uploadArtifact(TeamIdDTO theTeamId, DeliverableIdDTO deliverableId, OutcomingFileDTO theArtifact) 
			throws InvalidArgumentException, ForbiddenOperationException,
			UploadErrorException, UserNotLoggedInException; 
	
	/**
	 * Get information about the artifact given as parameter
	 * @param theArtifactId the id of the artifact to get information from
	 * @return the DTO containing the artifact information
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public ArtifactDTO getArtifactInfo(ArtifactIdDTO theArtifactId) throws InvalidArgumentException, UserNotLoggedInException;

}