package sessionBean;
import javax.ejb.Remote;

import entityBean.FileRelease;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import exception.TuplaNonTrovataException;

@Remote
public interface ManagerReleaseRemote {
	
	public FileRelease getById(Long id) 
			throws TuplaNonTrovataException;
	
	public void CondividiRelease(Long idRelease, String nomeGruppo) 
			throws TuplaNonTrovataException;

	public void AggiungiReleaseVotoNullo(Long idRelease, Long idProgettoGruppo) 
			throws TuplaNonTrovataException;
	public ProgettoRelease getProgettoRelease(Long id) throws TuplaNonTrovataException;
}
