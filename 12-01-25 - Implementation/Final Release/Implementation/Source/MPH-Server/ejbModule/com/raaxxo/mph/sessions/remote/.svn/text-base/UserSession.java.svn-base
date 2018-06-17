package com.raaxxo.mph.sessions.remote;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mph.beans.dto.ids.UserIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UserNotLoggedInException;
import mph.beans.sessions.UserSessionInterface;
import util.ObjectUtility;

import com.raaxxo.mph.entities.User;
import com.raaxxo.mph.sessions.local.UserManagerLocal;

/**
 * 
 * This represent the parent class of each stateful session bean.<br/>
 * It provides the basic mechanism of logging for all the users, giving some useful information about the session as well.
 * 
 */
abstract public class UserSession implements UserSessionInterface {
	
	@PersistenceContext(unitName = "mph") protected EntityManager em;
	@EJB UserManagerLocal userManager;

	protected boolean isLogged = false; 			//Once the client logged this variable will become true
	protected String username = ""; 				//Once the client logged in the session bean remembers its username and checks it for its own purposes.
	protected Class<? extends User> userType = null;
	
	public boolean isLogged() {
		return isLogged;
	}
	
	protected void setLogged(boolean theValue) {
		this.isLogged = theValue;
	}
		
	public String getUsername() {
		return username;
	}

	protected void setUsername(String theUsername) throws InvalidArgumentException {
		
		ObjectUtility.isNull(theUsername);
		this.username = theUsername;
	}

	protected void checkLoggedInStatus() throws UserNotLoggedInException {
		if (isLogged() == false) {
			throw new UserNotLoggedInException("Can't get done the action requested. The " + getUsername() +"is not logged in");
		}
	}
	
	protected void setUserType(Class<? extends User> theUserType) {
		userType = theUserType;
	}
	
	public Class<? extends User> getUserType() {
		return userType;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends UserIdDTO> T getUserIdDTO() {
		
		T aId = null;
		
		try {
			aId =  (T) userManager.getEntity(getUserType(),getUsername()).getDTO().getId();
		} catch (InvalidArgumentException e) {
			e.printStackTrace();
		}
		
		return aId;
		
	}
		
}