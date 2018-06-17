package com.raaxxo.mph.sessions.local;

import java.util.Set;

import javax.ejb.Local;

import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.Student;
import com.raaxxo.mph.entities.Team;
import com.raaxxo.mph.entities.classids.TeamId;

import mph.beans.dto.TeamDTO;
import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.TeamNameAlreadyExists;



/**
 * 
 * Local interface that manages the {@link Team} entities.
 * 
 */
@Local
public interface TeamManagerLocal  extends ManagerLocal<Team, TeamId, TeamDTO, TeamIdDTO>{
	
	TeamIdDTO createTeam(ProjectIdDTO theProjectIdDTO, String theTeamName,
			Student theStudent) throws InvalidArgumentException,
			TeamNameAlreadyExists, ForbiddenOperationException; 
	
	public void acceptPendingJoinRequest(JoinRequestIdDTO theJoinRequestId, StudentIdDTO theStudentIdDTO) throws InvalidArgumentException, ForbiddenOperationException;
	public void declinePendingJoinRequest(JoinRequestIdDTO theJoinRequestId, StudentIdDTO userIdDTO) throws InvalidArgumentException, ForbiddenOperationException;
	public void removePendingJoinRequest(JoinRequestIdDTO theJoinRequestId, StudentIdDTO theStudentIdDTO) throws InvalidArgumentException;
	
	public Set<JoinRequestIdDTO> getPendingJoinRequestsSetIdDTO(ProjectIdDTO theProjectIdDTO, StudentIdDTO theStudentIdDTO) throws InvalidArgumentException;
	
	public void joinTeam(TeamIdDTO theTeamIdDTO, StudentIdDTO theStudentIdDTO) throws InvalidArgumentException, ForbiddenOperationException;
	public void leaveTeam(TeamIdDTO theTeamIdDTO, StudentIdDTO theStudentIdDTO) throws InvalidArgumentException, ForbiddenOperationException;
	
	public Team reconstructEntity(TeamIdDTO theTeamIdDTO) throws InvalidArgumentException;
	public Team getEntity(TeamId theTeamId) throws InvalidArgumentException;
	
	public boolean existsEntity(TeamId theTeamId) throws InvalidArgumentException;

	public void checkEntityExists(TeamId theTeamId) throws InvalidArgumentException;

	public Set<Team> getTeamSet(Project theProject) throws InvalidArgumentException;
	public Set<TeamIdDTO> getTeamIdDTOSet(ProjectIdDTO theProjectId) throws InvalidArgumentException;
	
	public TeamDTO getTeamInfo(TeamIdDTO theTeamId) throws InvalidArgumentException;

	public TeamIdDTO getTeam(StudentIdDTO theStudentIdDTO, ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException;

	public void setTeamEnrollmentRequests(TeamIdDTO theTeamIdDTO, StudentIdDTO theStudentIdDTO, boolean areRequestsOpen) 
			throws InvalidArgumentException, ForbiddenOperationException;

	public Set<JoinRequestIdDTO> getPendingJoinRequest(TeamIdDTO theTeamId) throws InvalidArgumentException;
	
	public	Set<StudentIdDTO> getStudentComposingTeamIdDTO(TeamIdDTO theTeamId) throws InvalidArgumentException;
	
	public void closeStartedProjects();

	
}
