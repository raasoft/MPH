package com.raaxxo.mph.entities.classids;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.raaxxo.mph.entities.Deliverable;
import com.raaxxo.mph.entities.Team;
import com.raaxxo.mph.entities.TransferrableEntity;

import mph.beans.dto.ids.ArtifactIdDTO;

/**
 * Embeddable class representing the primary key for the Database Entity Artifact
 */
@Embeddable
public class ArtifactId implements TransferrableEntity {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade=CascadeType.ALL)
  	Deliverable deliverable;
	
	@ManyToOne
  	Team team;
    
    public ArtifactId(Deliverable theDeliverable, Team theTeam) {
    	this();
    	setDeliverable(theDeliverable);
    	setTeam(theTeam);
    }
    
    private ArtifactId() {}

	
	public Deliverable getDeliverable() {
		return deliverable;
	}

	public void setDeliverable(Deliverable deliverable) {
		this.deliverable = deliverable;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public boolean equals(Object object) {
        if (object instanceof ArtifactId) {
        	ArtifactId pk = (ArtifactId)object;
            return getDeliverable().equals( pk.getDeliverable() ) && getTeam().equals( pk.getTeam() );
        } else {
            return false;
        }
    }
 
    public int hashCode() {
        return getDeliverable().hashCode() + getTeam().hashCode();
    }

	@Override
	public ArtifactIdDTO getDTO() {
		return new ArtifactIdDTO(getTeam().getId().getDTO(), getDeliverable().getId().getDTO());
	}

	@Override
	public String toString() {
		return getDTO().toString();
	}
	
}

