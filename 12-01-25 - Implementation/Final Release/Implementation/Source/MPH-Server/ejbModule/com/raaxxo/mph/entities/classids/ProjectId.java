package com.raaxxo.mph.entities.classids;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import com.raaxxo.mph.entities.Course;
import com.raaxxo.mph.entities.TransferrableEntity;

import util.StringUtility;

import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.InvalidArgumentException;


/**
 * Embeddable class representing the primary key for the Database Entity Project
 */
@Embeddable
public class ProjectId implements TransferrableEntity {
	
	private static final long serialVersionUID = 1L;
	public static final int PROJECTTITLE_MAX_LENGTH = 50;

    @ManyToOne(cascade=CascadeType.ALL)
	Course course;
    
    @Column(name = "Title")
    String title;
    
    public ProjectId(Course theCourse, String theProjectTitle) throws InvalidArgumentException {
    	this();
    	setCourse(theCourse);
    	setTitle(theProjectTitle);
    }
    
    private ProjectId() {
    	
    }

    public Course getCourse() {
		return course;
	}

	private void setCourse(Course course) {
		this.course = course;
	}

	public String getTitle() {
		return title;
	}

	private void setTitle(String title) throws InvalidArgumentException {
		
		StringUtility.isNullOrEmpty(title, "Project Title");
		StringUtility.checkSize(title, "Project Title", PROJECTTITLE_MAX_LENGTH);
		
		this.title = title;
	}
	
    public boolean equals(Object object) {
        if (object instanceof ProjectId) {
            ProjectId pk = (ProjectId)object;
            return course == pk.getCourse() && getTitle()== pk.getTitle();
        } else {
            return false;
        }
    }
 
    public int hashCode() {
        return getCourse().hashCode() + getTitle().hashCode();
    }

	@Override
	public ProjectIdDTO getDTO() {
		return new ProjectIdDTO(getCourse().getDTO().getId(), getTitle());
	}

	@Override
	public String toString() {
		return getDTO().toString();
	}
	
	
}
