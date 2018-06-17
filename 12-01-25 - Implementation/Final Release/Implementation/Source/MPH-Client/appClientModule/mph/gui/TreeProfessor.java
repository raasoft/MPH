package mph.gui;

import mph.beans.dto.ProfessorDTO;

/**
 * Used to print a short name for the professors displayed in the JTree.<br/>
 * It extends {@link TreeEntity}.
 */
public class TreeProfessor extends TreeEntity {
	
	/**
	 * See {@link TreeEntity#TreeEntity(mph.beans.dto.EntityDTO)}
	 */
	public TreeProfessor(ProfessorDTO theDTO) {
		super(theDTO);
		isProfessor = true;
	}
	
	/**
	 * @return the professor object
	 */
	public ProfessorDTO getDTO() {
		return (ProfessorDTO) dto;
	}

	public String toString() {
		return ((ProfessorDTO) dto).getFirstName() + " " + ((ProfessorDTO) dto).getLastName();	
	}
}
