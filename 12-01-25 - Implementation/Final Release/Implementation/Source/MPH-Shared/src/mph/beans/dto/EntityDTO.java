package mph.beans.dto;

import mph.beans.dto.ids.IdentifierDTO;

/**
 * This interface is implemented by all the DTO classes.<br/>
 * It extends {@link DataTransferObject}.
 */
public interface EntityDTO extends DataTransferObject {
	
	/**
	 * @return the identifier of the DTO
	 */
	public IdentifierDTO getId();
	
}
