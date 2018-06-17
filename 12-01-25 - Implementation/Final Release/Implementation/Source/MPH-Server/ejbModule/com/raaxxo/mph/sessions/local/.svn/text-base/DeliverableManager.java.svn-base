package com.raaxxo.mph.sessions.local;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.raaxxo.mph.entities.Deliverable;
import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.classids.DeliverableId;

import mph.beans.dto.DeliverableDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import util.ObjectUtility;

@Stateless
public class DeliverableManager implements DeliverableManagerLocal {
	
	@PersistenceContext(unitName = "mph") protected EntityManager em;
	
	@EJB private ProjectManagerLocal projectManager;
	@EJB private EntityUtilityLocal searchUtility;
		

		@Override
		public boolean existsDeliverable(DeliverableId theDeliverableId)
				throws InvalidArgumentException {
			
			Deliverable aDeliverable = getEntity(theDeliverableId);

			if (aDeliverable == null)
				return false;
			else
				return true;
		}

	@Override
	public Deliverable getEntity(DeliverableId theDeliverableId)
			throws InvalidArgumentException {
	
		ObjectUtility.isNull(theDeliverableId);
		
		System.out.println("Trying to get the deliverable with id: " + theDeliverableId);
		
		Deliverable aDeliverable = (Deliverable) searchUtility.findOneEntity(Deliverable.class, theDeliverableId);
		
		if (aDeliverable != null)
			System.out.println("Succesfully got the deliverable with id: " + theDeliverableId);
		else
			System.out.println("Can't  find the deliverable with id: " + theDeliverableId);
		
		return aDeliverable;

	}

	
	
	@Override
	public Deliverable reconstructEntity(DeliverableIdDTO theDeliverableIdDTO) throws InvalidArgumentException
			  {
		
		ObjectUtility.isNull(theDeliverableIdDTO);
		
		Project aProject = projectManager.reconstructEntity(theDeliverableIdDTO.getProjectId());
		
		Deliverable aDeliverable = (Deliverable) searchUtility.findOneEntity(Deliverable.class, new DeliverableId(aProject,theDeliverableIdDTO.getDeliverableName())); 
		
		return aDeliverable;
		
	}

	@Override
	public Set<DeliverableIdDTO> getDeliverableIdDTOSet(
			ProjectIdDTO theProjectIdDTO) throws InvalidArgumentException {
		
		ObjectUtility.isNull(theProjectIdDTO);
		
		System.out.println("Trying to get the deliverable id dto set for the project: " + theProjectIdDTO);
		
		Project aProject = projectManager.reconstructEntity(theProjectIdDTO);
		
		Set<Deliverable> theDeliverableSet = getDeliverableSet(aProject);
		Set<DeliverableIdDTO> theDeliverableIdDTOSet = new HashSet<DeliverableIdDTO>();
		
		DeliverableIdDTO aDeliverableId = null;
		
		System.out.println("Building the id dto set");
		
		for (Deliverable aDeliverable : theDeliverableSet) 
		{
			aDeliverableId = aDeliverable.getId().getDTO();
			
			if (aDeliverableId == null) 
				throw new InvalidArgumentException("Internal error database.");
			
			theDeliverableIdDTOSet.add(aDeliverableId);
		
		}
		
		System.out.println("Returning the deliverable id dto set for the project: " + theProjectIdDTO);
		
		return theDeliverableIdDTOSet;
		
	}

	@Override
	public DeliverableDTO getDeliverableInfo(DeliverableIdDTO theDeliverableId)
			throws InvalidArgumentException {
		
		ObjectUtility.isNull(theDeliverableId);
		
		System.out.println("Trying to get the deliverable DTO for the deliverable with id: " + theDeliverableId);
		
		Deliverable aDeliverable = reconstructEntity(theDeliverableId);
		
		System.out.println("Creating some data structures for creating the deliverable DTO");

		DeliverableDTO aDeliverableDTO = new DeliverableDTO(theDeliverableId, aDeliverable.getDeadline(), aDeliverable.getDescription(), aDeliverable.getPenalty());
		
		System.out.println("Created the deliverable DTO. Returning the deliverable DTO");
		return aDeliverableDTO;

		
		
	}

	@Override
	public Set<Deliverable> getDeliverableSet(Project theProject) throws InvalidArgumentException {

		Query q = em.createQuery("FROM Deliverable d WHERE d.id.project.id = :n");
		q.setParameter("n", theProject.getId());		
		Set<Deliverable> theDeliverableSet = new HashSet<Deliverable>();
	
		System.out.println("Getting the deliverable set for the project selected");
		for (Object d : q.getResultList()) 
		{
			theDeliverableSet.add((Deliverable) d);	
		}
		
		System.out.println("Deliverable set cardinality: " + theDeliverableSet.size());
		
		return theDeliverableSet;
	}


	@Override
	public boolean existsEntity(DeliverableId theEntityId)
			throws InvalidArgumentException {
		
		Deliverable aDeliverable = getEntity(theEntityId);

		if (aDeliverable  == null) {
			System.out.println("Deliverable doesn't exist");
			return false;
		}
		else {
			System.out.println("Deliverable exists");
			return true;
		}
		
	}
	
	
	@Override
	public void checkEntityExists(DeliverableId theEntityId)
			throws InvalidArgumentException {
		
		if (existsEntity(theEntityId) == false) {
			throw new InvalidArgumentException("The deliverable you've searched for ("+ theEntityId + ") doesn't exists");
		}		
		
	}
		
}