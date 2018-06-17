package mph.beans.dto.ids;


/**
 * This class contains the values needed to identify a JoinRequestDTO.<br/>
 * It implements the {@link IdentifierDTO} interface.
 */
final public class JoinRequestIdDTO implements IdentifierDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TeamIdDTO teamId;
	private StudentIdDTO studentId;
	
	/**
	 * Create the id wit the given arguments
	 * @param theTeamId the id of the team referred by the request
	 * @param theStudentId the id of the student requesting the membership
	 */
	public JoinRequestIdDTO(TeamIdDTO theTeamId, StudentIdDTO theStudentId) {
		super();
		setTeamId(theTeamId);
		setStudentId(theStudentId);
	}

	
	/**
	 * Create a new instance of the id given as parameter.<br/>
	 * See {@link #JoinRequestIdDTO(TeamIdDTO, StudentIdDTO)}.
	 */
	public JoinRequestIdDTO(JoinRequestIdDTO theJoinRequestId) {
		this(
				new TeamIdDTO(theJoinRequestId.getTeamId()), 
				new StudentIdDTO(theJoinRequestId.getStudentId())
			);
	}


	/**
	 * @return the id of the team referred by the request
	 */
	public TeamIdDTO getTeamId() {
		return teamId;
	}


	/**
	 * Set the team id
	 * @param teamId the new id to set
	 */
	private void setTeamId(TeamIdDTO teamId) {
		this.teamId = teamId;
	}


	/**
	 * @return the id of the student requesting the membership
	 */
	public StudentIdDTO getStudentId() {
		return studentId;
	}


	/**
	 * Set the student id
	 * @param studentId the new id to set
	 */
	private void setStudentId(StudentIdDTO studentId) {
		this.studentId = studentId;
	}
	
	
}
