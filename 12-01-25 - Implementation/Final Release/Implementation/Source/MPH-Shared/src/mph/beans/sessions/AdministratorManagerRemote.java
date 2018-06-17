package mph.beans.sessions;

import javax.ejb.Remote;

import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.exceptions.InvalidArgumentException;

/**
 * Remote interface which contains methods available to the system administrator.<br/>
 * It extends {@link UserRegisteringFunctionalities}.
 */
@Remote
public interface AdministratorManagerRemote  extends UserRegisteringFunctionalities {
		  
		/**
		 * Add a new course to the MPH system
		 * @param theProfessorId the id of the professor holding the course
		 * @param theName the course name
		 * @param theDescription the course description
		 * @return the id of the newly added course
		 * @throws InvalidArgumentException if one of the given parameters is not valid
		 */
		CourseIdDTO newCourse(ProfessorIdDTO theProfessorId, String theName, String theDescription) throws InvalidArgumentException;
	
}