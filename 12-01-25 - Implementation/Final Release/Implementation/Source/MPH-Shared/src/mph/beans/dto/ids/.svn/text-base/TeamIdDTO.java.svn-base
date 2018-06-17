package mph.beans.dto.ids;



/**
 * This class contains the values needed to identify a TeamDTO.<br/>
 * It implements the {@link IdentifierDTO} interface.
 */
final public class TeamIdDTO implements IdentifierDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ProjectIdDTO projectId;
	private String teamName;


	/**
	 * Create the id with the given arguments
	 * @param theProjectId the id of the project referred by the team
	 * @param theName the team name
	 */
	public TeamIdDTO(ProjectIdDTO theProjectId, String theName) {
		super();
		setProjectId(theProjectId);
		setTeamName(theName);
	}

	/**
	 * Create a new instance of the id given as parameter.<br/>
	 * See {@link #TeamIdDTO(ProjectIdDTO, String)}.
	 */
	public TeamIdDTO(TeamIdDTO theTeamId) {
		this(new ProjectIdDTO(theTeamId.getProjectId()), new String(theTeamId.getTeamName()));				
	}

	/**
	 * @return the id of the project referred by the team
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
	 * @return the team name
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * Set the team name
	 * @param teamName the new name to set
	 */
	private void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String toString() {
		return  " [" + getProjectId() +
				" Team Name: " + getTeamName() + "] ";
	}
	
}