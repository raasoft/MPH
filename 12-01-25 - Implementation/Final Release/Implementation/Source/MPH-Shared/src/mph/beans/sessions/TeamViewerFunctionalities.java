package mph.beans.sessions;

import java.util.Set;

import mph.beans.dto.TeamDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provides methods to get information about project teams.
 */
interface TeamViewerFunctionalities {

	/**
	 * @param theProjectIdDTO the project id
	 * @return the set of teams enrolled in the project given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<TeamIdDTO> getTeamSet(ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException, UserNotLoggedInException;
	
	/**
	 * @param theTeamIdDTO the id of the team
	 * @return the DTO containing the information about the project team given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public TeamDTO getTeamInfo(TeamIdDTO theTeamIdDTO) throws InvalidArgumentException, UserNotLoggedInException;
	
	/**
	 * @param theTeamIdDTO the team id
	 * @return the set of students enrolled in the project team given as parameter
	 * @throws InvalidArgumentException if the argument is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public Set<StudentIdDTO> getStudentComposingTeam(TeamIdDTO theTeamIdDTO) throws InvalidArgumentException, UserNotLoggedInException;

	/**
	 * @param theStudentIdDTO the id of the student
	 * @param theProjectIdDTO the project id
	 * @return the id of the team which the given student belongs to for the project given as parameter
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 */
	public TeamIdDTO getProjectTeam(StudentIdDTO theStudentIdDTO, ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException, UserNotLoggedInException;
}
