package sessionBean;

import java.util.List;

import javax.ejb.Remote;

import entityBean.Gruppo;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Studente;
import exception.CambioPswFallitoException;
import exception.CampiNonCompletiException;
import exception.ConfrontoPasswordException;
import exception.EmailErrataException;
import exception.GruppoCompletoException;
import exception.GruppoEsistenteException;
import exception.GruppoNonTrovatoException;
import exception.MatricolaNonConformeException;
import exception.MatricolaUnicaException;
import exception.PasswordCortaException;
import exception.PasswordErrataException;
import exception.ProgettoGiaSeguitoException;
import exception.TuplaNonTrovataException;

@Remote
public interface ManagerStudenteRemote {

	public Studente getByUsername(String username) throws TuplaNonTrovataException;
	
	public List<ProgettoGruppo> getProgetti(Studente studente) throws TuplaNonTrovataException;
	
	public List<Gruppo> getGruppi(Studente studente) throws TuplaNonTrovataException;
	
	public List<Gruppo> getListaGruppi();
	
	public Studente aggiungiAGruppo(Studente s, String gruppo) throws GruppoCompletoException, GruppoNonTrovatoException;

	List<Progetto> getProgetti();
	
	void creaGruppo(String nome, boolean c, Studente s) throws GruppoEsistenteException, CampiNonCompletiException;

	public Studente modificaDati(String username, String oldpsw, String newpsw,
			String confnewpsw, String nome, String cognome, String email,
			String matricola) throws TuplaNonTrovataException, PasswordErrataException, PasswordCortaException, CampiNonCompletiException, EmailErrataException, ConfrontoPasswordException, MatricolaNonConformeException, MatricolaUnicaException, CambioPswFallitoException;

	List<Gruppo> getListaGruppiConPosti(Studente s);

	void verificaSeSegueProgetto(Studente studente, String nome)
			throws TuplaNonTrovataException, ProgettoGiaSeguitoException;

	void aggiungiProgettoAGruppo(Gruppo gruppo, Progetto progetto);

	Progetto getProgettoByNome(String nome) throws TuplaNonTrovataException;

	ProgettoGruppo getProgettoGruppoById(String nome) throws TuplaNonTrovataException;
	
	public ProgettoRelease getProgettoReleaseById(String id) throws TuplaNonTrovataException;
}
