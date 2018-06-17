package mph.beans.dto.ids;

/**
 * This class contains the values needed to identify a StudentDTO.<br/>
 * It extends {@link UserIdDTO}.
 */
final public class StudentIdDTO extends UserIdDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * See {@link UserIdDTO#UserIdDTO(String)}
	 */
	public StudentIdDTO(String theUsername) {
		super(theUsername);

	}
	
	/**
	 * See {@link UserIdDTO#UserIdDTO(UserIdDTO)}
	 */
	public StudentIdDTO(StudentIdDTO theStudentIdDTO) {
		super(theStudentIdDTO);

	}
	
	/** 
	 * @param theUsername
	 * @return the student id which refers to the username given as parameter
	 */
	public static StudentIdDTO getUser(String theUsername) {
		return new StudentIdDTO(theUsername);
	}
	
	public String toString() {
		return "[Student Username: " + getUsername()+"]";
	}


}
