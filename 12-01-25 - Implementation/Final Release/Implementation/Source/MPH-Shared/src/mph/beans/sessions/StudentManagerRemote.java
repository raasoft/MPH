package mph.beans.sessions;

import javax.ejb.Remote;

/**
 * This remote interface will be implemented by the StudentManager classes in the server and client.<br/>
 * It provides the methods available to students.<br/><br/>
 * It extends {@link BasicUserManagerInterface}, {@link UserRegisteringFunctionalities}, {@link ArtifactEditingFunctionalities}, {@link TeamManagementFunctionalities}, {@link TeamCreationFunctionalities}, {@link TeamBasicFunctionalities}.
 */
@Remote
public interface StudentManagerRemote extends 	
										BasicUserManagerInterface,
										UserRegisteringFunctionalities, 
										ArtifactEditingFunctionalities,
										TeamManagementFunctionalities, TeamCreationFunctionalities,	TeamBasicFunctionalities
										

{
	

}
