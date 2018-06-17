package sessionBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBean.Gruppo;
import entityBean.Professore;
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

@Stateless
@Remote(ManagerStudenteRemote.class)
public class ManagerStudente implements ManagerStudenteRemote {

	@PersistenceContext(unitName="mph")
	private EntityManager em;
	
	@Override
	public Studente getByUsername(String username)
			throws TuplaNonTrovataException {
		@SuppressWarnings("unchecked")
		List<Studente> stud = em.createNamedQuery("Studente.getStudenteByUsername").setParameter("username", username).getResultList();
		if(stud.size() > 0)
			return stud.get(0);
		else throw new TuplaNonTrovataException();
	}
	
	@Override
	public Progetto getProgettoByNome(String nome)
			throws TuplaNonTrovataException {
		@SuppressWarnings("unchecked")
		List<Progetto> proj = em.createNamedQuery("Progetto.getByNome").setParameter("nome", nome).getResultList();
		if(proj.size() > 0)
			return proj.get(0);
			else throw new TuplaNonTrovataException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProgettoGruppo> getProgetti(Studente studente)
			throws TuplaNonTrovataException {
		List<Gruppo> lista = em.createNamedQuery("Studente.getGruppi").setParameter("username", studente.getUsername()).getResultList();
		List<ProgettoGruppo> lista2 = new ArrayList<ProgettoGruppo>();
		for(Gruppo g : lista) {
			lista2.addAll(g.getProgettigruppo());
		}
		return lista2;
	}
	
	@Override
	public void aggiungiProgettoAGruppo (Gruppo gruppo, Progetto progetto) {
		ProgettoGruppo pg = new ProgettoGruppo();
		pg.setGruppo(gruppo);
		pg.setProgetto(progetto);
		pg.setVotoFinale(0);
		em.merge(pg);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void verificaSeSegueProgetto(Studente studente, String nome)
			throws TuplaNonTrovataException, ProgettoGiaSeguitoException {
		List<Gruppo> lista = em.createNamedQuery("Studente.getGruppi").setParameter("username", studente.getUsername()).getResultList();
		List<ProgettoGruppo> lista2 = new ArrayList<ProgettoGruppo>();
		for(Gruppo g : lista) {
			lista2.addAll(g.getProgettigruppo());
		}
		for(ProgettoGruppo pg : lista2)
			if(pg.getProgetto().getNome().compareTo(nome) == 0)
				throw new ProgettoGiaSeguitoException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gruppo> getGruppi(Studente studente)
			throws TuplaNonTrovataException {
		return em.createNamedQuery("Studente.getGruppi").setParameter("username", studente.getUsername()).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Progetto> getProgetti() {
		return em.createNamedQuery("Progetto.getAll").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gruppo> getListaGruppi() {
		List<Gruppo> gruppi = em.createNamedQuery("Gruppo.getGruppi").getResultList();
		return gruppi;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public ProgettoRelease getProgettoReleaseById(String id)
			throws TuplaNonTrovataException {
		List<ProgettoRelease> pr = em.createNamedQuery("ProgettoRelease.getById").setParameter("id", Long.valueOf(id)).getResultList();
		if (pr.size()>0)
			return pr.get(0);
		else
			throw new TuplaNonTrovataException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gruppo> getListaGruppiConPosti(Studente s) {
		List<Gruppo> gruppi = em.createNamedQuery("Studente.getGruppi").setParameter("username", s.getUsername()).getResultList();
		List<Gruppo> listagruppiconposti = new ArrayList<Gruppo>();
		for(Gruppo g1 : (List<Gruppo>)em.createNamedQuery("Gruppo.getGruppi").getResultList())
			if((g1.getStudenti().size() < 3) && (!g1.isControl()))
				listagruppiconposti.add(g1);
		/*for(Gruppo g : listagruppiconposti)
			for(Gruppo g1 : gruppi)
				if(g.getNome().compareTo(g1.getNome()) == 0)
					listagruppiconposti.remove(g);
		listagruppiconposti.removeAll(gruppi);*/
		for (Gruppo g2 : (List<Gruppo>) gruppi)
			listagruppiconposti.remove(g2);
		return listagruppiconposti;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Studente aggiungiAGruppo(Studente s, String gruppo)
			throws GruppoCompletoException, GruppoNonTrovatoException {
		List<Gruppo> gruppi = em.createNamedQuery("Gruppo.getGruppoByNome").setParameter("nome", gruppo).getResultList();
		Gruppo g = null;
		if(gruppi.size() > 0)
			g = gruppi.get(0);
		else
			throw new GruppoNonTrovatoException();
		if(g.getStudenti().size() < 3)
			g.addStudente(s);
		else 
			throw new GruppoCompletoException();
		em.merge(g);
		return s;
	}

	@Override
	public void creaGruppo(String nome, boolean c, Studente s)
			throws GruppoEsistenteException, CampiNonCompletiException {	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProgettoGruppo getProgettoGruppoById(String nome) throws TuplaNonTrovataException{	
		List<ProgettoGruppo> lista = em.createNamedQuery("ProgettoGruppo.getById").setParameter("id", Long.valueOf(nome)).getResultList();
		if(lista.size() > 0)
			return lista.get(0);
		else
			throw new TuplaNonTrovataException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Studente modificaDati(String username, String oldpsw, String newpsw, String confnewpsw, String nome, String cognome, String email, String matricola)
			throws TuplaNonTrovataException, PasswordErrataException, PasswordCortaException, CampiNonCompletiException, EmailErrataException, ConfrontoPasswordException, MatricolaNonConformeException, MatricolaUnicaException, CambioPswFallitoException {
		List<Studente> studenti = (List<Studente>) em.createNamedQuery("Studente.getStudenteByUsername").setParameter("username", username).getResultList();
		List<Studente> studenti2 = em.createNamedQuery("Studente.getStudenteByMatricola").setParameter("matricola", matricola).getResultList();
		String numeri = "0123456789";
		if (studenti.size() > 0) {
			if (email.isEmpty() || nome.isEmpty() || cognome.isEmpty() || matricola.isEmpty())
				throw new CampiNonCompletiException();
			Studente s = studenti.get(0);
			if (s.getMatricola().compareTo(matricola) != 0)
				if(studenti2.size() > 0)
					throw new MatricolaUnicaException();
			if (email.compareTo("") != 0) {
				Pattern pat = Pattern.compile(".+@.+\\.[a-z]+");
				Matcher m = pat.matcher(email);
				Boolean matchFound = m.matches();
				if (!matchFound)
					throw new EmailErrataException();
			}
			for (int i = 0; i < matricola.length(); i++) {           
				if (Character.isDigit(matricola.charAt(i)) == false) {                
					throw new MatricolaNonConformeException();	
				}
			}
			if ((!oldpsw.isEmpty()) && (!newpsw.isEmpty()) && (!confnewpsw.isEmpty())) {
				if (oldpsw.compareTo(s.getPassword()) == 0) {
					if (newpsw.length() < 4)
						throw new PasswordCortaException();
					else {
						if (confnewpsw.compareTo(newpsw) == 0) {
							s.setPassword(newpsw);
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
			s.setEmail(email);
			s.setMatricola(matricola);
			s.setNome(nome);
			s.setCognome(cognome);
			em.merge(s);
			return s;
		}
		else
			throw new TuplaNonTrovataException("<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Errore</h1></center>Studente non trovato, riprovare!',0,0,0,0)</script>");
	}
}
