package com.raaxxo.mph.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.raaxxo.mph.entities.classids.TeamId;

import util.ObjectUtility;

import mph.beans.dto.TeamDTO;
import mph.beans.dto.ids.JoinRequestIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
 
/**
 * Entity Bean implementation for the Database Entity Team
 */	
@Entity
public class Team implements TransferrableEntity {

	private static final long serialVersionUID = 3181531583548659369L;
	
	public static final int TEAM_MAXIMUM_SIZE = 3;
	
	@EmbeddedId 
	TeamId id;

	@Column(name="isClosed") private boolean closed;
	
	@ManyToMany(mappedBy = "studentTeams", targetEntity = Student.class)
	Set<Student> studentSet = new HashSet<Student>();
	
	@ManyToMany(mappedBy = "studentTeamEnrollmentRequests", targetEntity = Student.class)
	Set<Student> studentEnrollmentRequests = new HashSet<Student>();
	
	@OneToMany(targetEntity = Artifact.class, cascade=CascadeType.ALL)
	Set<Artifact> artifactSet= new HashSet<Artifact>();

	public Team(Project theProject, String theName) throws InvalidArgumentException {
		this();
		id = new TeamId(theProject, theName);
		setClosed(false);
	}
	
	public Set<Artifact> getArtifactSet() {
		return artifactSet;
	}

	public void setArtifactSet(Set<Artifact> artifactSet) {
		this.artifactSet = artifactSet;
	}

	private Team() {

	}

	public TeamId getId() {
		return id;
	}


	public void setId(TeamId id) {
		this.id = id;
	}


	public boolean isClosed() {
		return closed;
	}


	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	public Set<Student> getStudentList() {
		return studentSet;
	}
	
	public void addArtifact(Artifact theArtifact) throws InvalidArgumentException {
		ObjectUtility.isNull(theArtifact);
		artifactSet.add(theArtifact);
	}
	
	public void removeArtifact(Artifact theArtifact) throws InvalidArgumentException {
		ObjectUtility.isNull(theArtifact);
		artifactSet.remove(theArtifact);
	}
	
	

	public void setStudentList(Set<Student> studentList) {
		this.studentSet = studentList;
	}

	public Set<Student> getStudentEnrollmentRequests() {
		return studentEnrollmentRequests;
	}

	public void setStudentEnrollingRequests(Set<Student> studentEnrollingRequests) {
		this.studentEnrollmentRequests = studentEnrollingRequests;
	}
	
	public void addStudent(Student theStudent) {
		studentSet.add(theStudent);
	}
	
	public void addEnrollmentRequest(Student theStudent) {
		studentEnrollmentRequests.add(theStudent);
	}
	
	public void removeStudent(Student theStudent) {
		studentSet.remove(theStudent);
	}
	
	
	public void removeEnrollmentRequest(Student theStudent) {
		studentEnrollmentRequests.remove(theStudent);
	}
	
	public Set<JoinRequestIdDTO> getEnrollmentRequestIdDTO() {
		
		Set<JoinRequestIdDTO> aSet = new HashSet<JoinRequestIdDTO>();
		
		for (Student aStud : getStudentEnrollmentRequests()) {
			aSet.add(new JoinRequestIdDTO(getDTO().getId(), aStud.getDTO().getId()));
		}
		
		return aSet;
	}

	@Override
	public TeamDTO getDTO() {
		
		Set<StudentIdDTO> aSet = new HashSet<StudentIdDTO>();
		for (Student aStud : getStudentList()) {
			aSet.add(aStud.getDTO().getId());
		}
		
		return new TeamDTO(getId().getDTO(), aSet ,isClosed(), getTeamScore()); 
	}
	
	@Override
	public String toString() {
		return getDTO().toString();
	}
	
	public Double getTeamScore() {

		Double theScore = new Double(0);
		int noOfDeliverable = getId().getProject().getDeliverableList().size();
		
		for (Artifact anArtifact: getArtifactSet()) {
			theScore += anArtifact.getFinalScore();
		}
		
		theScore += noOfDeliverable - getArtifactSet().size();		
		theScore /= noOfDeliverable;
		
		return theScore;
	}
	
	@Override
	public boolean equals(Object object) {
        if (object instanceof Team) {
        	Team pk = (Team)object;     
        	
        
        	
            return  getId().equals( pk.getId() ) &&
            		isClosed() == pk.isClosed();
        } else {
            return false;
        }
    }
 
	@Override
    public int hashCode() {
        return getId().hashCode();
    }
	
 
}
