package com.raaxxo.mph.sessions.local;

import java.sql.Date;

import javax.ejb.Local;
import javax.persistence.EntityManager;

import mph.beans.exceptions.InvalidArgumentException;


/**
 * 
 * Local interface that provide useful functions to all the entity managers.
 * 
 */
@Local
public interface EntityUtilityLocal {
	
	public Object findOneEntity(Class<?> theTable, Object thePrimaryKey) throws InvalidArgumentException ;

	public EntityManager getEntityManager();
	
	public Date getServerTimestamp();

}
