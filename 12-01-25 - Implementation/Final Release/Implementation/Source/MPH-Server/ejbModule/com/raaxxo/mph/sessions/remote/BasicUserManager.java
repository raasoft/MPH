package com.raaxxo.mph.sessions.remote;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.EJBException;

import com.raaxxo.mph.entities.Professor;
import com.raaxxo.mph.entities.Student;
import com.raaxxo.mph.sessions.local.ArtifactManagerLocal;
import com.raaxxo.mph.sessions.local.CourseManagerLocal;
import com.raaxxo.mph.sessions.local.DeliverableManagerLocal;
import com.raaxxo.mph.sessions.local.EntityUtilityLocal;
import com.raaxxo.mph.sessions.local.ProjectManagerLocal;
import com.raaxxo.mph.sessions.local.TeamManagerLocal;
import com.raaxxo.mph.sessions.local.UserManagerLocal;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.CourseDTO;
import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.IncomingFileDTO;
import mph.beans.dto.PasswordHashDTO;
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
import mph.beans.exceptions.DownloadErrorException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.PasswordMismatchException;
import mph.beans.exceptions.UserDoesNotExistException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.sessions.BasicUserManagerInterface;

/**
 * 
 * This abstract class provides the reuse of code for the {@link ProfessorManager} and {@link StudentManager} stateful session bean for all of the
 * common methods implemented by both the managers.
 * 
 */
 abstract public class BasicUserManager extends UserSession implements BasicUserManagerInterface {
	
	@EJB protected UserManagerLocal userManager;
	@EJB protected CourseManagerLocal courseManager;
	@EJB protected ProjectManagerLocal projectManager;
	@EJB protected DeliverableManagerLocal deliverableManager;
	@EJB protected ArtifactManagerLocal artifactManager;
	@EJB protected TeamManagerLocal teamManager;
	@EJB protected EntityUtilityLocal searchUtility;
	
	@Override
	public void login(String theUsername, PasswordHashDTO thePasswordHash)
			throws InvalidArgumentException, AlreadyLoggedInException,
			UserDoesNotExistException, PasswordMismatchException {
		
		if (isLogged() ) {
			System.out.println("The user connected with the username " + theUsername + " is already logged in");
			throw new AlreadyLoggedInException("User "+ theUsername +" is already logged in. Logout before trying to login");
		}
		
		userManager.login(theUsername, thePasswordHash, getUserType());
		
		setLogged(true);
		setUsername(theUsername);
		
		System.out.println("The user connected with the username " + theUsername + " is NOW logged in");

		
	}
	
	@Override
	public void logout() throws UserNotLoggedInException {
		
		String aUsername = getUsername();
		
		if (isLogged() == false ) {
			System.out.println("The user connected with the username " + aUsername + " is not logged in");
			throw new UserNotLoggedInException("User "+ aUsername +" is not logged in. Login before trying to logout");
		}
		
		setLogged(false);
		try {
			setUsername("");
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		
		System.out.println("The user previously connected with the username " + aUsername + " is NOW logged out");

		
	}
	
	
	@Override
	public <W extends UserDTO> W getUserInfo(UserIdDTO theUserId)
			throws InvalidArgumentException, UserNotLoggedInException {
		
		checkLoggedInStatus();		
		return userManager.<W>getUserInfo(theUserId);
		
	}
	
	@Override
	public void updateUserInfo(UserDTO theUpdatedUserDTO)
			throws InvalidArgumentException, UserNotLoggedInException{
		
		checkLoggedInStatus();	
		
		String aUserUsername = theUpdatedUserDTO.getId().getUsername();
		
		System.out.println("Trying to update the user info of the user " + aUserUsername + " by the user " + getUsername());
		if (getUsername().equals(aUserUsername) == true) {
			userManager.updateUserInfo(theUpdatedUserDTO);
			System.out.println("Updated successfully the user info of the user " + getUsername());
		} else {
			System.out.println("User violation: the user " + getUsername() + " is trying to modify the personal info of the user " + aUserUsername);
			throw new InvalidArgumentException("The user info you are trying to update doesn't  belong to the user logged in");
		}
		
	}
	
	
	@Override
	public Set<CourseIdDTO> getCourseSet(ProfessorIdDTO theProfessorIdDTO) throws UserNotLoggedInException, InvalidArgumentException {
			
			checkLoggedInStatus();
			return courseManager.getCourseIdDTOSet(theProfessorIdDTO);
			
	}
	
	@Override
	public CourseDTO getCourseInfo(CourseIdDTO theCourseId)
			throws InvalidArgumentException, UserNotLoggedInException {
		
		checkLoggedInStatus();		
		return courseManager.getCourseInfo(theCourseId);
		
	}
	
	@Override
	public Set<ProjectIdDTO> getProjectSet(CourseIdDTO theCourseId)
			throws InvalidArgumentException, UserNotLoggedInException {
		
		checkLoggedInStatus();
		return projectManager.getProjectIdDTOSet(theCourseId);
	}

	@Override
	public ProjectDTO getProjectInfo(ProjectIdDTO theProjectId)
			throws InvalidArgumentException, UserNotLoggedInException {
		
		checkLoggedInStatus();		
		return projectManager.getProjectInfo(theProjectId);
		
	}
	
	@Override
	public Set<DeliverableIdDTO> getDeliverableSet(ProjectIdDTO theProjectId) throws InvalidArgumentException, UserNotLoggedInException {

		checkLoggedInStatus();
		return deliverableManager.getDeliverableIdDTOSet(theProjectId);
		
	}

	@Override
	public DeliverableDTO getDeliverableInfo(DeliverableIdDTO theDeliverableId)
			throws InvalidArgumentException, UserNotLoggedInException {
	
		checkLoggedInStatus();
		return deliverableManager.getDeliverableInfo(theDeliverableId);
		
	}
	
	@Override
	public Set<TeamIdDTO> getTeamSet(ProjectIdDTO theProjectId)
			throws InvalidArgumentException, UserNotLoggedInException {
		
		checkLoggedInStatus();
		return teamManager.getTeamIdDTOSet(theProjectId);
		
	}

	@Override
	public TeamDTO getTeamInfo(TeamIdDTO theTeamId)
			throws InvalidArgumentException, UserNotLoggedInException  {
		
		checkLoggedInStatus();
		return teamManager.getTeamInfo(theTeamId);
		
	}


	@Override
	public Set<ProfessorIdDTO> getProfessorSet()
			throws UserNotLoggedInException {

		checkLoggedInStatus();
		
		System.out.println("Trying to get the professor set");
	
		Set<ProfessorIdDTO> aSet = null;
		try {
			aSet = userManager.getUserIdDTOSet(Professor.class);
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
			throw new EJBException("getProfessorSet exception");
		}
		
		System.out.println("Professor set got. Returning it.");

		return aSet;
		
	}
		
	@Override
	public Set<StudentIdDTO> getStudentSet() throws UserNotLoggedInException {
			
		checkLoggedInStatus();
		
		System.out.println("Trying to get the student set");
		
		Set<StudentIdDTO> aSet = null;
		try {
			aSet = userManager.getUserIdDTOSet(Student.class);
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
			throw new EJBException("getStudentSet exception");
		}
		
		System.out.println("Student set got. Returning it.");


		return aSet;
		
	}

	@Override
	public TeamIdDTO getProjectTeam(StudentIdDTO theStudentIdDTO,
			ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException,
			UserNotLoggedInException {
		
		checkLoggedInStatus();
		return teamManager.getTeam(theStudentIdDTO, theProjectIdDTO);
		
		
	}

	@Override
	public Set<CourseIdDTO> getCourseSet() throws UserNotLoggedInException {
	
		checkLoggedInStatus();
		return courseManager.getCourseIdDTOSet();
		
	}

	@Override
	public Set<ProjectIdDTO> getEnrolledProjectSet(StudentIdDTO theStudentIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException {
	
		checkLoggedInStatus();
		return projectManager.getEnrolledProjectIdDTOSet(theStudentIdDTO);
		
	}
	
	@Override
	public Set<StudentIdDTO> getStudentComposingTeam(TeamIdDTO theTeamId)
			throws InvalidArgumentException, UserNotLoggedInException  {

		checkLoggedInStatus();
		return teamManager.getStudentComposingTeamIdDTO(theTeamId);

	}
	
	@Override
	public IncomingFileDTO downloadArtifact(ArtifactIdDTO theArtifactIdDTO)
			throws InvalidArgumentException, UserNotLoggedInException, DownloadErrorException {
		
		checkLoggedInStatus();
		return artifactManager.downloadArtifact(theArtifactIdDTO);
	}
	
	@Override
	public ArtifactIdDTO getArtifact(DeliverableIdDTO theDeliverableId,
			TeamIdDTO theTeamIdDTO) throws InvalidArgumentException,
			UserNotLoggedInException {
		checkLoggedInStatus();
		return artifactManager.getArtifact(theDeliverableId,theTeamIdDTO);

	}
	
	@Override
	public ArtifactDTO getArtifactInfo(ArtifactIdDTO theArtifactId) throws InvalidArgumentException, UserNotLoggedInException {
		
		checkLoggedInStatus();
		return artifactManager.getArtifactInfo(theArtifactId);
		
	}

	
	@Override
	public Set<ArtifactIdDTO> getArtifactSetByDeliverable(
			DeliverableIdDTO theDeliverableId) throws InvalidArgumentException, UserNotLoggedInException {
		
		checkLoggedInStatus();
		return artifactManager.getArtifactSetByDeliverable(theDeliverableId);
	}

	@Override
	public Set<ArtifactIdDTO> getArtifactSetByTeam(TeamIdDTO theTeamId)
			throws InvalidArgumentException, UserNotLoggedInException  {
		
		checkLoggedInStatus();
		return artifactManager.getArtifactSetByTeam(theTeamId);
	}


}