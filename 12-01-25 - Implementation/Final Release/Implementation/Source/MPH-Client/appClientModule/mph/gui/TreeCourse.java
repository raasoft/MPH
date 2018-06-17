package mph.gui;

import mph.beans.dto.CourseDTO;

/** 
 * Used to print a short name for the courses displayed in the JTree.<br/>
 * It extends {@link TreeEntity}.
 */
public class TreeCourse extends TreeEntity {

	/**
	 * See {@link TreeEntity#TreeEntity(mph.beans.dto.EntityDTO)}
	 */
	public TreeCourse(CourseDTO theDTO) {
		super(theDTO);
		isCourse = true;
	}
	
	/**
	 * @return the course object
	 */
	public CourseDTO getDTO() {
		return (CourseDTO) dto;
	}	
	
	public String toString() {
		return ((CourseDTO) dto).getName();
	}
}
