package sessionBean;
import java.io.File;
import java.text.ParseException;

import javax.ejb.Remote;

import exception.TuplaNonTrovataException;

@Remote
public interface UploadReleaseBeanRemote {
	
	public void creaRelease(String id, String nomeProg) throws TuplaNonTrovataException;
	
	public void uploadFile(File file, String nome, String descrizione, String path);

	public void remove();

	void finalizza() throws ParseException;

}
