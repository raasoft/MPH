package mph.connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.Set;

import javax.naming.Context;
import javax.naming.NamingException;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.CourseDTO;
import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.IncomingFileDTO;
import mph.beans.dto.OutcomingFileDTO;
import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.ProjectDTO;
import mph.beans.dto.StudentDTO;
import mph.beans.dto.TeamDTO;
import mph.beans.dto.UserDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.dto.ids.UserIdDTO;
import mph.beans.exceptions.AlreadyLoggedInException;
import mph.beans.exceptions.DownloadErrorException;
import mph.beans.exceptions.FileTooLargeException;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.PasswordMismatchException;
import mph.beans.exceptions.TeamNameAlreadyExists;
import mph.beans.exceptions.UploadErrorException;
import mph.beans.exceptions.UserDoesNotExistException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.exceptions.UsernameAlreadyExistsException;
import mph.beans.sessions.StudentManagerRemote;

/**
 * This class initializes the local interface of the Student and contains the methods of that interface, which are implemented by the Server.<br/>
 * It implements {@link StudentManagerRemote}.
 */
public class StudentManager implements StudentManagerRemote {
	
	private static StudentManager uniqueInstance = null;
	private StudentManagerRemote student = null;
	
	/**
	 * @return the unique instance of the singleton
	 */
	public static StudentManager getInstance() {
	      if(uniqueInstance == null) {
	         uniqueInstance = new StudentManager();
	      }
	      return uniqueInstance;
	   }
	
	/**
	 * It invokes {@link ConnectionManager#setConnection()} to try to set up a connection with the Server.<br/>
	 * Initialize the local student interface using {@link Context#lookup(String)}
	 * @throws NamingException if a connection error occurs during the lookup of the Server
	 */
	private void lookup() throws NamingException {

		try {
			ConnectionManager.getInstance().setConnection();
			student = (StudentManagerRemote) ConnectionManager.getInstance().getJndiContext().lookup("StudentManager/remote");
		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		}
	}


	/**
	 * Log in a student.<br/>
	 * It invokes {@link #lookup()} to execute a lookup of the Server.<br/>
	 * It Invokes {@link StudentManagerRemote#login(String, PasswordHashDTO)}.
	 * @param theUsername of the student
	 * @param thePassword inserted by the student
	 * @throws InvalidArgumentException if a given argument is not valid
	 * @throws AlreadyLoggedInException if the student is already logged in
	 * @throws UserDoesNotExistException if the username given as parameter does not exist
	 * @throws PasswordMismatchException if the password given as parameter does not match with the one stored in the database
	 * @throws NamingException if a connection error occurs
	 */
	public void login(String theUsername, String thePassword)
			throws InvalidArgumentException, AlreadyLoggedInException,
			UserDoesNotExistException, PasswordMismatchException, NamingException {
		
		lookup();
		
		PasswordHashDTO aPasswordHash = null;
		aPasswordHash = new PasswordHashDTO(thePassword);
				
		login(theUsername.toLowerCase(), aPasswordHash);
	}
	
	@Override
	public void login(String theUsername, PasswordHashDTO thePasswordHash)
			throws InvalidArgumentException, AlreadyLoggedInException,
			UserDoesNotExistException, PasswordMismatchException {
		student.login(theUsername, thePasswordHash);
	}

	/**
	 * @param theUserId
	 * @return an object containing the use information
	 * @throws InvalidArgumentException
	 * @throws UserNotLoggedInException
	 */
	public <T extends UserDTO> T getUserInfo(UserIdDTO theUserId)
			throws InvalidArgumentException, UserNotLoggedInException {
		
		return student.getUserInfo(theUserId);
	}

	/**
	 * Update the user information.<br/>
	 * It invokes {@link StudentManagerRemote#updateUserInfo(UserDTO)}
	 * @param theUserId of the student logged in
	 * @param theFirstName the new value
	 * @param theLastName the new value
	 * @param theBirthday the new value
	 * @param theEmail the new value
	 * @param theTelephoneNumber the new value
	 * @throws InvalidArgumentException if a given argument is not valid
	 * @throws UserNotLoggedInException if the student is not logged in
	 */
	public void updateUserInfo(UserIdDTO theUserId, String theFirstName,String theLastName, java.util.Date theBirthday, String theEmail, String theTelephoneNumber)
			throws InvalidArgumentException, UserNotLoggedInException {
		
		StudentDTO studentDTO = student.getUserInfo(theUserId);
		String studentUsername = studentDTO.getId().getUsername();
		
		java.sql.Date birthdayDate = null;
		if (theBirthday != null) {
			birthdayDate = new java.sql.Date(theBirthday.getTime());
		}
		
		StudentDTO updatedStudentDTO = new StudentDTO(new StudentIdDTO(studentUsername), theFirstName, theLastName, theEmail, theTelephoneNumber, birthdayDate);
		
		updateUserInfo(updatedStudentDTO);
		
	}
	
	@Override
	public void updateUserInfo(UserDTO theUpdatedUserDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		student.updateUserInfo(theUpdatedUserDTO);
	}

	@Override
	public Set<CourseIdDTO> getCourseSet() throws UserNotLoggedInException {
		return student.getCourseSet();
		
	}

	@Override
	public CourseDTO getCourseInfo(CourseIdDTO theCourseId) throws InvalidArgumentException, UserNotLoggedInException {
		return student.getCourseInfo(theCourseId);
	}

	@Override
	public Set<ProjectIdDTO> getProjectSet(CourseIdDTO theCourseId) throws InvalidArgumentException, UserNotLoggedInException {
		return student.getProjectSet(theCourseId);
	}

	@Override
	public ProjectDTO getProjectInfo(ProjectIdDTO theProjectId) throws InvalidArgumentException, UserNotLoggedInException {
		return student.getProjectInfo(theProjectId);		
	}
	
	@Override
	public Set<ProfessorIdDTO> getProfessorSet() throws UserNotLoggedInException {
		return student.getProfessorSet();
	}

	@Override
	public Set<DeliverableIdDTO> getDeliverableSet(ProjectIdDTO theProjectId)
			throws InvalidArgumentException, UserNotLoggedInException {
		return student.getDeliverableSet(theProjectId);
	}

	@Override
	public DeliverableDTO getDeliverableInfo(DeliverableIdDTO theDeliverableId) throws UserNotLoggedInException, InvalidArgumentException {
		return student.getDeliverableInfo(theDeliverableId);
	}

	@Override
	public Set<ArtifactIdDTO> getArtifactSetByDeliverable(
			DeliverableIdDTO theDeliverableId) throws InvalidArgumentException, UserNotLoggedInException {
		return student.getArtifactSetByDeliverable(theDeliverableId);
	}

	@Override
	public Set<ArtifactIdDTO> getArtifactSetByTeam(TeamIdDTO theTeamId) throws InvalidArgumentException, UserNotLoggedInException {
		return student.getArtifactSetByTeam(theTeamId);
	}

	@Override
	public IncomingFileDTO downloadArtifact(ArtifactIdDTO theArtifactId)
			throws InvalidArgumentException, UserNotLoggedInException, DownloadErrorException {
		return student.downloadArtifact(theArtifactId);
	}

	@Override
	public Set<TeamIdDTO> getTeamSet(ProjectIdDTO theProjectId)
			throws InvalidArgumentException, UserNotLoggedInException {
		return student.getTeamSet(theProjectId);
	}
	
	@Override
	public TeamDTO getTeamInfo(TeamIdDTO theTeamId) throws InvalidArgumentException, UserNotLoggedInException {
		return student.getTeamInfo(theTeamId);
	}

	@Override
	public Set<StudentIdDTO> getStudentComposingTeam(TeamIdDTO theTeamId) throws InvalidArgumentException, UserNotLoggedInException {
		return student.getStudentComposingTeam(theTeamId);
	}

	/**
	 * Register a new student into the system.<br/>
	 * It invokes {@link StudentManagerRemote#registerNewUser(String, PasswordHashDTO, String, String, Date, String, String)}
	 * @param theUsername chosen by the student
	 * @param thePassword chosen by the student
	 * @param theFirstName of the new student
	 * @param theLastName of the new student
	 * @param theBirthday of the new student
	 * @param theEmail of the new student
	 * @param theTelephoneNumber of the new student
	 * @throws UsernameAlreadyExistsException if the chosen username already exists in the database
	 * @throws InvalidArgumentException if a given argument is not valid
	 * @throws NamingException if a connection error occurs
	 */
	public void register(String theUsername, String thePassword, String theFirstName,String theLastName, java.util.Date theBirthday, String theEmail, String theTelephoneNumber) throws UsernameAlreadyExistsException, InvalidArgumentException,
	NamingException {

		lookup();

		java.sql.Date birthdayDate = null;
		if (theBirthday != null) {
			birthdayDate = new java.sql.Date(theBirthday.getTime());
		}

		registerNewUser(theUsername.toLowerCase(), new PasswordHashDTO(thePassword), theFirstName, theLastName, birthdayDate, theEmail, theTelephoneNumber);
	}
	
	@Override
	public void registerNewUser(String theUsername,
			PasswordHashDTO thePasswordHash, String theFirstName,
			String theLastName, Date theBirthday, String theEmail,
			String theTelephoneNumber) throws UsernameAlreadyExistsException,
			InvalidArgumentException {
		student.registerNewUser(theUsername, thePasswordHash, theFirstName, theLastName, theBirthday, theEmail, theTelephoneNumber);
		
	}

	/**
	 * Upload an artifact into the system.<br/>
	 * It invokes {@link StudentManagerRemote#uploadArtifact(TeamIdDTO, DeliverableIdDTO, OutcomingFileDTO)}
	 * @param theTeamId of the team uploading the artifact
	 * @param deliverableId of the deliverable the artifact refers to
	 * @param theFile the desired file to upload
	 * @return the id of the newly created artifact
	 * @throws FileNotFoundException if the file given as parameter does not exist
	 * @throws IllegalArgumentException if a given argument is not valid
	 * @throws ForbiddenOperationException if the student is not allowed to execute the operation
	 * @throws UploadErrorException if an error occurs during the upload
	 * @throws InvalidArgumentException if a given argument is not valid
	 * @throws UserNotLoggedInException if the student is not logged in
	 * @throws FileTooLargeException if the size of the file given as parameter is too large for the database
	 */
	public ArtifactIdDTO uploadArtifact(TeamIdDTO theTeamId,
			DeliverableIdDTO deliverableId, File theFile) throws FileNotFoundException, IllegalArgumentException,
																ForbiddenOperationException, UploadErrorException,
																InvalidArgumentException, UserNotLoggedInException, FileTooLargeException {
		return uploadArtifact(theTeamId, deliverableId, new OutcomingFileDTO(theFile.getAbsolutePath()));
	}
	
	@Override
	public ArtifactIdDTO uploadArtifact(TeamIdDTO theTeamId,
			DeliverableIdDTO deliverableId, OutcomingFileDTO theFileDTO)
					throws ForbiddenOperationException,
					UploadErrorException, InvalidArgumentException, UserNotLoggedInException {
		return student.uploadArtifact(theTeamId, deliverableId, theFileDTO);
	}

	@Override
	public Set<JoinRequestIdDTO> getPendingJoinRequests(TeamIdDTO theTeamId) 
			throws InvalidArgumentException, UserNotLoggedInException {
		return student.getPendingJoinRequests(theTeamId);
	}

	@Override
	public void setTeamEnrollmentRequests(TeamIdDTO theTeamId,
			boolean areRequestsOpen) 
					throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException {
		student.setTeamEnrollmentRequests(theTeamId, areRequestsOpen);
	}

	@Override
	public void acceptPendingJoinRequest(JoinRequestIdDTO theJoinRequestId)
			throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException {
		student.acceptPendingJoinRequest(theJoinRequestId);
	}

	@Override
	public TeamIdDTO createTeam(ProjectIdDTO theProjectId, String theTeamName) throws InvalidArgumentException, UserNotLoggedInException,
																					TeamNameAlreadyExists, ForbiddenOperationException  {
		return student.createTeam(theProjectId, theTeamName);
	}

	@Override
	public void joinTeam(TeamIdDTO theTeamId) throws ForbiddenOperationException, InvalidArgumentException, UserNotLoggedInException {
		student.joinTeam(theTeamId);
	}

	@Override
	public void leaveTeam(TeamIdDTO theTeamId) throws 			ForbiddenOperationException, InvalidArgumentException, UserNotLoggedInException {
		student.leaveTeam(theTeamId);
	}

	@Override
	public Set<StudentIdDTO> getStudentSet() throws UserNotLoggedInException {
		return student.getStudentSet();
	}

	@Override
	public Set<CourseIdDTO> getCourseSet(ProfessorIdDTO theProfessorIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
		return student.getCourseSet(theProfessorIdDTO);
	}

	@Override
	public Set<ProjectIdDTO> getEnrolledProjectSet(StudentIdDTO theStudentIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {

			return student.getEnrolledProjectSet(theStudentIdDTO);

	}

	@Override
	public TeamIdDTO getProjectTeam(StudentIdDTO theStudentIdDTO,
			ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException,
			UserNotLoggedInException {
		return student.getProjectTeam(theStudentIdDTO, theProjectIdDTO);
	}

	@Override
	public void declinePendingJoinRequest(JoinRequestIdDTO theJoinRequestId)
			throws InvalidArgumentException, UserNotLoggedInException,
			ForbiddenOperationException {
		student.declinePendingJoinRequest(theJoinRequestId);
	}

	@Override
	public void removePendingJoinRequest(JoinRequestIdDTO theJoinRequestId)
			throws InvalidArgumentException, UserNotLoggedInException,
			ForbiddenOperationException {
		student.removePendingJoinRequest(theJoinRequestId);
	}

	@Override
	public Set<JoinRequestIdDTO> getPendingJoinRequestSet(
			ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException,
			UserNotLoggedInException {
		return student.getPendingJoinRequestSet(theProjectIdDTO);
	}

	@Override
	public ArtifactDTO getArtifactInfo(ArtifactIdDTO theArtifactId)
			throws InvalidArgumentException, UserNotLoggedInException {
		return student.getArtifactInfo(theArtifactId);
	}

	@Override
	public ArtifactIdDTO getArtifact(DeliverableIdDTO theDeliverableId,
			TeamIdDTO theTeamIdDTO) throws InvalidArgumentException,
			UserNotLoggedInException {
		return student.getArtifact(theDeliverableId, theTeamIdDTO);
	}

	@Override
	public void logout() throws UserNotLoggedInException {
		
		student.logout();
		
	}

}
