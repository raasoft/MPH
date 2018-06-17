package com.raaxxo.mph.sessions.local;

import java.sql.Date;
import java.util.Set;

import javax.ejb.Local;

import com.raaxxo.mph.entities.Course;
import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.classids.ProjectId;

import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ProjectDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.exceptions.DateInconsistencyException;
import mph.beans.exceptions.DeliverableNameAlreadyExists;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.ProjectNameAlreadyExists;
import mph.beans.exceptions.UserNotLoggedInException;


/**
 * 
 * Local interface that manages the {@link Project} entities.
 * 
 */
@Local
public interface ProjectManagerLocal  extends ManagerLocal<Project, ProjectId, ProjectDTO, ProjectIdDTO> {
	
	public Project reconstructEntity(ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException;
	public Project getEntity(ProjectId theProjectId) throws InvalidArgumentException;


	public boolean existsEntity(ProjectId theProjectId) throws InvalidArgumentException;

	public ProjectIdDTO createProject(CourseIdDTO theCourse, String theProjectTitle, Date theStartDate, 
			Date theEndDate, String theDescription, Set<DeliverableDTO> theDeliverableDTOSet, ProfessorIdDTO theProfessorIdDTO) 
					throws InvalidArgumentException, UserNotLoggedInException, ProjectNameAlreadyExists, DateInconsistencyException, DeliverableNameAlreadyExists, ForbiddenOperationException;


	public Set<ProjectIdDTO> getProjectIdDTOSet(CourseIdDTO theCourseId) throws InvalidArgumentException;
	public Set<Project> getProjectSet(Course theCourse) throws InvalidArgumentException;
	public Set<ProjectIdDTO> getEnrolledProjectIdDTOSet(StudentIdDTO theStudentIdDTO) throws InvalidArgumentException;
	Set<Project> getEnrolledProjectSet(StudentIdDTO theStudentIdDTO) throws InvalidArgumentException;


	public ProjectDTO getProjectInfo(ProjectIdDTO theProjectId) throws InvalidArgumentException;

	public void checkProjectExists(ProjectId theProjectId)throws InvalidArgumentException;	

}
