package mph.beans.dto;

import java.sql.Date;

import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.exceptions.InvalidArgumentException;

/**
 * This class contains the information about a student.<br/>
 * It extends {@link UserDTO}.
 */
public class StudentDTO extends UserDTO {
	
	protected StudentIdDTO id;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8321863975922964363L;

	/**
	 * See {@link UserDTO#UserDTO(String, String, String, String, Date)}
	 */
	public StudentDTO(StudentIdDTO theStudentId, String theFirstName, String theLastName, String theEmail, String theTelephoneNumber, Date theBirthday) throws InvalidArgumentException {
		super(theFirstName, theLastName, theEmail, theTelephoneNumber, theBirthday);
		id = theStudentId;
	}

	@Override
	public StudentIdDTO getId() {
		return id;
	}
	
}
