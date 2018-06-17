package mph.beans.dto;

import java.sql.Date;

import mph.beans.dto.ids.ArtifactIdDTO;

/**
 * This class contains the information about an artifact.<br/>
 * It implements the {@link EntityDTO} interface.
 */
public class ArtifactDTO implements EntityDTO {
	
	/**
	 * 
	 */
	public static final Double FILE_MAX_SIZE_IN_MEGABYTES = new Double(10); // 10 megabyte?
	
	private static final long serialVersionUID = -2034469705163246044L;
	
	private ArtifactIdDTO id;
	private Double score;
	private Double finalScore;
	private Date submissionDate;
	private String fileName;

	
	/**
	 * Create an artifact DTO with the given arguments
	 * @param theId the artifact id
	 * @param theSubmissionDate the artifact date of submission
	 * @param theFileName the name of the file referred by the artifact
	 * @param theScore the score assigned to the artifact
	 * @param theFinalScore the final score computed for the artifact
	 */
	public ArtifactDTO(ArtifactIdDTO theId, Date theSubmissionDate, String theFileName, Double theScore, Double theFinalScore) {
		
		setId(theId);
		setSubmissionDate(theSubmissionDate);
		setFileName(theFileName);
		setScore(theScore);
		setFinalScore(theFinalScore);

		
	}


	/**
	 * @return the serialversionUID
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public ArtifactIdDTO getId() {
		return id;
	}


	/**
	 * @return the score assigned to the artifact by the professor
	 */
	public Double getScore() {
		return score;
	}


	/**
	 * @return the final score of the artifact
	 */
	public Double getFinalScore() {
		return finalScore;
	}


	/**
	 * @return the date of submission of the artifact
	 */
	public Date getSubmissionDate() {
		return submissionDate;
	}


	/**
	 * @return the name of the file referred by the artifact
	 */
	public String getFileName() {
		return fileName;
	}


	/**
	 * Set the artifact id
	 * @param id the new id to set
	 */
	private void setId(ArtifactIdDTO id) {
		this.id = id;
	}


	/**
	 * Set the artifact score
	 * @param score the new score to set
	 */
	private void setScore(Double score) {
		this.score = score;
	}


	/**
	 * Set the artifact final score
	 * @param finalScore the new final score to set
	 */
	private void setFinalScore(Double finalScore) {
		this.finalScore = finalScore;
	}


	/**
	 * Set the artifact submission date
	 * @param submissionDate the new date of submission to set
	 */
	private void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}


	/**
	 * Set the artifact file name
	 * @param fileName the new file name
	 */
	private void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String toString() {

		return  getId() +
				"\nFilename: " + getFileName() +
				"\nSubmission Date; " + getSubmissionDate() +
				"\nProfessor Score: " + getScore() +
				"\nFinal Score: " + getFinalScore();
	}


}
