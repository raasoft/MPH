package mph.beans.dto;

import java.sql.Date;

import mph.beans.dto.ids.DeliverableIdDTO;

/**
 * This class contains the information about a project deliverable.<br/>
 * It implements the {@link EntityDTO} interface.
 */
public class DeliverableDTO implements EntityDTO {
	
	private static final long serialVersionUID = -4550753183299417241L;
		
	private DeliverableIdDTO id;
	private Date deadline;
	private String description;
	private Double penalty;
	
	
	public DeliverableIdDTO getId() {
		return id;
	}

	/**
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	

	/**
	 * Set the deliverable penalty
	 * @param penalty the new penalty to set
	 */
	private void setPenalty(Double penalty) {
		this.penalty = penalty;
	}

	/**
	 * @return the SerialVersionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the penalty assigned to the artifacts delivered late for this deliverable
	 */
	public Double getPenalty() {
		return penalty;
	}

	/**
	 * Set the deliverable id
	 * @param id the new id to set
	 */
	private void setId(DeliverableIdDTO id) {
		this.id = id;
	}


	/**
	 * Set the deliverable deadline date
	 * @param deadline the new deadline to set
	 */
	private void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * set the deliverable description
	 * @param description the new description to set
	 */
	private void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Create the deliverable DTO with the given arguments
	 * @param theDeliverableId the id of the deliverable
	 * @param theDeadline the deliverable deadline
	 * @param theDescription the deliverable description
	 * @param thePenalty the penalty which will be assigned to late deliveries
	 */
	public DeliverableDTO(DeliverableIdDTO theDeliverableId, Date theDeadline, String theDescription, Double thePenalty) {
		setId(theDeliverableId);
		setDeadline(theDeadline);
		setDescription(theDescription);
		setPenalty(thePenalty);
	}
	
}
