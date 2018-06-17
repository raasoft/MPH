package mph.beans.dto;

import java.sql.Date;
import java.util.Set;

import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;

/**
 * This class contains the information about a project.<br/>
 * It implements the {@link EntityDTO} interface.
 */
public class ProjectDTO implements EntityDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6763988913396574247L;
		
	private ProjectIdDTO id;
	private Set<DeliverableIdDTO> deliverableSet;
	private Date startDate;
	private Date endDate;
	private String description;
	

	/**
	 * @return the id
	 */
	public ProjectIdDTO getId() {
		return id;
	}

	/**
	 * @return the deliverableList
	 */
	public Set<DeliverableIdDTO> getDeliverableSet() {
		return deliverableSet;
	}


	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Set the project id
	 * @param id the new id to set
	 */
	protected void setId(ProjectIdDTO id) {
		this.id = id;
	}

	/**
	 * Add a set of deliverables to the project
	 * @param deliverableSet the set of deliverables to add
	 */
	protected void setDeliverableSet(Set<DeliverableIdDTO> deliverableSet) {
		this.deliverableSet = deliverableSet;
	}

	/**
	 * Set the project start date
	 * @param startDate the new start date to set
	 */
	protected void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Set the project end date
	 * @param endDate the new end date to set
	 */
	protected void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Set the project description
	 * @param description the new description to set
	 */
	protected void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Create the DTO with the given arguments
	 */
	public ProjectDTO(ProjectIdDTO theProjectId, Set<DeliverableIdDTO> theDeliverableIdSet, Date theStartDate, Date theEndDate, String theDescription) {
		setId(theProjectId);
		setDeliverableSet(theDeliverableIdSet);
		setStartDate(theStartDate);
		setEndDate(theEndDate);
		setDescription(theDescription);
	}
	
	public String toString() {
		
		return  getId() +
				"\n" + getDeliverableSet() +
				"\nStart Date: " + getStartDate() +
				"\nEnd Date: " + getEndDate() +
				"\nDescription: " + getDescription();
	}
	
}
