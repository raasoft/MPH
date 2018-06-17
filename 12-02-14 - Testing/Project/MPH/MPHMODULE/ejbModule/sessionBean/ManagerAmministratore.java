package sessionBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBean.Amministratore;
import entityBean.FileRelease;
import entityBean.Gruppo;
import entityBean.Professore;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;
import entityBean.Studente;
import exception.CampiNonCompletiException;
import exception.EmailErrataException;
import exception.PasswordCortaException;
import exception.PasswordErrataException;
import exception.ProfessoreEsistenteException;
import exception.TelefonoErratoException;
import exception.TuplaNonTrovataException;

@Stateless
@Remote(ManagerAmministratoreRemote.class)
public class ManagerAmministratore implements ManagerAmministratoreRemote {
	
	@PersistenceContext(unitName="mph")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public void aggiungiProfessore(String username,
								   String password,
								   String nome,
								   String cognome,
								   String telefono,
								   String email)
	throws ProfessoreEsistenteException, CampiNonCompletiException, TelefonoErratoException, EmailErrataException,PasswordCortaException
	{
		Professore prof;
		List<Professore> professori = em.createNamedQuery("Professore.getProfessoreByUsername").setParameter("username", username).getResultList();
		List<Amministratore> admin = em.createNamedQuery("Amministratore.getAmministratoreByUsername").setParameter("username", username).getResultList();
		List<Studente> studenti = em.createNamedQuery("Studente.getStudenteByUsername").setParameter("username", username).getResultList();
		if((professori.size() > 0) || (studenti.size() > 0) || (admin.size() > 0))
			throw new ProfessoreEsistenteException("username in uso");
		if((username.compareTo("") != 0) && (password.compareTo("") != 0) && (nome.compareTo("") != 0) && (cognome.compareTo("") != 0) && (email.compareTo("") != 0)){
			if (telefono.compareTo("") != 0)
				for (int i = 0; i < telefono.length(); i++)           
					if (Character.isDigit(telefono.charAt(i)) == false){                
						throw new TelefonoErratoException();
					}
			if(email.compareTo("") != 0){
				Pattern pat = Pattern.compile(".+@.+\\.[a-z]+");
				Matcher m = pat.matcher(email);
				Boolean matchFound = m.matches();
				if (!matchFound)
					throw new EmailErrataException();
			}
			if(password.length() < 4)
				throw new PasswordCortaException();
			prof = new Professore(nome, cognome, username, telefono, email, password);
			em.persist(prof);
		}
		else
			throw new CampiNonCompletiException();
	}
	
	@SuppressWarnings("unchecked")
	public List<Professore> getListaProfessori()
	{
		return em.createNamedQuery("Professore.getProfessori").getResultList();
	}
	
	/*@SuppressWarnings("unchecked")
	public void eliminaProfessore(String username) throws TuplaNonTrovataException
	{
		List<Professore> prof = em.createNamedQuery("Professore.getProfessoreByUsername").setParameter("username", username).getResultList();
		if(prof.size() > 0)
		{
			Professore p = prof.get(0);
			for(Progetto pr : p.getProgetti())
			{
				for(ProgettoGruppo pg : pr.getProgettigruppo())
				{
					for(ProgettoRelease prel : pg.getListaRelease())
					{
						for(FileRelease f : prel.getFiles())
							em.remove(f);
						em.remove(prel);
					}
					//pg.setListaRelease(null);
					//pg.setGruppo(null);
					em.remove(pg);
				}
				for(Releas r : pr.getListaRelease())
					em.remove(r);
				em.remove(pr);
			}
			em.remove(p);
			for(Gruppo g : (ArrayList<Gruppo>) em.createNamedQuery("Gruppo.getGruppi").getResultList())
			{
				if(g.getProgettigruppo().size() == 0)
				{
					g.setStudenti(null);
					em.merge(g);
					//em.remove(g);
				}
			}
			for(Studente s : (ArrayList<Studente>) em.createNamedQuery("Studente.getAll").getResultList())
				if(s.getGruppi().size() == 0)
					em.remove(s);
		}
		else
			throw new TuplaNonTrovataException();
	}
	
	/*@SuppressWarnings("unchecked")
	public void eliminaGruppo(String nome) throws TuplaNonTrovataException
	{
		List<Gruppo> grup = em.createNamedQuery("Amministratore.getGruppoByNome").setParameter("nome", nome).getResultList();
		if(grup.size() > 0){
			Gruppo g = grup.get(0);
			//if(g.getStudenti().size() > 0)
			//{
				/*for(ProgettoGruppo pg : g.getProgettigruppo())
				{
					for(ProgettoRelease prel : pg.getListaRelease())
					{
						for(FileRelease f : prel.getFiles())
						{
							//prel.removeFile(f);
							em.remove(f);
						}
						prel.setFiles(new HashSet<FileRelease>());
						//em.merge(prel);
						//pg.removeRelease(prel);
						em.remove(prel);
					}
					pg.setListaRelease(new HashSet<ProgettoRelease>());
					//pg.setGruppo(null);
					//em.merge(pg);
					//g.removeProgettoGruppo(pg);
					em.remove(pg);
				}*/
				/*List<Studente> st = new ArrayList<Studente>();
				for(Studente s : g.getStudenti())
				{
					if(s.getGruppi().size() == 1)
					{
						st.add(s);
					}
				}
				for(Studente s: st)
				{
					g.removeStudente(s);
					//s.setGruppi(null);
					//em.merge(s);
					//em.merge(g);
					em.remove(s);
				}
			
			//g.setStudenti(new HashSet<Studente>());
			//em.merge(g);
			//g.setProgettigruppo(new HashSet<ProgettoGruppo>());
			//em.merge(g);
			/*Set<ProgettoGruppo> pgs = g.getProgettigruppo();
			g.setProgettigruppo(new HashSet<ProgettoGruppo>());
			for(ProgettoGruppo pg : pgs)
				em.remove(pg);*/
			/*em.remove(g);
		}
		else
			throw new TuplaNonTrovataException();
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<Studente> getListaStudenti() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("Studente.getAll").getResultList();
	}

	/*@SuppressWarnings("unchecked")
	public void eliminaStudente(String username) throws TuplaNonTrovataException
	{
		List<Studente> stud = em.createNamedQuery("Studente.getStudenteByUsername").setParameter("username", username).getResultList();
		if(stud.size() > 0)
		{
			Studente s = stud.get(0);
			for(Gruppo g: s.getGruppi())
			{
				g.removeStudente(s);
				//s.setGruppi(null);
				if(g.getStudenti().size() > 0)
					em.merge(g);
				else
					em.remove(g);
			}
			//em.merge(s);
			em.remove(s);
			//em.flush();
		}
		else
			throw new TuplaNonTrovataException();
	}*/
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Gruppo> getListaGruppi() {
		List<Gruppo> gruppi = em.createNamedQuery("Amministratore.getGruppi").getResultList();
		return gruppi;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Gruppo getGruppoByNome(String nome)
			throws TuplaNonTrovataException {
		List<Gruppo> grup = em.createNamedQuery("Amministratore.getGruppoByNome").setParameter("nome", nome).getResultList();
		if(grup.size() > 0)
			return grup.get(0);
		else throw new TuplaNonTrovataException();
	}
}
