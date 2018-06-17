package mph.beans.dto.ids;

/**
 * This abstract class will be extended by the DTO's containing the user identifier.<br/>
 * It implements the {@link IdentifierDTO} interface.
 */
abstract public class UserIdDTO implements IdentifierDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String username;
	
	/**
	 * Create the id with the given argument
	 * @param theUsername
	 */
	public UserIdDTO(String theUsername) {
		super();
		setUsername(theUsername);
	}

	/**
	 * Create a new instance of the id given as parameter.<br/>
	 * See {@link #UserIdDTO(String)}.
	 */
	public UserIdDTO(UserIdDTO theUserId) {
		this(new String(theUserId.getUsername()));
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Set the username
	 * @param username the new username to set
	 */
	private void setUsername(String username) {
		this.username = username;
	}
	
	public String toString() {
		return "[User Username: " + getUsername()+"]";
	}

	
}
