package com.raaxxo.mph.sessions.local;

import java.util.Set;

import javax.ejb.Local;

import com.raaxxo.mph.entities.Deliverable;
import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.classids.DeliverableId;

import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.InvalidArgumentException;



/**
 * 
 * Local interface that manages the {@link Deliverable} entities.
 * 
 */
@Local
public interface DeliverableManagerLocal extends ManagerLocal<Deliverable, DeliverableId, DeliverableDTO, DeliverableIdDTO> {
	
	public Deliverable reconstructEntity(DeliverableIdDTO theDeliverableIdDTO) throws InvalidArgumentException;
	public Deliverable getEntity(DeliverableId theDeliverableId)	throws InvalidArgumentException;	
	
	public Set<DeliverableIdDTO> getDeliverableIdDTOSet(ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException;
	public Set<Deliverable> getDeliverableSet(Project theProject) throws InvalidArgumentException;

	public boolean existsDeliverable(DeliverableId theDeliverableId3) throws InvalidArgumentException;
	
	
	public DeliverableDTO getDeliverableInfo(DeliverableIdDTO theDeliverableId) throws InvalidArgumentException;

}
