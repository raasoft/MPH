package com.raaxxo.mph.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.StudentDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import util.ObjectUtility;

/**
 * Entity Bean implementation for the Database Entity Student
 */	
@Entity
final public class Student extends User  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6232187360687203050L;
	
	public Student(String theUsername, PasswordHashDTO thePasswordHash, String theFirstName,
			String theLastName, Date theBirthday, String theEmail,
			String theTelephoneNumber) throws InvalidArgumentException {
		super(theUsername, thePasswordHash, theFirstName, theLastName, theBirthday,
				theEmail, theTelephoneNumber);
	}
	
	@SuppressWarnings("unused")
	private Student() {	}

	
	
	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Team.class)
	@JoinTable(	name = "StudentBelongsTeam" )
	Set<Team> studentTeams = new HashSet<Team>();


	@ManyToMany(cascade = CascadeType.ALL, targetEntity = Team.class)
	@JoinTable(	name = "StudentRequestsMembership" )
	Set<Team> studentTeamEnrollmentRequests = new HashSet<Team>();



	public Set<Team> getStudentTeams() {	
		return studentTeams;
	}


	public Set<Team> getStudentTeamEnrollmentRequests() {	
		return studentTeamEnrollmentRequests;
	}
	
	public void addTeamEnrollmentRequest(Team theTeam) throws InvalidArgumentException {
		ObjectUtility.isNull(theTeam);
		studentTeamEnrollmentRequests.add(theTeam);
	}
	
	public void joinTeam(Team theTeam) throws InvalidArgumentException {
		ObjectUtility.isNull(theTeam);
		studentTeams.add(theTeam);
	}
	
	public void leaveTeam(Team theTeam) {
		studentTeams.remove(theTeam);
	}
	
	
	public void removeTeamEnrollmentRequest(Team theTeam) {
		studentTeamEnrollmentRequests.remove(theTeam);
	}
	
	@Override
	String getTag() {
		return "Student";
	}

	@Override
	public StudentDTO getDTO() {
		
		StudentDTO aDTO = null;
		
		try {
			aDTO=  new StudentDTO(StudentIdDTO.getUser(getUsername()), getFirstName(), getLastName(), getEmail(), getTelephoneNumber(), getBirthday());
		} catch (InvalidArgumentException e) {
	
			e.printStackTrace();
		}
		
		return aDTO;
	}
	
	
}
