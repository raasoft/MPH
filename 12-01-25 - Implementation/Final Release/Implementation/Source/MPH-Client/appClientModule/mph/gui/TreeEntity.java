package mph.gui;

import mph.beans.dto.EntityDTO;

/**
 * Abstract class used to print a 'nice' name for the entities displayed in the JTree.
 */
public abstract class TreeEntity {
	
	protected EntityDTO dto = null;
	protected boolean isProfessor = false;
	protected boolean isCourse = false;
	protected boolean isProject= false;
	
	/**
	 * @param theDTO the entity object to store
	 */
	protected TreeEntity(EntityDTO theDTO) {
		this.dto = theDTO;
	}

	/**
	 * @return true if node is a Professor
	 */
	public boolean isProfessor() {
		return isProfessor;
	}


	/**
	 * @return true if node is a Course
	 */
	public boolean isCourse() {
		return isCourse;
	}


	/**
	 * @return true if node is a Project
	 */
	public boolean isProject() {
		return isProject;
	}	
}
