package com.raaxxo.mph.sessions.local;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.Query;

import mph.beans.dto.TeamDTO;
import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.TeamNameAlreadyExists;

import org.jboss.ejb3.annotation.IgnoreDependency;

import util.CalendarUtility;
import util.ObjectUtility;
import util.StringUtility;

import com.raaxxo.mph.entities.Course;
import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.Student;
import com.raaxxo.mph.entities.Team;
import com.raaxxo.mph.entities.classids.TeamId;

@Stateless
public class TeamManager implements TeamManagerLocal {
	
	@EJB ProjectManagerLocal projectManager;
	@EJB EntityUtilityLocal entityUtility;
	@EJB @IgnoreDependency private UserManagerLocal userManager;
	@EJB CourseManagerLocal courseManager;


	@Override
	public void acceptPendingJoinRequest(JoinRequestIdDTO theJoinRequestId, StudentIdDTO theStudentOperatorIdDTO)
			throws InvalidArgumentException, ForbiddenOperationException {
		
		userManager.checkUserExists(Student.class, theStudentOperatorIdDTO.getUsername());
		userManager.checkUserExists(Student.class, theJoinRequestId.getStudentId().getUsername());
		
		Team aTeam =  reconstructEntity(theJoinRequestId.getTeamId());
		checkEntityExists(aTeam.getId());
		
		Student aStudentOperator = userManager.getEntity(Student.class, theStudentOperatorIdDTO.getUsername());
		Student aStudent = userManager.getEntity(Student.class, theJoinRequestId.getStudentId().getUsername());
		
		if (aTeam.getStudentEnrollmentRequests().contains(aStudent) == false || aStudent.getStudentTeamEnrollmentRequests().contains(aTeam) == false) {
			throw new InvalidArgumentException("The student  " + aStudent.getDTO().getId().getUsername() +" to be accepted isn't in enrollment requests set for the team " + aTeam.getDTO().getId().getTeamName());
		}
		
		if (aTeam.getStudentEnrollmentRequests().size() >= Team.TEAM_MAXIMUM_SIZE) {
			throw new ForbiddenOperationException("Cannot accept this membership request: the team has already reached its maximum size  (  " + Team.TEAM_MAXIMUM_SIZE +"  students).");
		}
		
		checkStudentCanJoinTeam(aTeam, aStudent);
		
		if (aTeam.getStudentList().contains(aStudentOperator) == false) {
			throw new ForbiddenOperationException("The student "+ aStudentOperator.getUsername() +" didn't join the team, so he/she can't accept the enrollment request");
		}

		
		Set<Team> aSet = new HashSet<Team>();
		
		
		for (Team anotherTeam : aStudent.getStudentTeamEnrollmentRequests() ) {
			aSet.add(anotherTeam);
		}
		
		for (Team anotherTeam : aStudent.getStudentTeamEnrollmentRequests() ) {
			if (anotherTeam.getId().getProject().equals(aTeam.getId().getProject())) {
				aSet.remove(anotherTeam);
			}
		}
		
		aStudent.getStudentTeamEnrollmentRequests().clear();
		
		for (Team anotherTeam : aSet) {
			aStudent.getStudentTeamEnrollmentRequests().add(anotherTeam);
		}
		
		
		aTeam.removeEnrollmentRequest(aStudent);
		aTeam.addStudent(aStudent);
		
		aStudent.removeTeamEnrollmentRequest(aTeam);
		aStudent.joinTeam(aTeam);
		

		entityUtility.getEntityManager().merge(aTeam);	
		entityUtility.getEntityManager().merge(aStudent);
		
		try {
		entityUtility.getEntityManager().flush();
		}
		catch (Exception e) {
			throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
		}
	}

	@Override
	public TeamIdDTO createTeam(ProjectIdDTO theProjectIdDTO, String theTeamName,
			Student theStudent) throws InvalidArgumentException, TeamNameAlreadyExists, ForbiddenOperationException {
		
		ObjectUtility.isNull(theProjectIdDTO);
		StringUtility.isNullOrEmpty(theTeamName, "Team Name");
		ObjectUtility.isNull(theStudent);
		userManager.checkUserExists(Student.class, theStudent.getUsername());
		
		Project aProj = projectManager.reconstructEntity(theProjectIdDTO);
		
		Date projStartDate = aProj.getStartDate();
		
		if (existsEntity(new TeamId(aProj,theTeamName))) {
			throw new TeamNameAlreadyExists("The team name you are trying to register already exists.");
		} else if (	CalendarUtility.after(	projStartDate, entityUtility.getServerTimestamp()) ) {
			throw new ForbiddenOperationException("You cannot create a team if the project is already started.");
		} else {
	
			if (getTeam(theStudent.getDTO().getId(), theProjectIdDTO) != null) {
				throw new ForbiddenOperationException("You cannot create a team if you have already joined the team " + getTeam(theStudent.getDTO().getId(), theProjectIdDTO).getTeamName());
			}
			
			System.out.println("The team doesn't already exist so MPH can create a new team");
			
			Team newTeam = new Team(aProj, theTeamName); 
					
			entityUtility.getEntityManager().persist(newTeam);
			entityUtility.getEntityManager().merge(theStudent);
			
			theStudent.joinTeam(newTeam);
			newTeam.addStudent(theStudent);
			
			System.out.println("Now the student is enrolled in the following teams: " + theStudent.getStudentTeams());
			
			try {
			entityUtility.getEntityManager().flush();
			}catch (Exception e) {
				throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
			}
			return newTeam.getId().getDTO();
		
		}
	}


	@Override
	public boolean existsEntity(TeamId theTeamId) throws InvalidArgumentException {
	
		Team aTeam = getEntity(theTeamId);

		if (aTeam  == null) {
			System.out.println("Team doesn't exist");
			return false;
		}
		else {
			System.out.println("Team exists");
			return true;
		}
		
	}

	@Override
	public Team getEntity(TeamId theTeamId) throws InvalidArgumentException {
	
		ObjectUtility.isNull(theTeamId);
		
		System.out.println("Trying to get the team with id: " + theTeamId);
		
		Team aTeam = (Team) entityUtility.findOneEntity(Team.class, theTeamId);
		
		if (aTeam != null)
			System.out.println("Succesfully got the team with id: " + theTeamId);
		else
			System.out.println("Can't  find the team with id: " + theTeamId);
		
		return aTeam;
	}

	@Override
	public Team reconstructEntity(TeamIdDTO theTeamIdDTO)
			throws InvalidArgumentException {

		ObjectUtility.isNull(theTeamIdDTO);
		
		Project aProject = projectManager.reconstructEntity(theTeamIdDTO.getProjectId());
		
		return getEntity( new TeamId(aProject,theTeamIdDTO.getTeamName()) );
		
	}
	

	@Override
	public Set<TeamIdDTO> getTeamIdDTOSet(ProjectIdDTO theProjectIdDTO)
			throws InvalidArgumentException {
		
		ObjectUtility.isNull(theProjectIdDTO);
		
		System.out.println("Trying to get the team id dto set for the project: " + theProjectIdDTO);
		
		Project aProject = projectManager.reconstructEntity(theProjectIdDTO);
		
		Set<Team> theTeamSet = getTeamSet(aProject);
		Set<TeamIdDTO> theTeamIdDTOSet = new HashSet<TeamIdDTO>();
		
		TeamIdDTO aTeamId = null;
		
		System.out.println("Building the id dto set");
		
		for (Team aTeam : theTeamSet) 
		{
			aTeamId = aTeam.getId().getDTO();
			
			if (aTeamId == null) 
				throw new InvalidArgumentException("Internal error database.");
			
			theTeamIdDTOSet.add(aTeamId);
		
		}
		
		System.out.println("Returning the team id dto set for the project: " + theProjectIdDTO);
		
		return theTeamIdDTOSet;
		
	}

	@Override
	public TeamDTO getTeamInfo(TeamIdDTO theTeamIdDTO)
			throws InvalidArgumentException {
			
		ObjectUtility.isNull(theTeamIdDTO);
		
		System.out.println("Trying to get the team DTO for the team with id: " + theTeamIdDTO);
		
		Team aTeam = reconstructEntity(theTeamIdDTO);

		TeamDTO aTeamDTO = aTeam.getDTO();

		System.out.println("Created the team DTO. Returning the team DTO");
		return aTeamDTO;

	}

	@Override
	public Set<Team> getTeamSet(Project theProject)
			throws InvalidArgumentException {
		
		Query q = entityUtility.getEntityManager().createQuery("FROM Team t WHERE t.id.project.id = :n");
		q.setParameter("n", theProject.getId());		
		Set<Team> theTeamSet = new HashSet<Team>();
	
		System.out.println("Getting the team set for the project selected");
		for (Object t : q.getResultList()) 
		{
			theTeamSet.add((Team) t);	
		}
		
		System.out.println("Team set cardinality: " + theTeamSet.size());
		
		return theTeamSet;
	}

	@Override
	public void joinTeam(TeamIdDTO theTeamIdDTO, StudentIdDTO theStudentIdDTO)
			throws InvalidArgumentException, ForbiddenOperationException {
		
		System.out.println("Trying to add to the enrollment requests set of the team: " + theTeamIdDTO + " by the student " + theStudentIdDTO);
	
		userManager.checkUserExists(Student.class, theStudentIdDTO.getUsername());
		Team aTeam =  reconstructEntity(theTeamIdDTO);
		checkEntityExists(aTeam.getId());
		Student aStudent = userManager.getEntity(Student.class, theStudentIdDTO.getUsername());
		
		if (aTeam.getEnrollmentRequestIdDTO().contains(aStudent) || aStudent.getStudentTeamEnrollmentRequests().contains(aTeam)) {
			throw new ForbiddenOperationException("The student " + aStudent.getUsername() + " has already sent a join request for the team " + aTeam.getId().getName());
		}
		
		
		checkStudentCanJoinTeam(aTeam, aStudent);
		
		System.out.println("Before the enrollment request the team had: " + aTeam.getStudentEnrollmentRequests().size() + " and the student had: " + aStudent.getStudentTeamEnrollmentRequests().size());
		
		entityUtility.getEntityManager().merge(aTeam);
		entityUtility.getEntityManager().merge(aStudent);
		
		aTeam.addEnrollmentRequest(aStudent);		
		aStudent.addTeamEnrollmentRequest(aTeam);

try {		
		entityUtility.getEntityManager().flush();
}catch (Exception e) {
	throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
}	
		System.out.println("After the enrollment request the team has: " + aTeam.getStudentEnrollmentRequests().size() + " and the student has: " + aStudent.getStudentTeamEnrollmentRequests().size());
	}
	
	void checkStudentCanJoinTeam(Team theTeam, Student theStudent) throws ForbiddenOperationException, InvalidArgumentException {
		
		Team aTeam = theTeam;
		Student aStudent = theStudent;
		
		if (aTeam.getStudentList().size() == Team.TEAM_MAXIMUM_SIZE)  {
			throw new ForbiddenOperationException("The team " + aTeam.getId().getName() + " has already reached its maximum size: max " + Team.TEAM_MAXIMUM_SIZE + " students are allowed per team.");
		}
		
		if (aTeam.isClosed())  {
			throw new ForbiddenOperationException("The team " + aTeam.getId().getName() + " is closed");
		}
		
		if (aStudent.getStudentTeams().contains(aTeam)) {
			throw new ForbiddenOperationException("The student " + aStudent.getUsername() + " has already joined the team " + aTeam.getId().getName());
		} else {
		
			TeamIdDTO aTeamId = getTeam(aStudent.getDTO().getId(), aTeam.getDTO().getId().getProjectId());

			if (aTeamId != null) {
				throw new ForbiddenOperationException("The student " + aStudent.getUsername() + " has already joined the team " + aTeamId.getTeamName() + " for the project " +aTeam.getId().getProject().getId().getTitle());
			}
			
		}
		
		
	}

	@Override
	public TeamIdDTO getTeam(StudentIdDTO theStudentIdDTO,
			ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException {
		
		userManager.checkUserExists(Student.class, theStudentIdDTO.getUsername());
		ObjectUtility.isNull(theProjectIdDTO);
		
		Student aStudent = userManager.getEntity(Student.class, theStudentIdDTO.getUsername());
		Project aProject = projectManager.reconstructEntity(theProjectIdDTO);
		
		for (Team aTeam : aStudent.getStudentTeams()) {
			
			if (aTeam.getId().getProject().equals(aProject)) {
				return aTeam.getId().getDTO();
			}
		}
		
		return null;
	}

	@Override
	public void checkEntityExists(TeamId theTeamId)
			throws InvalidArgumentException {
		
		if (existsEntity(theTeamId) == false) {
			throw new InvalidArgumentException("The team you've searched for ("+ theTeamId.getName() + ") doesn't exists");
		}
		
	}

	@Override
	public void setTeamEnrollmentRequests(TeamIdDTO theTeamIdDTO,
			StudentIdDTO theStudentIdDTO, boolean areRequestsOpen)
			throws InvalidArgumentException, ForbiddenOperationException {
		
		System.out.println("Trying to change the Team Enrollment Requests for the team " + theTeamIdDTO + " by the student " + theStudentIdDTO);
		
		Team aTeam = reconstructEntity(theTeamIdDTO);
		checkEntityExists(aTeam.getId());
		
		userManager.checkUserExists(Student.class, theStudentIdDTO.getUsername());
		Student aStudent = userManager.getEntity(Student.class, theStudentIdDTO);
		
		if (aTeam.getStudentList().contains(aStudent) == false) {
			throw new ForbiddenOperationException("Student " + aStudent.getUsername() + " can't edit the team " + aTeam.getId().getName() + " because he/she is not a team member");
		}
		
		if (CalendarUtility.after(	aTeam.getId().getProject().getStartDate(), entityUtility.getServerTimestamp()) ) {
			aTeam.setClosed(true);
			throw new ForbiddenOperationException("The project "+ aTeam.getId().getProject().getId().getTitle() +" has already started, so the team must be closed");
		} else {
			aTeam.setClosed(areRequestsOpen);
		}
		
		if (aTeam.isClosed()) {
			System.out.println("Team Enrollment Requests changed to CLOSE");
		} else {
			System.out.println("Team Enrollment Requests changed to OPEN");
		}
		
	}

	@Override
	public Set<JoinRequestIdDTO> getPendingJoinRequest(TeamIdDTO theTeamId) throws InvalidArgumentException {

		Set<JoinRequestIdDTO> aSet = new HashSet<JoinRequestIdDTO>();
		
		System.out.println("Trying to retrevie pending join requests for the team " + theTeamId);

		
		if (theTeamId != null) {
		
			Team aTeam = reconstructEntity(theTeamId);
			ObjectUtility.isNull(aTeam);
			checkEntityExists(aTeam.getId());
			
			System.out.println("Retrieving pending join requests for the team " + theTeamId + ". They should be: " + aTeam.getStudentEnrollmentRequests().size() );
			
			aSet = aTeam.getEnrollmentRequestIdDTO();
			
			System.out.println("Retrieved " + aSet.size() + " pending join requests");
			
		}
		
		
		return aSet;

	}

	@Override
	public void leaveTeam(TeamIdDTO theTeamIdDTO, StudentIdDTO theStudentIdDTO)
			throws InvalidArgumentException, ForbiddenOperationException {

		System.out.println("Trying to  let the student " + theStudentIdDTO + " leave the team " + theTeamIdDTO);
		
		Team aTeam = reconstructEntity(theTeamIdDTO);
		ObjectUtility.isNull(aTeam);
		checkEntityExists(aTeam.getId());

		userManager.checkUserExists(Student.class, theStudentIdDTO.getUsername());
		Student aStudent = userManager.getEntity(Student.class, theStudentIdDTO);

		if (aTeam.getStudentList().contains(aStudent) == false) {
			throw new ForbiddenOperationException("Student " + aStudent.getUsername() + " can't leave the team " + aTeam.getId().getName() + " because he/she is not a team member");
		}

		aTeam.removeStudent(aStudent);
		aStudent.leaveTeam(aTeam);

		entityUtility.getEntityManager().merge(aTeam);
		entityUtility.getEntityManager().merge(aStudent);
try {
		entityUtility.getEntityManager().flush();
}catch (Exception e) {
	throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
}	
		System.out.println("The student " + theStudentIdDTO + " leaved the team " + theTeamIdDTO + " succesfully");
		
		if (aTeam.getStudentList().size() == 0) {

			System.out.println("The team " + aTeam.getId().getName()+ " is now without students. Removing it.");
			
			for (Student aStud : aTeam.getStudentEnrollmentRequests()) {
				aStud.getStudentTeamEnrollmentRequests().remove(aTeam);
			}
			aTeam.getStudentEnrollmentRequests().clear();
			
			entityUtility.getEntityManager().remove(aTeam);
			
			try {
			entityUtility.getEntityManager().flush();
			}catch (Exception e) {
				throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
			}
			System.out.println("The team " + aTeam.getId().getName()+ " has been removed.");

		}
	}

	/** 
	 * This method closes the project that has already started (i.e. the projects that satisfy
	 * the condition CalendarUtility.before(now, aStartDate) && CalendarUtility.after(now, aEndDate)
	 * where 'now' is the {@link EntityUtility#getServerTimestamp()}, 'aStartDate' is the project.getStartDate()
	 * and 'aEndDate' is the project getEndDate() method. 
	 */
	@Override
    @Schedule(minute="*/30", hour="*", persistent = false)
    public void closeStartedProjects() {

		System.out.println("Automatic timer checking for started projects on " + entityUtility.getServerTimestamp().toString());

		Date now = entityUtility.getServerTimestamp();
		
		try {
			
			for (Course aCourse : courseManager.getCourseSet()) {
				for (Project aProject : projectManager.getProjectSet(aCourse)) {

					Date aStartDate = new Date(aProject.getStartDate().getTime());
					Date aEndDate = new Date(aProject.getEndDate().getTime());					
					
					if ( CalendarUtility.before(now, aStartDate) && CalendarUtility.after(now, aEndDate) ) {
						System.out.println("Closing all the team of project " + aProject.getDTO().getId());

						for (Team aTeam : getTeamSet(aProject)) {

							aTeam.setClosed(true);
							for (Student stud : aTeam.getStudentEnrollmentRequests()) {
								stud.removeTeamEnrollmentRequest(aTeam);
							}
							aTeam.getStudentEnrollmentRequests().clear();

						}						
					}
				}
			}
		} catch (InvalidArgumentException e) { e.printStackTrace();	}
	}

	@Override
	public void declinePendingJoinRequest(JoinRequestIdDTO theJoinRequestId,
			StudentIdDTO theStudentOperatorIdDTO) throws InvalidArgumentException,
			ForbiddenOperationException {
		
		userManager.checkUserExists(Student.class, theStudentOperatorIdDTO.getUsername());
		
		Team aTeam =  reconstructEntity(theJoinRequestId.getTeamId());
		checkEntityExists(aTeam.getId());
		
		Student aStudentOperator = userManager.getEntity(Student.class, theStudentOperatorIdDTO.getUsername());
		
		if (aTeam.getStudentList().contains(aStudentOperator) == false) {
			throw new ForbiddenOperationException("The student "+ aStudentOperator.getUsername() +" didn't join the team, so he/she can't decline the enrollment request");
		}
		
		removePendingJoinRequest(theJoinRequestId, theStudentOperatorIdDTO);
		
	}

	@Override
	public void removePendingJoinRequest(JoinRequestIdDTO theJoinRequestId, StudentIdDTO theStudentIdDTO) throws InvalidArgumentException {
		
		userManager.checkUserExists(Student.class, theStudentIdDTO.getUsername());
		
		Team aTeam =  reconstructEntity(theJoinRequestId.getTeamId());
		checkEntityExists(aTeam.getId());
		
		Student aStudent = userManager.getEntity(Student.class, theJoinRequestId.getStudentId().getUsername());
		
		if (aTeam.getStudentEnrollmentRequests().contains(aStudent) == false || aStudent.getStudentTeamEnrollmentRequests().contains(aTeam) == false) {
			throw new InvalidArgumentException("The student  " + aStudent.getDTO().getId().getUsername() +"  isn't in enrollment requests set for the team " + aTeam.getId().getName());
		}
		
		entityUtility.getEntityManager().merge(aTeam);	
		entityUtility.getEntityManager().merge(aStudent);
		
		aTeam.removeEnrollmentRequest(aStudent);
		aStudent.removeTeamEnrollmentRequest(aTeam);
		try {
		entityUtility.getEntityManager().flush();
		}catch (Exception e) {
			throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
		}
		
	}

	@Override
	public Set<JoinRequestIdDTO> getPendingJoinRequestsSetIdDTO(ProjectIdDTO theProjectIdDTO, StudentIdDTO theStudentIdDTO) throws InvalidArgumentException {
		
		userManager.checkUserExists(Student.class, theStudentIdDTO.getUsername());
		
		Set<JoinRequestIdDTO> aSet = new HashSet<JoinRequestIdDTO>();

		if (theProjectIdDTO != null) {
			
			Project aProject =  projectManager.reconstructEntity(theProjectIdDTO);
			projectManager.checkProjectExists(aProject.getId());

			Student aStudent = userManager.getEntity(Student.class, theStudentIdDTO.getUsername());

			for (Team aTeam : aStudent.getStudentTeamEnrollmentRequests()) {
				
				if (aTeam.getId().getProject().equals(aProject))
					aSet.add(new JoinRequestIdDTO(aTeam.getId().getDTO(), aStudent.getDTO().getId()));
				
			}
			
		}
		
		return aSet;
		
		
	}

	@Override
	public Set<StudentIdDTO> getStudentComposingTeamIdDTO(TeamIdDTO theTeamIdDTO)
			throws InvalidArgumentException {
				
		Team aTeam = reconstructEntity(theTeamIdDTO);
		checkEntityExists(aTeam.getId());
		
		Set<StudentIdDTO> aSet = new HashSet<StudentIdDTO>();
		
		for (Student aStudent : aTeam.getStudentList()) {
			
			aSet.add(aStudent.getDTO().getId());
			
		}
		
		return aSet;

		
	}

}



