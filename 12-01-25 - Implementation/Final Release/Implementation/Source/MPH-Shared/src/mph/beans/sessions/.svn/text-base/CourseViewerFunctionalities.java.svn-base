package mph.beans.sessions;

import java.util.Set;

import mph.beans.dto.CourseDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provides methods to get information about courses. 
 */
interface CourseViewerFunctionalities {
	
	/**
	 * @return the set of courses registered into the MPH system
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<CourseIdDTO> getCourseSet()  throws UserNotLoggedInException;
	
	/**
	 * @param theProfessorIdDTO the id of the professor holding the course
	 * @return the set of courses hold by the professor given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<CourseIdDTO> getCourseSet(ProfessorIdDTO theProfessorIdDTO)  throws InvalidArgumentException ,UserNotLoggedInException;
	
	/**
	 * @param theCourseIdDTO the course id
	 * @return the DTO containing the information about the course given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public CourseDTO getCourseInfo(CourseIdDTO theCourseIdDTO) throws InvalidArgumentException, UserNotLoggedInException;
				
}
