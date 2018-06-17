package com.raaxxo.mph.testing.cases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import util.ExceptionUtility;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.sessions.AdministratorManagerRemote;
import mph.beans.sessions.ProfessorManagerRemote;
import mph.beans.sessions.StudentManagerRemote;

public class TestUtility {
	
	static private Context jndiContext;
	
	 ArrayList<String> studentsUsername;
	  ArrayList<String> professorsUsername;
	  Set<String> usersUsername;

	  HashMap<String, PasswordHashDTO> professorsPasswordMap;
	  HashMap<String, PasswordHashDTO> studentsPasswordMap;
	
	  AdministratorManagerRemote admin;
	
	  HashMap<String, StudentManagerRemote> studentManagerMap;
	  HashMap<String, ProfessorManagerRemote> professorManagerMap;
	  
	private boolean shouldCatchAnException = false;
	private boolean catchedAnException = false;
	
	static TestUtility instance;
	
	static public TestUtility getInstance() {
		if (instance == null) {
			instance = new TestUtility();
			return instance;
		}
		else
			return instance;
	}
	
	protected void setup() {
		
		studentsUsername = new ArrayList<String>();
		professorsUsername = new ArrayList<String>();
		usersUsername = new HashSet<String>();

		professorsPasswordMap= new HashMap<String, PasswordHashDTO>();
		studentsPasswordMap= new HashMap<String, PasswordHashDTO>();
		
		admin = null;
		
		studentManagerMap = new HashMap<String, StudentManagerRemote>();
		professorManagerMap = new HashMap<String, ProfessorManagerRemote>();

		/* This will setup the initial context for the Client */
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
		props.put(Context.PROVIDER_URL, "jnp://localhost:1099");
		
		try {
			jndiContext = new InitialContext(props);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	
		try {
			admin = (AdministratorManagerRemote) jndiContext.lookup("AdministratorManager/remote");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		studentsUsername.add("raa");
		studentsUsername.add("axxo");
		studentsUsername.add("lightning");
		
		professorsUsername.add("dinitto");
		professorsUsername.add("sami");
		professorsUsername.add("bonarini");
			
		//All the passwords for the users are the union of the word "test" with the username.
		
		try {

			for (String student : studentsUsername) {
				studentsPasswordMap.put(student, new PasswordHashDTO("test"+student));
				usersUsername.add(student);
				studentManagerMap.put(student,  (StudentManagerRemote) jndiContext.lookup("StudentManager/remote"));
			}
			for (String professor : professorsUsername) {
				professorsPasswordMap.put(professor, new PasswordHashDTO("test"+professor));
				usersUsername.add(professor);
				professorManagerMap.put(professor,  (ProfessorManagerRemote) jndiContext.lookup("ProfessorManager/remote"));
			}
			
		} catch (InvalidArgumentException e1) {
			e1.printStackTrace();
		}
		catch (NamingException e) {
			e.printStackTrace();
		}
		
		System.out.println("Students: "+studentsUsername);
		System.out.println("Professors: "+professorsUsername);
		
		System.out.println("Total no. of users: "+usersUsername.size() + " that are: " + usersUsername);

	}
	
	void checkIfCatchedAnException(String theAssertion) {
		
		if (shouldCatchAnException != catchedAnException) {
			System.out.println("-----------THIS ASSERTION FAILED -----------\n[MESSAGE] " + theAssertion);
		}
		
	}
	
	void checkIfCatchedAnException() throws Exception {
		
		if (shouldCatchAnException != catchedAnException) {
			System.out.println("-----------THIS ASSERTION FAILED -----------\n");
			throw new Exception("TEST ERROR");
		} else {
			if (shouldCatchAnException) {
				System.out.println("The test expected an exception, so it will continue without error");
			}  else {
				System.out.println("The test didn't expected an exception, so it will continue without error");
			}
		}
		
	}
	
	void setAssertionShouldCatchAnException(boolean theValue) {
		shouldCatchAnException = theValue;
		catchedAnException = false;
	}
	
	void setAssertionCatchedAnException(boolean theValue) {
		catchedAnException = theValue;
	}
	
	void showException(Exception e) {
		ExceptionUtility.showCaughtException(e);
		setAssertionCatchedAnException(true);
	}


}
