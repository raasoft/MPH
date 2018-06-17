package mph.beans.dto;

import java.util.Set;

import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;

/**
 * This class contains the information about a project team.<br/>
 * It implements the {@link EntityDTO} interface.
 */
public class TeamDTO implements EntityDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4460912079893408288L;
	
	private TeamIdDTO id;
	private Set<StudentIdDTO> studentSet;
	private Double teamScore;
	private boolean isClosed;
	
	/**
	 * Create the DTO with the given arguments
	 * @param theTeamIdDTO the id of the team
	 * @param theStudentSet the set of students belonging to the team
	 * @param isClosed true means the team does not accept any incoming membership requests
	 */
	public TeamDTO(TeamIdDTO theTeamIdDTO, Set<StudentIdDTO> theStudentSet, boolean isClosed, Double theTeamScore) {
		setId(theTeamIdDTO);
		setStudentSet(theStudentSet);
		setClosed(isClosed);
		setTeamScore(theTeamScore);
	}

	/**
	 * @return the id
	 */
	public TeamIdDTO getId() {
		return id;
	}

	

	/**
	 * @param theScore the team score
	 */
	private void setTeamScore(Double theScore) {
		teamScore = theScore;
	}

	/**
	 * @return the team score
	 */
	public Double getTeamScore() {
		return teamScore;
	}

	/**
	 * @return the studentList
	 */
	public Set<StudentIdDTO> getStudentSet() {
		return studentSet;
	}

	/**
	 * @return the isClosed
	 */
	public boolean isClosed() {
		return isClosed;
	}

	/**
	 * Set the team id
	 * @param id the new id to set
	 */
	private void setId(TeamIdDTO id) {
		this.id = id;
	}

	/**
	 * Add a set of students to the team
	 * @param studentSet the set of students to add
	 */
	private void setStudentSet(Set<StudentIdDTO> studentSet) {
		this.studentSet = studentSet;
	}

	/**
	 * Set whether the team accepts incoming membership requests or not
	 * @param isClosed true means the team accepts incoming requests
	 */
	private void setClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}
	
	public String toString() {
		return  getId() +
				"\nStudents: " + getStudentSet();
				
	}
	

}
