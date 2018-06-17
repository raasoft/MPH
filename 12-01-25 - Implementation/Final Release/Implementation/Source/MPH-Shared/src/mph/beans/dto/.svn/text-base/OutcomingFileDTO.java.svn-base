package mph.beans.dto;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.rmi.RemoteException;

import mph.beans.exceptions.FileTooLargeException;
import mph.beans.exceptions.UploadErrorException;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.SimpleRemoteInputStream;

/**
 * This class contains the information about an outgoing file.<br/>
 * It implements the {@link DataTransferObject} interface.
 */
public class OutcomingFileDTO implements DataTransferObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2034469705163246044L;

	
	private File filePath;
	private RemoteInputStream fileDataIn = null;
	
	/**
	 * Create the DTO with the given argument
	 * @param theFilepath the path of the file to upload
	 * @throws FileNotFoundException if the path given as parameter does not contain any file
	 * @throws FileTooLargeException if the file exceeds the maximum allowed size
	 * @throws UploadErrorException if an error occurs during the upload of the file
	 */
	public OutcomingFileDTO(String theFilepath) throws FileNotFoundException, FileTooLargeException, UploadErrorException {
		
		
		
		
		filePath =  new File(theFilepath);
		try {
			
			if (filePath.length() > ArtifactDTO.FILE_MAX_SIZE_IN_MEGABYTES * 1000000) {
				throw new FileTooLargeException("File size is too large. Only " + ArtifactDTO.FILE_MAX_SIZE_IN_MEGABYTES+ " megabytes are allowed.");
			}
			
			fileDataIn = (new SimpleRemoteInputStream(new FileInputStream(filePath))).export();
		} catch (RemoteException e) {
			throw new UploadErrorException("Can't prepare the file to be downloaded. Retry.");
		}
		
		
	}

	/**
	 * @return the filePath
	 */
	public File getFilePath() {
		return filePath;
	}

	/**
	 * @return the fileData
	 */
	public RemoteInputStream exportInUpload() {
		
		return fileDataIn;
	}
	
}
