package com.raaxxo.mph.sessions.remote;

import java.sql.Date;
import java.util.Set;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.DateInconsistencyException;
import mph.beans.exceptions.DeliverableNameAlreadyExists;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.ProjectNameAlreadyExists;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.sessions.ProfessorManagerRemote;

import com.raaxxo.mph.entities.Professor;

/**
 * 
 * Stateful session bean implementation of the ProfessorManagerRemote remote interface. <br/>
 * This method contains all the implementation of the remote interface shared with the client module through the MPH-Shared Project.
 * 
 */
@Stateful
@Remote(ProfessorManagerRemote.class)
public class ProfessorManager extends BasicUserManager implements ProfessorManagerRemote { 
	
	public ProfessorManager() {
		super();
		setUserType(Professor.class);
	}
	
	
	@Override
	public ProjectIdDTO registerNewProject(CourseIdDTO theCourseId, String theProjectTitle,
			Date theStartDate, Date theEndDate, String theDescription, Set<DeliverableDTO> theDeliverableDTOSet)
			throws InvalidArgumentException, UserNotLoggedInException, 
			ProjectNameAlreadyExists, DateInconsistencyException, DeliverableNameAlreadyExists, ForbiddenOperationException {
	
		checkLoggedInStatus();
		return projectManager.createProject(theCourseId, theProjectTitle, theStartDate,
						theEndDate, theDescription, theDeliverableDTOSet,  this.<ProfessorIdDTO>getUserIdDTO());

	}
	
	@Override
	public void setArtifactScore(ArtifactIdDTO theArtifactId, Double theScore)
			throws ForbiddenOperationException, InvalidArgumentException, UserNotLoggedInException {

		checkLoggedInStatus();
		artifactManager.setArtifactScore(theArtifactId, theScore);
		
	}
	
}