package com.raaxxo.mph.testing.cases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import mph.beans.dto.IncomingFileDTO;
import mph.beans.dto.OutcomingFileDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.DownloadErrorException;
import mph.beans.exceptions.FileTooLargeException;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UploadErrorException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.sessions.ProfessorManagerRemote;
import mph.beans.sessions.StudentManagerRemote;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class Test6AddArtifacts {
	
	Set<String> artifactSet;

	@Rule
    public TemporaryFolder folder = new TemporaryFolder();
	
	@Before
	public void setUp() throws Exception {
		
		artifactSet = new HashSet<String>();
		//artifactSet.add("./testFiles/test.pdf");		
		artifactSet.add("./testFiles/test.jpeg");	
		
	}

	@Test
	public void test() throws Exception {
		
		
		System.out.println("");
		System.out.println("*** TEST ADD ARTIFACTS ***");
		System.out.println("");
		
		String studentUsername = null;
		StudentManagerRemote studentManager = null;
		
		String professorUsername = null;
		ProfessorManagerRemote professorManager = null;
		
		Set<CourseIdDTO> aCourseSet = null;
		Set<ProjectIdDTO> aProjectSet = null;
		Set<DeliverableIdDTO> aDeliverableSet = null;

		TeamIdDTO aTeam = null;
		ArtifactIdDTO anUploadedArtifact = null;

		for (Entry<String, StudentManagerRemote> aStudent : TestUtility.getInstance().studentManagerMap.entrySet()) {
			studentUsername = aStudent.getKey();
			studentManager = aStudent.getValue();


			for (Entry<String, ProfessorManagerRemote> aProfessor : TestUtility.getInstance().professorManagerMap.entrySet()) {

				professorUsername = aProfessor.getKey();
				professorManager = aProfessor.getValue();

				try {
					TestUtility.getInstance().setAssertionShouldCatchAnException(false);
					aCourseSet = studentManager.getCourseSet(new ProfessorIdDTO(professorUsername));
				} 
				catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
				catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
				finally { TestUtility.getInstance().checkIfCatchedAnException();}

				for (CourseIdDTO aCourse : aCourseSet) {

					TestUtility.getInstance().setAssertionShouldCatchAnException(false);
					try {
						aProjectSet = studentManager.getProjectSet(aCourse);
					} 
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
					catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
					finally { TestUtility.getInstance().checkIfCatchedAnException();}

					for (ProjectIdDTO aProject : aProjectSet) {

						try {
							TestUtility.getInstance().setAssertionShouldCatchAnException(false);
							aTeam =	studentManager.getProjectTeam(new StudentIdDTO(studentUsername), aProject);
							if (aTeam == null) {
								System.out.println("The student " + studentUsername + " has not a team");
								continue;
							}
						} 
						catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
						catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }

						try {
							TestUtility.getInstance().setAssertionShouldCatchAnException(false);
							aDeliverableSet = studentManager.getDeliverableSet(aProject);
						} 
						catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
						catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }

						for (DeliverableIdDTO aDeliverable : aDeliverableSet) {

							for (String aFilepath : artifactSet) {

								try {
									TestUtility.getInstance().setAssertionCatchedAnException(false);
									System.out.println("Trying to upload a new artifact");
									anUploadedArtifact = studentManager.uploadArtifact(aTeam, aDeliverable, new OutcomingFileDTO(aFilepath));
									System.out.println("Artifact uploaded successfully");

								} 
								catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
								catch (ForbiddenOperationException e) { TestUtility.getInstance().showException(e); } 
								catch (UploadErrorException e) { TestUtility.getInstance().showException(e); } 
								catch (UserNotLoggedInException e)  { TestUtility.getInstance().showException(e); } 
								catch (FileNotFoundException e) { TestUtility.getInstance().showException(e); }
								catch (FileTooLargeException e) { TestUtility.getInstance().showException(e); }
								finally { TestUtility.getInstance().checkIfCatchedAnException(); }

								try {
									TestUtility.getInstance().setAssertionCatchedAnException(false);
									System.out.println("Trying to set a score to the artifact");
									professorManager.setArtifactScore(anUploadedArtifact, new Double(1+new Random().nextDouble()*9) );
									System.out.println("Score set successfully");
								}
								catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
								catch (ForbiddenOperationException e) { TestUtility.getInstance().showException(e); } 
								catch (UserNotLoggedInException e)  { TestUtility.getInstance().showException(e); } 
								finally { TestUtility.getInstance().checkIfCatchedAnException(); }

								try {
									TestUtility.getInstance().setAssertionCatchedAnException(false);
									System.out.println("Re-get artifact just uploaded");
									anUploadedArtifact = studentManager.getArtifact(anUploadedArtifact.getDeliverableId(), anUploadedArtifact.getTeamId());
									System.out.println("Artifact got successfully");
								} 
								catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
								catch (UserNotLoggedInException e)  { TestUtility.getInstance().showException(e); } 
								finally { TestUtility.getInstance().checkIfCatchedAnException(); }


								try {
									TestUtility.getInstance().setAssertionCatchedAnException(false);
									System.out.println("Get artifact info just uploaded");
									System.out.println(studentManager.getArtifactInfo(anUploadedArtifact));
									System.out.println("Artifact info showed successfully");
								} 
								catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
								catch (UserNotLoggedInException e)  { TestUtility.getInstance().showException(e); } 
								finally { TestUtility.getInstance().checkIfCatchedAnException(); }

								try {
									TestUtility.getInstance().setAssertionCatchedAnException(false);
									System.out.println("Trying to download a new artifact");
									IncomingFileDTO inFile = studentManager.downloadArtifact(anUploadedArtifact);
									inFile.saveAs("./"+inFile.getFileName());
									System.out.println("Artifact downloaded successfully");

								} catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
								catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
								catch (DownloadErrorException e) { TestUtility.getInstance().showException(e); } 
								catch (IOException e) { TestUtility.getInstance().showException(e); }
								finally {TestUtility.getInstance().checkIfCatchedAnException();}

							}
							
						}	
						
						try {
							TestUtility.getInstance().setAssertionCatchedAnException(false);
							aTeam = studentManager.getProjectTeam(new StudentIdDTO(studentUsername), aProject);
						} 
						catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
						catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
						
						try {
							TestUtility.getInstance().setAssertionCatchedAnException(false);
							System.out.println("++++ The team " + aTeam.getTeamName() + " average score is: "+ studentManager.getTeamInfo(aTeam).getTeamScore());
						} 
						catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
						catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
						
					}	
				}
			}
		}

		System.out.println("");
		System.out.println("******************************");
		System.out.println("");

		System.out.println("");
		System.out.println("*** TEST SHOW ARTIFACTS PER TEAM AND PER DELIVERABLE ***");
		System.out.println("");
		
		professorUsername = null;
		professorManager = null;
		
		aCourseSet = null;
		aProjectSet = null;
		aDeliverableSet = null;
		Set<TeamIdDTO> aTeamSet = new HashSet<TeamIdDTO>();
		Set<ArtifactIdDTO> anArtifactSet = new HashSet<ArtifactIdDTO>();


		aTeam = null;

		for (Entry<String, ProfessorManagerRemote> aProfessor : TestUtility.getInstance().professorManagerMap.entrySet()) {

			professorUsername = aProfessor.getKey();
			professorManager = aProfessor.getValue();

			try {
				TestUtility.getInstance().setAssertionShouldCatchAnException(false);
				aCourseSet = studentManager.getCourseSet(new ProfessorIdDTO(professorUsername));
			} 
			catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
			catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
			finally { TestUtility.getInstance().checkIfCatchedAnException();}

			for (CourseIdDTO aCourse : aCourseSet) {

				TestUtility.getInstance().setAssertionShouldCatchAnException(false);
				try {
					aProjectSet = studentManager.getProjectSet(aCourse);
				} 
				catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); } 
				catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
				finally { TestUtility.getInstance().checkIfCatchedAnException();}

				for (ProjectIdDTO aProject : aProjectSet) {
					
					System.out.println("\n\nPROJECT : " + aProject.getProjectTitle() + "\nARTIFACTS PER DELIVERABLE" );


					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						aDeliverableSet = studentManager.getDeliverableSet(aProject);
					} 
					catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
					finally { TestUtility.getInstance().checkIfCatchedAnException();}


					for (DeliverableIdDTO aDeliverable : aDeliverableSet) {

						try {
							TestUtility.getInstance().setAssertionShouldCatchAnException(false);
							anArtifactSet = professorManager.getArtifactSetByDeliverable(aDeliverable);
							System.out.println("\n\n$$$ deliverable " + aDeliverable.getDeliverableName() + " $$$\n" );
						} 
						catch (InvalidArgumentException e)  { TestUtility.getInstance().showException(e); } 
						catch (UserNotLoggedInException e)  { TestUtility.getInstance().showException(e); } 
						finally { TestUtility.getInstance().checkIfCatchedAnException(); }
						System.out.println("\n" );
						
						
						for (ArtifactIdDTO anArtifactId : anArtifactSet) {
							
							try {
								TestUtility.getInstance().setAssertionShouldCatchAnException(false);
								System.out.println("");
								System.out.println(professorManager.getArtifactInfo(anArtifactId).getId());
								System.out.println("");
							}
							catch (InvalidArgumentException e)  { TestUtility.getInstance().showException(e); } 
							catch (UserNotLoggedInException e)  { TestUtility.getInstance().showException(e); }
							finally { TestUtility.getInstance().checkIfCatchedAnException(); }

						}
					}
					
					System.out.println("\n\nPROJECT : " + aProject.getProjectTitle() + "\nARTIFACTS PER TEAM" );

					
					try {
						TestUtility.getInstance().setAssertionShouldCatchAnException(false);
						aTeamSet = studentManager.getTeamSet(aProject);
					} 
					catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); } 
					catch (UserNotLoggedInException e) { TestUtility.getInstance().showException(e); }
					finally { TestUtility.getInstance().checkIfCatchedAnException(); }

					for (TeamIdDTO anotherTeam: aTeamSet) {

						try {
							TestUtility.getInstance().setAssertionShouldCatchAnException(false);
							anArtifactSet = professorManager.getArtifactSetByTeam(anotherTeam);
							System.out.println("\n\n@@@ TEAM " + anotherTeam.getTeamName() + " @@@");
						} 
						catch (InvalidArgumentException e)  { TestUtility.getInstance().showException(e); } 
						catch (UserNotLoggedInException e)  { TestUtility.getInstance().showException(e); } 
						finally { TestUtility.getInstance().checkIfCatchedAnException(); }

						for (ArtifactIdDTO anArtifactId : anArtifactSet) {
							
							try {
								System.out.println("\n");

								TestUtility.getInstance().setAssertionShouldCatchAnException(false);
								System.out.println(professorManager.getArtifactInfo(anArtifactId).getId());
							}
							catch (InvalidArgumentException e)  { TestUtility.getInstance().showException(e); } 
							catch (UserNotLoggedInException e)  { TestUtility.getInstance().showException(e); }
							finally { TestUtility.getInstance().checkIfCatchedAnException(); }

						}
					}
					
					
					
					
					
					
					
					
				}	
			}	
		}

		System.out.println("");
		System.out.println("******************************");
		System.out.println("");
		

	}

}
