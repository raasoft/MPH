package com.raaxxo.mph.entities;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.raaxxo.mph.entities.classids.DeliverableId;

import mph.beans.dto.DeliverableDTO;
import mph.beans.exceptions.InvalidArgumentException;
import util.ObjectUtility;
 
/**
 * Entity Bean implementation for the Database Entity Deliverable
 */	
@Entity
public class Deliverable implements TransferrableEntity {

	private static final long serialVersionUID = 3181531583548659369L;
	public static final int DESCRIPTION_MAX_LENGTH = 10000;

	@EmbeddedId 
	DeliverableId id;

	@Column(name="Deadline") private Date deadline;
	@Column(name="Penalty") private Double penalty;
	@Column(name="Description", length=DESCRIPTION_MAX_LENGTH) private String description;
	
	@OneToMany(targetEntity = Artifact.class, cascade=CascadeType.ALL)
	Set<Artifact> artifactSet = new HashSet<Artifact>(0);

	public Deliverable(Project theProject, String theName, Date theDeadline, Double thePenalty, String theDescription) throws InvalidArgumentException {
		this();
		id = new DeliverableId(theProject, theName);
		setPenalty(thePenalty);
		setDeadline(theDeadline);
		setDescription(theDescription);
	}
	

	public Set<Artifact> getArtifactSet() {
		return artifactSet;
	}


	public void setArtifactSet(Set<Artifact> theArtifactSet) {
		this.artifactSet = theArtifactSet;
	}
	
	public void addArtifact(Artifact theArtifact) throws InvalidArgumentException {
		ObjectUtility.isNull(theArtifact);
		artifactSet.add(theArtifact);
	}
	
	public void removeArtifact(Artifact theArtifact) throws InvalidArgumentException {
		
		ObjectUtility.isNull(theArtifact);
		artifactSet.remove(theArtifact);
	}



	public DeliverableId getId() {
		return id;
	}


	private Deliverable() {

	}
	
	public Date getDeadline() {
		return deadline;
	}


	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}



	public Double getPenalty() {
		return penalty;
	}



	public void setPenalty(Double penalty) {
		this.penalty = penalty;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public DeliverableDTO getDTO() {
		return new DeliverableDTO(getId().getDTO(), getDeadline(),getDescription(), getPenalty());
	}
	
	@Override
	public String toString() {
		return getDTO().toString();
	}
	
	
	@Override
	public boolean equals(Object object) {
        if (object instanceof Deliverable) {
        	Deliverable pk = (Deliverable)object;    	
        	
            return  getId().equals( pk.getId() ) &&
            		getDeadline().equals(pk.getDeadline()) && 
            		getPenalty().equals( pk.getPenalty() ) && 
            		getDescription().equals(pk.getDescription());
        } else {
            return false;
        }
    }
 
	@Override
    public int hashCode() {
        return getId().hashCode() + getDeadline().hashCode() + getPenalty().hashCode() + getDescription().hashCode();
    }
 
}
