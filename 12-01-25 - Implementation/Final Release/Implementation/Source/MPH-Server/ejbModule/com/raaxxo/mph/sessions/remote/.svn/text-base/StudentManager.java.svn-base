package com.raaxxo.mph.sessions.remote;

import java.sql.Date;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import com.raaxxo.mph.entities.Student;
import com.raaxxo.mph.sessions.local.StudentFactoryLocal;
import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.OutcomingFileDTO;
import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.TeamNameAlreadyExists;
import mph.beans.exceptions.UploadErrorException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.exceptions.UsernameAlreadyExistsException;
import mph.beans.sessions.StudentManagerRemote;

/**
 * 
 * Stateful session bean implementation of the StudentManagerRemote remote interface. <br/>
 * This method contains all the implementation of the remote interface shared with the client module through the MPH-Shared Project.
 * 
 */
@Stateful
@Remote(StudentManagerRemote.class)
public class StudentManager extends BasicUserManager implements StudentManagerRemote {
	
	@EJB protected StudentFactoryLocal studentFactory;	

	public StudentManager() {
		super();
		setUserType(Student.class);
	}

	public void registerNewUser(String theUsername, PasswordHashDTO thePasswordHash, String theFirstName,
			String theLastName, Date theBirthday, String theEmail,
			String theTelephoneNumber) throws InvalidArgumentException, UsernameAlreadyExistsException {
		
		userManager.registerNewUser(studentFactory, theUsername, thePasswordHash, theFirstName, theLastName, theBirthday, theEmail, theTelephoneNumber);
				
	}
	
	
	public TeamIdDTO createTeam(ProjectIdDTO theProjectId, String theTeamName)
			throws InvalidArgumentException, UserNotLoggedInException, TeamNameAlreadyExists, ForbiddenOperationException {
		
		checkLoggedInStatus();
		Student aStudent = userManager.getEntity(Student.class, getUsername());
		TeamIdDTO aTeamIdDTO = teamManager.createTeam(theProjectId, theTeamName, aStudent);
		return aTeamIdDTO;

	}
	
	@Override
	public Set<ProjectIdDTO> getEnrolledProjectSet(StudentIdDTO theUser)
			throws InvalidArgumentException, UserNotLoggedInException {

		checkLoggedInStatus();
		return projectManager.getEnrolledProjectIdDTOSet(theUser);
		
	}
	
	public void setTeamEnrollmentRequests(TeamIdDTO theTeamIdDTO,
			boolean areRequestsOpen) throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException {
		
		checkLoggedInStatus();		
		teamManager.setTeamEnrollmentRequests(theTeamIdDTO, userManager.getEntity(Student.class, getUsername()).getDTO().getId(), areRequestsOpen);
		
	}


	@Override
	public Set<JoinRequestIdDTO> getPendingJoinRequests(TeamIdDTO theTeamId) 
			throws InvalidArgumentException, UserNotLoggedInException {
		checkLoggedInStatus();
		return teamManager.getPendingJoinRequest(theTeamId);
	}


	@Override
	public void acceptPendingJoinRequest(JoinRequestIdDTO theJoinRequestId)
			throws InvalidArgumentException, UserNotLoggedInException, ForbiddenOperationException {
	
		checkLoggedInStatus();
		teamManager.acceptPendingJoinRequest(theJoinRequestId, this.<StudentIdDTO>getUserIdDTO());
		
	}


	@Override
	public void joinTeam(TeamIdDTO theTeamIdDTO) throws ForbiddenOperationException, InvalidArgumentException, UserNotLoggedInException {

		checkLoggedInStatus();
		teamManager.joinTeam(theTeamIdDTO, this.<StudentIdDTO>getUserIdDTO() );

	}

	@Override
	public void leaveTeam(TeamIdDTO theTeamIdDTO) throws ForbiddenOperationException, InvalidArgumentException, UserNotLoggedInException {

		checkLoggedInStatus();
		teamManager.leaveTeam(theTeamIdDTO, this.<StudentIdDTO>getUserIdDTO());
	}
	
	@Override
	public Set<JoinRequestIdDTO> getPendingJoinRequestSet(
			ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException,
			UserNotLoggedInException {
		
		checkLoggedInStatus();
		return teamManager.getPendingJoinRequestsSetIdDTO(theProjectIdDTO, this.<StudentIdDTO>getUserIdDTO());
	}
	

	@Override
	public void declinePendingJoinRequest(JoinRequestIdDTO theJoinRequestId)
			throws InvalidArgumentException, UserNotLoggedInException,
			ForbiddenOperationException {
		
		checkLoggedInStatus();
		teamManager.declinePendingJoinRequest(theJoinRequestId, this.<StudentIdDTO>getUserIdDTO());
		
	}

	@Override
	public void removePendingJoinRequest(JoinRequestIdDTO theJoinRequestId)
			throws InvalidArgumentException, UserNotLoggedInException,
			ForbiddenOperationException {
		
		checkLoggedInStatus();
		teamManager.removePendingJoinRequest(theJoinRequestId, this.<StudentIdDTO>getUserIdDTO());
		
	}
	
	public ArtifactIdDTO uploadArtifact(TeamIdDTO theTeamId,
			DeliverableIdDTO theDeliverableId, OutcomingFileDTO theFileDTO)
					throws InvalidArgumentException, ForbiddenOperationException,
					UploadErrorException, UserNotLoggedInException {
		
		checkLoggedInStatus();
		return artifactManager.uploadArtifact(theTeamId, theDeliverableId, theFileDTO, this.<StudentIdDTO>getUserIdDTO());

	}

	@Override
	public ArtifactDTO getArtifactInfo(ArtifactIdDTO theArtifactId)
			throws InvalidArgumentException, UserNotLoggedInException {
		checkLoggedInStatus();
		return artifactManager.getArtifactInfo(theArtifactId);
	}
	
}