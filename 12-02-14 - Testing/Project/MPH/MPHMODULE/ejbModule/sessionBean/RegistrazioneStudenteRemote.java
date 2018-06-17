package sessionBean;
import java.util.List;

import javax.ejb.Remote;

import entityBean.Gruppo;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
import exception.CampiNonCompletiException;
import exception.ConfrontoPasswordException;
import exception.EmailErrataException;
import exception.EmailVuotaException;
import exception.GruppoCompletoException;
import exception.GruppoEsistenteException;
import exception.GruppoNonTrovatoException;
import exception.MatricolaNonConformeException;
import exception.MatricolaUnicaException;
import exception.PasswordCortaException;
import exception.PasswordErrataException;
import exception.ProgettoGiaSeguitoException;
import exception.StudenteEsistenteException;
import exception.TuplaNonTrovataException;

@Remote
public interface RegistrazioneStudenteRemote {

	public Studente creaStudente(String username, String password, String confirmPsw, String nome,
			String cognome, String email, String matricola)
			throws StudenteEsistenteException, CampiNonCompletiException, PasswordCortaException, EmailErrataException, ConfrontoPasswordException, MatricolaNonConformeException, MatricolaUnicaException;

	public void aggiungiAGruppo(Studente studente, String nomeGruppo) throws GruppoCompletoException, GruppoNonTrovatoException, ProgettoGiaSeguitoException;

	public Gruppo creaGruppo(String nome, boolean c, Studente s)
			throws GruppoEsistenteException, CampiNonCompletiException;

	ProgettoGruppo scegliProgetto(String nome);

	List<Gruppo> getListaGruppiConPosti();

	List<ProgettoGruppo> getProgetti(Studente studente)
			throws TuplaNonTrovataException;

	public void finalizza();
	
	public void remove();

	void aggiungiAGruppoRegistrazione(Studente s, String gruppo)
			throws GruppoCompletoException, GruppoNonTrovatoException;

}
