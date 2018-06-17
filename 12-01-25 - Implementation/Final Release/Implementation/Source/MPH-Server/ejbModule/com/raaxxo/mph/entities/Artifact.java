package com.raaxxo.mph.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.IncomingFileDTO;
import mph.beans.exceptions.DownloadErrorException;
import mph.beans.exceptions.InvalidArgumentException;
import util.CalendarUtility;

import com.raaxxo.mph.entities.classids.ArtifactId;

/**
 * Entity Bean implementation for the Database Entity Artifact
 */
@Entity
public class Artifact implements Serializable {

	private static final long serialVersionUID = 3181531583548659369L;

	@EmbeddedId 
	ArtifactId id;

	@Column(name="SubmissionDate") private Date submissionDate;
	@Column(name="Score") private Double score;
	@Column(name="File") @Lob private byte[] file;
	@Column(name="FileName") private String fileName;


	/**
	 * This method creates a new Artifact given some arguments
	 * @param theDeliverable is the deliverable assigned to the artifact
	 * @param theTeam is the artifact owner
	 * @param theSubmissionDate is the submission date of the artifact
	 * @param theFile is the binary file representing the artifact document
	 * @param fileName is the file name of the artifact
	 * @throws InvalidArgumentException if the score of the artifact is smaller than 1 or bigger than 10
	 */
	public Artifact(Deliverable theDeliverable, Team theTeam, Date theSubmissionDate, byte[] theFile, String fileName) throws InvalidArgumentException {
		this();
		setId(new ArtifactId(theDeliverable, theTeam));
		setSubmissionDate(theSubmissionDate);
		setScore(new Double(1));
		setFile(theFile);
		setFileName(fileName);
	}


	private Artifact() {}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public ArtifactId getId() {
		return id;
	}

	
	private void setId(ArtifactId theId) {
		id = theId;
	}


	public Date getSubmissionDate() {
		return submissionDate;
	}


	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}


	/**
	 * This method returns the score given by the professor to the Artifact. It could be from 1 to 10.
	 * @return the score given by the professor to the Artifact
	 */
	public Double getScore() {
		return score;
	}


	/**
	 * This method sets the score given by the professor to the Artifact. It could be from 1 to 10.
	 *
	 * @param score the score of the artifact
	 * @throws InvalidArgumentException if the score is smaller than 1 or bigger than 10
	 */
	public void setScore(Double score) throws InvalidArgumentException {
		if (score < 1 || score > 10) 
			throw new InvalidArgumentException("The score must be a double value in the range of [1-10]");
		
		this.score = score;
	}


	public IncomingFileDTO getFile() throws DownloadErrorException {
		return new IncomingFileDTO(file, fileName);
	}


	public void setFile(byte[] theFile) {
		this.file = theFile;
	}
	
	/** 
	 * This method calculates the final score of an Artifact. If the artifact wasn't submitted in late, the final score is equal
	 * to the professor score. If the artifact is delivered in late, the final score is computed with this formula: <br/><br/>
	 * final score = professor's score - (days in late) * (deliverable late penalty)
	 * @return the artifact final score
	 */
	public Double getFinalScore() {
		
		long daysInLate = CalendarUtility.daysBetween(getId().getDeliverable().getDeadline(), getSubmissionDate());
		
		System.out.println("Calculating final score of an artifact");
		System.out.println("Deliverable deadline: " + getId().getDeliverable().getDeadline());
		System.out.println("Artifact submission date: " + getSubmissionDate());
		System.out.println("Days in late: " + daysInLate);
		
		if (daysInLate < 0)
			daysInLate = 0;
		
		Double score = new Double(getScore() - daysInLate * getId().getDeliverable().getPenalty());
		
		if (score < 1)
			score = new Double(1);
		
		return score;
	}

	public ArtifactDTO getDTO() {

		return new ArtifactDTO(getId().getDTO(), getSubmissionDate(), getFileName(), getScore(), getFinalScore());
		
	}
	
	@Override
	public boolean equals(Object object) {
        if (object instanceof Artifact) {
        	Artifact pk = (Artifact)object;
  
        	
            return  getId().equals( pk.getId() ) &&
            		getSubmissionDate().equals(pk.getSubmissionDate()) && 
            		getFileName().equals(pk.getFileName());
        } else {
            return false;
        }
    }
 
	@Override
    public int hashCode() {
        return getId().hashCode() + getSubmissionDate().hashCode() + getFileName().hashCode();
    }
	
	
 
}
