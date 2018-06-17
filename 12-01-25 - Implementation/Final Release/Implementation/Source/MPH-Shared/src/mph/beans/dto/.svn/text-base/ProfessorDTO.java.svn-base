package mph.beans.dto;

import java.sql.Date;

import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.exceptions.InvalidArgumentException;

/**
 * This class contains the information about a professor.<br/>
 * It extends {@link UserDTO}.
 */
public class ProfessorDTO extends UserDTO {
	
	protected ProfessorIdDTO id;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8321863975922964363L;

	/**
	 * See {@link UserDTO#UserDTO(String, String, String, String, Date)}
	 */
	public ProfessorDTO(ProfessorIdDTO theUserId, String theFirstName, String theLastName, String theEmail, String theTelephoneNumber, Date theBirthday) throws InvalidArgumentException {
		super(theFirstName, theLastName, theEmail, theTelephoneNumber, theBirthday);
		id = theUserId;
	}
	
	/**
	 * See {@link UserDTO#UserDTO(String, String, String, String, Date)}
	 */
	public ProfessorDTO(ProfessorDTO theProfessorDTO) throws InvalidArgumentException {
		super(theProfessorDTO.getFirstName(), theProfessorDTO.getLastName(), theProfessorDTO.getEmail(), theProfessorDTO.getTelephoneNumber(), theProfessorDTO.getBirthday());
	}

	@Override
	public ProfessorIdDTO getId() {
		return id;
	}
	
}
