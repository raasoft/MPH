package com.raaxxo.mph.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.ProfessorDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import util.ObjectUtility;

/**
 * Entity Bean implementation for the Database Entity Professor
 */	
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Professor extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6232187360687203050L;

	public Professor(String theUsername, PasswordHashDTO thePasswordHash, String theFirstName, String theLastName, Date theBirthday, String theEmail, String theTelephoneNumber) throws InvalidArgumentException {
		super(theUsername, thePasswordHash, theFirstName, theLastName, theBirthday, theEmail, theTelephoneNumber);
		
	}

	@SuppressWarnings("unused")
	private Professor() {}
	
	@OneToMany(targetEntity = Course.class, cascade=CascadeType.ALL)
	Set<Course> coursesHolded = new HashSet<Course>();

	public Set<Course> getCoursesHolded() {	
		return coursesHolded;
	}

	public void setCoursesHolded(Set<Course> theCoursesHolded) {
		this.coursesHolded = theCoursesHolded;
	}
	
	public void joinCourse(Course theCourse) {
		try {
			ObjectUtility.isNull(theCourse);
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		coursesHolded.add(theCourse);
	}
	
	@Override
	String getTag() {
		return "Professor";
	}

	@Override
	public ProfessorDTO getDTO() {
		
		ProfessorDTO aDTO = null;
		
		try {
			aDTO =  new ProfessorDTO(ProfessorIdDTO.getUser(getUsername()), getFirstName(), getLastName(), getEmail(), getTelephoneNumber(), getBirthday());
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		
		return aDTO;
	}
	
}
