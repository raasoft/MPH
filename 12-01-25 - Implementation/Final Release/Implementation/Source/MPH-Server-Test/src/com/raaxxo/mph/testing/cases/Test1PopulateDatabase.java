package com.raaxxo.mph.testing.cases;

import java.sql.Date;
import java.util.Map.Entry;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UsernameAlreadyExistsException;

import org.junit.Before;
import org.junit.Test;

public class Test1PopulateDatabase {

	@Before
	public void setUp() throws Exception {

		TestUtility.getInstance().setup();

	}


	@Test
	public void test() throws Exception {

			System.out.println("");
			System.out.println("*** POPULATING THE DATABASE ***");
			System.out.println("");

			for (Entry<String, PasswordHashDTO> entry : TestUtility.getInstance().professorsPasswordMap.entrySet()) {

				try {
					TestUtility.getInstance().setAssertionShouldCatchAnException(false);
					System.out.println("Trying to register a new professor. Could catch an exception if this test is not run right after the server deployment.");
					TestUtility.getInstance().admin.registerNewUser(entry.getKey(), entry.getValue(), "Name of "+entry.getKey(), "Surname of "+entry.getKey(), Date.valueOf("2000-01-01"), entry.getKey()+"@"+entry.getKey()+".com", "0123456");

				} 
				catch (UsernameAlreadyExistsException e) { TestUtility.getInstance().showException(e); } 
				catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }

				TestUtility.getInstance().checkIfCatchedAnException();

			}

			for (Entry<String, PasswordHashDTO> entry : TestUtility.getInstance().studentsPasswordMap.entrySet()) {

				try {
					TestUtility.getInstance().setAssertionShouldCatchAnException(false);
					System.out.println("Trying to register a new student. Could catch an exception if this test is not run right after the server deployment.");
					TestUtility.getInstance().studentManagerMap.get(entry.getKey()).registerNewUser(entry.getKey(), entry.getValue(), "Name of "+entry.getKey(), "Surname of "+entry.getKey(), Date.valueOf("2000-01-01"), entry.getKey()+"@"+entry.getKey()+".com", "0123456");

				} 
				catch (UsernameAlreadyExistsException e) { TestUtility.getInstance().showException(e); } 
				catch (InvalidArgumentException e) { TestUtility.getInstance().showException(e); }

				TestUtility.getInstance().checkIfCatchedAnException();

			}
			System.out.println("");
			System.out.println("******************************");
			System.out.println("");		

	}
}
