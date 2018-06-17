package com.raaxxo.mph.sessions.local;

import java.sql.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.raaxxo.mph.entities.Student;
import com.raaxxo.mph.entities.User;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.exceptions.InvalidArgumentException;

/**
 * 
 * Factory object that creates a new {@link Student} entity bean.
 * 
 */
@Stateless
public class StudentFactory implements StudentFactoryLocal {

	@PersistenceContext(unitName = "mph") private EntityManager em;

	@Override
	public User registerNewUser(String theUsername, PasswordHashDTO thePasswordHash,
			String theFirstName, String theLastName, Date theBirthday,
			String theEmail, String theTelephoneNumber)
					throws InvalidArgumentException {


		Student student = new Student(theUsername, thePasswordHash,
				theFirstName, theLastName, theBirthday,
				theEmail,  theTelephoneNumber);

		em.persist(student);
		em.flush();

		return student;

	}




}