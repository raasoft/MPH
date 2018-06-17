package mph.beans.dto.ids;

/**
 * This class contains the values needed to identify a ProjectDTO.<br/>
 * It implements the {@link IdentifierDTO} interface.
 */
final public class ProjectIdDTO implements IdentifierDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CourseIdDTO courseId;
	private String projectTitle;

	/**
	 * Create the id with the given arguments
	 * @param theCourseId the id of the course referred by the project
	 * @param theTitle the project title
	 */
	public ProjectIdDTO(CourseIdDTO theCourseId, String theTitle) {
		super();
		setCourseId(theCourseId);
		setProjectTitle(theTitle);
	}
	
	/**
	 * Create a new instance of the id given as parameter.<br/>
	 * See {@link #ProjectIdDTO(CourseIdDTO, String)}.
	 */
	public ProjectIdDTO(ProjectIdDTO theProjectId) {
		this(
				new CourseIdDTO(theProjectId.getCourseId()), 
				new String(theProjectId.getProjectTitle())
			);
	}

	/**
	 * @return the id of the course referred by the project
	 */
	public CourseIdDTO getCourseId() {
		return courseId;
	}

	/**
	 * Set the course id
	 * @param courseId the new id to set
	 */
	private void setCourseId(CourseIdDTO courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the project title
	 */
	public String getProjectTitle() {
		return projectTitle;
	}

	/**
	 * Set the project title
	 * @param projectTitle the new title to set
	 */
	private void setProjectTitle(String projectTitle) {
		this.projectTitle = projectTitle;
	}
	
	public String toString() {
		return  " ["+getCourseId() +
				"Project Title: " + getProjectTitle() + "] ";
	}
	
}