package mph.beans.dto;

import java.util.HashSet;
import java.util.Set;

import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;

/**
 * This class contains the information about a course.<br/>
 * It implements the {@link EntityDTO} interface.
 */
final public class CourseDTO implements EntityDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4201380790806041285L;
	
	private CourseIdDTO uid;
	private String name;
	private ProfessorIdDTO professorId;
	private Set<ProjectIdDTO> projectIdSet = new HashSet<ProjectIdDTO>();
	private String description;
	
	
	
	public CourseIdDTO getId() {
		return uid;
	}



	/**
	 * @return the course name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @return the id of the professor holding the course
	 */
	public ProfessorIdDTO getProfessorId() {
		return professorId;
	}



	/**
	 * @return the set of project id's enrolled in the project
	 */
	public Set<ProjectIdDTO> getProjectIdSet() {
		return projectIdSet;
	}



	/**
	 * @return the course description
	 */
	public String getDescription() {
		return description;
	}

	


	/**
	 * Set the unique id
	 * @param uid the new id to set
	 */
	private void setId(CourseIdDTO uid) {
		this.uid = uid;
	}



	/**
	 * Set the course name
	 * @param name the new name to set
	 */
	private void setName(String name) {
		this.name = name;
	}



	/**
	 * Set the id of the professor holding the course
	 * @param professorId the id of the professor to set
	 */
	private void setProfessorId(ProfessorIdDTO professorId) {
		this.professorId = professorId;
	}



	/**
	 * Add a set of projects into the course 
	 * @param theProjectIdSet the set of project id's to add
	 */
	private void setProjectIdSet(Set<ProjectIdDTO> theProjectIdSet) {
		
		projectIdSet.clear();
		
		for (ProjectIdDTO id : theProjectIdSet) {
			projectIdSet.add(id);
		}
		
	}



	/**
	 * Set the course description
	 * @param description the new description to set
	 */
	private void setDescription(String description) {
		this.description = description;
	}



	/**
	 * Creates a course DTO with the given arguments
	 * @param theCourseUid the unique id of the course
	 * @param theName the course name
	 * @param theProfessorId the id of the professor holdin the course
	 * @param theProjectIdList the list of projects enrolled in the course
	 * @param theDescription the course description
	 */
	public CourseDTO(CourseIdDTO theCourseUid, String theName, ProfessorIdDTO theProfessorId, Set<ProjectIdDTO> theProjectIdList, String theDescription) {
		
		setId(theCourseUid);
		setName(theName);
		setProfessorId(theProfessorId);
		setProjectIdSet(theProjectIdList);
		setDescription(theDescription);
		
	}
	
	public String toString() {
		return  getId() +
				"\nName: " + getName() +
				"\n" + getProfessorId() +
				"\nProject ID Set: " + getProjectIdSet() +
				"\nDescription : " + getDescription();
	}
	
}
