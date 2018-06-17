package mph.gui;

import mph.beans.dto.ProjectDTO;

/**
 * Used to print a short name for the projects displayed in the JTree.<br/>
 * It extends {@link TreeEntity}.
 */
public class TreeProject extends TreeEntity {

	/**
	 * See {@link TreeEntity#TreeEntity(mph.beans.dto.EntityDTO)}
	 */
	public TreeProject(ProjectDTO theDTO) {
		super(theDTO);
		isProject = true;
	}

	/**
	 * @return the project object
	 */
	public ProjectDTO getDTO() {
		return (ProjectDTO) dto;
	}	
	
	public String toString() {
		return ((ProjectDTO) dto).getId().getProjectTitle();
	}
}
