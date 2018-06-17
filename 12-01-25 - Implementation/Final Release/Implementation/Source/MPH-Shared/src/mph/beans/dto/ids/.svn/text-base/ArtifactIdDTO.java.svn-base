package mph.beans.dto.ids;

/**
 * This class contains the values needed to identify an ArtifactDTO.<br/>
 * It implements the {@link IdentifierDTO} interface.
 */
final public class ArtifactIdDTO implements IdentifierDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TeamIdDTO teamId;
	private DeliverableIdDTO deliverableId;
	
	/**
	 * Create the id with the given arguments
	 * @param theTeamIdDTO the id of the team which has uploaded the artifact
	 * @param theDeliverableIdDTO the id of the deliverable which the artifact refers to
	 */
	public ArtifactIdDTO(TeamIdDTO theTeamIdDTO, DeliverableIdDTO theDeliverableIdDTO) {
		super();
		setDeliverableId(theDeliverableIdDTO);
		setTeamId(theTeamIdDTO);
	}
	
	/**
	 * Create a new instance of the id given as parameter.<br/>
	 * See {@link #ArtifactIdDTO(TeamIdDTO, DeliverableIdDTO)}.
	 * @param theArtifactId the id to put in the copy
	 */
	public ArtifactIdDTO(ArtifactIdDTO theArtifactId) {
		this(
				new TeamIdDTO(theArtifactId.getTeamId()), 
				new DeliverableIdDTO(theArtifactId.getDeliverableId())
			);	
	}

	/**
	 * @return the id of the team which has uploaded the artifact
	 */
	public TeamIdDTO getTeamId() {
		return teamId;
	}

	/**
	 * Set the team id
	 * @param teamId the team id to set
	 */
	private void setTeamId(TeamIdDTO teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the id of the deliverable referred by the artifact
	 */
	public DeliverableIdDTO getDeliverableId() {
		return deliverableId;
	}

	/**
	 * Set the deliverable id
	 * @param deliverableId the deliverable id to set
	 */
	private void setDeliverableId(DeliverableIdDTO deliverableId) {
		this.deliverableId = deliverableId;
	}
	
	public String toString() {
		return  " ["+getDeliverableId() + getTeamId()+ "] ";
	}
	
}
