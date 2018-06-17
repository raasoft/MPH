package com.raaxxo.mph.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.raaxxo.mph.entities.classids.ProjectId;

import mph.beans.dto.ProjectDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
 
/**
 * Entity Bean implementation for the Database Entity Project
 */	
@Entity
public class Project implements TransferrableEntity {
 
	
	private static final long serialVersionUID = 3181531583548659369L;
	public static final int DESCRIPTION_MAX_LENGTH = 30000;
	
	@EmbeddedId 
	ProjectId id;

	@Column(name="StartDate") private Date startDate;
	@Column(name="EndDate") private Date endDate;
	@Column(name="Description", length=DESCRIPTION_MAX_LENGTH) private String description;
	
	@OneToMany(targetEntity = Deliverable.class, cascade=CascadeType.ALL)
	Set<Deliverable> deliverableList = new HashSet<Deliverable>();

	public Project(Course theCourse, String theProjectTitle, Date theStartDate, Date theEndDate, String theDescription) throws InvalidArgumentException {
		this();
		setId(new ProjectId(theCourse, theProjectTitle));
		setStartDate(theStartDate);
		setEndDate(theEndDate);
		setDescription(theDescription);
	}

	private Project() {

	}

	public void addDeliverable(Deliverable theDeliverable) {
		deliverableList.add(theDeliverable);
	}

	

	public Set<Deliverable> getDeliverableList() {
		return deliverableList;
	}

	public void setDeliverableList(Set<Deliverable> deliverableList) {
		this.deliverableList = deliverableList;
	}

	public ProjectId getId() {
		return id;
	}

	private void setId(ProjectId id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public ProjectDTO getDTO() {
		
		Set<DeliverableIdDTO> aSet = new HashSet<DeliverableIdDTO>();
	
		return new ProjectDTO(getId().getDTO(), aSet, getStartDate(), getEndDate(), getDescription());
	}
	
		
	@Override
	public String toString() {
		return getDTO().toString();
	}
	
	@Override
	public boolean equals(Object object) {
        if (object instanceof Project) {
        	Project pk = (Project)object;        	
        	
            return  getId().equals( pk.getId() ) &&
            		getStartDate().equals(pk.getStartDate()) && 
            		getEndDate().equals( pk.getEndDate() ) && 
            		getDescription().equals( pk.getDescription() );
        } else {
            return false;
        }
    }
 
	@Override
    public int hashCode() {
        return getId().hashCode() + getStartDate().hashCode() + getStartDate().hashCode() + getDescription().hashCode();
    }
 
 
}
