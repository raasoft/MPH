package com.raaxxo.mph.sessions.remote;

import java.sql.Date;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.Query;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UsernameAlreadyExistsException;
import mph.beans.sessions.AdministratorManagerRemote;

import com.raaxxo.mph.entities.Course;
import com.raaxxo.mph.entities.Professor;
import com.raaxxo.mph.sessions.local.EntityUtilityLocal;
import com.raaxxo.mph.sessions.local.ProfessorFactoryLocal;

/**
 * 
 * Stateful session bean implementation of the AdministratorManagerRemote remote interface. It is used ONLY for testing purposes.<br/>
 * The client module of MPH never calls for such a remote interface. In the next version of MPH will be created a new user class, the "Administrator" class,
 * and this object will contain all the method callable by an "Administrator".
 * 
 */
@Stateful
@Remote(AdministratorManagerRemote.class)
public class AdministratorManager extends BasicUserManager implements AdministratorManagerRemote {
	
	@EJB protected ProfessorFactoryLocal professorFactory;
	@EJB protected EntityUtilityLocal searchUtility;
	
	@Override
	public void registerNewUser(String theUsername,
			PasswordHashDTO thePasswordHash, String theFirstName,
			String theLastName, Date theBirthday, String theEmail,
			String theTelephoneNumber) throws UsernameAlreadyExistsException,
			InvalidArgumentException {
		
		userManager.registerNewUser(professorFactory, theUsername, thePasswordHash, theFirstName, theLastName, theBirthday, theEmail, theTelephoneNumber);
		
	}
	
	@Override
	public CourseIdDTO newCourse(ProfessorIdDTO theProfessorId, String theName, String theDescription) {
		
		String aProfessorUsername = theProfessorId.getUsername();
		
		Query q = em.createQuery("SELECT p FROM Professor p WHERE p.username = :n");
		q.setParameter("n", aProfessorUsername);
				
		Professor aProfessor = (Professor) q.getSingleResult();

		System.out.println("Creating a new course helded by the professor " + aProfessor.getUsername());
		
		Course newCourse = new Course(aProfessor, theName, theDescription);
		
		em.persist(newCourse);
		
		System.out.println("Let a professor join a new course");
		aProfessor.joinCourse(newCourse);
		System.out.println("Professor joined the new course");
		System.out.println("Merging professor");
		em.merge(aProfessor);
		System.out.println("Professor merged");
		
		System.out.println("Flushing");
		em.flush();
		
		CourseIdDTO courseId = new CourseIdDTO(new Long(newCourse.getUid()));
		
		return courseId;
		
	}

	

}
