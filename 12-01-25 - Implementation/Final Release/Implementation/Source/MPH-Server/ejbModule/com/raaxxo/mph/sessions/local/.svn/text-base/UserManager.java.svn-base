package com.raaxxo.mph.sessions.local;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.raaxxo.mph.entities.User;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.dto.UserDTO;
import mph.beans.dto.ids.UserIdDTO;
import mph.beans.exceptions.AlreadyLoggedInException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.PasswordMismatchException;
import mph.beans.exceptions.UserDoesNotExistException;
import mph.beans.exceptions.UsernameAlreadyExistsException;
import util.CalendarUtility;
import util.ExceptionUtility;
import util.ObjectUtility;
import util.StringUtility;

//MAKE THIS CLASS FULLY PARAMETRIZABLE WITH GENERICS

@Stateless
@Local(UserManagerLocal.class)
public class UserManager implements UserManagerLocal {

	@PersistenceContext(unitName = "mph") private EntityManager em;
	
	
	@EJB EntityUtilityLocal entityUtility;

	@Override
	public void login(String theUsername, PasswordHashDTO thePasswordHash, Class<? extends User> theUserType)
			throws InvalidArgumentException, AlreadyLoggedInException, UserDoesNotExistException, PasswordMismatchException {

		StringUtility.isNullOrEmpty(theUsername, "Username");		
		StringUtility.isNullOrEmpty(thePasswordHash.toString(), "Password");

		System.out.println("The user connected with the username " + theUsername + " is not logged in.");

		if (userPerTypeExists(theUsername,theUserType) == false) {
			throw new UserDoesNotExistException("User "+ theUsername +" doesn't exist");
		}

		System.out.println("The user connected with the username " + theUsername + " exists in the database.");

		User user = (User) em.find(User.class, theUsername);

		System.out.println("Now the " + user.getUsername() + "'s password will be checked: " + user.getPassword() + " vs " + thePasswordHash);

		if (user.getPassword().equals(thePasswordHash.toString())) {

			System.out.println("The user connected with the username " + user.getUsername() + " can now log in.");

		}
		else {
			throw new PasswordMismatchException("The password inserted for the user " +  user.getUsername()  + " is not correct. Retry.");
		}


	}

	@Override
	public <T extends User> T getEntity(Class<T> theUserType, UserIdDTO theUserIdDTO)
			throws InvalidArgumentException {
		
		ObjectUtility.isNull(theUserIdDTO);
		ObjectUtility.isNull(theUserType);
		
		return getEntity(theUserType, theUserIdDTO.getUsername());
		
	}
	
	/**
	 * The method userPerTypeExists search an user ONLY in a specific table represented by the argument theUserType.<br>
	 * If the user doesn't exist in that specified table, the 
	 * player and log the player in.
	 * 
	 * @param theUsername
	 *            is the username of the user to be searched
	 * @param theUserType
	 *            is the class type of the table where search the user      
	 * @return true if the user doesn't exist in that specified table
	 * @throws InvalidArgumentException
	 *             is thrown if theUsername is empty or null or if theUserType is null
	 * 
	 */
	public boolean userPerTypeExists(String theUsername, Class<? extends User> theUserType) throws InvalidArgumentException {
		
		StringUtility.isNullOrEmpty(theUsername, "Username");
		ObjectUtility.isNull(theUserType);
		
		User anUser = null;
		try {
			anUser = (User) em.find(theUserType, theUsername);
		}
		catch (IllegalStateException e) {
			ExceptionUtility.showCaughtException(e);
			throw new InvalidArgumentException("This EntityManager has been closed.");
		}
		catch (IllegalArgumentException e) {
			ExceptionUtility.showCaughtException(e);
			throw new InvalidArgumentException("The first argument does not denote an entity type or the second argument is not a valid type for that entity's primary key");
		}
		
		if (anUser == null)
			return false;
		else
			return true;
		
	}

	@Override
	public void registerNewUser(UserFactoryLocal theUserFactory,
			String theUsername, PasswordHashDTO thePasswordHash,
			String theFirstName, String theLastName, Date theBirthday,
			String theEmail, String theTelephoneNumber)
			throws UsernameAlreadyExistsException, InvalidArgumentException {

		if (userExists(User.class, theUsername) == false) {
		
			theUserFactory.registerNewUser(theUsername, thePasswordHash, theFirstName, theLastName, theBirthday, theEmail, theTelephoneNumber);
			
		}
		else {
			
			throw new UsernameAlreadyExistsException("Username "+ theUsername +" is already in use.");
			
		}
		
		System.out.println("Registered new user succesfully.");
		
	}


	@Override
	public  <T extends User> boolean userExists(Class<T> theUserType, String theUsername)
			throws InvalidArgumentException {

		StringUtility.isNullOrEmpty(theUsername, "theUsername");
		
		Query q1 = em.createQuery("SELECT COUNT(*) FROM "+theUserType.getSimpleName()+" u WHERE u.username = :n");
		q1.setParameter("n", theUsername);

		if (((Long) q1.getSingleResult()).intValue() == 0)
			return false;
		else
			return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends UserDTO> T getUserInfo(UserIdDTO theUserIdDTO)
			throws InvalidArgumentException {
		
		ObjectUtility.isNull(theUserIdDTO);
		
		User user = getEntity(User.class, theUserIdDTO);
		
		return (T) user.getDTO();
		
	}

	@Override
	public void updateUserInfo(UserDTO theUpdatedUserInfoDTO)
			throws InvalidArgumentException {

		java.util.Date utilDate = theUpdatedUserInfoDTO.getBirthday();
		
		if (utilDate != null) {
			
		    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			
			if ( CalendarUtility.before(sqlDate, entityUtility.getServerTimestamp())  ) {
				throw new InvalidArgumentException("The student birthday cannot be posterior to the current date");
			}
			if (  CalendarUtility.after(sqlDate, Date.valueOf("1900-01-01"))  ) {
				throw new InvalidArgumentException("The student birthday cannot be anterior than the 01-01-1900");
			}		

		}

		User anUser = getEntity(User.class, theUpdatedUserInfoDTO.getId());

		anUser.setFirstName(theUpdatedUserInfoDTO.getFirstName());
		anUser.setLastName(theUpdatedUserInfoDTO.getLastName());
		anUser.setEmail(theUpdatedUserInfoDTO.getEmail());
		anUser.setTelephoneNumber(theUpdatedUserInfoDTO.getTelephoneNumber());
		anUser.setBirthday(theUpdatedUserInfoDTO.getBirthday());

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends User> T getEntity(Class<T> theUserType, String theUserId) throws InvalidArgumentException {
		
		StringUtility.isNullOrEmpty(theUserId, "Username");

		T aUser = (T) entityUtility.findOneEntity(theUserType, theUserId);		
		return aUser;
	}
	
	
	@SuppressWarnings("unchecked")
	<T extends User> Set<T> getUserSet(Class<T> theUserType) throws InvalidArgumentException {
		
		System.out.println("Trying to get the " + theUserType + " set for the entire MPH software");

		Query q = em.createQuery("select object(o) from " + theUserType.getSimpleName() + " o");

		System.out.println("Executing the searching query");

		Set<T> theUserSet = new HashSet<T>();

		System.out.println("Getting the "+theUserType.getSimpleName() + " set");
		for (Object p : q.getResultList()) 
		{
			theUserSet.add((T) p);
		}

		System.out.println(theUserType.getSimpleName() + " set cardinality: " + theUserSet.size());

		return theUserSet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends User, W extends UserIdDTO> Set<W> getUserIdDTOSet(Class<T> theUserType) throws InvalidArgumentException {
		
		Set<W> theUserIdDTOSet = new HashSet<W>();
		
		for (T aUser : this.<T>getUserSet(theUserType)) 
		{
				theUserIdDTOSet.add( (W) (aUser.getDTO().getId()) );	
		}
		
		return theUserIdDTOSet;
	}

	@Override
	public <T extends User> void checkUserExists(Class<T> theUserType,
			String theUsername) throws InvalidArgumentException {
		
		if (userExists(theUserType, theUsername) == false) {
			throw new InvalidArgumentException("The "+theUserType.getSimpleName()+ " you've searched for ("+ theUsername+ ") doesn't exists");
		}
	}
		
}