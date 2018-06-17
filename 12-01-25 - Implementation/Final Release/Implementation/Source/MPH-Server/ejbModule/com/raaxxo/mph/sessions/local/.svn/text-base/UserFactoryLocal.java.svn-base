package com.raaxxo.mph.sessions.local;

import java.sql.Date;

import com.raaxxo.mph.entities.User;

import mph.beans.dto.PasswordHashDTO;
import mph.beans.exceptions.InvalidArgumentException;

interface UserFactoryLocal {
	
	public User registerNewUser(String theUsername, PasswordHashDTO thePasswordHash, String theFirstName,
			String theLastName, Date theBirthday, String theEmail,
			String theTelephoneNumber) throws InvalidArgumentException;

}
