package com.raaxxo.mph.entities.classids;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.TransferrableEntity;

import util.StringUtility;

import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.InvalidArgumentException;

/**
 * Embeddable class representing the primary key for the Database Entity Team
 */
@Embeddable
public class TeamId implements TransferrableEntity {
	
	private static final long serialVersionUID = 1L;
	
	public static final int TEAMNAME_MAX_LENGTH = 50;


	@ManyToOne(cascade=CascadeType.ALL)
	/*@JoinColumns({
		@JoinColumn(name="CourseId", referencedColumnName="CourseId",  updatable=false, insertable=false),
		@JoinColumn(name="ProjectTitle", referencedColumnName="Title",  updatable=false, insertable=false)
	})*/
  	Project project;
    
    @Column(name = "Name")
    String name;
    
    public TeamId(Project theProject, String theTeamName) throws InvalidArgumentException {
    	this();
    	setProject(theProject);
    	setName(theTeamName);
    }
    
    private TeamId() {
    	
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
		
		StringUtility.isNullOrEmpty(theName, "Team Name");
		StringUtility.checkSize(theName, "Team Name", TEAMNAME_MAX_LENGTH);
		
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
	public TeamIdDTO getDTO() {
		
		return new TeamIdDTO(getProject().getDTO().getId(), getName());
	}

	@Override
	public String toString() {
		return getDTO().toString();
	}
	
	
}

