package sessionBean;

import java.util.List;
import java.util.regex.*;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBean.Professore;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;
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

@Stateless
@Remote(ManagerProfessoreRemote.class)
@Local(ManagerProfessoreLocal.class)
public class ManagerProfessore implements ManagerProfessoreRemote, ManagerProfessoreLocal {

	@PersistenceContext(unitName="mph")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Progetto> getProgetti(Professore professore) {
		List<Progetto> progetti = (List<Progetto>) em.createNamedQuery("Progetto.getProgettiByProfessore").setParameter("professore", professore).getResultList();
		return progetti;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Professore modificaDati(String username, String oldpsw, String newpsw, String confnewpsw, String nome, String cognome, String email,
			String telefono) throws TuplaNonTrovataException, PasswordErrataException, PasswordCortaException, CampiNonCompletiException, EmailErrataException, EmailVuotaException, ConfrontoPasswordException, TelefonoErratoException, CambioPswFallitoException {
		List<Professore> professori = (List<Professore>) em.createNamedQuery("Professore.getProfessoreByUsername").setParameter("username", username).getResultList();
		String numeri = "0123456789";
		if(professori.size() > 0) {
			if (email.isEmpty() || nome.isEmpty() || cognome.isEmpty() || username.isEmpty())
				throw new CampiNonCompletiException();
			Professore p = professori.get(0);	
			if(email.compareTo("") != 0) {
				Pattern pat = Pattern.compile(".+@.+\\.[a-z]+");
				Matcher m = pat.matcher(email);
				Boolean matchFound = m.matches();
				if (!matchFound)
					throw new EmailErrataException();
			}
			for (int i = 0; i < telefono.length(); i++)           
				if (Character.isDigit(telefono.charAt(i)) == false){                
					throw new TelefonoErratoException();	
				}
			if((!oldpsw.isEmpty()) && (!newpsw.isEmpty()) && (!confnewpsw.isEmpty())) {
				if(oldpsw.compareTo(p.getPassword()) == 0) {
					if(newpsw.length() < 4)
						throw new PasswordCortaException();
					else {
						if(confnewpsw.compareTo(newpsw) == 0) {
							p.setPassword(newpsw);
						}
						else
							throw new ConfrontoPasswordException();
					}		
				}
				else
					throw new PasswordErrataException();
			}
			if (!oldpsw.isEmpty() || !newpsw.isEmpty() || !confnewpsw.isEmpty()) 
				throw new CambioPswFallitoException();
			p.setEmail(email);
			p.setTelefono(telefono);
			p.setNome(nome);
			p.setCognome(cognome);
			em.merge(p);
			return p;
		}
		else
			throw new TuplaNonTrovataException("<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Professore non trovato, riprovare!',0,0,0,0)</script>");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Professore getProfessoreByUsername(String username)
			throws TuplaNonTrovataException {
		List<Professore> prof = em.createNamedQuery("Professore.getProfessoreByUsername").setParameter("username", username).getResultList();
		if(prof.size() > 0)
			return prof.get(0);
		else throw new TuplaNonTrovataException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Releas fineConsegna(Long id)
			throws TuplaNonTrovataException {
		
		List<Releas> release = em.createNamedQuery("Releas.getReleaseById").setParameter("id", id).getResultList();
		Releas tmp = null;
		if(release.size() > 0)
			tmp = release.get(0);
		else 
			throw new TuplaNonTrovataException();
		tmp.setConsegnabile(false);
		em.merge(tmp);
		return tmp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProgettoRelease Valuta(int voto, Long id) throws TuplaNonTrovataException{
		List<ProgettoRelease> lista = em.createNamedQuery("ProgettoRelease.getById").setParameter("id", id).getResultList();
		ProgettoRelease pr = null;
		if(lista.size() > 0)
			pr = lista.get(0);
		else
			throw new TuplaNonTrovataException();
		if(pr.getPenalita() != 0)
		{
			int votof = 0;
			if((voto - pr.getPenalita()) > 0)
			{
				votof = voto - pr.getPenalita();
				pr.setVotoParziale(votof);
			}
			else
				pr.setVotoParziale(1);
		}
		pr.setVotoParziale(voto);
		boolean ctrl = false;
		int vototot = 0;
		for(ProgettoRelease x : pr.getIdProgettoGruppo().getListaRelease())
		{
			if(x.getVotoParziale() == 0)
				ctrl = true;
			else
				vototot += x.getVotoParziale();
		}
		if(!ctrl)
		{
			float votom = vototot/pr.getIdProgettoGruppo().getListaRelease().size();
			pr.getIdProgettoGruppo().setVotoFinale(votom);
		}
		return pr;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Releas getReleaseById(Long id)
			throws TuplaNonTrovataException {
		List<Releas> rel = em.createNamedQuery("Releas.getReleaseById").setParameter("id", id).getResultList();
		if(rel.size() > 0)
			return rel.get(0);
		else throw new TuplaNonTrovataException();
	}
}
