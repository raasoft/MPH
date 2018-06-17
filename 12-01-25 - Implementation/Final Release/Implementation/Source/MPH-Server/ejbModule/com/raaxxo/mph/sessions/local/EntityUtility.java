package com.raaxxo.mph.sessions.local;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mph.beans.exceptions.InvalidArgumentException;
import util.ExceptionUtility;

@Stateless
public class EntityUtility implements EntityUtilityLocal {
	
	@PersistenceContext(unitName = "mph") private EntityManager em;
	
	@Override
	public Object findOneEntity(Class<?> theTable, Object thePrimaryKey) throws InvalidArgumentException {
		
		Object anObject = null;
		try {
			anObject = (Object) em.find(theTable, thePrimaryKey);
		}
		catch (IllegalStateException e) {
			ExceptionUtility.showCaughtException(e);
			throw new InvalidArgumentException("This EntityManager has been closed.");
		}
		catch (IllegalArgumentException e) {
			ExceptionUtility.showCaughtException(e);
			throw new InvalidArgumentException("The first argument does not denote an entity type or the second argument is not a valid type for that entity's primary key");
		}
		
		return anObject;
		
	}
	
	@Override
	public EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public Date getServerTimestamp() {
	    
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    
		//get current date time with Calendar()
		Calendar cal = Calendar.getInstance();		   
	    java.sql.Date sqlDate = Date.valueOf(dateFormat.format(cal.getTime()));//new java.sql.Date(utilDate.getTime());
		
		return sqlDate;
	}
		
}
