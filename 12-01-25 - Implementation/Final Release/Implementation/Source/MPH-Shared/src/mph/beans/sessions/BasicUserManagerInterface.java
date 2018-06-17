package mph.beans.sessions;


/**
 * This interface will be implemented by the user interfaces to provide the basic user functionalities such as viewing.<br/>
 * It extends {@link UserSessionInterface}, {@link CourseViewerFunctionalities}, {@link ProjectViewerFunctionalities}, {@link DeliverableViewerFunctionalities}, {@link ArtifactViewerFunctionalities}, {@link TeamViewerFunctionalities}.
 */
public interface BasicUserManagerInterface extends 	
										UserSessionInterface,
										CourseViewerFunctionalities,
										ProjectViewerFunctionalities,
										DeliverableViewerFunctionalities,
										ArtifactViewerFunctionalities,
										TeamViewerFunctionalities
										

{
	
		
}
