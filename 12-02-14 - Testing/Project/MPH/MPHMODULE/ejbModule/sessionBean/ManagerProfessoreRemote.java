package sessionBean;

import javax.ejb.Remote;

import java.util.List;

import exception.CambioPswFallitoException;
import exception.CampiNonCompletiException;
import exception.ConfrontoPasswordException;
import exception.EmailErrataException;
import exception.EmailVuotaException;
import exception.NoProjectsException;
import exception.PasswordCortaException;
import exception.PasswordErrataException;
import exception.TelefonoErratoException;
import exception.TuplaNonTrovataException;
import entityBean.Professore;
import entityBean.Progetto;
import entityBean.ProgettoRelease;
import entityBean.Releas;

@Remote
public interface ManagerProfessoreRemote {
	
	public List<Progetto> getProgetti(Professore professore);
	
	public Professore modificaDati(String username, String oldpsw, String newpsw, String confnewpsw, String nome, String cognome, String email, String telefono) 
				throws TuplaNonTrovataException, PasswordErrataException, PasswordCortaException, CampiNonCompletiException, EmailErrataException, EmailVuotaException, ConfrontoPasswordException, TelefonoErratoException, CambioPswFallitoException;
	
	public Professore getProfessoreByUsername(String username) throws TuplaNonTrovataException;

	public ProgettoRelease Valuta(int voto, Long id) throws TuplaNonTrovataException;

	public Releas fineConsegna(Long id) throws TuplaNonTrovataException;
	
	public Releas getReleaseById(Long id) throws TuplaNonTrovataException;
}
