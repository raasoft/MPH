package mph.beans.dto.ids;

/**
 * This class contains the values needed to identify a CourseDTO.<br/>
 * It implements the {@link IdentifierDTO} interface.
 */
final public class CourseIdDTO implements IdentifierDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	/**
	 * Create the id with the given parameter
	 * @param theId the value of the identifier
	 */
	public CourseIdDTO(Long theId) {
		super();
		setId(theId);
	}
	
	/**
	 * Create a new instance of the id given as parameter.<br/>
	 * See {@link #CourseIdDTO(Long)}.
	 */
	CourseIdDTO(CourseIdDTO theCourseId) {
		this(new Long(theCourseId.getId()));
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Set the course id
	 * @param id the new id to set
	 */
	private void setId(Long id) {
		this.id = id;
	}
	
	public String toString() {
		return " [Course ID: " + getId() + "] ";
	}
}