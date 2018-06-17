package mph.beans.dto.ids;

/**
 * This class contains the values needed to identify a ProfessorDTO.<br/>
 * It extends {@link UserIdDTO}.
 */
final public class ProfessorIdDTO extends UserIdDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * See {@link UserIdDTO#UserIdDTO(String)}
	 */
	public ProfessorIdDTO(String theUsername) {
		super(theUsername);
	}
	
	/**
	 * @param theUsername
	 * @return the professor id which refers to the username given as parameter
	 */
	public static ProfessorIdDTO getUser(String theUsername) {
		return new ProfessorIdDTO(theUsername);
	}
	
	
	public String toString() {
		return "[Professor Username: " + getUsername()+"]";
	}

}
