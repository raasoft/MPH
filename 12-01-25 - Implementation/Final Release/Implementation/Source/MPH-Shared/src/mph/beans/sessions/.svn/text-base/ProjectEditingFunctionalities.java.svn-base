package mph.beans.sessions;

import java.sql.Date;
import java.util.Set;

import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.DateInconsistencyException;
import mph.beans.exceptions.DeliverableNameAlreadyExists;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.ProjectNameAlreadyExists;
import mph.beans.exceptions.UserNotLoggedInException;

/**
 * This interface provide methods to publish a new project into the MPH system.
 */
interface ProjectEditingFunctionalities {

	/**
	 * Register a new project with the given arguments into the MPH system
	 * @param theCourseId the id of the course which contains the project to be published
	 * @param theProjectName the name of the project to be published
	 * @param theStartDate the start date of the project to be published 
	 * @param theEndDate the end date of the project to be published
	 * @param theDescription the description of the project to be published
	 * @param aDeliverableSet the set of deliverables composing the project to be published
	 * @return the id of the newly registered project
	 * @throws InvalidArgumentException if one of the arguments is not valid
	 * @throws UserNotLoggedInException if the user is not logged in
	 * @throws ProjectNameAlreadyExists if the project name given as parameter is already in use
	 * @throws DateInconsistencyException if the dates given as parameters are not consistent
	 * @throws DeliverableNameAlreadyExists if the set of deliverables contains duplicated names
	 * @throws ForbiddenOperationException if the user is not allowed to execute the operation
	 */
	public ProjectIdDTO registerNewProject(CourseIdDTO theCourseId, String theProjectName, Date theStartDate, Date theEndDate, String theDescription, Set<DeliverableDTO> aDeliverableSet) throws InvalidArgumentException, UserNotLoggedInException, ProjectNameAlreadyExists, DateInconsistencyException, DeliverableNameAlreadyExists, ForbiddenOperationException;
			
}
