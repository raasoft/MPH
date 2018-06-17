package com.raaxxo.mph.sessions.local;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

import org.jboss.ejb3.annotation.IgnoreDependency;

import util.CalendarUtility;
import util.ObjectUtility;
import util.StringUtility;

import com.raaxxo.mph.entities.Course;
import com.raaxxo.mph.entities.Deliverable;
import com.raaxxo.mph.entities.Professor;
import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.Student;
import com.raaxxo.mph.entities.Team;
import com.raaxxo.mph.entities.classids.ProjectId;

@Stateless
public class ProjectManager implements ProjectManagerLocal {

	@PersistenceContext(unitName = "mph") private EntityManager em;
	@EJB @IgnoreDependency private CourseManagerLocal courseManager;
	@EJB @IgnoreDependency private DeliverableManagerLocal deliverableManager;
	@EJB @IgnoreDependency private UserManagerLocal userManager;
	@EJB private EntityUtilityLocal entityUtility;

	@Override
	public ProjectIdDTO createProject(CourseIdDTO theCourseIdDTO, String theProjectTitle,
			Date theStartDate, Date theEndDate, String theDescription, Set<DeliverableDTO> theDeliverableDTOSet, ProfessorIdDTO theProfessorIdDTO)
					throws InvalidArgumentException, ProjectNameAlreadyExists, DateInconsistencyException, DeliverableNameAlreadyExists, ForbiddenOperationException {

		System.out.println("Trying to create a new project entering the createProject method");
		
		ObjectUtility.isNull(theProfessorIdDTO);
		userManager.checkUserExists(Professor.class, theProfessorIdDTO.getUsername());
			
		ObjectUtility.isNull(theCourseIdDTO);
		StringUtility.isNullOrEmpty(theProjectTitle, "Project Title");
		StringUtility.isNullOrEmpty(theDescription, "Description");
		ObjectUtility.isNull(theStartDate);
		ObjectUtility.isNull(theEndDate);
		
		System.out.println("Days between " + CalendarUtility.daysBetween(entityUtility.getServerTimestamp(), theStartDate));
		System.out.println("Days between " + CalendarUtility.daysBetween(theStartDate, theEndDate));
		
		if (  CalendarUtility.after(theStartDate, Date.valueOf("2150-01-01"))  ) {
			throw new InvalidArgumentException("The start date cannot be posterior than the 01-01-2150");
		}		
		if (  CalendarUtility.after(theEndDate, Date.valueOf("2150-01-01"))  ) {
			throw new InvalidArgumentException("The end date cannot be posterior than the 01-01-2150");
		}
		
		
		if ( CalendarUtility.beforeOrEqual(theStartDate, theEndDate)  ) {
			throw new DateInconsistencyException("The end date is preceding the start date (or is in the same day). Check the dates.");
		}
		if ( CalendarUtility.after(theStartDate, entityUtility.getServerTimestamp()) ) {
			throw new DateInconsistencyException("The start date ("+ theStartDate.toString() + ") is preceding the current date (" + entityUtility.getServerTimestamp().toString() + "). Check the dates.");
		}
		
		Course aCourse = courseManager.reconstructEntity(theCourseIdDTO);
		
		if (aCourse == null) {
			throw new InvalidArgumentException("The course you inserted doesn't exists");
		}
		
		System.out.println("The course where to register a new project has been verified.");
						
		Project newProject = null;
		Professor aProfessor = userManager.getEntity(Professor.class, theProfessorIdDTO.getUsername());
		
		if (courseManager.existsEntity(aCourse.getUid())) {
		
			if (existsEntity(new ProjectId(aCourse, theProjectTitle))) {
				throw new ProjectNameAlreadyExists("There is already another project for this course with the same name.");
			}
			
			if (theDeliverableDTOSet.size() == 0) {
				throw new InvalidArgumentException("A project must be at least one deliverable.");
			}
			
			if (aCourse.getProfessor().equals(aProfessor) == false) {
				throw new ForbiddenOperationException("The professor " + aProfessor.getUsername() + " cannot create a project in the course " + aCourse.getName() + " owned by the professor " + aCourse.getProfessor().getUsername());
			}
			
			newProject = new Project(aCourse, theProjectTitle, theStartDate, theEndDate, theDescription);
			
			Set<String> aDelName = new HashSet<String>();
			
			for (DeliverableDTO aDel : theDeliverableDTOSet) {

				StringUtility.isNullOrEmpty(aDel.getId().getDeliverableName(), "Deliverable Name");
				
				if (aDelName.contains(aDel.getId().getDeliverableName()) == false) {
					aDelName.add(aDel.getId().getDeliverableName());
				} else {
					throw new DeliverableNameAlreadyExists("You are trying to add two or more deliverable with the same name (" + aDel.getId().getDeliverableName() + ")");
				}
				
				ObjectUtility.isNull(aDel.getDescription());
				ObjectUtility.isNull(aDel.getDeadline());
				ObjectUtility.isNull(aDel.getPenalty());

				if (aDel.getPenalty() < 0) {
					throw new InvalidArgumentException("The penalty of the deliverable " + aDel.getId().getDeliverableName() +  "must be a non negative number!");
				}

				System.out.println("Trying to create a new deliverable entering the addNewDeliverable method");

				if (  CalendarUtility.after(aDel.getDeadline(), Date.valueOf("2150-01-01"))  ) {
					throw new InvalidArgumentException("The deliverable " + aDel.getId().getDeliverableName() +  " deadline cannot be posterior than the 01-01-2150");
				}
				
				if ( CalendarUtility.beforeOrEqual(newProject.getStartDate(), aDel.getDeadline()) ) {
					throw new DateInconsistencyException("The deliverable " + aDel.getId().getDeliverableName() +  " deadline ("+ aDel.getDeadline().toString() + ") is preceding the project start date (" + newProject.getStartDate() + ") or is in the same day. Check the dates.");
				}
				if (  CalendarUtility.after(newProject.getEndDate(), aDel.getDeadline()) ) {
					throw new DateInconsistencyException("The deliverable " + aDel.getId().getDeliverableName() +  "date ("+ aDel.getDeadline().toString() + ") is later the project end date (" + newProject.getEndDate() + "). Check the dates.");
				}
				if ( CalendarUtility.beforeOrEqual(entityUtility.getServerTimestamp(), aDel.getDeadline()) ) {
					throw new DateInconsistencyException("The deliverable " + aDel.getId().getDeliverableName() +  "date ("+ aDel.getDeadline().toString() + ") is preceding the current date (" + entityUtility.getServerTimestamp().toString() + "). Check the dates.");
				}
			}
		
			em.persist(newProject);
			
			Deliverable newDeliverable = null;
			
			
			for (DeliverableDTO aDel : theDeliverableDTOSet) {
				

					newDeliverable = new Deliverable(newProject, aDel.getId().getDeliverableName(), aDel.getDeadline(), aDel.getPenalty(), aDel.getDescription());
					newProject.addDeliverable(newDeliverable);
					em.persist(newDeliverable);
					
					try {
						em.flush();
					}
					catch (Exception e) {
						throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
					}

			}
					
		} 	
		
	
		
		System.out.println("Project created. Now returning its reference.");
		
		return newProject.getId().getDTO();
		
		
	}
	

	@Override
	public boolean existsEntity(ProjectId theProjectId)
			throws InvalidArgumentException {
		
		System.out.println("Trying to figure out if exists the project with id: " + theProjectId);
		
		Project aProject = getEntity(theProjectId);

		if (aProject == null) {
			System.out.println("The project with id: " + theProjectId + " DOESN'T exists");
			return false;
		}
		else {
			System.out.println("The project with id: " + theProjectId + " exists");
			return true;
		}
		
	}


	@Override
	public Project getEntity(ProjectId theProjectId)
			throws InvalidArgumentException {
	
		ObjectUtility.isNull(theProjectId);
		
		System.out.println("Trying to get the project with id: " + theProjectId);
		
		Project aProject  = (Project) entityUtility.findOneEntity(Project.class, theProjectId);
		
		System.out.println("Trying to get the project with id: " + theProjectId);
		
		return aProject;

	}
	
	

	@Override
	public Project reconstructEntity(ProjectIdDTO theProjectIdDTO)
			throws InvalidArgumentException{
		
		ObjectUtility.isNull(theProjectIdDTO);
		
		Course aCourse = courseManager.getEntity(theProjectIdDTO.getCourseId().getId());
		
		Project aProject = (Project) entityUtility.findOneEntity(Project.class, new ProjectId(aCourse,theProjectIdDTO.getProjectTitle())); 
		
		return aProject;
	}

	@Override
	public void checkProjectExists(ProjectId theProjectId)
			throws InvalidArgumentException {
		
		if (existsEntity(theProjectId) == false) {
			throw new InvalidArgumentException("The project you've searched for ("+ theProjectId + ") doesn't exists");
		}
		
	}
	
	@Override
	public Set<ProjectIdDTO> getProjectIdDTOSet(CourseIdDTO theCourseIdDTO)
			throws InvalidArgumentException {
		
		ObjectUtility.isNull(theCourseIdDTO);
		
		Course aCourse = courseManager.reconstructEntity(theCourseIdDTO);
		
		Set<Project> theProjectSet = getProjectSet(aCourse);
		Set<ProjectIdDTO> theProjectIdDTOSet = new HashSet<ProjectIdDTO>();
		
		ProjectIdDTO aProjId = null;
		
		for (Project aProject : theProjectSet) 
		{
			aProjId = aProject.getId().getDTO();
			
			if (aProjId == null) 
				throw new InvalidArgumentException("Internal error database.");
			
			theProjectIdDTOSet.add(aProjId);
		
		}
		return theProjectIdDTOSet;
		
	}

	@Override
	public ProjectDTO getProjectInfo(ProjectIdDTO theProjectIdDTO)
			throws InvalidArgumentException {
		
		ObjectUtility.isNull(theProjectIdDTO);
		
		System.out.println("Trying to get the project DTO for the project with id: " + theProjectIdDTO);
		
		Project aProject = reconstructEntity(theProjectIdDTO);
		
		System.out.println("Creating some data structures for creating the project DTO");

		
		CourseIdDTO aCourseIdDTO = new CourseIdDTO(aProject.getId().getCourse().getUid());
		ProjectIdDTO aProjectIdDTO = new ProjectIdDTO(aCourseIdDTO, aProject.getId().getTitle());
		
		System.out.println("Ready to create the project DTO");

		ProjectDTO aProjectDTO = new ProjectDTO(aProjectIdDTO, deliverableManager.getDeliverableIdDTOSet(aProjectIdDTO), aProject.getStartDate(), aProject.getEndDate(), aProject.getDescription() );
		
		System.out.println("Created the project DTO. Returning the project DTO");
		return aProjectDTO;

		
	}

	@Override
	public Set<Project> getProjectSet(Course theCourse) throws InvalidArgumentException {

		Query q = em.createQuery("FROM Project p WHERE p.id.course.uid = :n");
		q.setParameter("n", theCourse.getUid());
		
		Set<Project> theProjectSet = new HashSet<Project>();
	
		System.out.println("Getting the projectset for the courses selected");
		for (Object p : q.getResultList()) 
		{
			theProjectSet.add((Project) p);	
		}
		
		System.out.println("Project set cardinality: " + theProjectSet.size());
		
		return theProjectSet;
	}

	@Override
	public Set<Project> getEnrolledProjectSet(StudentIdDTO theStudentIdDTO) throws InvalidArgumentException {

		userManager.checkUserExists(Student.class, theStudentIdDTO.getUsername());
		
		System.out.println("Getting the projects of the student " + theStudentIdDTO.getUsername());
		
		Student aStudent= (Student) entityUtility.findOneEntity(Student.class, theStudentIdDTO.getUsername());

		Set<Team> aTeamSet = aStudent.getStudentTeams();
		
		Set<Project> aProjectSet = new HashSet<Project>();
		
		System.out.println("Total list of teams: "  + aTeamSet.size());
		
		for (Team aTeam: aTeamSet) {
			aProjectSet.add(aTeam.getId().getProject());
		}
		
		System.out.println("Total list of projects: "  + aProjectSet.size() + " joined by the student " + theStudentIdDTO.getUsername());
		
		return aProjectSet;
	}

	@Override
	public Set<ProjectIdDTO> getEnrolledProjectIdDTOSet(StudentIdDTO theStudentIdDTO)
			throws InvalidArgumentException {
		
		userManager.checkUserExists(Student.class, theStudentIdDTO.getUsername());
		
		Set<ProjectIdDTO> aProjectIdDTOSet = new HashSet<ProjectIdDTO>();
		
		for (Project aProject : getEnrolledProjectSet(theStudentIdDTO)) {
				aProjectIdDTOSet.add(aProject.getId().getDTO());
		}
		
		System.out.println("Returning the project set ids");
		
		return aProjectIdDTOSet;
	}


	@Override
	public void checkEntityExists(ProjectId theEntityId)
			throws InvalidArgumentException {
		if (existsEntity(theEntityId) == false) {
			throw new InvalidArgumentException("The project you've searched for ("+ theEntityId + ") doesn't exists");
		}		
		
	}

}