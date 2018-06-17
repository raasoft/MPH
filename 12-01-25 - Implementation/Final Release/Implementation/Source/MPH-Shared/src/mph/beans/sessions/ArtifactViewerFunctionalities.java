package mph.beans.sessions;

import java.util.Set;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.IncomingFileDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.DownloadErrorException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;


/**
 * This interface provides methods to get information about artifacts and to download them.<br/>
 * It extends {@link UserEditingFunctionalities}.
 */
interface ArtifactViewerFunctionalities extends UserEditingFunctionalities {
	
	/**
	 * @param theDeliverableId the id of the deliverable referred by the artifact
	 * @param theTeamIdDTO the id of the team which delivered the artifact
	 * @return the id of the artifact delivered by the team for the deliverable given as parameter 
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public ArtifactIdDTO getArtifact(DeliverableIdDTO theDeliverableId, TeamIdDTO theTeamIdDTO) throws InvalidArgumentException, UserNotLoggedInException;

	/**
	 * @param theDeliverableId the id of the project deliverable
	 * @return a set of artifacts corresponding to the deliverable given as parameter
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<ArtifactIdDTO> getArtifactSetByDeliverable(DeliverableIdDTO theDeliverableId) throws InvalidArgumentException, UserNotLoggedInException;
	
	/**
	 * @param theTeamId the id of the project team
	 * @return a set of artifacts delivered by the team given as parameter
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<ArtifactIdDTO> getArtifactSetByTeam(TeamIdDTO theTeamId) throws InvalidArgumentException, UserNotLoggedInException; 
	
	/**
	 * Download the artifact given as parameter
	 * @param theArtifactId the id of the artifact to be downloaded
	 * @return the DTO containing the file
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws DownloadErrorException if an error occurs during the download of the file
	 */
	public IncomingFileDTO downloadArtifact(ArtifactIdDTO theArtifactId) throws InvalidArgumentException, UserNotLoggedInException, DownloadErrorException;
	
	/**
	 * @param theArtifactId the id of the artifact
	 * @return the DTO containing the artifact info
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public ArtifactDTO getArtifactInfo(ArtifactIdDTO theArtifactId) throws InvalidArgumentException, UserNotLoggedInException;

}
