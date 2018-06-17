package sessionBean;
 
import java.util.List;

import javax.ejb.Remote;

import entityBean.Progetto;
import entityBean.Releas;
import exception.CampiNonCompletiException;
import exception.ProgettoEsistenteException;
import exception.TuplaNonTrovataException;

@Remote
public interface CreaProgettoRemote {
	
	public List<Releas> aggiungiRelease(String tipo, String data) throws CampiNonCompletiException;
	
	public List<Releas> rimuoviRelease(String id) throws TuplaNonTrovataException;
	
	public Progetto creaProgetto(String nome, String descrizione, String materia, String username) 
			throws ProgettoEsistenteException, TuplaNonTrovataException, CampiNonCompletiException;
	
	public void finalizzaProgetto();
	
	public void annulla();
	
	public void remove();

}
