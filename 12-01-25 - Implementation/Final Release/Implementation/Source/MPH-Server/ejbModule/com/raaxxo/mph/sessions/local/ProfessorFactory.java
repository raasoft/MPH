package com.raaxxo.mph.sessions.local;

import java.sql.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.raaxxo.mph.entities.Professor;
import com.raaxxo.mph.entities.User;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.exceptions.InvalidArgumentException;

/**
 * 
 * Factory object that creates a new {@link Professor} entity bean.
 * 
 */
@Stateless
public class ProfessorFactory implements ProfessorFactoryLocal {

	@PersistenceContext(unitName = "mph") private EntityManager em;

	@Override
	public User registerNewUser(String theUsername, PasswordHashDTO thePasswordHash,
			String theFirstName, String theLastName, Date theBirthday,
			String theEmail, String theTelephoneNumber)
					throws InvalidArgumentException {


		Professor professor = new Professor(theUsername, thePasswordHash,
				theFirstName, theLastName, theBirthday,
				theEmail,  theTelephoneNumber);

		em.persist(professor);
		try {
		em.flush();
		}
		catch (Exception e) {
			throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
		}
		return professor;

	}




}