package mph.beans.sessions;

import java.util.Set;

import mph.beans.dto.ProjectDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provides methods to get information about projects.
 */
interface ProjectViewerFunctionalities {

	/**
	 * @param theCourse the id of the course
	 * @return the set of projects enrolled in the course given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public  Set<ProjectIdDTO> getProjectSet(CourseIdDTO theCourse) throws InvalidArgumentException, UserNotLoggedInException;

	/**
	 * @param theStudentIdDTO the id of the student
	 * @return the set of projects the student given as parameter is enrolled in
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public  Set<ProjectIdDTO> getEnrolledProjectSet(StudentIdDTO theStudentIdDTO) throws InvalidArgumentException, UserNotLoggedInException;

	/**
	 * @param theProjectId the id of the project
	 * @return the DTO containing the information about the project given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public ProjectDTO getProjectInfo(ProjectIdDTO theProjectId) throws InvalidArgumentException, UserNotLoggedInException;	
				
}

