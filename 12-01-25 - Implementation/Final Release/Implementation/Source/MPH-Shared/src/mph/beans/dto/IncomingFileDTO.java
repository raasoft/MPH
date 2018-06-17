package mph.beans.dto;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

import mph.beans.exceptions.DownloadErrorException;

import com.healthmarketscience.rmiio.RemoteInputStream;
import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.healthmarketscience.rmiio.RemoteInputStreamServer;
import com.healthmarketscience.rmiio.SimpleRemoteInputStream;
;

/**
 * This class contains the information about an incoming file.<br/>
 * It implements the {@link DataTransferObject} interface.
 */
public class IncomingFileDTO implements DataTransferObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2034469705163246044L;
	
	
	private RemoteInputStream fileDataIn = null;
	private String fileName;
	

	
	/**
	 * Create the DTO with the given arguments
	 * @param theFile an array of bytes representing the file
	 * @param theFileName the name of the file
	 * @throws DownloadErrorException if an error occurs during the download of the file
	 */
	public IncomingFileDTO(byte[] theFile, String theFileName) throws DownloadErrorException  {

		fileName = theFileName;

		RemoteInputStreamServer istream = null;
	    try {
	      istream = new SimpleRemoteInputStream( new ByteArrayInputStream( theFile  ) );
	      // export the final stream for returning to the client
	      fileDataIn = istream.export();
	      // after all the hard work, discard the local reference (we are passing
	      // responsibility to the client)
	      istream = null;

	    } catch (RemoteException e) {
			throw new DownloadErrorException("Can't prepare the file to be downloaded. Retry.");
		} finally {
	      // we will only close the stream here if the server fails before
	      // returning an exported stream
	      if(istream != null) istream.close();
	    }
	}

	/**
	 * @return the remote input stream of the file
	 */
	public RemoteInputStream exportInDownload() {
		
		return fileDataIn;
	}
	
	/**
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Save the file in the path given as parameter
	 * @param thePath the file path where the file will be saved
	 * @throws IOException if an error occurs while saving the file
	 */
	public void saveAs(String thePath) throws IOException {

		FileOutputStream out = null;
		try {
			File aFile = new File(thePath);
			
			System.out.println("Trying to save the file in " + aFile);
			
			try {
			out = new FileOutputStream(aFile);
			}
			catch (FileNotFoundException e) {
				throw new FileNotFoundException("Can't save the file to " + thePath);
			}
			InputStream istream = null;
			try {
				istream = RemoteInputStreamClient.wrap(exportInDownload());
			}
			catch (IOException e) { 
				throw new IOException("Error while reading the downloaded file (Error 79)"); 
				}
			
			int c;
			while ((c = istream.read()) != -1) {
				try {
					out.write(c);
				}
				catch (IOException e) { 
					throw new IOException("Error while writing the downloaded file (Error 80)"); 
					}
			}
		}
		finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}


	}

}
