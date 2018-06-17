package mph.beans.dto.ids;


/**
 * This class contains the values needed to identify a DeliverableDTO.<br/>
 * It implements the {@link IdentifierDTO} interface.
 */
final public class DeliverableIdDTO implements IdentifierDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ProjectIdDTO projectId;
	private String deliverableName;

	/**
	 * Create the id with the given arguments
	 * @param theProjectId the id of the project referred by the deliverable
	 * @param theName the name of the deliverable
	 */
	public DeliverableIdDTO(ProjectIdDTO theProjectId, String theName) {
		super();
			
		setProjectId(theProjectId);
		setDeliverableName(theName);
	}
	
	/**
	 * Create a new instance of the id given as parameter.<br/>
	 * See {@link #DeliverableIdDTO(ProjectIdDTO, String)}.
	 */
	public DeliverableIdDTO(DeliverableIdDTO theDeliverableId) {
		this(
				new ProjectIdDTO(theDeliverableId.getProjectId()), 
				new String(theDeliverableId.getDeliverableName())
			);	
	}

	/**
	 * @return the id of the project referred by the deliverable
	 */
	public ProjectIdDTO getProjectId() {
		return projectId;
	}

	/**
	 * Set the project id
	 * @param projectId the new id to set
	 */
	private void setProjectId(ProjectIdDTO projectId) {
		this.projectId = projectId;
	}

	/**
	 * @return the deliverable name
	 */
	public String getDeliverableName() {
		return deliverableName;
	}

	/**
	 * Set the deliverable name
	 * @param deliverableName the new name to set
	 */
	private void setDeliverableName(String deliverableName) {
		this.deliverableName = deliverableName;
	}

	public String toString() {
		return  " ["+getProjectId() +
				"Deliverable Name: " + getDeliverableName()+ "] ";
	}
	
}
