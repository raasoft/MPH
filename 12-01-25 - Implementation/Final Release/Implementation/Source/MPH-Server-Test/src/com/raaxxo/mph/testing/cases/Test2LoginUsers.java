package com.raaxxo.mph.testing.cases;

import java.util.Map.Entry;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.exceptions.AlreadyLoggedInException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.PasswordMismatchException;
import mph.beans.exceptions.UserDoesNotExistException;
import mph.beans.exceptions.UserNotLoggedInException;

import org.junit.Before;
import org.junit.Test;

import util.ExceptionUtility;

public class Test2LoginUsers {

	@Before
	public void setUp() throws Exception {
		
		TestUtility.getInstance().setup();
		
	}

	@Test
	public void test() {
		System.out.println("");
		System.out.println("*** TEST STUDENT LOGIN ***");
		System.out.println("");

		for (Entry<String, PasswordHashDTO> entry : TestUtility.getInstance().studentsPasswordMap.entrySet()) {

			try {
				System.out.println("Trying to login with null parameters. Should catch an exception.");
				TestUtility.getInstance().studentManagerMap.get(entry.getKey()).login(null, null);
			} catch (InvalidArgumentException e) {
				ExceptionUtility.showCaughtException(e);
			}  catch (AlreadyLoggedInException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (UserDoesNotExistException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (PasswordMismatchException e) {
				ExceptionUtility.showCaughtException(e);
			} 

			try {
				System.out.println("\nTrying to login with wrong password. Should catch an exception.");
				TestUtility.getInstance().studentManagerMap.get(entry.getKey()).login(entry.getKey(), new PasswordHashDTO("WRONG PASSWORD"));
			} catch (InvalidArgumentException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (AlreadyLoggedInException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (UserDoesNotExistException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (PasswordMismatchException e) {
				ExceptionUtility.showCaughtException(e);
			} 

			try {
				System.out.println("\nTrying to obtain the course list. Should catch an exception.");
				TestUtility.getInstance().studentManagerMap.get(entry.getKey()).getCourseSet();
			} catch (UserNotLoggedInException e) {
				ExceptionUtility.showCaughtException(e);
			}

			try {
				System.out.println("\nTrying to login with the right password. Should NOT catch an exception.");
				TestUtility.getInstance().studentManagerMap.get(entry.getKey()).login(entry.getKey(), entry.getValue());
			} catch (InvalidArgumentException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (AlreadyLoggedInException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (UserDoesNotExistException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (PasswordMismatchException e) {
				ExceptionUtility.showCaughtException(e);
			} 

			try {
				System.out.println("\nTrying to obtain the course list. Should NOT catch an exception.");
				TestUtility.getInstance().studentManagerMap.get(entry.getKey()).getCourseSet();
			} catch (UserNotLoggedInException e) {
				ExceptionUtility.showCaughtException(e);
			}

			try {
				System.out.println("\nTrying to login with the right password. Should catch an exception (you are already logged in).");
				TestUtility.getInstance().studentManagerMap.get(entry.getKey()).login(entry.getKey(), entry.getValue());
			} catch (InvalidArgumentException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (AlreadyLoggedInException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (UserDoesNotExistException e) {
				ExceptionUtility.showCaughtException(e);
			} catch (PasswordMismatchException e) {
				ExceptionUtility.showCaughtException(e);
			} 

		}

		System.out.println("");
		System.out.println("******************************");
		System.out.println("");

		System.out.println("");
		System.out.println("*** TEST PROFESSOR LOGIN ***");
		System.out.println("");
		
		for (Entry<String, PasswordHashDTO> entry : TestUtility.getInstance().professorsPasswordMap.entrySet()) {

		
		try {
			System.out.println("Trying to login with null parameters. Should catch an exception.");
			
			
			TestUtility.getInstance().professorManagerMap.get(entry.getKey()).login(null, null);
		} catch (InvalidArgumentException e) {
			ExceptionUtility.showCaughtException(e);
		}  catch (AlreadyLoggedInException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (UserDoesNotExistException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (PasswordMismatchException e) {
			ExceptionUtility.showCaughtException(e);
		} 

		try {
			System.out.println("\nTrying to login with wrong password. Should catch an exception.");
			TestUtility.getInstance().professorManagerMap.get(entry.getKey()).login(entry.getKey(), new PasswordHashDTO("WRONG PASSWORD"));
		} catch (InvalidArgumentException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (AlreadyLoggedInException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (UserDoesNotExistException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (PasswordMismatchException e) {
			ExceptionUtility.showCaughtException(e);
		} 
		
		try {
			System.out.println("\nTrying to obtain the course list. Should catch an exception.");
			TestUtility.getInstance().professorManagerMap.get(entry.getKey()).getCourseSet();
		} catch (UserNotLoggedInException e) {
			ExceptionUtility.showCaughtException(e);
		}

		try {
			System.out.println("\nTrying to login with the right password. Should NOT catch an exception.");
			TestUtility.getInstance().professorManagerMap.get(entry.getKey()).login(entry.getKey(), entry.getValue());
		} catch (InvalidArgumentException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (AlreadyLoggedInException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (UserDoesNotExistException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (PasswordMismatchException e) {
			ExceptionUtility.showCaughtException(e);
		} 
		
		try {
			System.out.println("\nTrying to obtain the course list. Should NOT catch an exception.");
			TestUtility.getInstance().professorManagerMap.get(entry.getKey()).getCourseSet();
		} catch (UserNotLoggedInException e) {
			ExceptionUtility.showCaughtException(e);
		}
		
		try {
			System.out.println("\nTrying to login with the right password. Should catch an exception (you are already logged in).");
			TestUtility.getInstance().professorManagerMap.get(entry.getKey()).login(entry.getKey(), entry.getValue());
		} catch (InvalidArgumentException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (AlreadyLoggedInException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (UserDoesNotExistException e) {
			ExceptionUtility.showCaughtException(e);
		} catch (PasswordMismatchException e) {
			ExceptionUtility.showCaughtException(e);
		} 
		
		}
		System.out.println("");
		System.out.println("******************************");
		System.out.println("");
		
	}

}
