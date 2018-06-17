package sessionBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBean.Amministratore;
import entityBean.Gruppo;
import entityBean.Professore;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.Studente;
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
import exception.StudenteEsistenteException;
import exception.TuplaNonTrovataException;

@Stateful
@Remote(RegistrazioneStudenteRemote.class)
public class RegistrazioneStudente implements RegistrazioneStudenteRemote {

	@PersistenceContext(unitName="mph")
	private EntityManager em;
	private Studente stud = null;
	Gruppo g = null;
	List<Gruppo> listagruppiconposti;
	ProgettoGruppo prg;
	boolean control = false;

	public RegistrazioneStudente() {
    }
    
    @SuppressWarnings("unchecked")
	@Override
	public Studente creaStudente(String username, String password, String confirmPsw, String nome, String cognome, String email, String matricola)
			throws StudenteEsistenteException, CampiNonCompletiException, PasswordCortaException, EmailErrataException, ConfrontoPasswordException, MatricolaNonConformeException, MatricolaUnicaException {
		List<Professore> professori = em.createNamedQuery("Professore.getProfessoreByUsername").setParameter("username", username).getResultList();
		List<Amministratore> admin = em.createNamedQuery("Amministratore.getAmministratoreByUsername").setParameter("username", username).getResultList();
		List<Studente> studenti = em.createNamedQuery("Studente.getStudenteByUsername").setParameter("username", username).getResultList();
		List<Studente> studenti2 = em.createNamedQuery("Studente.getStudenteByMatricola").setParameter("matricola", matricola).getResultList();
		stud = new Studente();
		if ((professori.size() > 0) || (studenti.size() > 0) || (admin.size() > 0))
			throw new StudenteEsistenteException("username in uso");
		if(studenti2.size() > 0)
			throw new MatricolaUnicaException();
		if ((username.compareTo("") == 0) || (password.compareTo("") == 0) || (nome.compareTo("") == 0) || (email.compareTo("") == 0) || (matricola.compareTo("") == 0) || (confirmPsw.compareTo("") == 0))
			throw new CampiNonCompletiException();
		if (email.compareTo("") != 0) {
			Pattern pat = Pattern.compile(".+@.+\\.[a-z]+");
			Matcher m = pat.matcher(email);
			Boolean matchFound = m.matches();
			if (matchFound)
				stud.setEmail(email);
			else
				throw new EmailErrataException();
		}
		if (password.length() < 4)
			throw new PasswordCortaException();
		else {
			if(confirmPsw.compareTo(password) == 0)
				stud.setPassword(password);
			else
				throw new ConfrontoPasswordException();
		}	
		if (matricola.compareTo("") != 0)
			for (int i = 0; i < matricola.length(); i++)           
				if (Character.isDigit(matricola.charAt(i)) == false)               
					throw new MatricolaNonConformeException();	
		stud.setUsername(username);
		stud.setCognome(cognome);
		stud.setEmail(email);
		stud.setPassword(password);
		stud.setMatricola(matricola);
		stud.setNome(nome);
		//em.persist(stud);
		listagruppiconposti = new ArrayList<Gruppo>();
		//listagruppiconposti = em.createNamedQuery("Gruppo.getGruppi").getResultList();
		for(Gruppo g1 : (List<Gruppo>)em.createNamedQuery("Gruppo.getGruppi").getResultList())
			if((g1.getStudenti().size() < 3) && (!g1.isControl()))
				listagruppiconposti.add(g1);
		control = true;
		return stud;
	}

    @SuppressWarnings("unchecked")
	@Override
	public void aggiungiAGruppo(Studente s, String gruppo)
			throws GruppoCompletoException, GruppoNonTrovatoException, ProgettoGiaSeguitoException {
		List<Gruppo> gruppi = em.createNamedQuery("Gruppo.getGruppoByNome").setParameter("nome", gruppo).getResultList();
		if (gruppi.size() > 0)
		{
			Set<ProgettoGruppo> prg = new HashSet<ProgettoGruppo>();
			for(Gruppo g : s.getGruppi())
				prg.addAll(g.getProgettigruppo());
			g = gruppi.get(0);
			for(ProgettoGruppo pg : g.getProgettigruppo())
				for(ProgettoGruppo p : prg)
					if(p.getProgetto().getNome().compareTo(pg.getProgetto().getNome()) == 0)
						throw new ProgettoGiaSeguitoException();
		}
		else
			throw new GruppoNonTrovatoException();
    	if (stud == null)
    		stud = s;
		
		if (g.getStudenti().size() > 3)
			//g.addStudente(stud);
		//else 
			throw new GruppoCompletoException();
		if (!control) {
			em.merge(stud);
			g.addStudente(stud);
			em.merge(g);
		}
	}
    
    @SuppressWarnings("unchecked")
	@Override
	public void aggiungiAGruppoRegistrazione(Studente s, String gruppo)
			throws GruppoCompletoException, GruppoNonTrovatoException {
    	if (stud == null)
    		stud = s;
		List<Gruppo> gruppi = em.createNamedQuery("Gruppo.getGruppoByNome").setParameter("nome", gruppo).getResultList();
		if (gruppi.size() > 0)
			g = gruppi.get(0);
		else
			throw new GruppoNonTrovatoException();
		if (g.getStudenti().size() > 3)
			//g.addStudente(stud);
		//else 
			throw new GruppoCompletoException();
		if (!control) {
			g.addStudente(stud);
			em.merge(stud);
			em.merge(g);
		}
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
    
    @SuppressWarnings("unchecked")
	@Override
	public Gruppo creaGruppo(String nome, boolean c, Studente s)
			throws GruppoEsistenteException, CampiNonCompletiException {
		if(stud == null)
			stud = s;
    	List<Gruppo> lista = em.createNamedQuery("Gruppo.getGruppoByNome").setParameter("nome", nome).getResultList();
    	if(nome.compareTo("") == 0) 
			throw new CampiNonCompletiException();
		if(lista.size() > 0)
			throw new GruppoEsistenteException();
		
		g = new Gruppo();
		g.setNome(nome);
		g.setControl(c);
		return g;
	}
    
    @SuppressWarnings("unchecked")
    @Override
	public ProgettoGruppo scegliProgetto(String nome) {
    	List<Progetto> proj = (List<Progetto>) em.createNamedQuery("Progetto.getByNome").setParameter("nome", nome).getResultList();
    	if (proj.size() > 0) {
    		prg = new ProgettoGruppo();
    		prg.setGruppo(g);
    		prg.setProgetto(proj.get(0));
    		if (!control) {
    			em.merge(stud);
    			g.addStudente(stud);
        		em.merge(g);
        		em.merge(prg);
    		}
    		/*if(stud != null)
    			em.persist(stud);*/
    		return prg;
    	}
    	return null;
    }
    
    @Override
    public void finalizza() {
    	if (control) {
    		em.persist(stud);
    		g.addStudente(stud);
    		em.merge(g);
    	}
		if (prg !=null )
			em.merge(prg);
    }
    
	@Override
	public List<Gruppo> getListaGruppiConPosti(){
		// TODO Auto-generated method stub
		/*List<Gruppo> lista = em.createNamedQuery("Gruppo.getGruppi").getResultList();
		for(Gruppo g1 : lista)
			if((g1.getStudenti().size() >= 3) || (g1.isControl()))
				lista.remove(g1);*/
		return listagruppiconposti;
	}
    
    @Remove
    public void remove() {
    }

}
