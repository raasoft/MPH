package com.raaxxo.mph.testing.cases;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.DateInconsistencyException;
import mph.beans.exceptions.DeliverableNameAlreadyExists;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.ProjectNameAlreadyExists;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.sessions.ProfessorManagerRemote;

import org.junit.Before;
import org.junit.Test;

public class Test4AddProjects {
	
	Set<String> projectsList;

	Map<String, Date> deliverableDateMap;

	@Before
	public void setUp() throws Exception {
		
		projectsList = new HashSet<String>();
		projectsList.add("TestProject1");
		projectsList.add("TestProject2");
		projectsList.add("TestProject3");
		
		deliverableDateMap = new HashMap<String, Date>();
		deliverableDateMap.put("Deliverable1", Date.valueOf("2012-02-02"));
		deliverableDateMap.put("Deliverable2", Date.valueOf("2012-05-02"));
		deliverableDateMap.put("Deliverable3", Date.valueOf("2012-09-02"));

	}

	@Test
	public void test() throws Exception {
		System.out.println("");
		System.out.println("*** TEST ADD PROJECTS ***");
		System.out.println("");
		
		String professor = null;
		ProfessorManagerRemote professorManager = null;

		for (Entry<String, ProfessorManagerRemote> entry: TestUtility.getInstance().professorManagerMap.entrySet()) {

			professor =  entry.getKey();
			professorManager = entry.getValue();
			ProjectIdDTO projId = null;
			Set<CourseIdDTO> aSet = null;
			
			
			try {
				System.out.println("\n\n\nTrying to register projects of the professor: " + professor);
				TestUtility.getInstance().setAssertionShouldCatchAnException(false);
				aSet = professorManager.getCourseSet(new ProfessorIdDTO(professor));
			}
			catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }	
			catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
			finally { TestUtility.getInstance().checkIfCatchedAnException(); }

			for (CourseIdDTO courseIdDTO : aSet) {

				try {
					TestUtility.getInstance().setAssertionShouldCatchAnException(false);
					System.out.println("Trying to register projects for the course " + professorManager.getCourseInfo(courseIdDTO).getName());
				} 
				catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }	
				catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
				finally { TestUtility.getInstance().checkIfCatchedAnException(); }

				for (String projName : projectsList) {


					//IN ORDER TO CREATE A NEW PROJECT, WE NEED TO PREPARE A DELIVERABLEDTO SET

					Set<DeliverableDTO> aDeliverableSet = new HashSet<DeliverableDTO>();

					String deliverable = null;
					Date deadline = null;
					for (Entry<String, Date> entry2 : deliverableDateMap.entrySet()) {

						deliverable = entry2.getKey();
						deadline = entry2.getValue();

						//This is the main point: create a new deliverable dto object this way.
						aDeliverableSet.add( new DeliverableDTO(new DeliverableIdDTO(null,deliverable), deadline, "Sample description of " + deliverable,  new Double(5)) );

					}					

					System.out.println("Trying to register the project "+ projName + " for the course. Should NOT catch any exception.");
					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						//then register a new project
						projId = professorManager.registerNewProject(courseIdDTO, projName, Date.valueOf("2012-02-01"), Date.valueOf("2012-12-12"), "Sample description of " + projName, aDeliverableSet);
						System.out.println("Project registered successfully.");

					} 
					catch (InvalidArgumentException e) {	TestUtility.getInstance().showException(e); }
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
					catch (ProjectNameAlreadyExists e) { TestUtility.getInstance().showException(e); }
					catch (DateInconsistencyException e) { TestUtility.getInstance().showException(e); } 
					catch (DeliverableNameAlreadyExists e) { TestUtility.getInstance().showException(e); }
					catch (ForbiddenOperationException e) { TestUtility.getInstance().showException(e); }
					finally { TestUtility.getInstance().checkIfCatchedAnException(); }


					System.out.println("\nProject info:");
					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						System.out.println(professorManager.getProjectInfo(projId));
					} 
					catch (InvalidArgumentException e) { e.getStackTrace(); }
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
					finally { TestUtility.getInstance().checkIfCatchedAnException(); }

				}

				System.out.println("\n\nShow the updated course info:");
				try {
					TestUtility.getInstance().setAssertionShouldCatchAnException(false);
					System.out.println(professorManager.getCourseInfo(courseIdDTO));
				} 
				catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
				catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
				finally { TestUtility.getInstance().checkIfCatchedAnException(); }

				System.out.println("");
			}
		}

		System.out.println("");
		System.out.println("******************************");
		System.out.println("");
	}
	

}
