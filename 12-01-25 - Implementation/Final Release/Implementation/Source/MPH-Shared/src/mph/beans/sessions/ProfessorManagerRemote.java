package mph.beans.sessions;

import javax.ejb.Remote;


/**
 * This remote interface will be implemented by the ProfessorManager classes in the server and client.<br/>
 * It provides the methods available to professors.<br/><br/>
 * It extends {@link BasicUserManagerInterface}, {@link ProjectEditingFunctionalities}, {@link ArtifactEvaluatorFunctionalities}.
 */
@Remote
public interface ProfessorManagerRemote extends 	BasicUserManagerInterface,
													ProjectEditingFunctionalities,
													ArtifactEvaluatorFunctionalities
													
{
			
}