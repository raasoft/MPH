package com.raaxxo.mph.testing.cases;

import java.util.ArrayList;
import java.util.Set;

import mph.beans.dto.CourseDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.sessions.StudentManagerRemote;

import org.junit.Before;
import org.junit.Test;

public class Test3AddCourses {
	
	ArrayList<String> coursesList;

	@Before
	public void setUp() throws Exception {
		
		coursesList = new ArrayList<String>();
		coursesList.add("TestCourse1");
		coursesList.add("TestCourse2");
		coursesList.add("TestCourse3");

	}

	@Test
	public void test() throws Exception {
		
		System.out.println("");
		System.out.println("*** TEST ADD COURSES ***");
		System.out.println("");

		
			for (String username : TestUtility.getInstance().professorsUsername) {
				
				for (String course : coursesList) {

					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						System.out.println("\nTrying to create a new course or the professor " + username + ". Should NOT catch an exception.");
						TestUtility.getInstance().admin.newCourse(ProfessorIdDTO.getUser(username), course, "Sample description of course " + course);
					} 
					catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }
					finally{ TestUtility.getInstance().checkIfCatchedAnException(); }
				}
			}

			System.out.println("");
			System.out.println("******************************");
			System.out.println("");


			System.out.println("");
			System.out.println("*** TEST READ COURSES ***");
			System.out.println("");

			Set<CourseIdDTO> courseSet = null;

			for (StudentManagerRemote studentManager : TestUtility.getInstance().studentManagerMap.values()) {

					
					Set<ProfessorIdDTO> aSet = null;
					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						aSet = studentManager.getProfessorSet();
					} catch (UserNotLoggedInException e1) { TestUtility.getInstance().showException(e1); }
					finally{ TestUtility.getInstance().checkIfCatchedAnException(); }
					
					for (ProfessorIdDTO prof : aSet) 
					{
						try {

							TestUtility.getInstance().setAssertionShouldCatchAnException(false);
							System.out.println("\nTrying to obtain the courses set for the professor . Should NOT catch an exception");
							courseSet = studentManager.getCourseSet(prof);

						} 
						catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
						catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
						finally{ TestUtility.getInstance().checkIfCatchedAnException(); }


						System.out.println("\nNow listing the courses set.\n");
						for (CourseIdDTO aCourseId : courseSet) {

							CourseDTO aCourseDTO = null;
							try {
								TestUtility.getInstance().setAssertionShouldCatchAnException(false);
								aCourseDTO = studentManager.getCourseInfo(aCourseId);
								System.out.println(aCourseDTO);
							} 
							catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
							catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
							finally{ TestUtility.getInstance().checkIfCatchedAnException(); }


						}
					}

			}

			System.out.println("");
			System.out.println("******************************");
			System.out.println("");

	}
}

