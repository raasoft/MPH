package com.raaxxo.mph.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mph.beans.dto.CourseDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;



/**
 * Entity Bean implementation for the Database Entity Course
 */					
@Entity
@Table	(	name="Course", 
uniqueConstraints = { 
    @UniqueConstraint(columnNames={"Professor", "Name"})
}
)
final public class Course implements TransferrableEntity {
	

	private static final long serialVersionUID = 7945559393365532526L;
	public static final int COURSENAME_MAX_LENGTH = 30;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "Uid")
	protected Long uid;
	
	@Column(name="Name", length = COURSENAME_MAX_LENGTH)
	protected String name;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="Professor")
	protected Professor professor;
	
	
	@Column(name="Description")
	protected String description;
	
	public Course(Professor theProfessor, String theName, String theDescription) {
		this();
		setProfessor(theProfessor);
		setName(theName);
		setDescription(theDescription);
	}
	
	private Course() {}

	public Long getUid() {
		return uid;
	}
	public void setUid(Long theUid) {
		this.uid = theUid;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String theName) {
		this.name = theName;
	}
	

	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor theProfessor) {
		this.professor = theProfessor;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String theDescription) {
		this.description = theDescription;
	}

	@Override
	public CourseDTO getDTO() {
		
		CourseIdDTO aCourseId = new CourseIdDTO(getUid());
		
		Set<ProjectIdDTO> aSet = new HashSet<ProjectIdDTO>();

		
		return new CourseDTO(aCourseId, getName(), getProfessor().getDTO().getId(), aSet, getDescription());
	}

	@Override
	public String toString() {
		return getDTO().toString();
	}
	
	@Override
	public boolean equals(Object object) {
        if (object instanceof Course) {
        	Course pk = (Course)object;
        	
            return  getUid().equals( pk.getUid() ) &&
            		getName().equals(pk.getName()) && 
            		getProfessor().equals( pk.getProfessor() ) && 
            		getDescription().equals(pk.getDescription());
        } else {
            return false;
        }
    }
 
	@Override
    public int hashCode() {
        return getUid().hashCode() + getName().hashCode() + getProfessor().hashCode() + getDescription().hashCode();
    }
	
	
}