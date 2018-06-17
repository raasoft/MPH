package mph.connection;

import java.sql.Date;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.CourseDTO;
import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.IncomingFileDTO;
import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.ProfessorDTO;
import mph.beans.dto.ProjectDTO;
import mph.beans.dto.TeamDTO;
import mph.beans.dto.UserDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.dto.ids.UserIdDTO;
import mph.beans.exceptions.AlreadyLoggedInException;
import mph.beans.exceptions.DateInconsistencyException;
import mph.beans.exceptions.DeliverableNameAlreadyExists;
import mph.beans.exceptions.DownloadErrorException;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.PasswordMismatchException;
import mph.beans.exceptions.ProjectNameAlreadyExists;
import mph.beans.exceptions.UserDoesNotExistException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.sessions.ProfessorManagerRemote;

/**
 * This singleton class initializes the local interface of the Professor and contains the methods of that interface, which are implemented by the Server.<br/>
 * It implements {@link ProfessorManagerRemote}.
 */
public class ProfessorManager implements ProfessorManagerRemote {
	
	private static ProfessorManager uniqueInstance = null;
	private ProfessorManagerRemote professor = null;
	
	/**
	 * @return the unique instance of the singleton
	 */
	public static ProfessorManager getInstance() {
	      if(uniqueInstance == null) {
	         uniqueInstance = new ProfessorManager();
	      }
	      return uniqueInstance;
	   }
	
	/**
	 * It invokes {@link ConnectionManager#setConnection()} to try to set up a connection with the Server.<br/>
	 * Initialize the local professor interface using {@link Context#lookup(String)}
	 * @throws NamingException if a connection error occurs during the lookup of the Server
	 */
	private void lookup() throws NamingException {

			ConnectionManager.getInstance().setConnection();
			professor = (ProfessorManagerRemote) ConnectionManager.getInstance().getJndiContext().lookup("ProfessorManager/remote");
	}

	/**
	 * Log in a professor.<br/>
	 * It invokes {@link #lookup()} to execute a lookup of the Server.<br/>
	 * It Invokes {@link ProfessorManagerRemote#login(String, PasswordHashDTO)}.
	 * @param theUsername of the professor
	 * @param thePassword inserted by the professor
	 * @throws InvalidArgumentException if a given argument is not valid
	 * @throws AlreadyLoggedInException if the professor is already logged in
	 * @throws UserDoesNotExistException if the username given as parameter does not exist
	 * @throws PasswordMismatchException if the password given as parameter does not match with the one stored in the database
	 * @throws NamingException if a connection error occurs
	 */
	public void login(String theUsername, String thePassword)
			throws InvalidArgumentException, AlreadyLoggedInException,
			UserDoesNotExistException, PasswordMismatchException, NamingException {
		
		lookup();
		
		PasswordHashDTO aPasswordHash = null;
		try {
			aPasswordHash = new PasswordHashDTO(thePassword);
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		
		login(theUsername.toLowerCase(), aPasswordHash);
	
	}

	@Override
	public void login(String theUsername, PasswordHashDTO thePasswordHash)
			throws InvalidArgumentException, AlreadyLoggedInException,
			UserDoesNotExistException, PasswordMismatchException {
		professor.login(theUsername, thePasswordHash);
	}

	@Override
	public <T extends UserDTO> T getUserInfo(UserIdDTO theUserId)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getUserInfo(theUserId);
	}

	@Override
	public Set<ProfessorIdDTO> getProfessorSet()
			throws UserNotLoggedInException {
		return professor.getProfessorSet();
	}

	@Override
	public Set<StudentIdDTO> getStudentSet() throws UserNotLoggedInException {
		return professor.getStudentSet();
	}

	/**
	 * Update the user information.<br/>
	 * It invokes {@link ProfessorManagerRemote#updateUserInfo(UserDTO)}
	 * @param theUserId of the professor logged in
	 * @param theFirstName the new value
	 * @param theLastName the new value
	 * @param theBirthday the new value
	 * @param theEmail the new value
	 * @param theTelephoneNumber the new value
	 * @throws InvalidArgumentException if a given argument is not valid
	 * @throws UserNotLoggedInException if the professor is not logged in
	 */
	public void updateUserInfo(UserIdDTO theUserId, String theFirstName,String theLastName, java.util.Date theBirthday, String theEmail, String theTelephoneNumber)
			throws InvalidArgumentException, UserNotLoggedInException {
		
		ProfessorDTO professorDTO = professor.getUserInfo(theUserId);
		String professorUsername = professorDTO.getId().getUsername();
		
		java.sql.Date birthdayDate = null;
		if (theBirthday != null) {
			birthdayDate = new java.sql.Date(theBirthday.getTime());
		}
		
		ProfessorDTO updatedProfessorDTO = new ProfessorDTO(new ProfessorIdDTO(professorUsername), theFirstName, theLastName, theEmail, theTelephoneNumber, birthdayDate);
		
		updateUserInfo(updatedProfessorDTO);
		
	}
	
	@Override
	public void updateUserInfo(UserDTO theUpdatedUserDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		professor.updateUserInfo(theUpdatedUserDTO);
	}

	@Override
	public Set<CourseIdDTO> getCourseSet() throws UserNotLoggedInException {
		return professor.getCourseSet();
	}

	@Override
	public Set<CourseIdDTO> getCourseSet(ProfessorIdDTO theProfessorIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getCourseSet(theProfessorIdDTO);
	}

	@Override
	public CourseDTO getCourseInfo(CourseIdDTO theCourseIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getCourseInfo(theCourseIdDTO);
	}

	@Override
	public Set<ProjectIdDTO> getProjectSet(CourseIdDTO theCourse)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getProjectSet(theCourse);
	}

	@Override
	public Set<ProjectIdDTO> getEnrolledProjectSet(StudentIdDTO theStudentIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getEnrolledProjectSet(theStudentIdDTO);
	}

	@Override
	public ProjectDTO getProjectInfo(ProjectIdDTO theProjectId)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getProjectInfo(theProjectId);
	}

	@Override
	public Set<DeliverableIdDTO> getDeliverableSet(ProjectIdDTO theProjectId)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getDeliverableSet(theProjectId);
	}

	@Override
	public DeliverableDTO getDeliverableInfo(DeliverableIdDTO theDeliverableId)
			throws UserNotLoggedInException, InvalidArgumentException {
		return professor.getDeliverableInfo(theDeliverableId);
	}

	@Override
	public IncomingFileDTO downloadArtifact(ArtifactIdDTO theArtifactId)
			throws InvalidArgumentException, UserNotLoggedInException, DownloadErrorException {
		return professor.downloadArtifact(theArtifactId);
	}

	@Override
	public Set<TeamIdDTO> getTeamSet(ProjectIdDTO theProjectIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getTeamSet(theProjectIdDTO);
	}

	@Override
	public TeamDTO getTeamInfo(TeamIdDTO theTeamIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getTeamInfo(theTeamIdDTO);
	}

	@Override
	public Set<StudentIdDTO> getStudentComposingTeam(TeamIdDTO theTeamIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getStudentComposingTeam(theTeamIdDTO);
	}

	@Override
	public TeamIdDTO getProjectTeam(StudentIdDTO theStudentIdDTO,
			ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException,
			UserNotLoggedInException {
		return professor.getProjectTeam(theStudentIdDTO, theProjectIdDTO);
	}
	
	@Override
	public void setArtifactScore(ArtifactIdDTO theArtifactId, Double theScore)
			throws InvalidArgumentException, ForbiddenOperationException, UserNotLoggedInException {
		professor.setArtifactScore(theArtifactId, theScore);
	}

	/**
	 * Publish a new project.<br/>
	 * It invokes {@link ProfessorManager#registerNewProject(CourseIdDTO, String, Date, Date, String, Set)}
	 * @param theCourseId of the course which the project refers to
	 * @param theProjectName the name of the new project
	 * @param theStartDate of the project
	 * @param theEndDate of the project
	 * @param theDescription of the new project
	 * @param aDeliverableSet a set of deliverables
	 * @return the newly published project id
	 * @throws InvalidArgumentException if a given argument is not valid
	 * @throws UserNotLoggedInException if the professor is not logged in
	 * @throws ProjectNameAlreadyExists if the name chosen for the project already exists
	 * @throws DateInconsistencyException if there is inconsistency with the given dates
	 * @throws DeliverableNameAlreadyExists if the given set of deliverables contains deliverable names which exist already
	 * @throws ForbiddenOperationException if the professor is not allowed to execute this operation
	 */
	public ProjectIdDTO publishNewProject(CourseIdDTO theCourseId,
			String theProjectName, java.util.Date theStartDate, java.util.Date theEndDate,
			String theDescription, Set<DeliverableDTO> aDeliverableSet)
			throws InvalidArgumentException, UserNotLoggedInException,
			ProjectNameAlreadyExists, DateInconsistencyException,
			DeliverableNameAlreadyExists, ForbiddenOperationException {
		
		java.sql.Date startDate = null;
		if (theStartDate != null) {
			startDate= new java.sql.Date(theStartDate.getTime());
		}
		
		java.sql.Date endDate = null;
		if (theEndDate != null) {
			endDate= new java.sql.Date(theEndDate.getTime());
		}
		
		return registerNewProject(theCourseId, theProjectName, startDate, endDate, theDescription, aDeliverableSet);
	}
	
	@Override
	public ProjectIdDTO registerNewProject(CourseIdDTO theCourseId,
			String theProjectName, Date theStartDate, Date theEndDate,
			String theDescription, Set<DeliverableDTO> aDeliverableSet)
			throws InvalidArgumentException, UserNotLoggedInException,
			ProjectNameAlreadyExists, DateInconsistencyException,
			DeliverableNameAlreadyExists, ForbiddenOperationException {
		return professor.registerNewProject(theCourseId, theProjectName, theStartDate, theEndDate, theDescription, aDeliverableSet);
	}

	@Override
	public ArtifactIdDTO getArtifact(DeliverableIdDTO theDeliverableId,
			TeamIdDTO theTeamIdDTO) throws InvalidArgumentException,
			UserNotLoggedInException {
		return professor.getArtifact(theDeliverableId, theTeamIdDTO);
	}

	@Override
	public ArtifactDTO getArtifactInfo(ArtifactIdDTO theArtifactId)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getArtifactInfo(theArtifactId);
	}

	@Override
	public Set<ArtifactIdDTO> getArtifactSetByDeliverable(
			DeliverableIdDTO theDeliverableId) throws InvalidArgumentException,
			UserNotLoggedInException {
		return professor.getArtifactSetByDeliverable(theDeliverableId);
	}

	@Override
	public Set<ArtifactIdDTO> getArtifactSetByTeam(TeamIdDTO theTeamId)
			throws InvalidArgumentException, UserNotLoggedInException {
		return professor.getArtifactSetByTeam(theTeamId);
	}

	@Override
	public void logout() throws UserNotLoggedInException {
		professor.logout();
	}

	
}
