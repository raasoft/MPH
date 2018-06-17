package com.raaxxo.mph.testing.cases;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;

import mph.beans.dto.CourseDTO;
import mph.beans.dto.ProjectDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.TeamNameAlreadyExists;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.sessions.StudentManagerRemote;

import org.junit.Before;
import org.junit.Test;

public class Test5AddTeams {

	Set<String> teamSet;
	
	@Before
	public void setUp() throws Exception {
		
		teamSet = new HashSet<String>();
		teamSet.add("TestTeam1");		
		
	}

	@Test
	public void test() throws Exception {
		
		System.out.println("");
		System.out.println("*** TEST ADD TEAMS ***");
		System.out.println("");
		
		Iterator<Entry<String, StudentManagerRemote>> iterator = TestUtility.getInstance().studentManagerMap.entrySet().iterator();
		
		Entry<String, StudentManagerRemote> firstStudent = iterator.next();

		String studentTeamOwner = firstStudent.getKey();
		StudentManagerRemote studentTeamOwnerManager = firstStudent.getValue();

		Entry<String, StudentManagerRemote> secondStudent = iterator.next();

		String studentNotTeamOwner = secondStudent.getKey();
		StudentManagerRemote studentNotTeamOwnerManager = secondStudent.getValue();

		TeamIdDTO aTeam = null;	
		Set<CourseIdDTO> aCourseSet = null;
		Set<ProjectIdDTO> aProjectSet = null;
		ProjectDTO aProj = null;
		CourseDTO aCourse = null;
		Set<JoinRequestIdDTO> aPendingSet = null;

		try {
			TestUtility.getInstance().setAssertionShouldCatchAnException(false);
			aCourseSet = studentTeamOwnerManager.getCourseSet();
		}
		catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
		finally { TestUtility.getInstance().checkIfCatchedAnException(); }

		System.out.println("\n\n\nTrying to register new teams with the student: " + studentTeamOwner);


		for (CourseIdDTO courseIdDTO : aCourseSet) {	

			try {
				TestUtility.getInstance().setAssertionShouldCatchAnException(false);
				aProjectSet = studentTeamOwnerManager.getProjectSet(courseIdDTO);
			} 
			catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
			catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
			finally { TestUtility.getInstance().checkIfCatchedAnException(); }

			for (ProjectIdDTO projectIdDTO : aProjectSet) {

				try {
					TestUtility.getInstance().setAssertionShouldCatchAnException(false);
					aProj = studentTeamOwnerManager.getProjectInfo(projectIdDTO);
					aCourse = studentTeamOwnerManager.getCourseInfo(courseIdDTO);
				}  
				catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
				catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
				finally { TestUtility.getInstance().checkIfCatchedAnException(); }

				for (String teamName : teamSet) {
					
					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						System.out.println("\nTrying to create a new team for the project " + aProj.getId().getProjectTitle() +  " of the course " + aCourse.getName());
						aTeam =	studentTeamOwnerManager.createTeam(aProj.getId(), teamName);
						System.out.println("Team created successfully.\n");
					} 
					catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
					catch (TeamNameAlreadyExists e) { TestUtility.getInstance().showException(e); } 
					catch (ForbiddenOperationException e) { TestUtility.getInstance().showException(e); }
					finally { TestUtility.getInstance().checkIfCatchedAnException(); }
					
					
					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(true);
						System.out.println("\nTrying to join a new team for the project " + aProj.getId().getProjectTitle() +  " of the course " + aCourse.getName() + " by the student " + studentTeamOwner);
						studentTeamOwnerManager.joinTeam(aTeam);
						System.out.println("Added a Team Join Request successfully.\n");
					} 
					catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
					catch (ForbiddenOperationException e) { TestUtility.getInstance().showException(e); }
					finally { TestUtility.getInstance().checkIfCatchedAnException(); }
					
					
					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						System.out.println("\nTrying to join a new team for the project " + aProj.getId().getProjectTitle() +  " of the course " + aCourse.getName() + " by the student " + studentNotTeamOwner);
						studentNotTeamOwnerManager.joinTeam(aTeam);
						System.out.println("Added a Team Join Request successfully.\n");
					} 
					catch (IllegalArgumentException e) { TestUtility.getInstance().showException(e); }
					catch (ForbiddenOperationException e) { TestUtility.getInstance().showException(e); }
					catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
					finally { TestUtility.getInstance().checkIfCatchedAnException(); }					
					
					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						System.out.println("\nTrying to get the pending join requests for the team: " + aTeam.getTeamName());
						aPendingSet = studentTeamOwnerManager.getPendingJoinRequests(aTeam);
						System.out.println("There are: " + aPendingSet.size() +  " join requests for the team: " + aTeam.getTeamName());
					} 
					catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
					finally { TestUtility.getInstance().checkIfCatchedAnException(); }					
					
					for (JoinRequestIdDTO aRequest : aPendingSet) {
						
						try {
							TestUtility.getInstance().setAssertionShouldCatchAnException(false);
							System.out.println("\nTrying to accept the join request made by the user " + aRequest.getStudentId().getUsername()+  " for joining the team " + aRequest.getTeamId().getTeamName());
							studentTeamOwnerManager.acceptPendingJoinRequest(aRequest);
							System.out.println("The join request made by the user " + aRequest.getStudentId().getUsername()+  " for joining the team " + aRequest.getTeamId().getTeamName() + " was accepted.");
						} 
						catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
						catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
						catch (ForbiddenOperationException e) { TestUtility.getInstance().showException(e); }
						finally { TestUtility.getInstance().checkIfCatchedAnException(); }					
					}
				}
			}
		}



		System.out.println("");
		System.out.println("******************************");
		System.out.println("");


	}

}
