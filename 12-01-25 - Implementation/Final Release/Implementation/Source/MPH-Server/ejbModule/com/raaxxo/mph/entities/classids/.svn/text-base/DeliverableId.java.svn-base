package com.raaxxo.mph.entities.classids;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.TransferrableEntity;

import util.StringUtility;

import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.exceptions.InvalidArgumentException;

/**
 * Embeddable class representing the primary key for the Database Entity Deliverable
 */
@Embeddable
public class DeliverableId implements TransferrableEntity {
	
	private static final long serialVersionUID = 1L;
	public static final int DELIVERABLENAME_MAX_LENGTH = 30;

	@ManyToOne(cascade=CascadeType.ALL)
  	Project project;
    
    @Column(name = "Name")
    String name;
    
    public DeliverableId(Project theProject, String theDeliverableName) throws InvalidArgumentException {
    	this();
    	setProject(theProject);
    	setName(theDeliverableName);
    }
    
    private DeliverableId() {
    	
    }

	
    public Project getProject() {
		return project;
	}

	public void setProject(Project theProject) {
		this.project = theProject;
	}

	public String getName() {
		return name;
	}

	public void setName(String theName) throws InvalidArgumentException {
		
		StringUtility.isNullOrEmpty(theName, "Deliverable Name");
		StringUtility.checkSize(theName, "Deliverable Name", DELIVERABLENAME_MAX_LENGTH);
		
		this.name = theName;
	}

	public boolean equals(Object object) {
        if (object instanceof DeliverableId) {
        	DeliverableId pk = (DeliverableId)object;
            return project == pk.getProject() && getName()== pk.getName();
        } else {
            return false;
        }
    }
 
    public int hashCode() {
        return getProject().hashCode() + getName().hashCode();
    }

	@Override
	public DeliverableIdDTO getDTO() {
		return new DeliverableIdDTO(getProject().getDTO().getId(), getName());
	}
    
	@Override
	public String toString() {
		return getDTO().toString();
	}
	
}

