package com.raaxxo.mph.sessions.local;

import javax.ejb.Local;

import mph.beans.exceptions.InvalidArgumentException;

/**
 * 
 * Local interface that identifies a session bean that manages an entity.
 * 
 */
@Local
public interface ManagerLocal<Entity, EntityId, EntityDTO, EntityIdDTO> {
	
	
	public boolean existsEntity(EntityId theEntityId) throws InvalidArgumentException;
	public void checkEntityExists(EntityId theEntityId) throws InvalidArgumentException;

	public Entity reconstructEntity(EntityIdDTO theEntityIdDTO) throws InvalidArgumentException;
	public Entity getEntity(EntityId theEntityId) throws InvalidArgumentException;

	
}
