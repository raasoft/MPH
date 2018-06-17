package sessionBean;

import java.util.List;

import javax.ejb.Remote;

import entityBean.Gruppo;
import entityBean.Professore;
import entityBean.Studente;
import exception.CampiNonCompletiException;
import exception.EmailErrataException;
import exception.PasswordCortaException;
import exception.PasswordErrataException;
import exception.ProfessoreEsistenteException;
import exception.TelefonoErratoException;
import exception.TuplaNonTrovataException;

@Remote
public interface ManagerAmministratoreRemote {
	
	public void aggiungiProfessore(String nome,
			  String cognome,
			  String username,
			  String telefono,
			  String email,
			  String password)
	throws ProfessoreEsistenteException, CampiNonCompletiException, TelefonoErratoException, EmailErrataException, PasswordCortaException;
	
	public List<Professore> getListaProfessori();
	
	//public void eliminaProfessore(String username) throws TuplaNonTrovataException;
	
	//public void eliminaStudente(String username) throws TuplaNonTrovataException;
	
	//public void eliminaGruppo(String username) throws TuplaNonTrovataException;

	public List<Studente> getListaStudenti();

	public List<Gruppo> getListaGruppi();
	
	public Gruppo getGruppoByNome(String nome) throws TuplaNonTrovataException;

}
